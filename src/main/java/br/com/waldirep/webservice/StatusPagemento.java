package br.com.waldirep.webservice;

public enum StatusPagemento {
	
	
	Autorizado("Autorizado"),
	NaoAutorizado("N�o autorizado pela institui��o financeira"),
	CartaoInvalido("Cart�o inv�lido ou bloqueado");
	
	String descricao;
	
	
	
	private StatusPagemento(String descricao) {
		this.descricao = descricao;
	}
	
	
	public String getDescricao() {
		return descricao;
	}

}
