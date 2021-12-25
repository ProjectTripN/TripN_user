package shop.tripn.api.user.controller;

import io.swagger.annotations.*;
import jdk.dynalink.linker.LinkerServices;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.tripn.api.board.domain.Board;
import shop.tripn.api.common.CommonController;
import shop.tripn.api.security.domain.SecurityProvider;
import shop.tripn.api.security.domain.SecurityToken;
import shop.tripn.api.user.domain.User;
import shop.tripn.api.user.domain.UserDTO;
import shop.tripn.api.user.repository.UserRepository;
import shop.tripn.api.user.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(tags = "users")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController implements CommonController<User, Long> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserService userService;
    private final UserRepository userRepository;
    private final SecurityProvider securityProvider;



    @PostMapping("/join")
    @ApiOperation(value="${UserController.signup}")
    @ApiResponses(value={
            @ApiResponse(code=400,message = "Something Wrong"),
            @ApiResponse(code=403,message = "승인거절"),
            @ApiResponse(code=422,message = "중복된 ID"),
    })
    public ResponseEntity<String> save(@ApiParam("Signup User") @RequestBody User user) {
        logger.info(String.format("회원가입 정보: %s", user.toString()));
        User u = user.toEntity();
        userRepository.save(u);
        return ResponseEntity.ok(user.getName()+"님의 회원가입을 축하드립니다.");
    }

    @GetMapping("/existsById/{username}")
    public ResponseEntity<Boolean> existById(@PathVariable String username) {
        boolean b = userRepository.existsByUsername(username);
        logger.info(String.format("아이디 존재 여부 :" + b));
        return ResponseEntity.ok(b);
    }

    @Override
    public ResponseEntity<Boolean> existById(Long id) { return null; }

    @PostMapping("/login")
    @ApiOperation(value="${UserController.signin}")
    @ApiResponses(value={
            @ApiResponse(code=400,message = "Something Wrong"),
            @ApiResponse(code=422,message = "유효하지 않는 아이디 / 비밀번호")})
    public ResponseEntity<UserDTO> login(@ApiParam("Signin User") @RequestBody UserDTO userDTO)
            throws IOException {
        logger.info("로그인에서 들어온 user값"+userDTO.toString());
        UserDTO entityDto = userService.login(userDTO);
        logger.info("로그인결과 : "+entityDto.getMessage());
        logger.info("token 값: "+ entityDto.getToken());
        return ResponseEntity.ok(entityDto);
    }

    @GetMapping("/list")
    @Override
    public ResponseEntity<List<User>> findAll() {
        logger.info(String.format("전체 조회 :" + userRepository.findAll()));
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping()
    @Override
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userRepository.getById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<User>> searchByName(@PathVariable String name) {
        logger.info(String.format("회원이름으로 찾기 : %s ", userRepository.searchByName(name)));
        return ResponseEntity.ok().body(userRepository.searchByName(name));
    }

    @GetMapping("/userList/phoneNumber/{phone_number}")
    public ResponseEntity<List<User>> searchByPhoneNumber(@PathVariable String phone_number){
        logger.info(String.format("휴대폰번호로 찾기 : %s", userRepository.searchByPhoneNumber(phone_number)));
        return ResponseEntity.ok().body(userRepository.searchByPhoneNumber(phone_number));
    }

    @GetMapping("/userList/birth/{birth}")
    public ResponseEntity<List<User>> searchByBirth(@PathVariable String birth){
        logger.info(String.format("생년월일로 찾기 : %s", userRepository.searchByBirth(birth)));
        return ResponseEntity.ok().body(userRepository.searchByBirth(birth));
    }

    @GetMapping("/userList/email/{email}")
    public ResponseEntity<User> searchByEmail(@PathVariable String email){
        logger.info(String.format("이메일로 찾기 : %s", userRepository.searchByEmail(email)));
        return ResponseEntity.ok().body(userRepository.searchByEmail(email));
    }

    @GetMapping("/userList/username/{username}}")
    public ResponseEntity<List<User>> searchByUsername(@PathVariable String username){
        logger.info(String.format("Username 으로 찾기 : %s", userRepository.searchByUsername(username)));
        return ResponseEntity.ok().body(userRepository.searchByUsername(username));
    }

    @GetMapping("/userList/{username}/{birth}/{phone_number}")
    public ResponseEntity<List<User>> searchByUserList(
            @PathVariable String username, @PathVariable String birth, @PathVariable String phone_number){
        return ResponseEntity.ok().body(userRepository.searchByUserList(username, birth, phone_number));
    }

//    @GetMapping("/userList/option1/{option1}/option2/{option2}")
//    public ResponseEntity<List<User>> searchByUserListBirth(
//            @PathVariable String option1, @PathVariable String option2){
//        return ResponseEntity.ok().body(userRepository.searchByUserListBirth(option1, option2));
//    }
    /**
    @GetMapping("/userList/{username}/{birth}")
    public ResponseEntity<List<User>> searchByUserListName(
            @PathVariable String username, @PathVariable String birth){
        return ResponseEntity.ok().body(userRepository.searchByUserListName(username, birth));
    }

    @GetMapping("/userList/{username}/{phone_number}")
    public ResponseEntity<List<User>> searchByUserListBirth(
            @PathVariable String birth, @PathVariable String phone_number){
        return ResponseEntity.ok().body(userRepository.searchByUserListBirth(birth, phone_number));
    }

    @GetMapping("/userList/{birth}/{username}")
    public ResponseEntity<List<User>> searchByUserList(
            @PathVariable String username, @PathVariable String birth, @PathVariable String phone_number){
        return ResponseEntity.ok().body(userRepository.searchByUserList(username, birth, phone_number));
    }

    @GetMapping("/users/userList/{phone_number}/{username}")
    public ResponseEntity<List<User>> searchByUserList(
            @PathVariable String username, @PathVariable String birth, @PathVariable String phone_number){
        return ResponseEntity.ok().body(userRepository.searchByUserList(username, birth, phone_number));
    }

    @GetMapping("//users/userList/{phone_number}/{birth}")
    public ResponseEntity<List<User>> searchByUserList(
            @PathVariable String username, @PathVariable String birth, @PathVariable String phone_number){
        return ResponseEntity.ok().body(userRepository.searchByUserList(username, birth, phone_number));
    }*/

    @GetMapping("/list/{id}")
    @Override
    public ResponseEntity<Optional<User>> findById(@PathVariable Long id) {
        userRepository.findById(id);
        return ResponseEntity.ok(userRepository.findById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user) {
        logger.info(String.format("회원수정 정보: %s", user.toString()));
        userRepository.save(user);
        return ResponseEntity.ok(userRepository.getById(user.getUserId()));
    }

    @PutMapping("/mbti")
    public ResponseEntity<User> updateMbti(@RequestBody User user){
        userRepository.updateMbti(user.getUserId(), user.getMbti());
        return ResponseEntity.ok(userRepository.getById(user.getUserId()));
    }

    @PutMapping("/mbtiList")
    public ResponseEntity<User> updateMbtiList(@RequestBody User user){
        userRepository.updateMbtiList(user.getUserId(), user.getMbti_list());
        return ResponseEntity.ok(userRepository.getById(user.getUserId()));
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<User> updatePassword(@RequestBody User user){
        userRepository.updatePassword(user.getUserId(), user.getPassword());
        return ResponseEntity.ok(userRepository.getById(user.getUserId()));
    }

    @Override
    public ResponseEntity<Long> count() {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        return null;
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteByUsername(@PathVariable String username){
        userRepository.deleteByUsername(username);
        return ResponseEntity.ok(username+"님의 탈퇴가 완료되었습니다");
    }

}
