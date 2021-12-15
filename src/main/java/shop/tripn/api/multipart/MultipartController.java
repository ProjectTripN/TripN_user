//package shop.tripn.api.multipart;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Date;
//import java.util.Random;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.spring.multipart.dto.FoodDto;
//
//@RestController
//public class MultipartController {
//    @PostMapping("/uploadFiles")
//    public ResponseEntity<Object> uploadFiles(MultipartFile[] multipartFiles, String stringFoodDto) { // 파라미터의 이름은 client의 formData key값과 동일해야함
//        String UPLOAD_PATH = "F:\\myUpload"; // 업로드 할 위치
//
//        try {
//            // 객체는 client에서 직렬화를 하여 전달
//            FoodDto foodDto = new ObjectMapper().readValue(stringFoodDto, FoodDto.class); // String to Object
//            System.out.println("foodDto= " + foodDto);
//
//            for(int i=0; i<multipartFiles.length; i++) {
//                MultipartFile file = multipartFiles[i];
//
//                String fileId = (new Date().getTime()) + "" + (new Random().ints(1000, 9999).findAny().getAsInt()); // 현재 날짜와 랜덤 정수값으로 새로운 파일명 만들기
//                String originName = file.getOriginalFilename(); // ex) 파일.jpg
//                String fileExtension = originName.substring(originName.lastIndexOf(".") + 1); // ex) jpg
//                originName = originName.substring(0, originName.lastIndexOf(".")); // ex) 파일
//                long fileSize = file.getSize(); // 파일 사이즈
//
//                File fileSave = new File(UPLOAD_PATH, fileId + "." + fileExtension); // ex) fileId.jpg
//                if(!fileSave.exists()) { // 폴더가 없을 경우 폴더 만들기
//                    fileSave.mkdirs();
//                }
//
//                file.transferTo(fileSave); // fileSave의 형태로 파일 저장
//
//                System.out.println("fileId= " + fileId);
//                System.out.println("originName= " + originName);
//                System.out.println("fileExtension= " + fileExtension);
//                System.out.println("fileSize= " + fileSize);
//            }
//        } catch(IOException e) {
//            return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
//        }
//
//        return new ResponseEntity<Object>("Success", HttpStatus.OK);
//    }
//}
//}
