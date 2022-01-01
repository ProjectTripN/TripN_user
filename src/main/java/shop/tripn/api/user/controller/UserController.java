package shop.tripn.api.user.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import shop.tripn.api.common.CommonController;
import shop.tripn.api.security.domain.JwtResponse;
import shop.tripn.api.security.domain.SecurityProvider;
import shop.tripn.api.security.domain.UserDetailsImpl;
import shop.tripn.api.user.domain.User;
import shop.tripn.api.user.domain.UserDTO;
import shop.tripn.api.user.repository.UserRepository;
import shop.tripn.api.user.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(tags = "users")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController implements CommonController<User, Long> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final SecurityProvider securityProvider;

    @PostMapping("/join")
    @ApiOperation(value="${UserController.signup}")
    @ApiResponses(value={
            @ApiResponse(code=400,message = "Something Wrong"),
            @ApiResponse(code=403,message = "승인거절"),
            @ApiResponse(code=422,message = "중복된 ID"),
    })
    public ResponseEntity<String> save(@ApiParam("Signup User") @RequestBody UserDTO userDTO) {
        logger.info(String.format("회원가입 정보: %s", userDTO.toString()));
        //        System.out.println("encodePassword: "+userDTO.getPassword());
        Map<String, String> m = userService.join(userDTO);
        System.out.println("토큰 들어왔나? "+ m);
        //        System.out.println("???"+m);
//                userRepository.save(m);
        //        Map<String, String> resultMap = new HashMap<>();
        //        return new ResponseEntity(userService.join(user), HttpStatus.OK);
        return ResponseEntity.ok(userDTO.getName()+"님의 회원가입을 축하드립니다.");
       }
/**
 public ResponseEntity<String> save(@ApiParam("Signup User") @RequestBody User user) {
 logger.info(String.format("회원가입 정보: %s", user.toString()));
 User u = user.toEntity();
 userRepository.save(u);
 return ResponseEntity.ok(user.getName()+"님의 회원가입을 축하드립니다.");

 public ResponseEntity<String> save(@ApiParam("Signup User") @RequestBody UserDTO userDTO) {
 logger.info(String.format("회원가입 정보: %s", userDTO.toString()));
 //        System.out.println("encodePassword: "+userDTO.getPassword());
 Map<String, String> m = userService.join(userDTO);
 //        System.out.println("???"+m);
 //        userRepository.save();
 //        Map<String, String> resultMap = new HashMap<>();
 //        return new ResponseEntity(userService.join(user), HttpStatus.OK);
 return ResponseEntity.ok(userDTO.getName()+"님의 회원가입을 축하드립니다.");*/
    @GetMapping("/existsById/{username}")
    public ResponseEntity<Boolean> existById(@PathVariable String userName) {
        boolean b = userRepository.existsByUserName(userName);
        logger.info(String.format("아이디 존재 여부 :" + b));
        return ResponseEntity.ok(b);
    }

    @Override
    public ResponseEntity<Boolean> existById(Long id) { return null; }

    /**
    @PostMapping("/login")
    @ApiOperation(value="${UserController.signin}")
    @ApiResponses(value={
            @ApiResponse(code=400,message = "Something Wrong"),
            @ApiResponse(code=422,message = "유효하지 않는 아이디 / 비밀번호")})
    public ResponseEntity<UserDTO> login(@ApiParam("Signin User") @RequestBody UserDTO userDTO)
            throws Exception {
        logger.info("로그인에서 들어온 user값"+userDTO.toString());
        String pw = userDTO.getPassword();
        System.out.println("넘어온 비번"+pw);
        String pw2 = passwordEncoder.encode(pw);
        System.out.println("암호비번"+pw2);
        userDTO.setPassword(pw2);
        UserDTO entityDto = userService.login(userDTO);
        logger.info("로그인결과 : "+entityDto.getMessage());
        logger.info("token 값: "+ entityDto.getToken());
        return ResponseEntity.ok(entityDto);
    }*/

    @PostMapping("/login/login")
