package uz.pdp.app.lc.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import lombok.*;
import uz.pdp.app.lc.entity.base.BaseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class ClientEntity extends BaseEntity {

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String phoneNumber;

    private Boolean deleted;

    @PrePersist
    public void setDefaultDeleted() {
        this.deleted = false;
    }
}
