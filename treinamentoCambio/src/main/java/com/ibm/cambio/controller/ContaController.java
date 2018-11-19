package com.ibm.cambio.controller;
import com.ibm.cambio.model.Conta;
import com.ibm.cambio.repository.ClienteRepository;
import com.ibm.cambio.repository.ContaRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conta")
public class ContaController {
    private ContaRepository contaRepository;
    private ClienteRepository clienteRepository;

    @Autowired
    public ContaController(ContaRepository contaRepository,
                           ClienteRepository clienteRepository) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Conta> buscaConta(@PathVariable Long id){
        Optional<Conta> contaOptional = contaRepository.findById(id);
        return contaOptional.isPresent() ? ResponseEntity.ok(contaOptional.get()) :
                ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Conta> novaConta(@RequestBody Conta conta){
        if (conta.getCliente() == null || conta.getCliente().getId() == null)
            return ResponseEntity.badRequest().build();
        if (!clienteRepository.existsById(conta.getCliente().getId()))
            return ResponseEntity.notFound().build();
        Conta savedConta = contaRepository.save(conta);
        return ResponseEntity.ok(savedConta);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Conta> updateConta(@RequestBody Conta conta) {
        if (conta == null || conta.getId() == null)
            return ResponseEntity.badRequest().build();
        if (!contaRepository.existsById(conta.getId()))
            return ResponseEntity.notFound().build();

        Conta contaUpdated = contaRepository.save(conta);
        return ResponseEntity.ok(contaUpdated);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteConta(@PathVariable Long id) {
        if (id == null)
            return ResponseEntity.badRequest().build();
        if (!contaRepository.existsById(id))
            return ResponseEntity.notFound().build();

        contaRepository.deleteById(id);
        return ResponseEntity.ok("Conta removida com sucesso!");
    }
}
