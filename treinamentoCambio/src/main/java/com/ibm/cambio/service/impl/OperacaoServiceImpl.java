package com.ibm.cambio.service.impl;
import com.ibm.cambio.model.Conta;
import com.ibm.cambio.service.OperacaoService;

public class OperacaoServiceImpl implements OperacaoService {
	
	public OperacaoServiceImpl() {
		
	}
	
	@Override
	public Conta Sacar(Conta conta, Double valor) {
		
	
		if(conta.getSaldo() < valor)
	
		return Conta conta.getSaldo();
	}

	@Override
	public Conta Depositar(Conta conta, Double valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Conta Transferir(Conta origem, Conta destino, Double valor) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	


}
