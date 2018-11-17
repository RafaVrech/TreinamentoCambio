package com.ibm.cambio.service;
import com.ibm.cambio.model.Conta;

public interface ContaService {
    Conta buscarConta (Long id);
    Conta salvarConta (Conta conta);
    Conta atualizarConta (Conta conta);
    boolean deletarConta (Long id);
}
