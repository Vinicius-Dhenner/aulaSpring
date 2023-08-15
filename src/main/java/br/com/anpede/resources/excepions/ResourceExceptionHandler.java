package br.com.anpede.resources.excepions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.anpede.services.exceptions.EntityNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandartError> entityNotFound(EntityNotFoundException e, HttpServletRequest request){
		//Instanciamos uma mensagem de erro com base no padrão que criamos
		StandartError error = new StandartError();
		//Horário da ocorrencia
		error.setTimestamp(Instant.now());
		//Código HTTP que enviado, nesse caso 404.
		error.setStatus(HttpStatus.NOT_FOUND.value());
		//Titulo da exceção
		error.setError("Recurso não encontrado");
		//Captura da mensagem de erro interceptada e que foi definida na camada de servico 
		error.setMessage(e.getMessage());
		//Captura da URI que gerou a exceção
		error.setPath(request.getRequestURI());
		//Empacota a exceção no formato HTTP e entrega ao cliente
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
}
