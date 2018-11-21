package com.ibm.cambio.service.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.cambio.exception.ObjetoNaoEncontradoException;
import com.ibm.cambio.exception.ParametroInvalidoException;
import com.ibm.cambio.model.Conta;
import com.ibm.cambio.repository.ClienteRepository;
import com.ibm.cambio.repository.ContaRepository;
import com.ibm.cambio.service.ContaService;
import com.ibm.cambio.view.ContaView;

@Service
public class ContaServiceImpl implements ContaService {

	private ContaRepository contaRepository;
	private ClienteRepository clienteRepository;
    private ObjectMapper mapper;

    @Autowired
    public ContaServiceImpl(ContaRepository contaRepository, ClienteRepository clienteRepository, ObjectMapper mapper){
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
        this.mapper = mapper;
    }

    @Override
    public Conta buscarConta(Long id) { //TODO Voltar a view
        Optional<Conta> contaOptional = contaRepository.findById(id);
       
        return contaOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Conta de id \"" + id + "\" não encontrada"));
    }
    
    @Override
    public List<Conta> buscarTodasConta(String filtro) {
        if (filtro != null && !filtro.isEmpty())
            return contaRepository.findAllByNumeroContains(filtro);
        return contaRepository.findAll();
    }

    @Override
    public ContaView salvarConta(Conta conta) {
    	if (conta.getCliente() == null) 
    		throw new ParametroInvalidoException("O cliente não pode ser nulo");
    	if(!clienteRepository.existsById(conta.getCliente().getId()))
			throw new ObjetoNaoEncontradoException("O cliente da conta não existe");
    	
        //TODO Validar numero da conta pra nao deixar criar outra igual
    	
        contaRepository.save(conta);
        return mapper.convertValue(conta, ContaView.class);
    }

    @Override
    public ContaView atualizarConta(Conta conta) {
        if (conta == null || conta.getId() == null)
            throw new ParametroInvalidoException("A conta e seu ID são obrigatorios");
        
        Optional<Conta> retornoBanco = contaRepository.findById(conta.getId());
        if (!retornoBanco.isPresent())
            throw new ObjetoNaoEncontradoException("Cliente não encontrado");
        
        Conta contaBanco = retornoBanco.get();
        if(contaBanco.getTipoMoeda() != conta.getTipoMoeda())
        	conta.setSaldo(contaBanco.getTipoMoeda().converter(conta.getTipoMoeda(), contaBanco.getSaldo()));
        	
        //TODO Validar numero da conta pra nao deixar criar outra igual
        
        contaRepository.save(conta);
        return mapper.convertValue(conta, ContaView.class);
    }

    @Override
    public boolean deletarConta(Long id) {
        if (id == null)
            throw new ParametroInvalidoException("ID a ser deletado inválido");
        if (!contaRepository.existsById(id))
        	throw new ObjetoNaoEncontradoException("Conta a ser deletado não encontrado");
        contaRepository.deleteById(id);
        return true;
    }
}
