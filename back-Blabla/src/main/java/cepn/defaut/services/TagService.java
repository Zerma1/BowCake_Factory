package cepn.defaut.services;

import cepn.defaut.models.Tag;
import cepn.defaut.repository.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    @Transactional(readOnly = true)
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Tag findById(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tag non trouvé avec l'ID: " + id));
    }

    @Transactional(readOnly = true)
    public Tag findByNom(String nom) {
        return tagRepository.findByNom(nom)
                .orElseThrow(() -> new EntityNotFoundException("Tag non trouvé: " + nom));
    }

    @Transactional(readOnly = true)
    public List<Tag> searchByNom(String nom) {
        return tagRepository.findByNomContainingIgnoreCase(nom);
    }

    public Tag create(Tag tag) {
        if (tag.getId() != null) {
            throw new IllegalArgumentException("Un nouveau tag ne peut pas avoir d'ID");
        }
        if (tagRepository.existsByNom(tag.getNom())) {
            throw new IllegalArgumentException("Un tag avec ce nom existe déjà");
        }
        return tagRepository.save(tag);
    }

    public Tag update(Long id, Tag tagDetails) {
        Tag tag = findById(id);
        if (!tag.getNom().equals(tagDetails.getNom()) &&
                tagRepository.existsByNom(tagDetails.getNom())) {
            throw new IllegalArgumentException("Un tag avec ce nom existe déjà");
        }
        tag.setNom(tagDetails.getNom());
        tag.setDescription(tagDetails.getDescription());
        return tagRepository.save(tag);
    }

    public void delete(Long id) {
        if (!tagRepository.existsById(id)) {
            throw new EntityNotFoundException("Tag non trouvé avec l'ID: " + id);
        }
        tagRepository.deleteById(id);
    }
}