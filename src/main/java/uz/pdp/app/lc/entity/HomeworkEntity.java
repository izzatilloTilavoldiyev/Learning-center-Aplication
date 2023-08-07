package uz.pdp.app.lc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
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
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private GroupEntity groupEntity;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private UserEntity createdBy;

}
