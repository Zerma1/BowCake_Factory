package fr.cepn.testspringpo84.repository;


import fr.cepn.testspringpo84.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<Utilisateur, Long> {

    Optional<Utilisateur> findByNom(String username);
}
