package uz.pdp.app.lc.entity.attachment;

import jakarta.persistence.Entity;
import lombok.*;
import uz.pdp.app.lc.entity.base.BaseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "attachment")
public class AttachmentEntity extends BaseEntity {

    private String name;

    private Long size;

    private String contentType;
}
