package com.devicex.api.controller;

import com.devicex.api.dto.HistoricoStatusResponseDTO;
import com.devicex.api.dto.OrdemServicoResponseDTO;
import com.devicex.api.model.OrdemServico;
import com.devicex.api.model.StatusOrdemServico;
import com.devicex.api.service.OrdemServicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService;

    public OrdemServicoController(
            OrdemServicoService ordemServicoService) {

        this.ordemServicoService = ordemServicoService;
    }

    @GetMapping
    public ResponseEntity<List<OrdemServicoResponseDTO>> listarTodas() {

        return ResponseEntity.ok(
                ordemServicoService.listarTodas()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServicoResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ordemServicoService.buscarPorId(id)
        );
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<OrdemServicoResponseDTO>> listarPorCliente(
            @PathVariable Long clienteId) {

        return ResponseEntity.ok(
                ordemServicoService.listarPorCliente(clienteId)
        );
    }

    @GetMapping("/dispositivo/{dispositivoId}")
    public ResponseEntity<List<OrdemServicoResponseDTO>> listarPorDispositivo(
            @PathVariable Long dispositivoId) {

        return ResponseEntity.ok(
                ordemServicoService.listarPorDispositivo(dispositivoId)
        );
    }

    @GetMapping("/{id}/historico")
    public ResponseEntity<List<HistoricoStatusResponseDTO>> listarHistorico(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ordemServicoService.listarHistorico(id)
        );
    }

    @PostMapping("/cliente/{clienteId}/dispositivo/{dispositivoId}")
    public ResponseEntity<OrdemServicoResponseDTO> salvar(
            @PathVariable Long clienteId,
            @PathVariable Long dispositivoId,
            @Valid @RequestBody OrdemServico ordemServico) {

        return ResponseEntity.ok(
                ordemServicoService.salvar(
                        clienteId,
                        dispositivoId,
                        ordemServico
                )
        );
    }

    @PutMapping("/{id}/cliente/{clienteId}/dispositivo/{dispositivoId}")
    public ResponseEntity<OrdemServicoResponseDTO> atualizar(
            @PathVariable Long id,
            @PathVariable Long clienteId,
            @PathVariable Long dispositivoId,
            @Valid @RequestBody OrdemServico ordemServico) {

        return ResponseEntity.ok(
                ordemServicoService.atualizar(
                        id,
                        clienteId,
                        dispositivoId,
                        ordemServico
                )
        );
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrdemServicoResponseDTO> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusOrdemServico status) {

        return ResponseEntity.ok(
                ordemServicoService.atualizarStatus(
                        id,
                        status
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        ordemServicoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}