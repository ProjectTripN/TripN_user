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
    List<User> findByName(@Param("name") String name);
    @Query(value = "select * from users where users.phone_number=:phone_number", nativeQuery = true)
    List<User> findByPhoneNumber(@Param("phone_number") String phone_number);
    @Query(value = "select * from users where users.birth=:birth", nativeQuery = true)
    List<User> findByBirth(@Param("birth") String birth);
    @Query(value = "select * from users where users.password=:password", nativeQuery = true)
    List<User> findByPassword(@Param("password") String password);
    @Query(value = "select * from users where users.email=:email", nativeQuery = true)
    List<User> findByEmail(@Param("email") String email);
    @Query(value = "select * from users where users.username=:username", nativeQuery = true)
    List<User> findByUsername(@Param("username") String username);

    @Transactional
    String deleteByUsername(@Param("username") String username);
    /**
     * @Modifying
     * @Query("update User u set u.active = false where u.lastLoginDate < :date")
     * update User set users.active = false where users.lastLoginDate < :date
     * void deactivateUsersNotLoggedInSince(@Param("date") LocalDate date);
     * UPDATE mysql.user SET Password=PASSWORD('Password') WHERE User='User'
     * @Modifying
     *   @Query("update User u set u.password= :password where u.username = :username")
     *   void updatePassword(@Param("username") String username, @Param("password") String password);
     *
     * @Modifying
     *     @Query(value = "update * set users.password=:password where users.username=:username", nativeQuery = true)
     *     void updatePassword(@Param("username") String username, @Param("password") String password);
     *  update user set pass='~~'    where id='~~~'*/

    @Modifying
    @Transactional
    @Query(value = "update users set password='password' where username='username'", nativeQuery = true)
    void updatePassword(@Param("password") String password);

//    @Modifying
//    @Transactional
//    @Query(value = "update users set ")
}
