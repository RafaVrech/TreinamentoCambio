package com.ibm.cambio.service;
import com.ibm.cambio.model.Conta;

public interface OperacaoService {
	Conta Sacar (Conta conta, Double valor);
	Conta Depositar (Conta conta, Double valor);
	Conta Transferir (Conta origem, Conta destino, Double valor);	
}
