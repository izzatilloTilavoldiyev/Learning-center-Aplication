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
@Entity
public class CourseEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @JsonIgnore
    @ManyToMany
    private Set<UserEntity> teachers;

    private Boolean deleted;

    @PrePersist
    public void setDefaultDeleted() {
        this.deleted = false;
    }
}
