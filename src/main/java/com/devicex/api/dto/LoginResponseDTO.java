package com.devicex.api.dto;

import com.devicex.api.model.Perfil;

public class LoginResponseDTO {

    private String token;
    private String tipo;
    private Long usuarioId;
    private String nome;
    private String email;
    private Perfil perfil;

    public LoginResponseDTO(String token, Long usuarioId, String nome,
                            String email, Perfil perfil) {
        this.token = token;
        this.tipo = "Bearer";
        this.usuarioId = usuarioId;
        this.nome = nome;
        this.email = email;
        this.perfil = perfil;
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }

    public Long getUsuarioId() {
        return usuarioId;
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
}