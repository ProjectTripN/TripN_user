package shop.tripn.api.sendEmail;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {
    private final MailService mailService;

    @Value("")
    private String from;
    @GetMapping("/sendmail")
    public String dispMail() {
        return "mail";
    }

    @PostMapping("/sendmail")
    public void execMail(@RequestBody MailDto mailDto) {
        System.out.println(mailDto.getAddress());
        mailService.mailSend(mailDto);
    }

    @PostMapping("/joinSendmail")
    public void joinMail(@RequestBody MailDto mailDto) {
        System.out.println(mailDto.getAddress());
        mailService.joinMailSend(mailDto);
    }
}