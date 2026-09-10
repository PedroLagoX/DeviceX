package com.devicex.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> tratarErrosDeValidacao(
            MethodArgumentNotValidException exception) {

        Map<String, String> erros = new LinkedHashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(erro ->
                        erros.put(
                                erro.getField(),
                                erro.getDefaultMessage()
                        )
                );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erros);
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<Map<String, String>> tratarClienteNaoEncontrado(
            ClienteNotFoundException exception) {

        return criarResposta(
                HttpStatus.NOT_FOUND,
                exception.getMessage()
        );
    }

    @ExceptionHandler(DispositivoNotFoundException.class)
    public ResponseEntity<Map<String, String>> tratarDispositivoNaoEncontrado(
            DispositivoNotFoundException exception) {

        return criarResposta(
                HttpStatus.NOT_FOUND,
                exception.getMessage()
        );
    }

    @ExceptionHandler(OrdemServicoNotFoundException.class)
    public ResponseEntity<Map<String, String>> tratarOrdemServicoNaoEncontrada(
            OrdemServicoNotFoundException exception) {

        return criarResposta(
                HttpStatus.NOT_FOUND,
                exception.getMessage()
        );
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<Map<String, String>> tratarRegraDeNegocio(
            RegraNegocioException exception) {

        return criarResposta(
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );
    }

    private ResponseEntity<Map<String, String>> criarResposta(
            HttpStatus status,
            String mensagem) {

        Map<String, String> erro = new LinkedHashMap<>();

        erro.put("erro", mensagem);

        return ResponseEntity
                .status(status)
                .body(erro);
    }
}