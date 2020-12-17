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
@RestController // Controller via webService, acessivel via REST, operações HTTP
public class SistemaPagamentoService {
	
	
	// TOKEN FIXO
	private static final String AUTH_TOKEN = "r2d2";
	
	
	/**
	 * A integração com web services normalmente e passado um token que serve para autenticar voce, o web service precisa saber com quem ele esta conversando.
	 * Esse token normalmente e passado no Header do protocolo, não no corpo do protocolo.
	 * 
	 * @RequestHeader("Token") String token) -> extrai o token(valor) do header da requisição e armazena na variavel token.
	 * Os dados do cartao vão vir dentro do protocolo(corpo) no formato JSON.
	 * @ResponseBody DadosCartao dadosCartao -> Os dados são extraidos de dentro do corpo do protocolo.
	 * @Valid -> Faz a validação dos dados do cartão
	 * 
	 * @PostMapping(path = "/pay") -> Requisição do tipo POST
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
			return ResponseEntity.ok(new PaymentResponse(StatusPagemento.CartaoInvalido)); // E um responseEntity de OK mas avisando sobre o erro do cartão
		}
		
		String numCartao = dadosCartao.getNumCartao();
		
		// Se o numero do cartao começar com 1111 o status do pagamento e autorizado, senão é não autorizado
		StatusPagemento status = numCartao.startsWith("1111") ? StatusPagemento.Autorizado : StatusPagemento.NaoAutorizado; 
		return ResponseEntity.ok(new PaymentResponse(status));
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
