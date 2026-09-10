package com.devicex.api.service;

import com.devicex.api.dto.ClienteResponseDTO;
import com.devicex.api.exception.ClienteNotFoundException;
import com.devicex.api.exception.RegraNegocioException;
import com.devicex.api.model.Cliente;
import com.devicex.api.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteResponseDTO> listarTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public ClienteResponseDTO buscarPorId(Long id) {
        Cliente cliente = buscarEntidadePorId(id);

        return converterParaDTO(cliente);
    }

    public ClienteResponseDTO salvar(Cliente cliente) {

        if (clienteRepository.existsByCpf(cliente.getCpf())) {
            throw new RegraNegocioException(
                    "Já existe um cliente cadastrado com este CPF"
            );
        }

        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new RegraNegocioException(
                    "Já existe um cliente cadastrado com este e-mail"
            );
        }

        Cliente clienteSalvo = clienteRepository.save(cliente);

        return converterParaDTO(clienteSalvo);
    }

    public ClienteResponseDTO atualizar(Long id, Cliente cliente) {

        Cliente clienteExistente = buscarEntidadePorId(id);

        if (clienteRepository.existsByCpfAndIdNot(
                cliente.getCpf(), id)) {

            throw new RegraNegocioException(
                    "Já existe outro cliente cadastrado com este CPF"
            );
        }

        if (clienteRepository.existsByEmailAndIdNot(
                cliente.getEmail(), id)) {

            throw new RegraNegocioException(
                    "Já existe outro cliente cadastrado com este e-mail"
            );
        }

        clienteExistente.setNome(cliente.getNome());
        clienteExistente.setCpf(cliente.getCpf());
        clienteExistente.setTelefone(cliente.getTelefone());
        clienteExistente.setEmail(cliente.getEmail());
        clienteExistente.setEndereco(cliente.getEndereco());

        Cliente clienteAtualizado =
                clienteRepository.save(clienteExistente);

        return converterParaDTO(clienteAtualizado);
    }

    public void excluir(Long id) {

        Cliente cliente = buscarEntidadePorId(id);

        clienteRepository.delete(cliente);
    }

    private Cliente buscarEntidadePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() ->
                        new ClienteNotFoundException(
                                "Cliente não encontrado"
                        ));
    }

    private ClienteResponseDTO converterParaDTO(Cliente cliente) {

        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getTelefone(),
                cliente.getEmail(),
                cliente.getEndereco()
        );
    }
}