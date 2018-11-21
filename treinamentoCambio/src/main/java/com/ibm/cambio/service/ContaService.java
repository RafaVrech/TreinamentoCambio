package com.ibm.cambio.service;
import java.util.List;

import com.ibm.cambio.model.Conta;
import com.ibm.cambio.view.ContaView;

public interface ContaService {
    Conta buscarConta (Long id);
    ContaView salvarConta (Conta conta);
    ContaView atualizarConta (Conta conta);
    boolean deletarConta (Long id);
    List<Conta> buscarTodasConta(String filtro);
}
