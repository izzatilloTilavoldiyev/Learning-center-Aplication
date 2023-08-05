package uz.pdp.app.lc.entity;

import jakarta.persistence.Entity;
import lombok.*;
import uz.pdp.app.lc.entity.base.BaseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "documents")
public class Document extends BaseEntity {
    private String originalName;
    private String extension;
    private String fileType;
    private Long size;
    private String fileDownloadUri;
}
