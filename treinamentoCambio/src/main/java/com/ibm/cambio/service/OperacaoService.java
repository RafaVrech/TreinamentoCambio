package com.ibm.cambio.service;
import com.ibm.cambio.model.Moeda;
import com.ibm.cambio.view.ContaView;

public interface OperacaoService {
	ContaView sacar(Long idConta, Double valor, Moeda moeda);	
	ContaView depositar (Long idConta, Double valor, Moeda moeda);
	ContaView transferir (Long idContaOrigem, Long idContaDestino, Double valor, Moeda moeda);
}
