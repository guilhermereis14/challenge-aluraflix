package br.com.aluraflix.domain.video.output;

import br.com.aluraflix.domain.common.BaseModel;
import br.com.aluraflix.infraestructure.exception.ServiceException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "video")
@Getter
@Setter
@ToString
@NoArgsConstructor
@SuperBuilder
public class VideoEntity extends BaseModel {

    public enum Status {
        VISIBLE,
        HIDDEN
    }

    private String title;
    private String description;
    private String url;

    @Enumerated(EnumType.STRING)
    private Status status;

    public void initialize() {
        this.status = Status.VISIBLE;
    }

    public void markToHidden() {
        if(Status.HIDDEN.equals(this.status))
            throw new ServiceException("Vídeo já está oculto!");
        this.status = Status.HIDDEN;
    }
}
