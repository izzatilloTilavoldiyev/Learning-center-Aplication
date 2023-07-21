package uz.pdp.app.lc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
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

    @JsonIgnore
    @ManyToMany
    private Set<UserEntity> students;

    private boolean deleted;

}
