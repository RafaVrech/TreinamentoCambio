package com.ibm.cambio.view;

import lombok.Data;

@Data
public class ContaView {
	private Short agencia;
    private String numero;
    private Double saldo;
    private String tipoConta;
}
