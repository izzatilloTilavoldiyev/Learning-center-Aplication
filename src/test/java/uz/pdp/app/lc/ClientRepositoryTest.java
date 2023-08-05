package uz.pdp.app.lc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import uz.pdp.app.lc.repository.ClientRepository;

import static org.assertj.core.api.AssertionsForClassTypes.*;

@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository underTest;

    @Test
    void existsById() {
        Long id = 1L;
        boolean result = underTest.existsById(id);
        assertThat(result).isFalse();
    }
}
