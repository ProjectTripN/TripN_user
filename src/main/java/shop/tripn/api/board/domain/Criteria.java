package shop.tripn.api.board.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class Criteria {
    private int pageNum;
    private int amount;
    private int skip;
    private String keyword;
    private String type;
    private String[] typeArr;
    public Criteria(){
        this(1,10);
        this.skip = 0;
    }
    public Criteria(int pageNum, int amount){
        this.pageNum = pageNum;
        this.amount = amount;
        this.skip = (pageNum-1)*amount;
    }
    @Builder
    Criteria(String keyword, String type, String[] typeArr){
        this.keyword = keyword;
        this.type = type;
        this.typeArr = typeArr;
    }
}
