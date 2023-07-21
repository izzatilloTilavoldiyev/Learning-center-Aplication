package uz.pdp.app.lc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app.lc.entity.attachment.AttachmentEntity;

public interface AttachmentRepository extends JpaRepository<AttachmentEntity, Long> {
}
