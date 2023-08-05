package uz.pdp.app.lc.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import uz.pdp.app.lc.dto.UploadDocumentResponse;
import uz.pdp.app.lc.entity.Document;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DocumentMapper {

    DocumentMapper DOCUMENT_MAPPER = Mappers.getMapper(DocumentMapper.class);

    Document toEntity(UploadDocumentResponse uploadDocumentResponse);

    UploadDocumentResponse toDto(Document document);

}
