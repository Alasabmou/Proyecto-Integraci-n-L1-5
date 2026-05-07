package aiss.videominer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import aiss.videominer.model.Caption;

public interface CaptionRepository extends JpaRepository<Caption, String> {
}
