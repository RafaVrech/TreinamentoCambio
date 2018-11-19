package com.ibm.cambio.view;

import java.util.List;

import lombok.Data;

@Data
public class ClienteView {
	private String nome;

    private List<ContaView> contas;
}
