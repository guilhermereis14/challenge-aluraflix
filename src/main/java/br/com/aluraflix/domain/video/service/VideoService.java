package br.com.aluraflix.domain.video.service;

import br.com.aluraflix.domain.video.output.VideoDAO;
import br.com.aluraflix.domain.video.output.VideoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class VideoService {

    @Autowired
    private VideoDAO videoDAO;

    public VideoEntity add(VideoEntity video) {
        video.initialize();
        return videoDAO.save(video);
    }

    public VideoEntity getById(String id) {
        return videoDAO.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Video não encontrado com o id informado"));
    }


}
