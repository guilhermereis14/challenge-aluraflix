package br.com.aluraflix.infraestructure.exception;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
public class ErrorResponse implements Serializable {

    @ApiModelProperty(notes = "Instante em que o erro foi gerado", example = "10/02/2020 12:22:11")
    private String timestamp;

    @ApiModelProperty(notes = "Codigo HTTP do erro", example = "422")
    private int code;

    @ApiModelProperty(notes = "Mensagens de erro", example = "['Falha ao salvar registro']")
    private List<String> messages;

}
