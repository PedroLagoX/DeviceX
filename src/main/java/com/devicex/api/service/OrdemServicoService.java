package com.devicex.api.service;

import com.devicex.api.dto.HistoricoStatusResponseDTO;
import com.devicex.api.dto.OrdemServicoResponseDTO;
import com.devicex.api.exception.ClienteNotFoundException;
import com.devicex.api.exception.DispositivoNotFoundException;
import com.devicex.api.exception.OrdemServicoNotFoundException;
import com.devicex.api.exception.RegraNegocioException;
import com.devicex.api.model.Cliente;
import com.devicex.api.model.Dispositivo;
import com.devicex.api.model.HistoricoStatus;
import com.devicex.api.model.OrdemServico;
import com.devicex.api.model.StatusOrdemServico;
import com.devicex.api.repository.ClienteRepository;
import com.devicex.api.repository.DispositivoRepository;
import com.devicex.api.repository.HistoricoStatusRepository;
import com.devicex.api.repository.OrdemServicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrdemServicoService {

    private final OrdemServicoRepository ordemServicoRepository;
    private final HistoricoStatusRepository historicoStatusRepository;
    private final ClienteRepository clienteRepository;
    private final DispositivoRepository dispositivoRepository;

    public OrdemServicoService(
            OrdemServicoRepository ordemServicoRepository,
            HistoricoStatusRepository historicoStatusRepository,
            ClienteRepository clienteRepository,
            DispositivoRepository dispositivoRepository) {

        this.ordemServicoRepository = ordemServicoRepository;
        this.historicoStatusRepository = historicoStatusRepository;
        this.clienteRepository = clienteRepository;
        this.dispositivoRepository = dispositivoRepository;
    }

    public List<OrdemServicoResponseDTO> listarTodas() {

        return ordemServicoRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public OrdemServicoResponseDTO buscarPorId(Long id) {

        OrdemServico ordemServico = buscarEntidadePorId(id);

        return converterParaDTO(ordemServico);
    }

    public List<OrdemServicoResponseDTO> listarPorCliente(
            Long clienteId) {

        if (!clienteRepository.existsById(clienteId)) {
            throw new ClienteNotFoundException(
                    "Cliente não encontrado"
            );
        }

        return ordemServicoRepository
                .findByClienteId(clienteId)
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public List<OrdemServicoResponseDTO> listarPorDispositivo(
            Long dispositivoId) {

        if (!dispositivoRepository.existsById(dispositivoId)) {
            throw new DispositivoNotFoundException(
                    "Dispositivo não encontrado"
            );
        }

        return ordemServicoRepository
                .findByDispositivoId(dispositivoId)
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    @Transactional
    public OrdemServicoResponseDTO salvar(
            Long clienteId,
            Long dispositivoId,
            OrdemServico ordemServico) {

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() ->
                        new ClienteNotFoundException(
                                "Cliente não encontrado"
                        ));

        Dispositivo dispositivo =
                dispositivoRepository.findById(dispositivoId)
                        .orElseThrow(() ->
                                new DispositivoNotFoundException(
                                        "Dispositivo não encontrado"
                                ));

        if (!dispositivo.getCliente().getId().equals(clienteId)) {
            throw new RegraNegocioException(
                    "O dispositivo não pertence ao cliente informado"
            );
        }

        ordemServico.setCliente(cliente);
        ordemServico.setDispositivo(dispositivo);
        ordemServico.setStatus(StatusOrdemServico.RECEBIDO);
        ordemServico.setDataEntrada(LocalDateTime.now());

        OrdemServico ordemSalva =
                ordemServicoRepository.save(ordemServico);

        registrarHistorico(
                ordemSalva,
                StatusOrdemServico.RECEBIDO
        );

        return converterParaDTO(ordemSalva);
    }

    @Transactional
    public OrdemServicoResponseDTO atualizar(
            Long id,
            Long clienteId,
            Long dispositivoId,
            OrdemServico ordemServico) {

        OrdemServico ordemExistente =
                buscarEntidadePorId(id);

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() ->
                        new ClienteNotFoundException(
                                "Cliente não encontrado"
                        ));

        Dispositivo dispositivo =
                dispositivoRepository.findById(dispositivoId)
                        .orElseThrow(() ->
                                new DispositivoNotFoundException(
                                        "Dispositivo não encontrado"
                                ));

        if (!dispositivo.getCliente().getId().equals(clienteId)) {
            throw new RegraNegocioException(
                    "O dispositivo não pertence ao cliente informado"
            );
        }

        ordemExistente.setCliente(cliente);
        ordemExistente.setDispositivo(dispositivo);
        ordemExistente.setDescricaoProblema(
                ordemServico.getDescricaoProblema()
        );
        ordemExistente.setDiagnostico(
                ordemServico.getDiagnostico()
        );
        ordemExistente.setServicoRealizado(
                ordemServico.getServicoRealizado()
        );
        ordemExistente.setValor(
                ordemServico.getValor()
        );
        ordemExistente.setObservacoes(
                ordemServico.getObservacoes()
        );

        OrdemServico ordemAtualizada =
                ordemServicoRepository.save(ordemExistente);

        return converterParaDTO(ordemAtualizada);
    }

    @Transactional
    public OrdemServicoResponseDTO atualizarStatus(
            Long id,
            StatusOrdemServico novoStatus) {

        OrdemServico ordemServico =
                buscarEntidadePorId(id);

        if (ordemServico.getStatus() == novoStatus) {
            throw new RegraNegocioException(
                    "A ordem de serviço já possui este status"
            );
        }

        ordemServico.setStatus(novoStatus);

        if (novoStatus == StatusOrdemServico.ENTREGUE
                || novoStatus == StatusOrdemServico.CANCELADO
                || novoStatus == StatusOrdemServico.REPROVADO) {

            ordemServico.setDataConclusao(
                    LocalDateTime.now()
            );

        } else {
            ordemServico.setDataConclusao(null);
        }

        OrdemServico ordemAtualizada =
                ordemServicoRepository.save(ordemServico);

        registrarHistorico(
                ordemAtualizada,
                novoStatus
        );

        return converterParaDTO(ordemAtualizada);
    }

    public List<HistoricoStatusResponseDTO> listarHistorico(
            Long ordemServicoId) {

        if (!ordemServicoRepository.existsById(ordemServicoId)) {
            throw new OrdemServicoNotFoundException(
                    "Ordem de serviço não encontrada"
            );
        }

        return historicoStatusRepository
                .findByOrdemServicoIdOrderByDataAlteracaoAsc(
                        ordemServicoId
                )
                .stream()
                .map(this::converterHistoricoParaDTO)
                .toList();
    }

    @Transactional
    public void excluir(Long id) {

        OrdemServico ordemServico =
                buscarEntidadePorId(id);

        historicoStatusRepository.deleteAll(
                historicoStatusRepository
                        .findByOrdemServicoIdOrderByDataAlteracaoAsc(id)
        );

        ordemServicoRepository.delete(ordemServico);
    }

    private OrdemServico buscarEntidadePorId(Long id) {

        return ordemServicoRepository.findById(id)
                .orElseThrow(() ->
                        new OrdemServicoNotFoundException(
                                "Ordem de serviço não encontrada"
                        ));
    }

    private OrdemServicoResponseDTO converterParaDTO(
            OrdemServico ordemServico) {

        return new OrdemServicoResponseDTO(
                ordemServico.getId(),
                ordemServico.getCliente().getId(),
                ordemServico.getCliente().getNome(),
                ordemServico.getDispositivo().getId(),
                ordemServico.getDispositivo().getMarca()
                        + " "
                        + ordemServico.getDispositivo().getModelo(),
                ordemServico.getDescricaoProblema(),
                ordemServico.getDiagnostico(),
                ordemServico.getServicoRealizado(),
                ordemServico.getValor(),
                ordemServico.getStatus(),
                ordemServico.getDataEntrada(),
                ordemServico.getDataConclusao(),
                ordemServico.getObservacoes()
        );
    }

    private HistoricoStatusResponseDTO converterHistoricoParaDTO(
            HistoricoStatus historico) {

        return new HistoricoStatusResponseDTO(
                historico.getId(),
                historico.getStatus(),
                historico.getDataAlteracao()
        );
    }

    private void registrarHistorico(
            OrdemServico ordemServico,
            StatusOrdemServico status) {

        HistoricoStatus historico =
                new HistoricoStatus();

        historico.setOrdemServico(ordemServico);
        historico.setStatus(status);
        historico.setDataAlteracao(
                LocalDateTime.now()
        );

        historicoStatusRepository.save(historico);
    }
}