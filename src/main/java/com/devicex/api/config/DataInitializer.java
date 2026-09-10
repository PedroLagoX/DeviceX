package com.devicex.api.config;

import com.devicex.api.model.Perfil;
import com.devicex.api.model.Usuario;
import com.devicex.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner criarAdministradorInicial(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            @Value("${devicex.admin.email}") String email,
            @Value("${devicex.admin.password}") String senha) {

        return args -> {

            if (!usuarioRepository.existsByEmail(email)) {

                Usuario admin = new Usuario();

                admin.setNome("Administrador");
                admin.setEmail(email);
                admin.setSenha(passwordEncoder.encode(senha));
                admin.setPerfil(Perfil.ADMIN);
                admin.setAtivo(true);

                usuarioRepository.save(admin);

                System.out.println(
                        "Usuário administrador inicial criado: " + email
                );
            }
        };
    }
}