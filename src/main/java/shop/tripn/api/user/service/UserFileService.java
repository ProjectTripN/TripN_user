package shop.tripn.api.user.service;

import org.springframework.web.multipart.MultipartFile;
import shop.tripn.api.common.ModelMapperUtils;
import shop.tripn.api.user.domain.User;
import shop.tripn.api.user.domain.UserDTO;
import shop.tripn.api.user.domain.UserFile;
import shop.tripn.api.user.domain.UserFileDTO;
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

    default UserDTO entityDto2(User user){
        UserDTO entityDto = UserDTO.builder().userId(user.getUserId()).userName(user.getUserName()).firstName(user.getFirstName())
                .lastName(user.getLastName()).gender(user.getGender()).phoneNumber(user.getPhoneNumber())
                .password(user.getPassword()).name(user.getName()).email(user.getEmail())
                .birth(user.getBirth()).address(user.getAddress()).cardNumber(user.getCardNumber())
                .cardCompany(user.getCardCompany()).mbti(user.getMbti()).mbtiList(user.getMbtiList())
                .regDate(user.getRegDate()).build();
        return entityDto;
    }
}
