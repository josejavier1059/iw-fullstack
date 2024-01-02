package es.uca.iw.fullstackwebapp.option.repositories;

import es.uca.iw.fullstackwebapp.option.domain.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {
    // Puedes añadir métodos específicos del repositorio si es necesario
}
