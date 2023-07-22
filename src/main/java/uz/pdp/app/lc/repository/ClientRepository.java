package uz.pdp.app.lc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.app.lc.entity.ClientEntity;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    @Query(value = "from clients c where c.id =:id and c.deleted = false ")
    Optional<ClientEntity> findClientById(Long id);

    @Query(value = "from clients c where c.deleted = false ")
    List<ClientEntity> getAll();
}
