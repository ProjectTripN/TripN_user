package shop.tripn.api.user.controller;

import io.swagger.annotations.*;
import jdk.dynalink.linker.LinkerServices;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.tripn.api.common.CommonController;
import shop.tripn.api.user.domain.User;
import shop.tripn.api.user.domain.UserDTO;
import shop.tripn.api.user.repository.UserRepository;
import shop.tripn.api.user.service.UserService;

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

//    @GetMapping("/exist/{username}")
//    public ResponseEntity<Boolean> exist(@PathVariable String username) {
//        return ResponseEntity.ok(userRepository.existsByUsername(username));
//    }

    @PostMapping("/login")
    @ApiOperation(value="${UserController.signin}")
    @ApiResponses(value={
            @ApiResponse(code=400,message = "Something Wrong"),
            @ApiResponse(code=422,message = "유효하지 않는 아이디 / 비밀번호")
    })
    public ResponseEntity<User> login(@ApiParam("Signin User") @RequestBody User user){
        logger.info(String.format("로그인 정보: %s", user.toString()));
        return ResponseEntity.ok(userService.login(user.getUsername(), user.getPassword()).orElse(new User()));
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
//        logger.info(String.format("userId 조회 :" + userRepository.getById(id)));
        return ResponseEntity.ok(userRepository.getById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<User>> searchByName(@PathVariable String name) {
        logger.info(String.format("회원이름으로 찾기 : %s ", userRepository.searchByName(name)));
        return ResponseEntity.ok().body(userRepository.searchByName(name));
    }

    @GetMapping("/phoneNumber/{phone_number}")
    public ResponseEntity<List<User>> searchByPhoneNumber(@PathVariable String phone_number){
        logger.info(String.format("휴대폰번호로 찾기 : %s", userRepository.searchByPhoneNumber(phone_number)));
        return ResponseEntity.ok().body(userRepository.searchByPhoneNumber(phone_number));
    }

    @GetMapping("/birth/{birth}")
    public ResponseEntity<List<User>> searchByBirth(@PathVariable String birth){
        logger.info(String.format("생년월일로 찾기 : %s", userRepository.searchByBirth(birth)));
        return ResponseEntity.ok().body(userRepository.searchByBirth(birth));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<User>> findByPassword(@PathVariable String email){
        logger.info(String.format("이메일로 찾기 : %s", userRepository.findByPassword(email)));
        return ResponseEntity.ok().body(userRepository.findByPassword(email));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<List<User>> searchByUsername(@PathVariable String username){
        logger.info(String.format("Username 으로 찾기 : %s", userRepository.searchByUsername(username)));
        return ResponseEntity.ok().body(userRepository.searchByUsername(username));
    }

//    @GetMapping("/{password}")
//    public ResponseEntity<List<User>> findByPassword(@PathVariable String password){
//        logger.info(String.format("비밀번호 찾기 : %s", userRepository.findByPassword(password)));
//        return ResponseEntity.ok().body(userRepository.findByBirth(password));
//    }

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

//    @PutMapping("/mbti")
//    public ResponseEntity<User> updateMbti(@RequestBody User user){
//        String mbti = user.getMbti();
//        logger.info(String.format("mbti update", user.getMbti()));
//        return ResponseEntity.ok(userRepository.updateMbti(userId, mbti));
//    }
//    @PutMapping("/password")
//    public ResponseEntity<User> updatePassword(@PathVariable String password){
////        logger.info(String.format("비밀번호 변경: ", userRepository.updatePassword(password));
////        ;return ResponseEntity.ok().body(userRepository.findByUsername(username));
//        return ResponseEntity.ok().body(userRepository.updatePassword(password));
//    }

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
//        logger.info(String.format("회원이름으로 지우기 : %s ", userRepository.deleteByUsername(username)));
        userRepository.deleteByUsername(username);
        return ResponseEntity.ok(username+"님의 탈퇴가 완료되었습니다");
    }


//    @DeleteMapping("/{userId}")
//    public ResponseEntity<String> deleteById(@PathVariable String userId){
//        userRepository.deleteByUsername(userId);
//        return ResponseEntity.ok(userId+"님의 탈퇴가 완료되었습니다");
//    }
}
