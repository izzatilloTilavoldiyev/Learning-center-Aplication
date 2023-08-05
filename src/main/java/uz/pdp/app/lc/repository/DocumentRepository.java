package uz.pdp.app.lc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app.lc.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
