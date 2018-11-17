package com.ibm.cambio.service;
import com.ibm.cambio.model.Cliente;

public interface ClienteService {
    Cliente buscarCliente (Long id);
    Cliente salvarCliente (Cliente cliente);
    Cliente atualizarCliente (Cliente cliente);
    boolean deletarCliente(Long id);
}
