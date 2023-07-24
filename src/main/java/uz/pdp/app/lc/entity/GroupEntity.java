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
@Entity(name = "groups")
public class GroupEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private CourseEntity courseEntity;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @JsonIgnore
    @ManyToMany
    private Set<UserEntity> students;

    private boolean deleted;

}
