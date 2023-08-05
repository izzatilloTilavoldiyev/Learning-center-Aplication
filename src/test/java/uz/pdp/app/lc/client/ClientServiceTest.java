package uz.pdp.app.lc.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import uz.pdp.app.lc.dto.ClientDTO;
import uz.pdp.app.lc.entity.ClientEntity;
import uz.pdp.app.lc.mapper.ClientMapper;
import uz.pdp.app.lc.repository.ClientRepository;
import uz.pdp.app.lc.service.client.ClientService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ClientServiceTest {

    private final String fullName = "Izzatillo Tilavoldiyev";
    private final String phoneNumber = "5555";

    private ClientDTO clientDTO;
    private ClientEntity clientEntity;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        initMocks(this);
        clientDTO = new ClientDTO(fullName, phoneNumber);
        clientEntity = ClientEntity.builder()
                .fullName(fullName)
                .phoneNumber(phoneNumber)
                .build();
    }

    @Test
    void addClient() {
        when(clientMapper.toEntity(clientDTO)).thenReturn(clientEntity);
        when(clientRepository.save(clientEntity)).thenReturn(clientEntity);

        ClientDTO dto = clientService.addClient(clientDTO);
        assertEquals(phoneNumber, dto.phoneNumber());
    }

}
