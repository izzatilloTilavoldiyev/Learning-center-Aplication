package uz.pdp.app.lc.service.attachment;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.app.lc.entity.attachment.AttachmentEntity;

public interface AttachmentService {
    AttachmentEntity uploadFile(MultipartHttpServletRequest request);
}
