package cepn.defaut.services;

import cepn.defaut.models.Commande;
import cepn.defaut.models.Panier;
import cepn.defaut.models.Produit;
import cepn.defaut.repository.CommandeRepository;
import cepn.defaut.repository.PanierRepository;
import cepn.defaut.repository.ProduitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PanierService {

    private final PanierRepository panierRepository;
    private final CommandeRepository commandeRepository;
    private final ProduitRepository produitRepository;

    @Transactional(readOnly = true)
    public List<Panier> findAll() {
        return panierRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Panier findById(Long id) {
        return panierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Panier non trouvé avec l'ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<Panier> findByCommande(Long commandeId) {
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new EntityNotFoundException("Commande non trouvée"));
        return panierRepository.findByCommande(commande);
    }

    @Transactional(readOnly = true)
    public BigDecimal calculateTotalForCommande(Long commandeId) {
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new EntityNotFoundException("Commande non trouvée"));
        return panierRepository.calculateTotalForCommande(commande);
    }

    public Panier create(Panier panier) {
        if (panier.getId() != null) {
            throw new IllegalArgumentException("Un nouveau panier ne peut pas avoir d'ID");
        }
        return panierRepository.save(panier);
    }

    public Panier update(Long id, Panier panierDetails) {
        Panier panier = findById(id);
        panier.setQuantite(panierDetails.getQuantite());
        panier.setPrixUnitaire(panierDetails.getPrixUnitaire());
        return panierRepository.save(panier);
    }

    public void delete(Long id) {
        if (!panierRepository.existsById(id)) {
            throw new EntityNotFoundException("Panier non trouvé avec l'ID: " + id);
        }
        panierRepository.deleteById(id);
    }
}