package uz.pdp.app.lc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO<ClientEntity>> addClient(
            @Valid @RequestBody ClientDTO clientDTO
    ) {
        return ResponseEntity.ok(new ResponseDTO<>(clientService.addClient(clientDTO)));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO<ClientEntity>> getClientById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(new ResponseDTO<>(clientService.getById(id)));
    }

    @PreAuthorize(value = "hasRole('MANAGER')")
    @GetMapping("/get/all")
    public ResponseEntity<ResponseDTO<List<ClientEntity>>> getAll() {
        return ResponseEntity.ok(new ResponseDTO<>(clientService.getAll()));
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
