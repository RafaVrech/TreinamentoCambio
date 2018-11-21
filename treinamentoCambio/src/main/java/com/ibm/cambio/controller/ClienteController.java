package com.ibm.cambio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.cambio.model.Cliente;
import com.ibm.cambio.service.ClienteService;
import com.ibm.cambio.view.ClienteView;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) 
    {
        this.clienteService = clienteService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> buscaCliente(@PathVariable Long id) 
    {
        return ResponseEntity.ok(new Resposta(0, "", clienteService.buscarCliente(id)));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<Object> buscaTodosCliente(
							@RequestParam(value = "filtro", required = false) String filtro) 
    {
        return ResponseEntity.ok(new Resposta(0, "", clienteService.buscarTodosCliente(filtro)));
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> novoCliente(@RequestBody ClienteView clienteView) 
    {
        return ResponseEntity.ok(new Resposta(0, "", clienteService.salvarCliente(clienteView)));
    }

    @RequestMapping(value = "/update", method = RequestMethod.PATCH)
    public ResponseEntity<Object> updateCliente(@RequestBody Cliente cliente) 
    {
        return ResponseEntity.ok(new Resposta(0, "", clienteService.atualizarCliente(cliente)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteCliente(@PathVariable Long id) 
    {
       clienteService.deletarCliente(id);
       return ResponseEntity.ok(new Resposta(0, "", "Cliente removido com sucesso!")); 
    }
}
