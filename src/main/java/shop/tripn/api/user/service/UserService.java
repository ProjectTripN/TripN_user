package shop.tripn.api.user.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import shop.tripn.api.user.domain.User;

import java.util.List;
import java.util.Optional;

@Component
public interface UserService {
    Optional<User> findById(long userId);
    Optional<User> login(String username, String password);
    List<User> findByName(String username);

}
