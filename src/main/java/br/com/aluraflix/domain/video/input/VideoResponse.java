package br.com.aluraflix.domain.video.input;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class VideoResponse {

    @ApiModelProperty(notes = "Id do vídeo", example = "27355564-f463-11ea-adc1-0242ac120002")
    private String id;

    @ApiModelProperty(value = "Título do vídeo", example = "Criando uma API com Spring Boot")
    private String title;

    @ApiModelProperty(value = "Descrição do vídeo", example = "Vídeo com o passo a passo de criação de uma API com Spring Boot")
    private String description;

    @ApiModelProperty(value = "Link do vídeo", example = "https://www.youtube.com/watch?v=9GWK9A79tEc")
    private String url;

    @ApiModelProperty(value = "Status do vídeo", example = "VISIBLE")
    private String status;
}
