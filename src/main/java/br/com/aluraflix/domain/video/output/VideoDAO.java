package br.com.aluraflix.domain.video.output;

import org.springframework.data.repository.CrudRepository;

public interface VideoDAO extends CrudRepository<VideoEntity, String> {

}
