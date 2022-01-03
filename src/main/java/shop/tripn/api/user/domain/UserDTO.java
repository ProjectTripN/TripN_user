package shop.tripn.api.user.domain;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@NoArgsConstructor
@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private long userId;
    private String userName;
    private String password;
    private String name;
    private String firstName;
    private String lastName;
    private String email;
    private String birth;
    private String gender;
    private String address;
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})}") private String phoneNumber;
    private String passport;
    private String mbti;
    private String cardNumber;
    private String cardCompany;
    private String mbtiList;
    private String regDate;
    private String token;
    private String message;




    @Builder
    UserDTO(long userId, String userName, String password, String name,
            String firstName,String lastName, String email, String birth,
            String gender, String address, String phoneNumber, String passport, String mbti,
            String cardNumber, String cardCompany, String mbtiList, String regDate, String token) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birth = birth;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.passport = passport;
        this.mbti = mbti;
        this.cardNumber = cardNumber;
        this.cardCompany = cardCompany;
        this.mbtiList = mbtiList;
        this.regDate = regDate;
        this.token = token;
    }
}
