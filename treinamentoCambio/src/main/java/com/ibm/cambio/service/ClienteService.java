package com.ibm.cambio.service;
import java.util.List;

import com.ibm.cambio.model.Cliente;
import com.ibm.cambio.view.ClienteView;

public interface ClienteService {
    Cliente buscarCliente (Long id);
    ClienteView salvarCliente (ClienteView cliente);
    ClienteView atualizarCliente (Cliente cliente);
    boolean deletarCliente(Long id);
	List<Cliente> buscarTodosCliente(String filtro);
}
