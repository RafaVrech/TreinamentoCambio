package com.ibm.cambio.service.impl;

import com.ibm.cambio.exception.ObjetoNaoEncontradoException;
import com.ibm.cambio.model.Cliente;
import com.ibm.cambio.model.Conta;
import com.ibm.cambio.repository.ClienteRepository;
import com.ibm.cambio.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ClienteServiceImpl implements ClienteService {
    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    @java.lang.Override
    public Cliente buscarCliente(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        return clienteOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Cliente de id" + id + "não encontrado"));
    }

    @java.lang.Override
    public Cliente salvarCliente(Cliente cliente) {
        if (cliente.getContas() != null) {
            for(Conta conta : cliente.getContas()) {
                conta.setCliente(cliente);
            }
        }
        return clienteRepository.save(cliente);
    }

    @java.lang.Override
    public Cliente atualizarCliente(Cliente cliente) {
        if (cliente == null || cliente.getId() == null)
            throw new RuntimeException("Id não encontrado");
        if (!clienteRepository.existsById(cliente.getId()))
            throw new RuntimeException();

        return clienteRepository.save(cliente);
    }

    @java.lang.Override
    public boolean deletarCliente(Long id) {
        if (id == null)
            return false;
        if (!clienteRepository.existsById(id))
            return false;
        clienteRepository.deleteById(id);
        return true;
    }
}
