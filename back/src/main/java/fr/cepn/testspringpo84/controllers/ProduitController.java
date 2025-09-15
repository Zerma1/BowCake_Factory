package fr.cepn.testspringpo84.controllers;


import fr.cepn.testspringpo84.controllers.dtos.ProduitDto;
import fr.cepn.testspringpo84.controllers.dtos.assemblers.ProduitDtoAssemblers;
import fr.cepn.testspringpo84.models.Produit;
import fr.cepn.testspringpo84.services.ProduitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/produit")
public class ProduitController {

    private final ProduitService produitService;
    private final ProduitDtoAssemblers produitDtoAssemblers;

    public ProduitController(
            ProduitService produitService,
            ProduitDtoAssemblers produitDtoAssemblers) {
        this.produitService = produitService;
        this.produitDtoAssemblers = produitDtoAssemblers;
    }

    @GetMapping("/")
    public ResponseEntity<Iterable<ProduitDto>> getAll() {
        try {
            List<Produit> Produits = produitService.findAll();
            Collection<ProduitDto> ProduitDtos = produitDtoAssemblers.toDtoList(Produits);
            return ResponseEntity.ok(ProduitDtos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}/")
    public ResponseEntity<ProduitDto> getById(@PathVariable("id") Long id) {
        try {
            Optional<Produit> produit = produitService.findById(id);
            if (produit.isPresent()) {
                ProduitDto produitDto = produitDtoAssemblers.toDto(produit.get());
                return ResponseEntity.ok(produitDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<ProduitDto> create(@RequestBody ProduitDto produitDto) {
        try {
            if (produitDto.getId() != null) {
                return ResponseEntity.badRequest().build();
            }
            Produit produit = produitDtoAssemblers.toEntity(produitDto);
            Produit savedproduit = produitService.save(produit);
            return ResponseEntity.ok(produitDtoAssemblers.toDto(savedproduit));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}/")
    public ResponseEntity<ProduitDto> update(@PathVariable("id") Long id, @RequestBody ProduitDto produitDto) {
        try {
            if (produitDto.getId() == null || !produitDto.getId().equals(id)) {
                return ResponseEntity.badRequest().build();
            }
            Optional<Produit> existingproduit = produitService.findById(id);
            if (existingproduit.isPresent()) {
                Produit updatedproduit = produitService.save(produitDtoAssemblers.toEntity(produitDto));
                produitDto = produitDtoAssemblers.toDto(updatedproduit);
                return ResponseEntity.ok(produitDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try {
            Optional<Produit> existingproduit = produitService.findById(id);
            if (existingproduit.isPresent()) {
                produitService.delete(existingproduit.get());
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}