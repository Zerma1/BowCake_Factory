package cepn.defaut.services;

import cepn.defaut.models.TypeProduit;
import cepn.defaut.repository.TypeProduitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TypeProduitService {

    private final TypeProduitRepository typeProduitRepository;

    @Transactional(readOnly = true)
    public List<TypeProduit> findAll() {
        return typeProduitRepository.findAll();
    }

    @Transactional(readOnly = true)
    public TypeProduit findById(Long id) {
        return typeProduitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Type de produit non trouvé avec l'ID: " + id));
    }

    @Transactional(readOnly = true)
    public TypeProduit findByNom(String nom) {
        return typeProduitRepository.findByNom(nom)
                .orElseThrow(() -> new EntityNotFoundException("Type de produit non trouvé: " + nom));
    }

    public TypeProduit create(TypeProduit typeProduit) {
        if (typeProduit.getId() != null) {
            throw new IllegalArgumentException("Un nouveau type de produit ne peut pas avoir d'ID");
        }
        if (typeProduitRepository.existsByNom(typeProduit.getNom())) {
            throw new IllegalArgumentException("Un type de produit avec ce nom existe déjà");
        }
        return typeProduitRepository.save(typeProduit);
    }

    public TypeProduit update(Long id, TypeProduit typeProduitDetails) {
        TypeProduit typeProduit = findById(id);
        if (!typeProduit.getNom().equals(typeProduitDetails.getNom()) &&
                typeProduitRepository.existsByNom(typeProduitDetails.getNom())) {
            throw new IllegalArgumentException("Un type de produit avec ce nom existe déjà");
        }
        typeProduit.setNom(typeProduitDetails.getNom());
        typeProduit.setDescription(typeProduitDetails.getDescription());
        return typeProduitRepository.save(typeProduit);
    }

    public void delete(Long id) {
        if (!typeProduitRepository.existsById(id)) {
            throw new EntityNotFoundException("Type de produit non trouvé avec l'ID: " + id);
        }
        typeProduitRepository.deleteById(id);
    }
}