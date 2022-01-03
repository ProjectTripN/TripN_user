package shop.tripn.api.user.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.util.List;


@Builder
@Data
@Entity
@Table(name = "users")
@Component
@Proxy(lazy = false)
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    @Column(length = 50) private @NotNull String userName;
    @Column private @NotNull String password;
    @Column(length = 20) private @NotNull String name;
    @Column(length = 20) private @NotNull String firstName;
    @Column(length = 20) private @NotNull String lastName;
    @Column(length = 20) private @NotNull String email;
    @Column private @NotNull String birth;
    @Column(length = 10) private @NotNull String gender;
    @Column(length = 100) private @NotNull String address;
    @Column(length = 50) private @NotNull String phoneNumber;
    @Column(length = 20) private @NotNull String passport;
    @Column(length = 20) private @NotNull String mbti;
    @Column(length = 30) private @NotNull String cardNumber;
    @Column(length = 10) private @NotNull String cardCompany;
    @Column private @NotNull String mbtiList;
    @Column(name = "reg_date", length = 20) private @NotNull String regDate;
    @Column private String token;

    @ElementCollection(fetch = FetchType.EAGER)
    public List<Role> roles;

    public void changeRoles(List<Role> roles) {
        this.roles = roles;
    }

    public User toEntity() {
        return new User(userId, userName, password, name, firstName, lastName, email, birth, gender, address, phoneNumber, passport,
                mbti, cardNumber, cardCompany, mbtiList, regDate, token,roles);
    }


}
