package br.com.aluraflix.domain.video.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoRequest implements Serializable {

    @ApiModelProperty(value = "Título do vídeo", example = "Criando uma API com Spring Boot")
    @NotBlank(message = "Título do vídeo deve ser informado")
    private String title;

    @ApiModelProperty(value = "Descrição do vídeo", example = "Vídeo com o passo a passo de criação de uma API com Spring Boot")
    @NotBlank(message = "Descrição do vídeo deve ser informada")
    private String description;

    @ApiModelProperty(value = "Link do vídeo", example = "https://www.youtube.com/watch?v=9GWK9A79tEc")
    @NotBlank(message = "Link do vídeo deve ser informado")
    private String url;
}
