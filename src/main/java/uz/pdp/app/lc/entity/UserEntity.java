package uz.pdp.app.lc.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.app.lc.entity.base.BaseEntity;
import uz.pdp.app.lc.entity.enums.UserRole;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class UserEntity extends BaseEntity {

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    private Integer password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(nullable = false)
    private Integer age;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ProfileEntity profileEntity;

    private Boolean deleted;

    @PrePersist
    public void setDefaultDeleted() {
        this.deleted = false;
    }

}
