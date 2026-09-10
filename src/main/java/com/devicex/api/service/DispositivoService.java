package com.devicex.api.service;

import com.devicex.api.dto.DispositivoResponseDTO;
import com.devicex.api.exception.ClienteNotFoundException;
import com.devicex.api.exception.DispositivoNotFoundException;
import com.devicex.api.model.Cliente;
import com.devicex.api.model.Dispositivo;
import com.devicex.api.repository.ClienteRepository;
import com.devicex.api.repository.DispositivoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispositivoService {

    private final DispositivoRepository dispositivoRepository;
    private final ClienteRepository clienteRepository;

    public DispositivoService(
            DispositivoRepository dispositivoRepository,
            ClienteRepository clienteRepository) {

        this.dispositivoRepository = dispositivoRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<DispositivoResponseDTO> listarTodos() {

        return dispositivoRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public DispositivoResponseDTO buscarPorId(Long id) {

        Dispositivo dispositivo = buscarEntidadePorId(id);

        return converterParaDTO(dispositivo);
    }

    public List<DispositivoResponseDTO> listarPorCliente(
            Long clienteId) {

        if (!clienteRepository.existsById(clienteId)) {
            throw new ClienteNotFoundException(
                    "Cliente não encontrado"
            );
        }

        return dispositivoRepository
                .findByClienteId(clienteId)
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public DispositivoResponseDTO salvar(
            Long clienteId,
            Dispositivo dispositivo) {

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() ->
                        new ClienteNotFoundException(
                                "Cliente não encontrado"
                        ));

        dispositivo.setCliente(cliente);

        Dispositivo dispositivoSalvo =
                dispositivoRepository.save(dispositivo);

        return converterParaDTO(dispositivoSalvo);
    }

    public DispositivoResponseDTO atualizar(
            Long id,
            Long clienteId,
            Dispositivo dispositivo) {

        Dispositivo dispositivoExistente =
                buscarEntidadePorId(id);

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() ->
                        new ClienteNotFoundException(
                                "Cliente não encontrado"
                        ));

        dispositivoExistente.setTipo(dispositivo.getTipo());
        dispositivoExistente.setMarca(dispositivo.getMarca());
        dispositivoExistente.setModelo(dispositivo.getModelo());
        dispositivoExistente.setNumeroSerie(
                dispositivo.getNumeroSerie()
        );
        dispositivoExistente.setDescricao(
                dispositivo.getDescricao()
        );
        dispositivoExistente.setCliente(cliente);

        Dispositivo dispositivoAtualizado =
                dispositivoRepository.save(dispositivoExistente);

        return converterParaDTO(dispositivoAtualizado);
    }

    public void excluir(Long id) {

        Dispositivo dispositivo = buscarEntidadePorId(id);

        dispositivoRepository.delete(dispositivo);
    }

    private Dispositivo buscarEntidadePorId(Long id) {

        return dispositivoRepository.findById(id)
                .orElseThrow(() ->
                        new DispositivoNotFoundException(
                                "Dispositivo não encontrado"
                        ));
    }

    private DispositivoResponseDTO converterParaDTO(
            Dispositivo dispositivo) {

        return new DispositivoResponseDTO(
                dispositivo.getId(),
                dispositivo.getTipo(),
                dispositivo.getMarca(),
                dispositivo.getModelo(),
                dispositivo.getNumeroSerie(),
                dispositivo.getDescricao(),
                dispositivo.getCliente().getId(),
                dispositivo.getCliente().getNome()
        );
    }
}