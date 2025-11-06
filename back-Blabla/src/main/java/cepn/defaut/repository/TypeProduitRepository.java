package cepn.defaut.repository;


import cepn.defaut.models.TypeProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeProduitRepository extends JpaRepository<TypeProduit, Long> {

    /**
     * Trouve un type de produit par son nom
     * @param nom le nom du type de produit
     * @return Optional contenant le type si trouvé
     */
    Optional<TypeProduit> findByNom(String nom);

    /**
     * Trouve tous les types de produits dont le nom contient la chaîne donnée (insensible à la casse)
     * @param nom partie du nom à rechercher
     * @return liste des types correspondants
     */
    List<TypeProduit> findByNomContainingIgnoreCase(String nom);

    /**
     * Vérifie si un type de produit existe par son nom
     * @param nom le nom du type de produit
     * @return true si le type existe
     */
    boolean existsByNom(String nom);
}