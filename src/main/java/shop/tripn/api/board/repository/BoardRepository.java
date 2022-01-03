package shop.tripn.api.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shop.tripn.api.board.domain.Board;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query(value = "select * from board where board.question like question", nativeQuery = true)
    List<Board> findByQuestion(@Param("question") String question);

    @Query(value = "select * from board b where (b.question LIKE %:keyword%) OR (b.answer LIKE %:keyword%) ", nativeQuery = true)
    List<Board> findByKeywordSearch(String keyword);

}