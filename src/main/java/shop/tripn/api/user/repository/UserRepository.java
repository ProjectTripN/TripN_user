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
    @Query(value = "select * from users where users.userName=:userName and users.password=:password", nativeQuery = true)
    Optional<User> login(@Param("userName") String userName, @Param("password") String password);
    boolean existsByUserName(@Param("userName") String userName);
    @Query(value = "select * from users where name=:name", nativeQuery = true)
    List<User> searchByName(@Param("name") String name);
    @Query(value = "select * from users where phone_number=:phoneNumber", nativeQuery = true)
    List<User> searchByPhoneNumber(@Param("phoneNumber") String phoneNumber);
    @Query(value = "select * from users where birth=:birth", nativeQuery = true)
    List<User> searchByBirth(@Param("birth") String birth);
    @Query(value = "select * from users where email=:email", nativeQuery = true)
    User searchByEmail(@Param("email") String email);
    @Query(value = "select * from users where user_name=:userName", nativeQuery = true)
    List<User> searchByUserName(@Param("userName") String userName);
    @Query(value = "select * from users where name=:name and birth=:birth and phone_number=:phoneNumber", nativeQuery = true)
    List<User> searchByUserList(@Param("name") String name, @Param("birth") String birth, @Param("phoneNumber") String phoneNumber);
    @Query(value = "select * from users where name=:name and birth=:birth", nativeQuery = true)
    List<User> searchByUserBirth(@Param("name") String name, @Param("birth") String birth);
    @Query(value = "select * from users where name=:name and phone_number=:phoneNumber", nativeQuery = true)
    List<User> searchByUserPhone(@Param("name") String name, @Param("phoneNumber") String phoneNumber);
    @Query(value = "select * from users where birth=:birth and phone_number=:phoneNumber", nativeQuery = true)
    List<User> searchByBirthPhone(@Param("birth") String birth, @Param("phoneNumber") String phoneNumber);
    Optional<User> findByUserName(String userName);
    @Transactional
    String deleteByUserName(@Param("userName") String userName);

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

    @Modifying
    @Transactional
    @Query(value = "update users set mbti=:mbti, mbti_list=:mbtiList where user_id=:userId", nativeQuery = true)
    void updateMbti2(@Param("userId") long userId, @Param("mbtiList") String mbtiList, @Param("mbti") String mbti);

    @Query(value = "SELECT COUNT(*) count FROM users", nativeQuery = true)
    long count();

}
