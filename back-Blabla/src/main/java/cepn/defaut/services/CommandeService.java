package cepn.defaut.services;

import cepn.defaut.models.*;
import cepn.defaut.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandeService {

    private final CommandeRepository commandeRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ProduitRepository produitRepository;
    private final StatuCommandeRepository statuCommandeRepository;
    private final PanierRepository panierRepository;

    /**
     * Récupère toutes les commandes
     */
    @Transactional(readOnly = true)
    public List<Commande> findAll() {
        return commandeRepository.findAll();
    }

    /**
     * Récupère une commande par son ID
     */
    @Transactional(readOnly = true)
    public Commande findById(Long id) {
        return commandeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Commande non trouvée avec l'ID: " + id));
    }

    /**
     * Récupère une commande par son numéro
     */
    @Transactional(readOnly = true)
    public Commande findByNumeroCommande(String numeroCommande) {
        return commandeRepository.findByNumeroCommande(numeroCommande)
                .orElseThrow(() -> new EntityNotFoundException("Commande non trouvée: " + numeroCommande));
    }

    /**
     * Récupère les commandes d'un utilisateur
     */
    @Transactional(readOnly = true)
    public List<Commande> findByUtilisateur(Long utilisateurId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé"));
        return commandeRepository.findByUtilisateur(utilisateur);
    }

    /**
     * Récupère les commandes par statut
     */
    @Transactional(readOnly = true)
    public List<Commande> findByStatut(String statutNom) {
        StatuCommande statut = statuCommandeRepository.findByStatu(statutNom)
                .orElseThrow(() -> new EntityNotFoundException("Statut non trouvé: " + statutNom));
        return commandeRepository.findByStatut(statut);
    }

    /**
     * Récupère les commandes entre deux dates
     */
    @Transactional(readOnly = true)
    public List<Commande> findByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return commandeRepository.findByDateCommandeBetween(startDate, endDate);
    }

    /**
     * Crée une nouvelle commande
     */
    public Commande create(Long utilisateurId, Map<Long, Integer> produitsQuantites,
                           String adresseLivraison, String ville, String codePostal,
                           String pays, String commentaire) {

        // Récupérer l'utilisateur
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé"));

        // Récupérer le statut "EN_ATTENTE" ou "NOUVELLE"
        StatuCommande statut = statuCommandeRepository.findByStatu("EN_ATTENTE")
                .or(() -> statuCommandeRepository.findByStatu("NOUVELLE"))
                .orElseThrow(() -> new EntityNotFoundException("Statut de commande par défaut non trouvé"));

        // Créer la commande
        Commande commande = Commande.builder()
                .utilisateur(utilisateur)
                .dateCommande(LocalDateTime.now())
                .montantTotal(BigDecimal.ZERO)
                .statut(statut)
                .adresseLivraison(adresseLivraison)
                .ville(ville)
                .codePostal(codePostal)
                .pays(pays)
                .commentaire(commentaire)
                .build();

        // Calculer le montant total et créer les items du panier
        BigDecimal montantTotal = BigDecimal.ZERO;

        for (Map.Entry<Long, Integer> entry : produitsQuantites.entrySet()) {
            Long produitId = entry.getKey();
            Integer quantite = entry.getValue();

            // Vérifier que le produit existe
            Produit produit = produitRepository.findById(produitId)
                    .orElseThrow(() -> new EntityNotFoundException("Produit non trouvé: " + produitId));

            // Vérifier le stock
            if (produit.getStock() < quantite) {
                throw new IllegalStateException("Stock insuffisant pour le produit: " + produit.getNom());
            }

            // Créer l'item du panier
            Panier panier = Panier.builder()
                    .commande(commande)
                    .produit(produit)
                    .quantite(quantite)
                    .prixUnitaire(produit.getPrixTTC())
                    .build();

            commande.getPaniers().add(panier);

            // Calculer le sous-total
            montantTotal = montantTotal.add(panier.getSousTotal());

            // Décrémenter le stock
            produit.setStock(produit.getStock() - quantite);
            produitRepository.save(produit);
        }

        commande.setMontantTotal(montantTotal);
        return commandeRepository.save(commande);
    }

    /**
     * Met à jour le statut d'une commande
     */
    public Commande updateStatut(Long commandeId, String nouveauStatut) {
        Commande commande = findById(commandeId);

        StatuCommande statut = statuCommandeRepository.findByStatu(nouveauStatut)
                .orElseThrow(() -> new EntityNotFoundException("Statut non trouvé: " + nouveauStatut));

        commande.setStatut(statut);
        return commandeRepository.save(commande);
    }

    /**
     * Annule une commande
     */
    public Commande cancel(Long commandeId) {
        Commande commande = findById(commandeId);

        // Vérifier que la commande peut être annulée
        if (commande.getStatut().getStatu().equals("LIVREE") ||
                commande.getStatut().getStatu().equals("ANNULEE")) {
            throw new IllegalStateException("Cette commande ne peut pas être annulée");
        }

        // Restaurer le stock des produits
        for (Panier panier : commande.getPaniers()) {
            Produit produit = panier.getProduit();
            produit.setStock(produit.getStock() + panier.getQuantite());
            produitRepository.save(produit);
        }

        // Mettre à jour le statut
        StatuCommande statutAnnule = statuCommandeRepository.findByStatu("ANNULEE")
                .orElseThrow(() -> new EntityNotFoundException("Statut ANNULEE non trouvé"));

        commande.setStatut(statutAnnule);
        return commandeRepository.save(commande);
    }

    /**
     * Supprime une commande
     */
    public void delete(Long id) {
        if (!commandeRepository.existsById(id)) {
            throw new EntityNotFoundException("Commande non trouvée avec l'ID: " + id);
        }
        commandeRepository.deleteById(id);
    }

    /**
     * Récupère les commandes récentes d'un utilisateur
     */
    @Transactional(readOnly = true)
    public List<Commande> findRecentByUtilisateur(Long utilisateurId, int limit) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé"));
        return commandeRepository.findTop5ByUtilisateurOrderByDateCommandeDesc(utilisateur)
                .stream()
                .limit(limit)
                .toList();
    }

    /**
     * Calcule le montant total des ventes entre deux dates
     */
    @Transactional(readOnly = true)
    public BigDecimal calculateTotalSales(LocalDateTime startDate, LocalDateTime endDate) {
        return commandeRepository.calculateTotalSalesBetweenDates(startDate, endDate);
    }
}