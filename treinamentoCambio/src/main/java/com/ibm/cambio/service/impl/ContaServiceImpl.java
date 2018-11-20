package com.ibm.cambio.service.impl;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.cambio.exception.ObjetoNaoEncontradoException;
import com.ibm.cambio.exception.ParametroInvalidoException;
import com.ibm.cambio.model.Conta;
import com.ibm.cambio.repository.ContaRepository;
import com.ibm.cambio.service.ContaService;

@Service
public class ContaServiceImpl implements ContaService {
	
	@Autowired
	private ContaRepository contaRepository;
	
    public ContaServiceImpl(){

    }
    @java.lang.Override
    public Conta buscarConta(Long id) {
    	Optional<Conta> contaOptional = contaRepository.findById(id);
        return contaOptional.orElseThrow(() ->
                new ObjetoNaoEncontradoException("Não foi possivel localizar a conta de id " + id));
    }
    @java.lang.Override
    public Conta salvarConta(Conta conta) {
    	return null;
    }

    @java.lang.Override
    public Conta atualizarConta(Conta conta) {
    	if (conta == null || conta.getId() == null)
            throw new RuntimeException("Id não encontrado");
        if (!contaRepository.existsById(conta.getId()))
            throw new RuntimeException();

        return contaRepository.save(conta);
    }

    @java.lang.Override
    public boolean deletarConta(Long id) {
    	if (id == null)
    		throw new ParametroInvalidoException("A conta e seu ID são obrigatorios");
        if (!contaRepository.existsById(id))
        	throw new ObjetoNaoEncontradoException("Conta não encontrada");
        contaRepository.deleteById(id);
        return true;
    }
}
