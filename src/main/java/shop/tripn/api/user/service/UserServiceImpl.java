package shop.tripn.api.user.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import shop.tripn.api.security.domain.SecurityProvider;
import shop.tripn.api.security.exception.SecurityRuntimeException;
import shop.tripn.api.user.controller.UserController;
import shop.tripn.api.user.domain.User;
import shop.tripn.api.user.domain.UserDTO;
import shop.tripn.api.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final SecurityProvider provider;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public Optional<User> findById(long userid) {
        return userRepository.findById(userid);
    }

    @Override
    public UserDTO login(UserDTO userDTO) {
        logger.info("로그인에서 들어온 user값"+userDTO.toString());
        try {
            Optional<User> userLogin = userRepository.login(userDTO.getUsername(), userDTO.getPassword()); //login 정보 담기
            UserDTO entityDto = new UserDTO(); //
            if(userLogin != null){
              entityDto = entityDto(userLogin.get()); //userlogin.get(): optional을 풀어줌
              String Token = provider.createToken(entityDto.getUsername(), //token 생성
                userRepository.findByUsername(entityDto.getUsername()).get().getRoles());
                entityDto.setToken(Token);//token을 담아줌
                entityDto.setMessage("LOGIN SUCCESS");
                return entityDto;
            }else{
                entityDto.setMessage("LOGIN FAIL");
                return entityDto;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SecurityRuntimeException("Invalid User-Username / Password supplied",
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public Optional<List<User>> searchOption2(String op1, String op2) {
        List<String> arr = new ArrayList<>();
        arr.add(op1);
        arr.add(op2);
        List<User> ulist = new ArrayList<User>();
        for(int i=0; i < arr.size(); i++){
            List<User> names = userRepository.searchByUsername(arr.get(i));
            List<User> birthList = userRepository.searchByBirth(arr.get(i));
            List<User> phoneNumber = userRepository.searchByPhoneNumber(arr.get(i));

            if(names.size() == 0 && birthList.size() == 0 && phoneNumber.size() == 0){
                return Optional.empty();
            }else if(names.size() == 0 && birthList.size() == 0){
                return Optional.empty();
            }else if(names.size() == 0 && phoneNumber.size() == 0){
                return Optional.empty();
            }else if(phoneNumber.size() == 0 && birthList.size() == 0){
                return Optional.empty();
            }else{
                ulist = userRepository.searchByPhoneNumber(arr.get(i));
                // ulist = userRepository.searchByUsername("");
                // ulist = userRepository.searchByBirth("");
                break;


            }
        }


        return Optional.of(ulist);
    }

    @Override
    public List<User> searchByName(String username) {
        return userRepository.searchByName(username);
    }


}
