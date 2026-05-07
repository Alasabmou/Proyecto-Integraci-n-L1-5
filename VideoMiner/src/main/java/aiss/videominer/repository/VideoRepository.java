package aiss.videominer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import aiss.videominer.model.Video;

public interface VideoRepository extends JpaRepository<Video, String> {
}
