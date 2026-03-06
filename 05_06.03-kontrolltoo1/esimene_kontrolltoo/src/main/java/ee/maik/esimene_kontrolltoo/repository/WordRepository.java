package ee.maik.esimene_kontrolltoo.repository;

import ee.maik.esimene_kontrolltoo.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {
}