package com.devicex.api.dto;

public class ClienteResponseDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String endereco;

    public ClienteResponseDTO(
            Long id,
            String nome,
            String cpf,
            String telefone,
            String email,
            String endereco) {

        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }
}