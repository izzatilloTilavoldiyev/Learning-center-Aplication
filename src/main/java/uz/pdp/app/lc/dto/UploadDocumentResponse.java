package uz.pdp.app.lc.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UploadDocumentResponse {
    private String originalName;
    private String fileType;
    private Long size;
    private String fileDownloadUri;
}
