package uz.pdp.app.lc.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.app.lc.entity.attachment.AttachmentEntity;
import uz.pdp.app.lc.entity.base.BaseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class ProfileEntity extends BaseEntity {

    @Column(nullable = false)
    private String fullName;

    private String bio;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AttachmentEntity attachment;

}
