package shop.tripn.api.user.domain;

import lombok.*;
import org.springframework.stereotype.Component;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Component
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserFileDTO {
    private Long userFileId;
    private String uuid;
    private String imgName;
    private String path;
    private User user;

    public Long getArtistFileId() {
        return userFileId;
    }

    public String getPath() {
        return path;
    }

    public String getUuid() {
        return uuid;
    }

    public String getImgName() {
        return imgName;
    }

    public String getImageURL() {
        try {
            return URLEncoder.encode(path + "/" + uuid + "_" + imgName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getTumbnailURL() {
        try {
            return URLEncoder.encode(path + "/s_" + uuid + "_" + imgName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
