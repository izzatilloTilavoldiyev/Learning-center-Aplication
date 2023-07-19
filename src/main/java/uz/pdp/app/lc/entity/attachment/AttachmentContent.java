package uz.pdp.app.lc.entity.attachment;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.*;
import uz.pdp.app.lc.entity.base.BaseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "attachment_content")
public class AttachmentContent extends BaseEntity {

    private byte[] mainContent;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AttachmentEntity attachment;
}

