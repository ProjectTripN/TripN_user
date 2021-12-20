package shop.tripn.api.user.domain;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@NoArgsConstructor
@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private long userId;
    private String username;
    private String password;
    private String name;
    private String first_name;
    private String last_name;
    private String email;
    private String birth;
    private String gender;
    private String address;
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})}") private String phone_number;
    private String passport;
    private String mbti;
    private String card_number;
    private String card_company;
    private String mbti_list;
    private String regDate;
    private String token;




    @Builder
    UserDTO(long userId, String username, String password, String name,
            String first_name,String last_name, String email, String birth,
            String gender, String address, String phone_number, String passport, String mbti,
            String card_number, String card_company, String mbti_list, String regDate, String token) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.birth = birth;
        this.gender = gender;
        this.address = address;
        this.phone_number = phone_number;
        this.passport = passport;
        this.mbti = mbti;
        this.card_number = card_number;
        this.card_company = card_company;
        this.mbti_list = mbti_list;
        this.regDate = regDate;
        this.token = token;
    }
}
