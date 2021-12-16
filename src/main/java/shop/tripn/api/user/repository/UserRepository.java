package shop.tripn.api.user.repository;

import jdk.jfr.MetadataDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shop.tripn.api.user.domain.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Transactional
    @Query(value = "select * from users where users.username=:username and users.password=:password", nativeQuery = true)
    Optional<User> login(@Param("username") String username, @Param("password") String password);
    boolean existsByUsername(@Param("username") String username);
//    @Query(value = "select users.name from users where users.name=:name", nativeQuery = true)
//    Optional<User> findByUsername(@Param("name") String name);
    @Query(value = "select * from users where users.name=:name", nativeQuery = true)
    List<User> searchByName(@Param("name") String name);
    @Query(value = "select * from users where users.phone_number=:phone_number", nativeQuery = true)
    List<User> searchByPhoneNumber(@Param("phone_number") String phone_number);
    @Query(value = "select * from users where users.birth=:birth", nativeQuery = true)
    List<User> searchByBirth(@Param("birth") String birth);
    @Query(value = "select * from users where users.password=:password", nativeQuery = true)
    List<User> findByPassword(@Param("password") String password);
    @Query(value = "select * from users where users.email=:email", nativeQuery = true)
    List<User> searchByEmail(@Param("email") String email);
    @Query(value = "select * from users where users.username=:username", nativeQuery = true)
    List<User> searchByUsername(@Param("username") String username);

    Optional<User> findByUsername(String username);
    @Transactional
    String deleteByUsername(@Param("username") String username);


    @Modifying
    @Transactional
    @Query(value = "update users set password=:password where email=:email", nativeQuery = true)
    void updatePassword(@Param("email") String email, @Param("password") String password);
}
