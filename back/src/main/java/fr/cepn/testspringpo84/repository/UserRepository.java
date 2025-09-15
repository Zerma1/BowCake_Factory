package fr.cepn.testspringpo84.repository;


import fr.cepn.testspringpo84.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
