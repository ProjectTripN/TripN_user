package shop.tripn.api.user.repository;

import jdk.jfr.MetadataDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import shop.tripn.api.board.domain.Board;
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
    @Query(value = "select * from users where users.name=:name", nativeQuery = true)
    List<User> searchByName(@Param("name") String name);
    @Query(value = "select * from users where users.phone_number=:phone_number", nativeQuery = true)
    List<User> searchByPhoneNumber(@Param("phone_number") String phone_number);
    @Query(value = "select * from users where users.birth=:birth", nativeQuery = true)
    List<User> searchByBirth(@Param("birth") String birth);
    @Query(value = "select * from users where users.email=:email", nativeQuery = true)
    User searchByEmail(@Param("email") String email);
    @Query(value = "select * from users where users.username=:username", nativeQuery = true)
    List<User> searchByUsername(@Param("username") String username);
    @Query(value = "select * from users where users.username=:username and users.birth=:birth and users.phone_number=:phone_number", nativeQuery = true)
    List<User> searchByUserList(@Param("username") String username, @Param("birth") String birth, @Param("phone_number") String phone_number);
    @Query(value = "select * from users where users.phone_number=:phone_number and users.username=:username ", nativeQuery = true)
    List<User> searchByUserListPhone(@Param("phone_number") String phone_number, @Param("username") String username);

//    @Query(value = "select * from users where users.birth=:birth and users.phone_number=:phone_number", nativeQuery = true)
//    List<User> searchByUserListBirth(@Param("birth") String birth, @Param("phone_number") String phone_number);
//    @Query(value = "select * from users where users.username=:username and users.birth=:birth ", nativeQuery = true)
//    List<User> searchByUserListName(@Param("username") String username, @Param("birth") String birth);
//    @Query(value = "select * from users where users.password=:password", nativeQuery = true)
//    List<User> searchByPassword(@Param("password") String password);
//    @Query(value = "select * from users where users.username=:username LIKE %:keyword% OR users.birth=:birth LIKE %:keyword% OR users.phone_number=:phone_number LIKE %:keyword% ", nativeQuery = true)
//    List<Board> findKeywordSearch(String keyword);

    Optional<User> findByUsername(String username);
    @Transactional
    String deleteByUsername(@Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "update users set password=:password where email=:email", nativeQuery = true)
    void forgotPassword(@Param("email") String email, @Param("password") String password);

    @Modifying
    @Transactional
    @Query(value = "update users set password=:password where user_id=:userId", nativeQuery = true)
    void updatePassword(@Param("userId") long userId, @Param("password") String password);

    @Modifying
    @Transactional
    @Query(value = "update users set mbti=:mbti where user_id=:userId", nativeQuery = true)
    void updateMbti(@Param("userId") long userId, @Param("mbti") String mbti);

    @Modifying
    @Transactional
    @Query(value = "update users set mbti_list=:mbtiList where user_id=:userId", nativeQuery = true)
    void updateMbtiList(@Param("userId") long userId, @Param("mbtiList") String mbtiList);
}
