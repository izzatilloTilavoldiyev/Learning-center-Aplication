package uz.pdp.app.lc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import uz.pdp.app.lc.entity.attachment.AttachmentEntity;
import uz.pdp.app.lc.entity.base.BaseEntity;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "homework")
public class HomeworkEntity extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    private boolean deleted;

    @JsonIgnore
    @OneToOne
    private GroupEntity groupEntity;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private AttachmentEntity attachment;

}
