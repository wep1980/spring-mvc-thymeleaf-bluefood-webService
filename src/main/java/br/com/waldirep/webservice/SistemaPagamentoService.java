package br.com.waldirep.webservice;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;



/**
 * Existem 2 formas de os dados trafegarem entre cliente e servidor, normalmente e utilizado o formato JSON(Text) que trafega sobre o HTTP.
 * Existe tb o XML que e pouco utilizado.
 * @author wepbi
 *
 */
@RestController // Controller via webService, acessivel via REST, opera��es HTTP
public class SistemaPagamentoService {
	
	
	// TOKEN FIXO
	private static final String AUTH_TOKEN = "r2d2";
	
	
	/**
	 * A integra��o com web services normalmente e passado um token que serve para autenticar voce, o web service precisa saber com quem ele esta conversando.
	 * Esse token normalmente e passado no Header do protocolo, n�o no corpo do protocolo.
	 * 
	 * @RequestHeader("Token") String token) -> extrai o token(valor) do header da requisi��o e armazena na variavel token.
	 * Os dados do cartao v�o vir dentro do protocolo(corpo) no formato JSON.
	 * @ResponseBody DadosCartao dadosCartao -> Os dados s�o extraidos de dentro do corpo do protocolo.
	 * @Valid -> Faz a valida��o dos dados do cart�o
	 * 
	 * @PostMapping(path = "/pay") -> Requisi��o do tipo POST
	 * consumes = MediaType.APPLICATION_JSON_VALUE -> O tipo de dado recebido e o JSON
	 * produces = MediaType.APPLICATION_JSON_VALUE -> O tipo de dado enviado
	 * 
	 * NOSSO TOKEN E FIXO.
	 * 
	 * REGRAS : Se o numero do cartao começar com 4 numeros um(1111)
	 * 
	 * @param token
	 * @return
	 */
	@PostMapping(path = "/pay", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaymentResponse> pagar(
			@RequestHeader("Token") String token,
			@Valid @RequestBody DadosCartao dadosCartao,
			Errors erros){
		
		if(!AUTH_TOKEN.equals(token)) {
			return ResponseEntity.badRequest().body(new PaymentResponse("Token inválido"));
		}
		
		if(erros.hasErrors()) {
			return ResponseEntity.ok(new PaymentResponse(StatusPagemento.CartaoInvalido)); // E um responseEntity de OK mas avisando sobre o erro do cart�o
		}
		
		String numCartao = dadosCartao.getNumCartao();
		
		// Se o numero do cartao come�ar com 1111 o status do pagamento e autorizado, sen�o � n�o autorizado
		StatusPagemento status = numCartao.startsWith("1111") ? StatusPagemento.Autorizado : StatusPagemento.NaoAutorizado; 
		return ResponseEntity.ok(new PaymentResponse(status));
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
