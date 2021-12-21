package shop.tripn.api.user.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import shop.tripn.api.user.domain.User;
import shop.tripn.api.user.domain.UserDTO;
import shop.tripn.api.user.domain.UserFile;
import shop.tripn.api.user.domain.UserFileDTO;
import shop.tripn.api.user.repository.UserFileRepository;
import shop.tripn.api.user.repository.UserRepository;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.io.File;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Transactional
@Log4j2
@Service
@RequiredArgsConstructor
public class UserFileServiceImpl implements UserFileService {
    private final UserFileRepository userFileRepository;
    private final UserRepository userRepository;

    @Value("${shop.upload.path}")
    private String uploadPath;

    @Transactional
    @Override
    public ArrayList<UserFileDTO> saveFile(List<MultipartFile> uploadFiles) {
        ArrayList<UserFileDTO> UserFileResultList = new ArrayList<>();

        for (MultipartFile uploadFile : uploadFiles) {
            String ofName = uploadFile.getOriginalFilename();
            int index = ofName.lastIndexOf(".");
            String ofHeader = ofName.substring(0, index);
            String ext = ofName.substring(index);
            String uuid = UUID.randomUUID().toString();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(uploadPath).append(File.separator).append(uuid).append("_").append(ofHeader)
                    .append(ext);
            String saveName = stringBuilder.toString();
            Path savePath = Paths.get(saveName);

            try {
                uploadFile.transferTo(savePath);
                String thumbnailSaveName = uploadPath + File.separator + "s_" + uuid + ofName;
                Thumbnails.of(new File(saveName)).size(100, 100).outputFormat("jpg").toFile(thumbnailSaveName);

                Thumbnails.of(new File(saveName)).scale(1)
                        .watermark(Positions.BOTTOM_CENTER,
                                ImageIO.read(new File(uploadPath + File.separator + "warterMark.png")), 0.5f)
                        .toFile(new File(uploadPath + File.separator + "w+" + uuid + "_" + ofName));

                UserFileDTO artistFileDto = UserFileDTO.builder().uuid(uuid).imgName(ofName).build();

                UserFileResultList.add(artistFileDto);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return UserFileResultList;

    }

    @Override
    public void userFileDelete(Long userFileId) {

    }

    @Override
    public UserFileDTO EntityToDtoArtistFile(UserFile userFile) {
        return UserFileService.super.EntityToDtoArtistFile(userFile);
    }

    @Override
    public UserFile dtoToEntityUserFile(UserFileDTO userFileDTO) {
        return UserFileService.super.dtoToEntityUserFile(userFileDTO);
    }

    @Override
    public UserFile dtoToEntity(UserFileDTO userFileDTO) {
        return UserFileService.super.dtoToEntity(userFileDTO);
    }

    @Override
    public UserFileDTO entityToDto(UserFile userFile) {
        return UserFileService.super.entityToDto(userFile);
    }

    @Override
    public UserFile dtoEntityFileToEntityFile(UserFileDTO userFileDTO) {
        return UserFileService.super.dtoEntityFileToEntityFile(userFileDTO);
    }

    @Override
    public User dtoEntity(UserDTO userDTO) {
        return UserFileService.super.dtoEntity(userDTO);
    }

    @Override
    public UserDTO entityDto(User user) {
        return UserFileService.super.entityDto(user);
    }

    @Override
    public UserDTO entityDto2(User user) {
        return UserFileService.super.entityDto2(user);
    }
}
