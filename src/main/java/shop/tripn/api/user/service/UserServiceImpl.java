package shop.tripn.api.user.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import shop.tripn.api.security.domain.SecurityProvider;
import shop.tripn.api.security.exception.SecurityRuntimeException;
import shop.tripn.api.user.domain.Role;
import shop.tripn.api.user.domain.User;
import shop.tripn.api.user.domain.UserDTO;
import shop.tripn.api.user.repository.UserRepository;
import javax.transaction.Transactional;
import java.util.*;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final SecurityProvider provider;
    private final PasswordEncoder passwordEncoder;

    //    private final Logger logger = LoggerFactory.getLogger(this.getClass());



    @Transactional
    @Override
    public Map<String, String> join(UserDTO userDTO) {
        UserDTO entityDto = new UserDTO(); //
        if (!userRepository.existsByUserName(userDTO.getUserName())) {
            String passwd = passwordEncoder.encode(userDTO.getPassword());
            String pwd = userDTO.getPassword();
            userDTO.setPassword(passwd);
            List<Role> list = new ArrayList<>();
            list.add(Role.USER_ROLES);

            Map<String, String> resultMap = new HashMap<>();
            resultMap.put("JwtToken", provider.createToken(userDTO.getUserName(),list));

            User u = new User();
            u.setUserId(userDTO.getUserId());
            u.setAddress(userDTO.getAddress());
            u.setBirth(userDTO.getBirth());
            u.setCardCompany(userDTO.getCardCompany());
            u.setCardNumber(userDTO.getCardNumber());
            u.setEmail(userDTO.getEmail());
            u.setFirstName(userDTO.getFirstName());
            u.setGender(userDTO.getGender());
            u.setLastName(userDTO.getLastName());
            u.setMbti(userDTO.getMbti());
            u.setMbtiList(userDTO.getMbtiList());
            u.setName(userDTO.getName());
            u.setPassport(userDTO.getPassport());
            u.setPassword(userDTO.getPassword());
            u.setPhoneNumber(userDTO.getPhoneNumber());
            u.setUserName(userDTO.getUserName());
            u.setToken(userDTO.getToken());

            userRepository.saveAndFlush(u); // save ????????? saveAndFlush ????????????
//            String Token = provider.createToken(entityDto.getUserName(), //token ??????
//                        userRepository.findByUserName(entityDto.getUserName()).get().getRoles());
//            entityDto.setToken(Token);//token??? ?????????
//            System.out.println(Token);
            return resultMap;
        } else {
            System.out.println("username ???");

            throw new SecurityRuntimeException("User Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public UserDTO login(UserDTO userDTO) {
        try {
            User entity = dtoEntity(userDTO);
            userRepository.login(entity.getUserName(),entity.getPassword());
            UserDTO entityDto =  new UserDTO();
            Optional<User> userLogin = userRepository.login(userDTO.getUserName(), userDTO.getPassword()); //login ?????? ??????
            if(userLogin != null){
                entityDto = entityDto(userLogin.get()); //userlogin.get(): optional??? ?????????
                String Token = provider.createToken(entityDto.getUserName(), //token ??????
                        userRepository.findByUserName(entityDto.getUserName()).get().getRoles());
                entityDto.setToken(Token);//token??? ?????????
                entityDto.setMessage("LOGIN SUCCESS"+Token);
                UserDTO d = new UserDTO();
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
    /**
    @Override
    public UserDTO login(UserDTO userDTO) {
        logger.info("??????????????? ????????? user???"+userDTO.toString());
        try {
            String pwd = passwordEncoder.encode(userDTO.getPassword());
            System.out.println("????????? ?????????"+userDTO.getUserName());
            System.out.println("????????? ?????? "+pwd);
            long entity = userRepository.count(); //login ?????? ??????
            UserDTO entityDto = new UserDTO(); //
            if(entity == 0.0){
                System.out.println(" ### ???????????? ????????? ### ");
            }
            if(entity != 0.0){
                System.out.println(" ### ???????????? ?????? ????????? ### ");
                //userDTO.setUserId(entity.getUserId());
                 userDTO.setAddress(entity.getAddress());
                 userDTO.setBirth(entity.getBirth());
                 userDTO.setCardCompany(entity.getCardCompany());
                 userDTO.setCardNumber(entity.getCardNumber());
                 userDTO.setEmail(entity.getEmail());
                 userDTO.setFirstName(entity.getFirstName());
                 userDTO.setGender(entity.getGender());
                 userDTO.setLastName(entity.getLastName());
                 userDTO.setMbti(entity.getMbti());
                 userDTO.setMbtiList(entity.getMbtiList());
                 userDTO.setName(entity.getName());
                 userDTO.setPassport(entity.getPassport());
                 userDTO.setPassword(entity.getPassword());
                 userDTO.setPhoneNumber(entity.getPhoneNumber());
                //userDTO.setUserName(entity.getUserName());

                String Token = provider.createToken(entityDto.getUserName(), //token ??????
                        userRepository.findByUserName(entityDto.getUserName()).get().getRoles());
                entityDto.setToken(Token);//token??? ?????????
                entityDto.setMessage("LOGIN SUCCESS");
            }else{
                entityDto.setMessage("LOGIN FAIL");
            }
            return entityDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SecurityRuntimeException("Invalid User-Username / Password supplied",
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }*/
    @Override
    public Optional<User> findById(long userid) {
        return null;
    }
    @Override
    public List<User> searchByName(String username) {
        return userRepository.searchByName(username);
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
            ulist = userRepository.searchByName(Name);
        }else if (Name.equals("NONE") && !birth.equals("NONE") && phoneNumber.equals("NONE")){
            ulist = userRepository.searchByBirth(birth);
        }else if (Name.equals("NONE") && birth.equals("NONE") && !phoneNumber.equals("NONE")){
            ulist = userRepository.searchByPhoneNumber(phoneNumber);
        }else if (Name.equals("NONE") && birth.equals("NONE") && phoneNumber.equals("NONE")){
            ulist = userRepository.findAll();
        }else{
            System.out.println("???????????? ??????");
        }
        return Optional.of(ulist);
    }
}
