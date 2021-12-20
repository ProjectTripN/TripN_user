package shop.tripn.api.multipart;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class UserFile {
    private String memberId;
    private String title;
    private String content;
    private List<MultipartFile> files;
}