//    @ApiOperation(value="${UserController.signin}")
//    @ApiResponses(value={
//            @ApiResponse(code=400,message = "Something Wrong"),
//            @ApiResponse(code=422,message = "유효하지 않는 아이디 / 비밀번호")})
    public ResponseEntity<UserDTO> authenticateUser(@Valid @RequestBody UserDTO userDTO){
//        logger.info("로그인에서 들어온 user값"+userDTO.toString());
        String pw = userDTO.getPassword();
        System.out.println("넘어온 비번"+pw);
        String pw2 = passwordEncoder.encode(pw);
        System.out.println("암호비번"+pw2);
        userDTO.setPassword(pw2);
//        String u = userDTO.toString();
//        System.out.println(">>>>>>>>>>>>>>>>>>"+u);
//        UserDTO entityDto = userService.login(userDTO);
//        logger.info("token 값: "+ entityDto.getToken());
        User entity = userRepository.findByUserName(userDTO.getUserName())
                .orElseThrow(()-> null);

//        System.out.println("아이디에 맞는 비번 찾아와 "+entity.getPassword());
        UserDTO dto = new UserDTO();
        dto.setUserId(entity.getUserId());
//        dto.setUserName(entity.getUserName());
//        dto.setAddress(entity.getAddress());
//        dto.setBirth(entity.getBirth());
//        dto.setCardCompany(entity.getCardCompany());
//        dto.setCardNumber(entity.getCardNumber());
//        dto.setEmail(entity.getEmail());
//        dto.setFirstName(entity.getFirstName());
//        dto.setGender(entity.getGender());
//        dto.setLastName(entity.getLastName());
        dto.setMbti(entity.getMbti());
        dto.setMbtiList(entity.getMbtiList());
        dto.setName(entity.getName());
//        dto.setPassport(entity.getPassport());
        dto.setPassword(entity.getPassword());
//        dto.setPhoneNumber(entity.getPhoneNumber());
//        dto.setUserName(entity.getUserName());
//        String u = userDTO.toString();
        System.out.println(">>>>>>>>>>>>>>>>>>"+dto);
        return ResponseEntity.ok(dto);
    }
    /**
    @PostMapping("/login/login")
//    @ApiOperation(value="${UserController.signin}")
//    @ApiResponses(value={
//            @ApiResponse(code=400,message = "Something Wrong"),
//            @ApiResponse(code=422,message = "유효하지 않는 아이디 / 비밀번호")})
    public ResponseEntity<UserDTO> authenticateUser(@Valid @RequestBody UserDTO userDTO){
//        logger.info("로그인에서 들어온 user값"+userDTO.toString());
        String pw = userDTO.getPassword();
        System.out.println("넘어온 비번"+pw);
        String pw2 = passwordEncoder.encode(pw);
        System.out.println("암호비번"+pw2);
        userDTO.setPassword(pw2);
//        String u = userDTO.toString();
//        System.out.println(">>>>>>>>>>>>>>>>>>"+u);
//        UserDTO entityDto = userService.login(userDTO);
//        logger.info("token 값: "+ entityDto.getToken());
        logger.info("로그인 값 들어왔니?"+userDTO.toString());
        User entity = userRepository.findById(userDTO.getUserId())
                        .orElseThrow(()-> null);

//        System.out.println("아이디에 맞는 비번 찾아와 "+entity.getPassword());
        UserDTO dto = new UserDTO();
        dto.setUserId(entity.getUserId());
//        String u = userDTO.toString();
//        System.out.println(">>>>>>>>>>>>>>>>>>"+dto);
//        UserDTO entityDto = userService.login(userDTO);
//        logger.info("로그인결과 : "+entityDto.getMessage());

        return ResponseEntity.ok(dto);
    }*/

