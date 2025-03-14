package application.entity.client;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

import java.util.Base64;

@Data
@Entity
@Table(name = "CIAFO")
public class CiafoItem {
    @Id
    @GeneratedValue
    private Long id;
    private String category;
    @Transient
    private Boolean delete;
    private String description;
    private byte[] image1;
    private byte[] image2;
    private byte[] image3;
    private byte[] image4;

    public String getImage1Base64() {
        return getBase64(image1);
    }

    public String getImage2Base64() {
        return getBase64(image2);
    }

    public String getImage3Base64() {
        return getBase64(image3);
    }

    public String getImage4Base64() {
        return getBase64(image4);
    }

    private String getBase64(byte[] image) {
        if (image == null) {
            return null;
        }
        return "data:image/jpeg;base64,".concat(Base64.getEncoder().encodeToString(image));
    }
}