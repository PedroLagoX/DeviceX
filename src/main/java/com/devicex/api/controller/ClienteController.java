package com.devicex.api.controller;

import com.devicex.api.dto.ClienteResponseDTO;
import com.devicex.api.model.Cliente;
import com.devicex.api.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarTodos() {

        return ResponseEntity.ok(
                clienteService.listarTodos()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                clienteService.buscarPorId(id)
        );
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> salvar(
            @Valid @RequestBody Cliente cliente) {

        return ResponseEntity.ok(
                clienteService.salvar(cliente)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Cliente cliente) {

        return ResponseEntity.ok(
                clienteService.atualizar(id, cliente)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        clienteService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}