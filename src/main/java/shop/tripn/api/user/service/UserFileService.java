package shop.tripn.api.user.service;

import org.springframework.web.multipart.MultipartFile;
import shop.tripn.api.common.ModelMapperUtils;
import shop.tripn.api.user.domain.User;
import shop.tripn.api.user.domain.UserDTO;
import shop.tripn.api.user.domain.UserFile;
import shop.tripn.api.user.domain.UserFileDTO;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public interface UserFileService {

    ArrayList<UserFileDTO> saveFile(List<MultipartFile> uploadFiles);

    void userFileDelete(Long userFileId);

//    PageResultDto<UserDTO, User> getPageFileList(PageRequestDto requestDto);

    default UserFileDTO EntityToDtoArtistFile(UserFile userFile) {

        UserFileDTO userFileDTO = UserFileDTO.builder().userFileId(userFile.getUserFileId())
                .uuid(userFile.getUuid()).imgName(userFile.getImgName()).path(userFile.getPath()).build();

        return userFileDTO;
    }

    default UserFile dtoToEntityUserFile(UserFileDTO userFileDTO) {

        UserFile userFile = UserFile.builder().userFileId(userFileDTO.getArtistFileId())
                .uuid(userFileDTO.getUuid()).imgName(userFileDTO.getImgName()).path(userFileDTO.getPath())
                .build();

        return userFile;
    }

    default UserFile dtoToEntity(UserFileDTO userFileDTO) {
        UserFile userFile = ModelMapperUtils.getModelMapper().map(userFileDTO, UserFile.class);

        return userFile;
    }

    default UserFileDTO entityToDto(UserFile userFile) {
        UserFileDTO userFileDTO = ModelMapperUtils.getModelMapper().map(userFile, UserFileDTO.class);

        return userFileDTO;
    }

    default UserFile dtoEntityFileToEntityFile(UserFileDTO userFileDTO) {
        UserFile entityFile = UserFile.builder().userFileId(userFileDTO.getArtistFileId())
                .imgName(userFileDTO.getImgName()).uuid(userFileDTO.getUuid()).build();

        return entityFile;

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

    default UserDTO entityDto2(User user){
        UserDTO entityDto = UserDTO.builder().userId(user.getUserId()).username(user.getUsername()).first_name(user.getFirst_name())
                .last_name(user.getLast_name()).gender(user.getGender()).phone_number(user.getPhone_number())
                .password(user.getPassword()).name(user.getName()).email(user.getEmail())
                .birth(user.getBirth()).address(user.getAddress()).card_number(user.getCard_number())
                .card_company(user.getCard_company()).mbti(user.getMbti()).mbti_list(user.getMbti_list())
                .regDate(user.getRegDate()).build();
        return entityDto;
    }
}
