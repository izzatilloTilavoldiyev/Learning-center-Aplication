package uz.pdp.app.lc.service.client;

import org.springframework.data.domain.Page;
import uz.pdp.app.lc.dto.ClientDTO;
import uz.pdp.app.lc.entity.ClientEntity;

import java.util.List;

public interface ClientService {

    ClientDTO addClient(ClientDTO clientDTO);

    ClientEntity getById(Long id);

    List<ClientEntity> getAll();

    Page<ClientEntity> getAllDeletedPages(Integer page, Integer size);

    void deleteById(Long id);
}
