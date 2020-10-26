package br.com.ecommerce.mercadolivre.repository;

import br.com.ecommerce.mercadolivre.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import java.util.Optional;

/**
 * Carga intrínseca máxima permitida - 3
 * Carga intrínseca da classe - 1
 */

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // +1
    Optional<Usuario> findByLogin(@Email String login);

}
