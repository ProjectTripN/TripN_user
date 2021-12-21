package shop.tripn.api.user.repository;

import shop.tripn.api.user.domain.UserFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UserFileRepository extends JpaRepository<UserFile, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM UserFile rf WHERE rf.user.userId = :userId")
    void userFileDelete(@Param("userId") Long userId);

}
