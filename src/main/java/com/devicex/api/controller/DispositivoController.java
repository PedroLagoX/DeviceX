package com.devicex.api.controller;

import com.devicex.api.dto.DispositivoResponseDTO;
import com.devicex.api.model.Dispositivo;
import com.devicex.api.service.DispositivoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dispositivos")
public class DispositivoController {

    private final DispositivoService dispositivoService;

    public DispositivoController(DispositivoService dispositivoService) {
        this.dispositivoService = dispositivoService;
    }

    @GetMapping
    public ResponseEntity<List<DispositivoResponseDTO>> listarTodos() {

        return ResponseEntity.ok(
                dispositivoService.listarTodos()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispositivoResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                dispositivoService.buscarPorId(id)
        );
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<DispositivoResponseDTO>> listarPorCliente(
            @PathVariable Long clienteId) {

        return ResponseEntity.ok(
                dispositivoService.listarPorCliente(clienteId)
        );
    }

    @PostMapping("/cliente/{clienteId}")
    public ResponseEntity<DispositivoResponseDTO> salvar(
            @PathVariable Long clienteId,
            @Valid @RequestBody Dispositivo dispositivo) {

        return ResponseEntity.ok(
                dispositivoService.salvar(
                        clienteId,
                        dispositivo
                )
        );
    }

    @PutMapping("/{id}/cliente/{clienteId}")
    public ResponseEntity<DispositivoResponseDTO> atualizar(
            @PathVariable Long id,
            @PathVariable Long clienteId,
            @Valid @RequestBody Dispositivo dispositivo) {

        return ResponseEntity.ok(
                dispositivoService.atualizar(
                        id,
                        clienteId,
                        dispositivo
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        dispositivoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}