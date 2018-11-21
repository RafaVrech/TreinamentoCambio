package com.ibm.cambio.service;
import com.ibm.cambio.model.Moeda;

public interface CambioService {
	Double converter(Moeda moedaOrigem, Moeda moedaDestino, Double valor);
}
