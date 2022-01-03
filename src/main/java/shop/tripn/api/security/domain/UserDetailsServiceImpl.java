package shop.tripn.api.security.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import shop.tripn.api.user.domain.User;
import shop.tripn.api.user.repository.UserRepository;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(repository.findByUserName(username))
                .orElseThrow(() -> new UsernameNotFoundException(username+ "에 해당하는 객체가 존재하지 않습니다."));
        return UserDetailsImpl.build(user.get());
    }
}
