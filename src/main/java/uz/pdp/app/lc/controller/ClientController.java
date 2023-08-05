package uz.pdp.app.lc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app.lc.dto.ClientDTO;
import uz.pdp.app.lc.dto.ResponseDTO;
import uz.pdp.app.lc.entity.ClientEntity;
import uz.pdp.app.lc.service.client.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PreAuthorize(value = "hasAnyRole('MANAGER', 'STUDENT')")
    @PostMapping("/add")
    public ResponseEntity<ResponseDTO<ClientDTO>> addClient(
            @Valid @RequestBody ClientDTO clientDTO
    ) {
        ClientDTO client = clientService.addClient(clientDTO);
        return ResponseEntity.ok(new ResponseDTO<>(client));
    }

    @PreAuthorize(value = "hasAnyRole('MANAGER', 'STUDENT')")
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO<ClientEntity>> getClientById(
            @PathVariable Long id
    ) {
        ClientEntity client = clientService.getById(id);
        return ResponseEntity.ok(new ResponseDTO<>(client));
    }

    @PreAuthorize(value = "hasRole('MANAGER')")
    @GetMapping("/get/all")
    public ResponseEntity<ResponseDTO<List<ClientEntity>>> getAll() {
        List<ClientEntity> clientList = clientService.getAll();
        return ResponseEntity.ok(new ResponseDTO<>(clientList));
    }

    @PreAuthorize(value = "hasRole('MANAGER')")
    @GetMapping("/get/all-deleted")
    public ResponseEntity<ResponseDTO<Page<ClientEntity>>> getAllDeletedPages(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "3") Integer size
    ) {
        Page<ClientEntity> allDeletedPages = clientService.getAllDeletedPages(page, size);
        return ResponseEntity.ok(new ResponseDTO<>(allDeletedPages));
    }

    @PreAuthorize(value = "hasRole('MANAGER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteById(
            @PathVariable Long id
    ) {
        clientService.deleteById(id);
        return ResponseEntity.ok(new ResponseDTO<>("Success"));
    }
}
