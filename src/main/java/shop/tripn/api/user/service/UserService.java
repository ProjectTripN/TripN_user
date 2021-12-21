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
    Optional<User> login(String username, String password);
    List<User> searchByName(String username);
    UserDTO login(UserDTO userDTO);
    default Map<String, Object> dtoToEntity(UserDTO userDTO){
        Map<String, Object> entityMap = new HashMap<>();
        User user = User.builder().userId(userDTO.getUserId()).username(userDTO.getUsername()).password(userDTO.getPassword())
                .name(userDTO.getName()).first_name(userDTO.getFirst_name()).last_name(userDTO.getLast_name()).address(userDTO.getAddress())
                .birth(userDTO.getBirth()).email(userDTO.getEmail()).gender(userDTO.getGender()).mbti(userDTO.getMbti()).mbti_list(userDTO.getMbti_list())
                .passport(userDTO.getPassport()).card_company(userDTO.getCard_company()).card_number(userDTO.getCard_number()).regDate(userDTO.getRegDate())
                .phone_number(userDTO.getPhone_number()).build();
        entityMap.put("user", user);
        return entityMap;
    }
    default UserDTO entityToDto(User user){
        UserDTO userDTO = UserDTO.builder().userId(user.getUserId()).username(user.getUsername()).first_name(user.getFirst_name())
                .last_name(user.getLast_name()).gender(user.getGender()).phone_number(user.getPhone_number())
                .password(user.getPassword()).name(user.getName()).email(user.getEmail())
                .birth(user.getBirth()).address(user.getAddress()).card_number(user.getCard_number())
                .card_company(user.getCard_company()).mbti(user.getMbti()).mbti_list(user.getMbti_list())
                .regDate(user.getRegDate()).build();
        return userDTO;
    }
    default User dtoEntity(UserDTO userDTO){
        User entity = User.builder().userId(userDTO.getUserId()).username(userDTO.getUsername()).password(userDTO.getPassword())
                .name(userDTO.getName()).first_name(userDTO.getFirst_name()).last_name(userDTO.getLast_name()).address(userDTO.getAddress())
                .birth(userDTO.getBirth()).email(userDTO.getEmail()).gender(userDTO.getGender()).mbti(userDTO.getMbti())
                .mbti_list(userDTO.getMbti_list()).passport(userDTO.getPassport()).card_company(userDTO.getCard_company())
                .card_number(userDTO.getCard_number()).regDate(userDTO.getRegDate()).phone_number(userDTO.getPhone_number()).build();
        return entity;
    }
    default UserDTO entityDto(User user){
        UserDTO entityDto = UserDTO.builder().userId(user.getUserId()).username(user.getUsername()).first_name(user.getFirst_name())
                .last_name(user.getLast_name()).gender(user.getGender()).phone_number(user.getPhone_number())
                .password(user.getPassword()).name(user.getName()).email(user.getEmail())
                .birth(user.getBirth()).address(user.getAddress()).card_number(user.getCard_number())
                .card_company(user.getCard_company()).mbti(user.getMbti()).mbti_list(user.getMbti_list())
                .regDate(user.getRegDate()).build();
        return entityDto;
    }
}
