package com.devicex.api.dto;

import com.devicex.api.model.StatusOrdemServico;

import java.time.LocalDateTime;

public class HistoricoStatusResponseDTO {

    private Long id;
    private StatusOrdemServico status;
    private LocalDateTime dataAlteracao;

    public HistoricoStatusResponseDTO(
            Long id,
            StatusOrdemServico status,
            LocalDateTime dataAlteracao) {

        this.id = id;
        this.status = status;
        this.dataAlteracao = dataAlteracao;
    }

    public Long getId() {
        return id;
    }

    public StatusOrdemServico getStatus() {
        return status;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }
}