/**
    @PostMapping("/login/login")
//    @ApiOperation(value="${UserController.signin}")
//    @ApiResponses(value={
//            @ApiResponse(code=400,message = "Something Wrong"),
//            @ApiResponse(code=422,message = "유효하지 않는 아이디 / 비밀번호")})
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody UserDTO userDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getUserName(),userDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = securityProvider.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        String pw = userDTO.getPassword();
        System.out.println("넘어온 비번"+pw);
        String pw2 = passwordEncoder.encode(pw);
        System.out.println("암호비번"+pw2);
        userDTO.setPassword(pw2);
        User entity = userRepository.findByUserName(userDTO.getUserName())
                .orElseThrow(()-> null);
//        String u = userDTO.toString();
//        System.out.println(">>>>>>>>>>>>>>>>>>"+u);
//        UserDTO entityDto = userService.login(userDTO);
//        logger.info("token 값: "+ entityDto.getToken());
//        logger.info("로그인 값 들어왔니?"+userDTO.toString());
//        User entity = userRepository.findByUserName(userDTO.getUserName())
//                .orElseThrow(()-> null);

//        System.out.println("아이디에 맞는 비번 찾아와 "+entity.getPassword());
//        UserDTO dto = new UserDTO();
//        dto.setUserId(entity.getUserId());
//        String u = userDTO.toString();
//        System.out.println(">>>>>>>>>>>>>>>>>>"+dto);
//        UserDTO entityDto = userService.login(userDTO);
//        logger.info("로그인결과 : "+entityDto.getMessage());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUserId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }*/
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
    public ResponseEntity<List<User>> searchByPhoneNumber(@PathVariable String phoneNumber){
        logger.info(String.format("휴대폰번호로 찾기 : %s", userRepository.searchByPhoneNumber(phoneNumber)));
        return ResponseEntity.ok().body(userRepository.searchByPhoneNumber(phoneNumber));
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
    public ResponseEntity<List<User>> searchByUserName(@PathVariable String userName){
        logger.info(String.format("Username 으로 찾기 : %s", userRepository.searchByUserName(userName)));
        return ResponseEntity.ok().body(userRepository.searchByUserName(userName));
    }

    @PostMapping("/userSearch")
    public ResponseEntity <Optional<List<User>>> searchByUserList(
            @RequestBody UserDTO u ){
        return ResponseEntity.ok(userService.searchOption(u));
    }

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

    @PutMapping("/mbti/mbti")
    public ResponseEntity<User> updateMbti(@RequestBody User user){
        userRepository.updateMbti(user.getUserId(), user.getMbti());
        return ResponseEntity.ok(userRepository.getById(user.getUserId()));
    }

    @PutMapping("/mbti/mbtiList")
    public ResponseEntity<User> updateMbtiList(@RequestBody User user){
        userRepository.updateMbtiList(user.getUserId(), user.getMbtiList());
        return ResponseEntity.ok(userRepository.getById(user.getUserId()));
    }

    @PutMapping("/mbti")
    public ResponseEntity<User> updateMbti2(@RequestBody User user){
        userRepository.updateMbti2(user.getUserId(), user.getMbti(), user.getMbtiList());
        User u = user.toEntity();
        System.out.println(u);
        return ResponseEntity.ok(userRepository.getById(user.getUserId()));
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<User> updatePassword(@RequestBody User user){
        String pw = user.getPassword();
        System.out.println("넘어온 비번"+pw);
        String pw2 = passwordEncoder.encode(pw);
        System.out.println("암호비번"+pw2);
        user.setPassword(pw2);
        userRepository.updatePassword(user.getUserId(), pw2);
        return ResponseEntity.ok(userRepository.getById(user.getUserId()));
    }

    @GetMapping("/count")
    @Override
    public ResponseEntity <Long> count(){
        return ResponseEntity.ok(userRepository.count());
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        return null;
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteByUserName(@PathVariable String userName){
        userRepository.deleteByUserName(userName);
        return ResponseEntity.ok(userName+"님의 탈퇴가 완료되었습니다");
    }

}
