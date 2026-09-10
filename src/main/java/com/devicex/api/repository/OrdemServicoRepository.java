package com.devicex.api.repository;

import com.devicex.api.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdemServicoRepository
        extends JpaRepository<OrdemServico, Long> {

    List<OrdemServico> findByClienteId(Long clienteId);

    List<OrdemServico> findByDispositivoId(Long dispositivoId);
}