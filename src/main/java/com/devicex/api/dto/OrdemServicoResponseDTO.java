package com.devicex.api.dto;

import com.devicex.api.model.StatusOrdemServico;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrdemServicoResponseDTO {

    private Long id;

    private Long clienteId;
    private String clienteNome;

    private Long dispositivoId;
    private String dispositivoDescricao;

    private String descricaoProblema;
    private String diagnostico;
    private String servicoRealizado;
    private BigDecimal valor;
    private StatusOrdemServico status;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataConclusao;
    private String observacoes;

    public OrdemServicoResponseDTO(
            Long id,
            Long clienteId,
            String clienteNome,
            Long dispositivoId,
            String dispositivoDescricao,
            String descricaoProblema,
            String diagnostico,
            String servicoRealizado,
            BigDecimal valor,
            StatusOrdemServico status,
            LocalDateTime dataEntrada,
            LocalDateTime dataConclusao,
            String observacoes) {

        this.id = id;
        this.clienteId = clienteId;
        this.clienteNome = clienteNome;
        this.dispositivoId = dispositivoId;
        this.dispositivoDescricao = dispositivoDescricao;
        this.descricaoProblema = descricaoProblema;
        this.diagnostico = diagnostico;
        this.servicoRealizado = servicoRealizado;
        this.valor = valor;
        this.status = status;
        this.dataEntrada = dataEntrada;
        this.dataConclusao = dataConclusao;
        this.observacoes = observacoes;
    }

    public Long getId() {
        return id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public Long getDispositivoId() {
        return dispositivoId;
    }

    public String getDispositivoDescricao() {
        return dispositivoDescricao;
    }

    public String getDescricaoProblema() {
        return descricaoProblema;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getServicoRealizado() {
        return servicoRealizado;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public StatusOrdemServico getStatus() {
        return status;
    }

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }

    public String getObservacoes() {
        return observacoes;
    }
}