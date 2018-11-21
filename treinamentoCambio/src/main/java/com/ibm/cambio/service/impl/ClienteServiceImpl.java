package com.ibm.cambio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.cambio.exception.ObjetoNaoEncontradoException;
import com.ibm.cambio.exception.ParametroInvalidoException;
import com.ibm.cambio.model.Cliente;
import com.ibm.cambio.model.Conta;
import com.ibm.cambio.repository.ClienteRepository;
import com.ibm.cambio.service.ClienteService;
import com.ibm.cambio.view.ClienteView;

@Service
public class ClienteServiceImpl implements ClienteService {
    private ClienteRepository clienteRepository;
    private ObjectMapper mapper;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, ObjectMapper mapper){
        this.clienteRepository = clienteRepository;
        this.mapper = mapper;
    }

    @Override
    public Cliente buscarCliente(Long id) { //TODO Voltar a view
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
       
        return clienteOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Cliente de id \"" + id + "\" não encontrado"));
    }
    
    @Override
    public List<Cliente> buscarTodosCliente(String filtro) {
        if (filtro != null && !filtro.isEmpty())
            return clienteRepository.findAllByNomeContains(filtro);
        return clienteRepository.findAll();
    }

    @Override
    public ClienteView salvarCliente(ClienteView clienteView) {
    	if (clienteView.getContas() == null) 
    		throw new ParametroInvalidoException("O cliente não pode ser nulo");
    	
    	Cliente cliente = mapper.convertValue(clienteView, Cliente.class);
        for(Conta conta : cliente.getContas()) {
        	conta.setCliente(cliente);
        }
        //TODO Validar numero da conta pra nao deixar criar outra igual
        clienteRepository.save(cliente);
        return clienteView;
    }

    @Override
    public ClienteView atualizarCliente(Cliente cliente) {
        if (cliente == null || cliente.getId() == null)
            throw new ParametroInvalidoException("O cliente e seu ID são obrigatorios");
        if (!clienteRepository.existsById(cliente.getId()))
            throw new ObjetoNaoEncontradoException("Cliente não encontrado");
        //TODO Validar numero da conta pra nao deixar criar outra igual
        clienteRepository.save(cliente);
        return mapper.convertValue(cliente, ClienteView.class);
    }

    @Override
    public boolean deletarCliente(Long id) {
        if (id == null)
            throw new ParametroInvalidoException("ID a ser deletado inválido");
        if (!clienteRepository.existsById(id))
        	throw new ObjetoNaoEncontradoException("Cliente a ser deletado não encontrado");
        clienteRepository.deleteById(id);
        return true;
    }
}
