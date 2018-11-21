package com.ibm.cambio.service.impl;
import org.springframework.stereotype.Service;

import com.ibm.cambio.model.Moeda;
import com.ibm.cambio.service.CambioService;

@Service
public class CambioServiceImpl implements CambioService {

	@Override
	public Double converter(Moeda moedaOrigem, Moeda moedaDestino, Double valor) {
		return moedaOrigem.converter(moedaDestino, valor);
	}
}

