package cepn.defaut.services;

import cepn.defaut.models.EtatProduit;
import cepn.defaut.repository.EtatProduitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EtatProduitService {

    private final EtatProduitRepository etatProduitRepository;

    @Transactional(readOnly = true)
    public List<EtatProduit> findAll() {
        return etatProduitRepository.findAll();
    }

    @Transactional(readOnly = true)
    public EtatProduit findById(Long id) {
        return etatProduitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("État de produit non trouvé avec l'ID: " + id));
    }

    @Transactional(readOnly = true)
    public EtatProduit findByEtat(String etat) {
        return etatProduitRepository.findByEtat(etat)
                .orElseThrow(() -> new EntityNotFoundException("État de produit non trouvé: " + etat));
    }

    @Transactional(readOnly = true)
    public List<EtatProduit> findActiveLimitedStates() {
        return etatProduitRepository.findActiveLimitedStates(LocalDate.now());
    }

    public EtatProduit create(EtatProduit etatProduit) {
        if (etatProduit.getId() != null) {
            throw new IllegalArgumentException("Un nouvel état de produit ne peut pas avoir d'ID");
        }
        return etatProduitRepository.save(etatProduit);
    }

    public EtatProduit update(Long id, EtatProduit etatProduitDetails) {
        EtatProduit etatProduit = findById(id);
        etatProduit.setEtat(etatProduitDetails.getEtat());
        etatProduit.setLimite(etatProduitDetails.isLimite());
        etatProduit.setDateFin(etatProduitDetails.getDateFin());
        return etatProduitRepository.save(etatProduit);
    }

    public void delete(Long id) {
        if (!etatProduitRepository.existsById(id)) {
            throw new EntityNotFoundException("État de produit non trouvé avec l'ID: " + id);
        }
        etatProduitRepository.deleteById(id);
    }
}