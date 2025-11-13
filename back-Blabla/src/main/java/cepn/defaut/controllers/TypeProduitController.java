package cepn.defaut.controllers;

import cepn.defaut.controllers.dtos.TypeProduitDto;
import cepn.defaut.controllers.dtos.assemblers.TypeProduitDtoAssembler;
import cepn.defaut.models.TypeProduit;
import cepn.defaut.services.TypeProduitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * Contrôleur REST pour la gestion des types de produits
 * Expose les endpoints CRUD pour TypeProduit
 */
@RestController
@RequestMapping("/api/type-produits")
@CrossOrigin(origins = "*") // À configurer selon vos besoins de sécurité
public class TypeProduitController {

    private final TypeProduitService typeProduitService;
    private final TypeProduitDtoAssembler typeProduitDtoAssembler;

    /**
     * Constructeur avec injection de dépendances
     */
    public TypeProduitController(TypeProduitService typeProduitService,
                                 TypeProduitDtoAssembler typeProduitDtoAssembler) {
        this.typeProduitService = typeProduitService;
        this.typeProduitDtoAssembler = typeProduitDtoAssembler;
    }

    /**
     * Récupère tous les types de produits
     * GET /api/type-produits
     *
     * @return Collection de TypeProduitDto
     */
    @GetMapping
    public ResponseEntity<Collection<TypeProduitDto>> findAll() {
        Collection<TypeProduit> typeProduits = typeProduitService.findAll();
        Collection<TypeProduitDto> typeProduitDtos = typeProduitDtoAssembler.toDtoList(
                typeProduits.toArray(new TypeProduit[0])
        );
        return ResponseEntity.ok(typeProduitDtos);
    }

    /**
     * Récupère un type de produit par son ID
     * GET /api/type-produits/{id}
     *
     * @param id L'identifiant du type de produit
     * @return TypeProduitDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<TypeProduitDto> findById(@PathVariable Long id) {
        TypeProduit typeProduit = typeProduitService.findById(id);
        TypeProduitDto dto = typeProduitDtoAssembler.toDto(typeProduit);
        return ResponseEntity.ok(dto);
    }

    /**
     * Crée un nouveau type de produit
     * POST /api/type-produits
     *
     * @param typeProduitDto Les données du type de produit à créer
     * @return TypeProduitDto créé avec statut 201 (Created)
     */
    @PostMapping
    public ResponseEntity<TypeProduitDto> create(@RequestBody TypeProduitDto typeProduitDto) {
        // Conversion DTO -> Entité
        TypeProduit typeProduit = TypeProduit.builder()
                .nom(typeProduitDto.getNom())
                .build();

        // Sauvegarde via le service
        TypeProduit savedTypeProduit = typeProduitService.create(typeProduit);

        // Conversion Entité -> DTO
        TypeProduitDto responseDto = typeProduitDtoAssembler.toDto(savedTypeProduit);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    /**
     * Met à jour un type de produit existant
     * PUT /api/type-produits/{id}
     *
     * @param id L'identifiant du type de produit
     * @param typeProduitDto Les nouvelles données
     * @return TypeProduitDto mis à jour
     */
    @PutMapping("/{id}")
    public ResponseEntity<TypeProduitDto> update(@PathVariable Long id,
                                                 @RequestBody TypeProduitDto typeProduitDto) {
        // Conversion DTO -> Entité
        TypeProduit typeProduit = TypeProduit.builder()
                .nom(typeProduitDto.getNom())
                .build();

        // Mise à jour via le service
        TypeProduit updatedTypeProduit = typeProduitService.update(id, typeProduit);

        // Conversion Entité -> DTO
        TypeProduitDto responseDto = typeProduitDtoAssembler.toDto(updatedTypeProduit);

        return ResponseEntity.ok(responseDto);
    }

    /**
     * Supprime un type de produit
     * DELETE /api/type-produits/{id}
     *
     * @param id L'identifiant du type de produit à supprimer
     * @return Statut 204 (No Content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        typeProduitService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Recherche des types de produits par nom/libellé
     * GET /api/type-produits/search?nom=...
     *
     * @param nom Le nom à rechercher
     * @return Collection de TypeProduitDto correspondants
     */
    @GetMapping("/search")
    public ResponseEntity<Collection<TypeProduitDto>> searchByNom(@RequestParam String nom) {
        Collection<TypeProduit> typeProduits = Collections.singleton(typeProduitService.findByNom(nom));

        // Vérifie si la collection est vide
        if (typeProduits.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retourne 204 No Content
        }

        Collection<TypeProduitDto> typeProduitDtos = typeProduitDtoAssembler.toDtoList(
                typeProduits.toArray(new TypeProduit[0])
        );

        return ResponseEntity.ok(typeProduitDtos);
    }
}