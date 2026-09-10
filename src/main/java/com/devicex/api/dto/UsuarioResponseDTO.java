package com.devicex.api.dto;

import com.devicex.api.model.Perfil;

public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private Perfil perfil;
    private boolean ativo;

    public UsuarioResponseDTO(
            Long id,
            String nome,
            String email,
            Perfil perfil,
            boolean ativo) {

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.perfil = perfil;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public boolean isAtivo() {
        return ativo;
    }
}