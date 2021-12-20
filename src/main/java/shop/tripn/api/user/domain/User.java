package shop.tripn.api.user.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;


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
    @Column(length = 50) private @NotNull String username;
    @Column(length = 20) private @NotNull String password;
    @Column(length = 20) private @NotNull String name;
    @Column(length = 20) private @NotNull String first_name;
    @Column(length = 20) private @NotNull String last_name;
    @Column(length = 20) private @NotNull String email;
    @Column private @NotNull String birth;
    @Column(length = 10) private @NotNull String gender;
    @Column(length = 100) private @NotNull String address;
    @Column(length = 50) private @NotNull String phone_number;
    @Column(length = 20) private @NotNull String passport;
    @Column(length = 20) private @NotNull String mbti;
    @Column(length = 30) private @NotNull String card_number;
    @Column(length = 10) private @NotNull String card_company;
    @Column private @NotNull String mbti_list;
    @Column(name = "reg_date", length = 20) private @NotNull String regDate;

    @ElementCollection(fetch = FetchType.EAGER)
    public List<Role> roles;

    public User toEntity() {
        return new User(userId, username, password, name, first_name, last_name, email, birth, gender, address, phone_number, passport,
                mbti, card_number, card_company, mbti_list, regDate, roles);
    }
}
