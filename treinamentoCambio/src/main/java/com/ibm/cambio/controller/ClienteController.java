package com.ibm.cambio.controller;

import com.ibm.cambio.exception.ObjetoNaoEncontradoException;
import com.ibm.cambio.model.Cliente;
import com.ibm.cambio.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> buscaCliente(@PathVariable Long id) {
        try {
            Cliente cliente = clienteService.buscarCliente(id);
            return ResponseEntity.ok(new Resposta(0, "", cliente));
        } catch (ObjetoNaoEncontradoException e) {
            return ResponseEntity.badRequest().body(new Resposta(e.getCode(), e.getMessage(), null)); }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> novoCliente (@RequestBody Cliente cliente) {
        try {
            return ResponseEntity.ok(clienteService.salvarCliente(cliente));
        } catch (RuntimeException re) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCliente(@RequestBody Cliente cliente) {
        try {
            return ResponseEntity.ok(clienteService.atualizarCliente(cliente));
        } catch (RuntimeException re) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCliente(@PathVariable Long id) {
        try {
            clienteService.deletarCliente(id);
            return ResponseEntity.ok("Cliente removido com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
