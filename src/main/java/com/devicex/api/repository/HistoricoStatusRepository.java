package com.devicex.api.repository;

import com.devicex.api.model.HistoricoStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoricoStatusRepository
        extends JpaRepository<HistoricoStatus, Long> {

    List<HistoricoStatus> findByOrdemServicoIdOrderByDataAlteracaoAsc(
            Long ordemServicoId
    );
}