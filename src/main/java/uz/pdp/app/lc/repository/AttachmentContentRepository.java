package uz.pdp.app.lc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app.lc.entity.attachment.AttachmentContent;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Long> {
}
