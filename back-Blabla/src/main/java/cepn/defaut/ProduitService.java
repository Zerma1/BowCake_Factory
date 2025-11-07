package cepn.defaut.services;

import cepn.defaut.models.Produit;
import cepn.defaut.models.EtatProduit;
import cepn.defaut.models.TypeProduit;
import cepn.defaut.repository.ProduitRepository;
import cepn.defaut.repository.EtatProduitRepository;
import cepn.defaut.repository.TypeProduitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProduitService {

    private final ProduitRepository produitRepository;
    private final EtatProduitRepository etatProduitRepository;
    private final TypeProduitRepository typeProduitRepository;

    /**
     * Récupère tous les produits
     */
    @Transactional(readOnly = true)
    public List<Produit> findAll() {
        return produitRepository.findAll();
    }

    /**
     * Récupère tous les produits avec pagination
     */
    @Transactional(readOnly = true)
    public Page<Produit> findAll(Pageable pageable) {
        return produitRepository.findAll(pageable);
    }

    /**
     * Récupère un produit par son ID
     */
    @Transactional(readOnly = true)
    public Produit findById(Long id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produit non trouvé avec l'ID: " + id));
    }

    /**
     * Recherche des produits par nom
     */
    @Transactional(readOnly = true)
    public List<Produit> searchByNom(String nom) {
        return produitRepository.findByNomContainingIgnoreCase(nom);
    }

    /**
     * Récupère les produits disponibles en stock
     */
    @Transactional(readOnly = true)
    public List<Produit> findAvailable() {
        return produitRepository.findByStockGreaterThan(0);
    }

    /**
     * Récupère les produits par type
     */
    @Transactional(readOnly = true)
    public List<Produit> findByTypeProduit(Long typeProduitId) {
        TypeProduit typeProduit = typeProduitRepository.findById(typeProduitId)
                .orElseThrow(() -> new EntityNotFoundException("Type de produit non trouvé"));
        return produitRepository.findByTypeProduit(typeProduit);
    }

    /**
     * Récupère les produits par état
     */
    @Transactional(readOnly = true)
    public List<Produit> findByEtatProduit(Long etatProduitId) {
        EtatProduit etatProduit = etatProduitRepository.findById(etatProduitId)
                .orElseThrow(() -> new EntityNotFoundException("État de produit non trouvé"));
        return produitRepository.findByEtatProduit(etatProduit);
    }

    /**
     * Récupère les produits dans une fourchette de prix
     */
    @Transactional(readOnly = true)
    public List<Produit> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return produitRepository.findByPrixHTBetween(minPrice, maxPrice);
    }

    /**
     * Crée un nouveau produit
     */
    public Produit create(Produit produit) {
        if (produit.getId() != null) {
            throw new IllegalArgumentException("Un nouveau produit ne peut pas avoir d'ID");
        }
        return produitRepository.save(produit);
    }

    /**
     * Met à jour un produit existant
     */
    public Produit update(Long id, Produit produitDetails) {
        Produit produit = findById(id);

        produit.setNom(produitDetails.getNom());
        produit.setDescription(produitDetails.getDescription());
        produit.setPrixHT(produitDetails.getPrixHT());
        produit.setTva(produitDetails.getTva());
        produit.setStock(produitDetails.getStock());
        produit.setImageUrl(produitDetails.getImageUrl());
        produit.setTypeProduit(produitDetails.getTypeProduit());
        produit.setEtatProduit(produitDetails.getEtatProduit());
        produit.setRecette(produitDetails.getRecette());

        return produitRepository.save(produit);
    }

    /**
     * Supprime un produit
     */
    public void delete(Long id) {
        if (!produitRepository.existsById(id)) {
            throw new EntityNotFoundException("Produit non trouvé avec l'ID: " + id);
        }
        produitRepository.deleteById(id);
    }

    /**
     * Met à jour le stock d'un produit
     */
    public Produit updateStock(Long id, Integer newStock) {
        Produit produit = findById(id);

        if (newStock < 0) {
            throw new IllegalArgumentException("Le stock ne peut pas être négatif");
        }

        produit.setStock(newStock);
        return produitRepository.save(produit);
    }

    /**
     * Décrémente le stock d'un produit
     */
    public Produit decrementStock(Long id, Integer quantity) {
        Produit produit = findById(id);

        if (quantity <= 0) {
            throw new IllegalArgumentException("La quantité doit être positive");
        }

        if (produit.getStock() < quantity) {
            throw new IllegalStateException("Stock insuffisant pour le produit: " + produit.getNom());
        }

        produit.setStock(produit.getStock() - quantity);
        return produitRepository.save(produit);
    }

    /**
     * Incrémente le stock d'un produit
     */
    public Produit incrementStock(Long id, Integer quantity) {
        Produit produit = findById(id);

        if (quantity <= 0) {
            throw new IllegalArgumentException("La quantité doit être positive");
        }

        produit.setStock(produit.getStock() + quantity);
        return produitRepository.save(produit);
    }

    /**
     * Vérifie si un produit est disponible
     */
    @Transactional(readOnly = true)
    public boolean isAvailable(Long id, Integer quantity) {
        Produit produit = findById(id);
        return produit.getStock() >= quantity;
    }
}