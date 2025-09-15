package fr.cepn.testspringpo84.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface Exemple extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate")
    List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
