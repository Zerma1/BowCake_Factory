package cepn.defaut.services;

import cepn.defaut.models.StatuCommande;
import cepn.defaut.repository.StatuCommandeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StatuCommandeService {

    private final StatuCommandeRepository statuCommandeRepository;

    @Transactional(readOnly = true)
    public List<StatuCommande> findAll() {
        return statuCommandeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public StatuCommande findById(Long id) {
        return statuCommandeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Statut de commande non trouvé avec l'ID: " + id));
    }

    @Transactional(readOnly = true)
    public StatuCommande findByStatu(String statu) {
        return statuCommandeRepository.findByStatu(statu)
                .orElseThrow(() -> new EntityNotFoundException("Statut de commande non trouvé: " + statu));
    }

    public StatuCommande create(StatuCommande statuCommande) {
        if (statuCommande.getId() != null) {
            throw new IllegalArgumentException("Un nouveau statut de commande ne peut pas avoir d'ID");
        }
        if (statuCommandeRepository.existsByStatu(statuCommande.getStatu())) {
            throw new IllegalArgumentException("Un statut de commande avec ce libellé existe déjà");
        }
        return statuCommandeRepository.save(statuCommande);
    }

    public StatuCommande update(Long id, StatuCommande statuCommandeDetails) {
        StatuCommande statuCommande = findById(id);
        if (!statuCommande.getStatu().equals(statuCommandeDetails.getStatu()) &&
                statuCommandeRepository.existsByStatu(statuCommandeDetails.getStatu())) {
            throw new IllegalArgumentException("Un statut de commande avec ce libellé existe déjà");
        }
        statuCommande.setStatu(statuCommandeDetails.getStatu());
        statuCommande.setDescription(statuCommandeDetails.getDescription());
        return statuCommandeRepository.save(statuCommande);
    }

    public void delete(Long id) {
        if (!statuCommandeRepository.existsById(id)) {
            throw new EntityNotFoundException("Statut de commande non trouvé avec l'ID: " + id);
        }
        statuCommandeRepository.deleteById(id);
    }
}