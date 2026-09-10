package com.devicex.api.controller;

import com.devicex.api.dto.LoginRequestDTO;
import com.devicex.api.dto.LoginResponseDTO;
import com.devicex.api.model.Usuario;
import com.devicex.api.repository.UsuarioRepository;
import com.devicex.api.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager,
                          UsuarioRepository usuarioRepository,
                          JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO request) {

        var authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getSenha()
                );

        authenticationManager.authenticate(authenticationToken);

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new IllegalStateException("Usuário não encontrado"));

        String token = jwtService.gerarToken(usuario);

        LoginResponseDTO response = new LoginResponseDTO(
                token,
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPerfil()
        );

        return ResponseEntity.ok(response);
    }
}