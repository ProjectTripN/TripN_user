package shop.tripn.api.sendEmail;

import lombok.*;



@Data
@NoArgsConstructor
public class MailDto {
    private String address;
    private String title;
    private String message;
    private String name;
    }
