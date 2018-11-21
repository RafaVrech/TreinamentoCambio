package com.ibm.cambio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.cambio.model.Moeda;
import com.ibm.cambio.service.OperacaoService;

@RestController
@RequestMapping("/operacoes")
public class OperacaoController {
	
	private OperacaoService operacaoService;
	
	@Autowired
	public OperacaoController(OperacaoService operacaoService)
	{
		this.operacaoService = operacaoService;
	}
	
	@RequestMapping(value = "/saque/{idConta}", method = RequestMethod.PATCH)
	public ResponseEntity<Object> sacar(@PathVariable("idConta") Long idConta, @RequestParam("valor") Double valor, @RequestParam("moeda") Moeda moeda){
		return ResponseEntity.ok(new Resposta(0, "", operacaoService.sacar(idConta, valor, moeda)));
	}
	
	@RequestMapping(value = "/deposito/{idConta}", method = RequestMethod.PATCH)
	public ResponseEntity<Object> depositar(@PathVariable Long idConta, @RequestParam("valor") Double valor, @RequestParam("moeda") Moeda moeda){
		return ResponseEntity.ok(new Resposta(0, "", operacaoService.depositar(idConta, valor, moeda)));
	}
	
	@RequestMapping(value = "/transferencia/{idContaOrigem}/{idContaDestino}", method = RequestMethod.PATCH)
	public ResponseEntity<Object> transferir(
										@PathVariable("idContaOrigem") 	Long idContaOrigem, 
										@PathVariable("idContaDestino") Long idContaDestino, 
										@RequestParam("valor") 			Double valor, 
										@RequestParam("moeda") 			Moeda moeda
									){
		return ResponseEntity.ok(new Resposta(0, "Sucesso", operacaoService.transferir(idContaOrigem, idContaDestino, valor, moeda)));
	}
	
	
	
}
