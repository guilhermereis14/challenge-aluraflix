package br.com.aluraflix.infraestructure.exception;

import br.com.aluraflix.domain.common.ValidationRule;
import br.com.aluraflix.domain.video.output.VideoDAO;
import br.com.aluraflix.domain.video.output.VideoEntity;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class CannotRepeatNameVideoValidationRule implements ValidationRule {

    private final VideoEntity video;
    private final VideoDAO videoDAO;

    @Override
    public Optional<String> validate() {
        Optional<VideoEntity> optional = videoDAO.findById(video.getId());
        if(optional.isPresent() && !optional.get().getId().equals(video.getId()))
            return Optional.of("Ja existe um video cadastrado com esse nome");
        return Optional.empty();
    }
}
