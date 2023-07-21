package uz.pdp.app.lc.service.attachment;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.app.lc.entity.attachment.AttachmentContent;
import uz.pdp.app.lc.entity.attachment.AttachmentEntity;
import uz.pdp.app.lc.repository.AttachmentContentRepository;
import uz.pdp.app.lc.repository.AttachmentRepository;

import java.util.Iterator;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService{
    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;
    @SneakyThrows
    @Override
    public AttachmentEntity uploadFile(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        AttachmentEntity attachment = new AttachmentEntity();
        assert file != null;
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        AttachmentEntity savedAttachment = attachmentRepository.save(attachment);

        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setMainContent(file.getBytes());
        attachmentContent.setAttachment(savedAttachment);
        attachmentContentRepository.save(attachmentContent);
        return savedAttachment;
    }
}
