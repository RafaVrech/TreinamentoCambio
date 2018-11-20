package com.ibm.cambio.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ibm.cambio.exception.ParametroInvalidoException;
import com.ibm.cambio.model.Conta;
import com.ibm.cambio.repository.ContaRepository;
import com.ibm.cambio.service.OperacaoService;

@Service
public class OperacaoServiceImpl implements OperacaoService {
	private ContaRepository contaRepository;
	private ContaRepository contaRepository2;
	
	@Autowired
	public OperacaoServiceImpl(ContaRepository contaRepository) {
		this.contaRepository = contaRepository;
	}
	
	
	@Override
	public Conta Sacar(Conta conta, Double valor) {
        contaRepository.findById(conta.getId());
		if(conta.getId() == null || conta == null) {
			throw new ParametroInvalidoException("A conta e seu ID são obrigatorios");
		}
		if(conta.getSaldo() < valor) {
			throw new RuntimeException("Seu saldo está abaixo de " + valor);
		}else 
			conta.setSaldo(conta.getSaldo() - valor);
		return contaRepository.save(conta);
	}

	@Override
	public Conta Depositar(Conta conta, Double valor) {
		contaRepository.findById(conta.getId());
		if (conta.getId() == null || conta == null) {
			throw new ParametroInvalidoException("A conta e seu ID são obrigatorios");
		}else
			conta.setSaldo(conta.getSaldo() + valor);
		return contaRepository.save(conta);
	}

	@Override
	public Conta Transferir(Conta origem, Conta destino, Double valor) {
		contaRepository.findById(origem.getId());
		contaRepository2.findById(destino.getId()); //goHorse aqui... Não consigo armazenar duas variáveis no mesmo Repository
		if(origem.getId() == null || destino.getId() == null) {
			throw new ParametroInvalidoException("O ID da conta é obrigatório!");
		}

		else if(origem == null || destino == null) {
			throw new ParametroInvalidoException("Preencha todos os campos da conta!");
		}

		else if(origem.getSaldo() == null || destino.getSaldo() == null) {
			throw new ParametroInvalidoException("Preencha todos os campos da conta!");
		}
		
		else if(origem.getSaldo() < valor) {
			throw new RuntimeException("Saldo insuficiente!");
		}else 
			origem.setSaldo(origem.getSaldo()-valor);
			destino.setSaldo(destino.getSaldo()+valor);
			contaRepository.save(origem);
			
			return contaRepository2.save(destino); //GoHorse ao extremo... Preciso descobrir como retornar amabas as contas
	}

	@Override
	public Conta salvarConta(Conta conta) {
				return null;
	}

}
