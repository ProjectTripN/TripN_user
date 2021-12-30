package shop.tripn.api.user.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import shop.tripn.api.user.domain.User;
import shop.tripn.api.user.domain.UserDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public interface UserService {
    Optional<User> findById(long userId);
    //    Optional<User> login(String username, String password);
    List<User> searchByName(String username);
    //    UserDTO join(UserDTO userDTO);
    Map<String, String> join(UserDTO userDTO);
    UserDTO login(UserDTO userDTO);


//    boolean matches(CharSequence rawPassword, String encodedPAssword);



    default Map<String, Object> dtoToEntity(UserDTO userDTO){
        Map<String, Object> entityMap = new HashMap<>();
        User user = User.builder().userId(userDTO.getUserId()).userName(userDTO.getUserName()).password(userDTO.getPassword())
                .name(userDTO.getName()).firstName(userDTO.getFirstName()).lastName(userDTO.getLastName()).address(userDTO.getAddress())
                .birth(userDTO.getBirth()).email(userDTO.getEmail()).gender(userDTO.getGender()).mbti(userDTO.getMbti()).mbtiList(userDTO.getMbtiList())
                .passport(userDTO.getPassport()).cardCompany(userDTO.getCardCompany()).cardNumber(userDTO.getCardNumber()).regDate(userDTO.getRegDate())
                .phoneNumber(userDTO.getPhoneNumber()).build();
        entityMap.put("user", user);
        return entityMap;
    }
    default UserDTO entityToDto(User user){
        UserDTO userDTO = UserDTO.builder().userId(user.getUserId()).userName(user.getUserName()).firstName(user.getFirstName())
                .lastName(user.getLastName()).gender(user.getGender()).phoneNumber(user.getPhoneNumber())
                .password(user.getPassword()).name(user.getName()).email(user.getEmail())
                .birth(user.getBirth()).address(user.getAddress()).cardNumber(user.getCardNumber())
                .cardCompany(user.getCardCompany()).mbti(user.getMbti()).mbtiList(user.getMbtiList())
                .regDate(user.getRegDate()).build();
        return userDTO;
    }
    default User dtoEntity(UserDTO userDTO){
        User entity = User.builder().userId(userDTO.getUserId()).userName(userDTO.getUserName()).password(userDTO.getPassword())
                .name(userDTO.getName()).firstName(userDTO.getFirstName()).lastName(userDTO.getLastName()).address(userDTO.getAddress())
                .birth(userDTO.getBirth()).email(userDTO.getEmail()).gender(userDTO.getGender()).mbti(userDTO.getMbti())
                .mbtiList(userDTO.getMbtiList()).passport(userDTO.getPassport()).cardCompany(userDTO.getCardCompany())
                .cardNumber(userDTO.getCardNumber()).regDate(userDTO.getRegDate()).phoneNumber(userDTO.getPhoneNumber()).build();
        return entity;
    }
    default UserDTO entityDto(User user){
        UserDTO entityDto = UserDTO.builder().userId(user.getUserId()).userName(user.getUserName()).firstName(user.getFirstName())
                .lastName(user.getLastName()).gender(user.getGender()).phoneNumber(user.getPhoneNumber())
                .password(user.getPassword()).name(user.getName()).email(user.getEmail())
                .birth(user.getBirth()).address(user.getAddress()).cardNumber(user.getCardNumber())
                .cardCompany(user.getCardCompany()).mbti(user.getMbti()).mbtiList(user.getMbtiList())
                .regDate(user.getRegDate()).build();
        return entityDto;
    }
    Optional<List<User>> searchOption(UserDTO userDTO);
}
