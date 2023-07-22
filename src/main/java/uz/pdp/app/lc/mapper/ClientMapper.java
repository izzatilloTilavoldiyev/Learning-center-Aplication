package uz.pdp.app.lc.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import uz.pdp.app.lc.dto.ClientDTO;
import uz.pdp.app.lc.entity.ClientEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {

    ClientMapper CLIENT_MAPPER = Mappers.getMapper(ClientMapper.class);

    @Mapping(target = "fullName",source = "fullName")
    ClientEntity toEntity(ClientDTO clientDTO);

}
