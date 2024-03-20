package uz.devops.currency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.devops.currency.domain.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {}
