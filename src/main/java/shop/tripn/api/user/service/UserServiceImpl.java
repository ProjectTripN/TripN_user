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
            Optional<User> userLogin = userRepository.login(userDTO.getUserName(), userDTO.getPassword()); //login 정보 담기
            UserDTO entityDto = new UserDTO(); //
            if(userLogin != null){
              entityDto = entityDto(userLogin.get()); //userlogin.get(): optional을 풀어줌
              String Token = provider.createToken(entityDto.getUserName(), //token 생성
                userRepository.findByUserName(entityDto.getUserName()).get().getRoles());
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
    public Optional<List<User>> searchOption(UserDTO userDTO) {
        String Name = userDTO.getName();
        String birth = userDTO.getBirth();
        String phoneNumber = userDTO.getPhoneNumber();
        List<User> ulist = new ArrayList<User>();
        if (!Name.equals("NONE") && !birth.equals("NONE") && !phoneNumber.equals("NONE")){
            ulist = userRepository.searchByUserList(Name, birth, phoneNumber);
        }else if(Name.equals("NONE") && !birth.equals("NONE") && !phoneNumber.equals("NONE")){
            ulist = userRepository.searchByBirthPhone(birth, phoneNumber);
        }else if(!Name.equals("NONE") && birth.equals("NONE") && !phoneNumber.equals("NONE")){
            ulist = userRepository.searchByUserPhone(Name, phoneNumber);
        }else if(!Name.equals("NONE") && !birth.equals("NONE") && phoneNumber.equals("NONE")){
            ulist = userRepository.searchByUserBirth(Name,birth);
        }else if(!Name.equals("NONE") && birth.equals("NONE") && phoneNumber.equals("NONE")){
            ulist = userRepository.searchByUserName(Name);
        }else if (Name.equals("NONE") && !birth.equals("NONE") && phoneNumber.equals("NONE")){
            ulist = userRepository.searchByBirth(birth);
        }else if (Name.equals("NONE") && birth.equals("NONE") && !phoneNumber.equals("NONE")){
            ulist = userRepository.searchByPhoneNumber(phoneNumber);
        }else if (Name.equals("NONE") && birth.equals("NONE") && phoneNumber.equals("NONE")){
            ulist = userRepository.findAll();
        }else{
            System.out.println("해당사항 없음");
        }
        return Optional.of(ulist);
    }

    @Override
    public List<User> searchByName(String username) {
        return userRepository.searchByName(username);
    }


}
