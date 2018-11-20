package com.ibm.cambio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ibm.cambio.model.Conta;
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
	
	@RequestMapping(value = "/saque", method = RequestMethod.PATCH)
	public ResponseEntity<Object> Sacar(@RequestBody Conta conta){
		return ResponseEntity.ok(new Resposta(0, "", operacaoService.salvarConta(conta)));
	}
	
	@RequestMapping(value = "/deposito", method = RequestMethod.PATCH)
	public ResponseEntity<Object> Depositar(@RequestBody Conta conta){
		return ResponseEntity.ok(new Resposta(0, "", operacaoService.salvarConta(conta)));
	}
	
	@RequestMapping(value = "/transferir", method = RequestMethod.PATCH)
	public ResponseEntity<Object> Transferir(@RequestBody Conta origem, Conta destino){
		return ResponseEntity.ok(new Resposta(0, "Sucesso", destino));
	}
	
	
	
}
