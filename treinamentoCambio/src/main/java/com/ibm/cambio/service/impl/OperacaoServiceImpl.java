package com.ibm.cambio.service.impl;
import com.ibm.cambio.model.Conta;
import com.ibm.cambio.model.Moeda;
import com.ibm.cambio.service.OperacaoService;
import com.ibm.cambio.view.ContaView;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.cambio.exception.ObjetoNaoEncontradoException;
import com.ibm.cambio.exception.ParametroInvalidoException;
import com.ibm.cambio.repository.ContaRepository;

@Service
public class OperacaoServiceImpl implements OperacaoService {
	private ContaRepository contaRepository;
    private ObjectMapper mapper;
	
	@Autowired
	public OperacaoServiceImpl(ContaRepository contaRepository, ObjectMapper mapper) {
		this.contaRepository = contaRepository;
        this.mapper = mapper;
	}

	@Override
	public ContaView sacar(Long idConta, Double valor, Moeda moeda) {
		if(idConta == null) 
			throw new ParametroInvalidoException("O id da conta é obrigatorio");
			
		Optional<Conta> contaBanco = contaRepository.findById(idConta);
		if(!contaBanco.isPresent())
			throw new ObjetoNaoEncontradoException("Conta não encontrada");
		
		if(contaBanco.get().getSaldo() == null || contaBanco.get().getTipoMoeda() == null || contaBanco.get().getSaldo() < valor) 
			throw new RuntimeException("Seu saldo está abaixo de " + valor);
		
		Conta contaBancoSalvar = contaBanco.get();
		contaBancoSalvar.setSaldo(contaBanco.get().getSaldo().doubleValue() - moeda.converter(contaBanco.get().getTipoMoeda(), valor));

		contaRepository.save(contaBanco.get());
		
		return mapper.convertValue(contaBanco.get(), ContaView.class);
	}
	
	@Override
	public ContaView depositar(Long idConta, Double valor, Moeda moeda) {
		if (idConta == null) 
			throw new ParametroInvalidoException("O id da conta é obrigatorio");
		
		Optional<Conta> contaBanco = contaRepository.findById(idConta);
		if(!contaBanco.isPresent())
			throw new ObjetoNaoEncontradoException("Conta não encontrada");
		
		contaBanco.get().setSaldo(contaBanco.get().getSaldo().doubleValue() + moeda.converter(contaBanco.get().getTipoMoeda(), valor));
		
		contaRepository.save(contaBanco.get());
		
		return mapper.convertValue(contaBanco.get(), ContaView.class);
	}

	@Override
	public ContaView transferir(Long idContaOrigem, Long idContaDestino, Double valor, Moeda moeda) {
		if(idContaOrigem == null) 
			throw new ParametroInvalidoException("O ID da conta de origem é obrigatório!");
		
		if(idContaDestino == null) 
			throw new ParametroInvalidoException("O ID da conta de destino é obrigatório!");
		
		Optional<Conta> contaBancoOrigem = contaRepository.findById(idContaOrigem);
		Optional<Conta> contaBancoDestino = contaRepository.findById(idContaDestino);
		
		if(!contaBancoOrigem.isPresent())
			throw new ObjetoNaoEncontradoException("Conta origem não encontrada");
		if(!contaBancoDestino.isPresent())
			throw new ObjetoNaoEncontradoException("Conta destino não encontrada");
		
		if(contaBancoOrigem.get().getSaldo() == null || contaBancoDestino.get().getSaldo() == null)
			throw new ParametroInvalidoException("Preencha todos os campos da conta!");
		
		if(contaBancoOrigem.get().getSaldo() < valor) 
			throw new RuntimeException("Saldo insuficiente!");
		
		sacar(idContaOrigem, valor, moeda);
		depositar(idContaDestino, valor, moeda);
		
//		origem.setSaldo(origem.getSaldo()- moeda.converter(contaBancoOrigem.get().getTipoMoeda(), valor));
//		destino.setSaldo(destino.getSaldo()+ moeda.converter(contaBancoDestino.get().getTipoMoeda(), valor));
		
		contaRepository.save(contaBancoOrigem.get());
		contaRepository.save(contaBancoDestino.get()); 
		
		return mapper.convertValue(contaBancoDestino.get(), ContaView.class);//Retornando só o destino pra facilitar
	}
}

