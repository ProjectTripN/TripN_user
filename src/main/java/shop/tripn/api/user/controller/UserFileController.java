package shop.tripn.api.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import shop.tripn.api.user.domain.UserFileDTO;
import shop.tripn.api.user.service.UserFileServiceImpl;
import java.util.ArrayList;
import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = "/user_files")
public class UserFileController {
    private final UserFileServiceImpl service;


    @Value("${shop.upload.path}")
    private String uploadPath;


    @PostMapping("/uploadAjax")
    public ResponseEntity<List<UserFileDTO>> uploadFile(List<MultipartFile> files) {
        for (MultipartFile file : files) {

            // 이미지 파일만 업로드 가능
            if (!file.getContentType().startsWith("image")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }
        return ResponseEntity.ok(service.saveFile(files));
    }

    @PutMapping("/update_file/{reviewFileId}")
    public ResponseEntity<ArrayList<UserFileDTO>> updateFile(List<MultipartFile> files) {

        return ResponseEntity.ok(service.saveFile(files));
    }

    @DeleteMapping("/delete_file/{artistFileId}")
    public ResponseEntity<String> deleteFile(@PathVariable("aritstFileId") Long userFileId) {
        service.userFileDelete(userFileId);

        return ResponseEntity.ok("Delete Success");
    }
}
