package com.devicex.api.controller;

import com.devicex.api.dto.UsuarioResponseDTO;
import com.devicex.api.model.Usuario;
import com.devicex.api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(
            @Valid @RequestBody Usuario usuario) {

        return ResponseEntity.ok(
                usuarioService.criarUsuario(usuario)
        );
    }
}