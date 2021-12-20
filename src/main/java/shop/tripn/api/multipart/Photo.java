//package shop.tripn.api.multipart;
//
//
//import lombok.AccessLevel;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import shop.tripn.api.user.domain.User;
//
//import javax.persistence.*;
//
//@Getter
//@NoArgsConstructor(access = AccessLevel.PUBLIC)
//@Entity
//@Table(name = "file")
//public class Photo extends BaseTimeEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "file_id")
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @Column(nullable = false)
//    private String origFileName;  // 파일 원본명
//
//    @Column(nullable = false)
//    private String filePath;  // 파일 저장 경로
//
//    private Long fileSize;
//
//    @Builder
//    public Photo(String origFileName, String filePath, Long fileSize){
//        this.origFileName = origFileName;
//        this.filePath = filePath;
//        this.fileSize = fileSize;
//    }
//
//    // Board 정보 저장
//    public void setBoard(User user){
//        this.user = user;
//
//        // 게시글에 현재 파일이 존재하지 않는다면
//        if(!user.getPhoto().contains(this))
//            // 파일 추가
//            user.getPhoto().add(this);
//    }
//}
