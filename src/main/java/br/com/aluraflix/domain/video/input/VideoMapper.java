package br.com.aluraflix.domain.video.input;

import br.com.aluraflix.domain.video.output.VideoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VideoMapper {

    VideoEntity mapToVideoEntity(VideoRequest videoEntity);

    VideoResponse mapToVideoResponse(VideoEntity videoEntity);

}
