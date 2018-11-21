package com.ibm.cambio.model;

public enum Moeda {
	REAL(1), DOLAR(3.76), EURO(4.30);
	
	private final double valor;
	Moeda(double valorOpcao){
	    valor = valorOpcao;
	}
	public double getValor(){
	    return valor;
	}
}
