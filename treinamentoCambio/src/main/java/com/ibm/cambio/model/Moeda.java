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
	
	public double converter(Moeda moeda, double valor)
	{
		if(this == REAL)
			return valor * moeda.getValor();
		
		return REAL.converter(moeda, valor / REAL.getValor());
	}
}
