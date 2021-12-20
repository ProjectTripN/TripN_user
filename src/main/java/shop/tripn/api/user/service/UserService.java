package shop.tripn.api.user.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import shop.tripn.api.user.domain.User;
import shop.tripn.api.user.domain.UserDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public interface UserService {
    Optional<User> findById(long userId);
    Optional<User> login(String username, String password);
    List<User> searchByName(String username);
    /*default UserDTO entityDto(User user) {
        UserDTO entityDto = UserDTO.builder().userId(user.getUserId()).username(user.getUsername()).first_name(user.getFirst_name())
                .last_name(user.getLast_name()).gender(user.getGender()).phone_number(user.getPhone_number())
                .password(user.getPassword()).name(user.getName()).email(user.getEmail())
                .birth(user.getBirth()).address(user.getAddress()).card_number(user.getCard_number())
                .card_company(user.getCard_company()).mbti(user.getMbti()).mbti_list(user.getMbti_list())
                .regDate(user.getRegDate()).build();

        return entityDto;

    }*/
//    default User dtoEntity(UserDTO userDTO) {
//        Artist entity = Artist.builder().artistId(artistDto.getArtistId()).username(artistDto.getUsername())
//                .password(artistDto.getPassword()).name(artistDto.getName()).email(artistDto.getEmail())
//                .phoneNumber(artistDto.getPhoneNumber()).address(artistDto.getAddress()).school(artistDto.getSchool())
//                .department(artistDto.getDepartment()).build();
//
//        return entity;
//
//    }
}
