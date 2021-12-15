package shop.tripn.api.sendEmail;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import shop.tripn.api.user.repository.UserRepository;


@Service
@AllArgsConstructor
public class MailService {

    private UserRepository userRepository;

    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "yjyoon2107@gmail.com";

    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 6; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    public void mailSend(MailDto mailDto) {
        String str = getTempPassword();
        try {
            MailHandler mailHandler = new MailHandler(mailSender);

            mailHandler.setTo(mailDto.getAddress());
            mailHandler.setFrom(MailService.FROM_ADDRESS);
            mailHandler.setSubject(mailDto.getName()+"님의 TripN 임시비밀번호 안내 이메일 입니다.");
            userRepository.updatePassword(str);
            mailHandler.setText("안녕하세요.\n\n TripN 임시비밀번호 안내 관련 이메일 입니다.\n\n"+mailDto.getName()+"님의 임시 비밀번호는 '"
            +str+"' 입니다.\n\n 아래 링크를 통해 비밀번호를 변경해 주세요.");

            mailHandler.send();
        }

        catch(Exception e){
            e.printStackTrace();
        }

    }
}
