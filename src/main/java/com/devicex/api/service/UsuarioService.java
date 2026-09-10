package com.devicex.api.service;

import com.devicex.api.dto.UsuarioResponseDTO;
import com.devicex.api.exception.RegraNegocioException;
import com.devicex.api.model.Usuario;
import com.devicex.api.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponseDTO criarUsuario(Usuario usuario) {

        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RegraNegocioException(
                    "Já existe um usuário cadastrado com este e-mail"
            );
        }

        usuario.setSenha(
                passwordEncoder.encode(usuario.getSenha())
        );

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return converterParaDTO(usuarioSalvo);
    }

    private UsuarioResponseDTO converterParaDTO(Usuario usuario) {

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPerfil(),
                usuario.isAtivo()
        );
    }
}