package com.devicex.api.dto;

public class DispositivoResponseDTO {

    private Long id;
    private String tipo;
    private String marca;
    private String modelo;
    private String numeroSerie;
    private String descricao;
    private Long clienteId;
    private String clienteNome;

    public DispositivoResponseDTO(
            Long id,
            String tipo,
            String marca,
            String modelo,
            String numeroSerie,
            String descricao,
            Long clienteId,
            String clienteNome) {

        this.id = id;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.descricao = descricao;
        this.clienteId = clienteId;
        this.clienteNome = clienteNome;
    }

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public String getClienteNome() {
        return clienteNome;
    }
}