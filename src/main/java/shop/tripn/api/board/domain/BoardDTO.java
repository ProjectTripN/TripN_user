package shop.tripn.api.board.domain;

import lombok.Builder;
import lombok.Data;
import java.io.Serializable;

@Data
public class BoardDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private long boardId;
    private String question;
    private String answer;
    private String category;
    private String categoryDetail;
    private int bno;
    private String type;
    private String keyword;


    @Builder
    BoardDTO(long boardId, String question, String answer, String category, String categoryDetail, int bno, String type, String keyword){
        this.boardId = boardId;
        this.question = question;
        this.answer = answer;
        this.category = category;
        this.categoryDetail = categoryDetail;
        this.bno = bno;
        this.type = type;
        this.keyword = keyword;
    }
}
