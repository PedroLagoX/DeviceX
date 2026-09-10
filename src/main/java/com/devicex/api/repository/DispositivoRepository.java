package com.devicex.api.repository;

import com.devicex.api.model.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {

    List<Dispositivo> findByClienteId(Long clienteId);
}