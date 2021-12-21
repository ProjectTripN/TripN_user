package shop.tripn.api.user.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString(exclude = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_file")
public class UserFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_file_id")
    private Long userFileId;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "img_name")
    private String imgName;

    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
