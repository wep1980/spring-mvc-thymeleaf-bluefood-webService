package br.com.waldirep.webservice;

import javax.validation.constraints.Pattern;

public class DadosCartao {
	
	
	@Pattern(regexp = "\\d{16}", message = "O n�mero do cart�o e inv�lido") // Valida��o do campo que so aceita 16 digitos(numeros) seguidos
	private String numCartao;
	
	
	
	
	public void setNumCartao(String numCartao) {
		this.numCartao = numCartao;
	}
	
	public String getNumCartao() {
		return numCartao;
	}

}
