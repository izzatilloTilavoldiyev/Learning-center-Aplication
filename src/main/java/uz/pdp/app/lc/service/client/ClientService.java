package uz.pdp.app.lc.service.client;

import uz.pdp.app.lc.dto.ClientDTO;
import uz.pdp.app.lc.entity.ClientEntity;

import java.util.List;

public interface ClientService {

    ClientEntity addClient(ClientDTO clientDTO);

    ClientEntity getById(Long id);

    List<ClientEntity> getAll();

    void deleteById(Long id);
}
