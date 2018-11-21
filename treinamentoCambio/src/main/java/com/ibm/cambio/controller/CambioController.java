package com.ibm.cambio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.cambio.model.Moeda;
import com.ibm.cambio.service.CambioService;

@RestController
@RequestMapping("/cambio")
public class CambioController {
	
	private CambioService cambioService;
	
	@Autowired
	public CambioController(CambioService cambioService)
	{
		this.cambioService = cambioService;
	}
	
	@RequestMapping(value = "/consultar", method = RequestMethod.GET)
	public ResponseEntity<Object> transferir(
										@RequestParam("valor") 			Double valor, 
										@RequestParam("moedaOrigem") 	Moeda moedaOrigem,
										@RequestParam("moedaDestino") 	Moeda moedaDestino
									){
		return ResponseEntity.ok(new Resposta(0, "Sucesso", cambioService.converter(moedaOrigem, moedaDestino, valor)));
	}
	
	
	
}
