package shop.tripn.api.board.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Data
@Component
@Table(name = "board")
@Proxy(lazy = false)
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long boardId;

    @Column(length = 1000000000)  private @NotNull String question;
    @Column(length = 1000000000)  private @NotNull String answer;
    @Column(length = 10000) private @NotNull String category;
    @Column(length = 10000) private @NotNull String categoryDetail;


    public Board toEntity(){
        return new Board(boardId, question, answer, category, categoryDetail);
    }
}
