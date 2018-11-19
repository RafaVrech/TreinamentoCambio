package com.ibm.cambio.model;

public enum Moeda {
	REAL(1), DOLAR(2), EURO(3);
	
	private final int valor;
	Moeda(int valorOpcao){
	    valor = valorOpcao;
	}
	public int getValor(){
	    return valor;
	}
}
