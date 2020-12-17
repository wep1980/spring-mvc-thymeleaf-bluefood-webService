package br.com.waldirep.webservice;

/**
 * Classe responsavel por enviar a resposta do webService.
 * 
 * O objeto vai enviar o status ou o erro
 * @author wepbi
 *
 */
public class PaymentResponse {
	
	
	private StatusPagemento status;
	
	private String error;
	
	
	/**
	 * Contrutor 1
	 * @param status
	 */
	public PaymentResponse(StatusPagemento status) {
		this.status = status;
	}
	
	
	/**
	 * Construtor 2
	 * @param error
	 */
	public PaymentResponse(String error) {
		this.error = error;
	}

	
	
	public StatusPagemento getStatus() {
		return status;
	}
	
	
	public String getError() {
		return error;
	}
}
