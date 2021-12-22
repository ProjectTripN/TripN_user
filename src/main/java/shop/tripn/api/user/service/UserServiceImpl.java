package shop.tripn.api.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import shop.tripn.api.security.domain.SecurityProvider;
import shop.tripn.api.security.exception.SecurityRuntimeException;
import shop.tripn.api.user.domain.User;
import shop.tripn.api.user.domain.UserDTO;
import shop.tripn.api.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final SecurityProvider provider;

    @Override
    public Optional<User> findById(long userid) {
        return userRepository.findById(userid);
    }

    /**
    @Override
    public Optional<User> login(String username, String password) {
        return Optional.empty();
    }*/

    @Override
    public UserDTO login(UserDTO userDTO) {
        try {
//            User user = new User();
//            user.setUsername(username);
//            user.setPassword(password);
//            UserDTO userDTO = entityDto(user);
            userRepository.login(userDTO.getUsername(), userDTO.getPassword());
            User entity = dtoEntity(userDTO);
            userRepository.login(entity.getUsername(), entity.getPassword());

            UserDTO entityDto = entityDto(entity);
            Optional<User> comprehensiveInfomationUser = userRepository.findByUsername(entity.getUsername());
//            Long artistFileId = comprehensiveInfomationUser.get().getUserId();
            entityDto(comprehensiveInfomationUser.get());
            entityDto = entityDto(comprehensiveInfomationUser.get());
            String Token = provider.createToken(entity.getUsername(),
                    userRepository.findByUsername(entity.getUsername()).get().getRoles());
            entityDto.setToken(Token);

            return entityDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SecurityRuntimeException("Invalid User-Username / Password supplied",
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
//        return userRepository.login(username,password);
    }

    @Override
    public List<User> searchByName(String username) {
        return userRepository.searchByName(username);
    }


}
