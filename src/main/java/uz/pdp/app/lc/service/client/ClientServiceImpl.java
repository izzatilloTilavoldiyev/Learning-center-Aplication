package uz.pdp.app.lc.service.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.app.lc.dto.ClientDTO;
import uz.pdp.app.lc.entity.ClientEntity;
import uz.pdp.app.lc.exception.DataNotFoundException;
import uz.pdp.app.lc.repository.ClientRepository;

import java.util.List;

import static uz.pdp.app.lc.mapper.ClientMapper.CLIENT_MAPPER;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;

    @Override
    public ClientEntity addClient(ClientDTO clientDTO) {
        ClientEntity clientEntity = CLIENT_MAPPER.toEntity(clientDTO);
        return clientRepository.save(clientEntity);
    }

    @Override
    public ClientEntity getById(Long id) {
        return getClientById(id);
    }

    @Override
    public List<ClientEntity> getAll() {
        return clientRepository.getAll();
    }

    @Override
    public void deleteById(Long id) {
        ClientEntity clientEntity = getClientById(id);
        clientEntity.setDeleted(true);
        clientRepository.save(clientEntity);
    }

    public ClientEntity getClientById(Long id) {
        return clientRepository.findClientById(id).orElseThrow(
                () -> new DataNotFoundException("Client not found")
        );
    }
}
