package fr.cepn.testspringpo84.models;


import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Commande Test vide")
public class CommandeTest {

    private Commande commande;
    private Validator validator;

    Utilisateur mockUtilistateur = mock(Utilisateur.class);
    StatutCommande mockStatutCommande = mock(StatutCommande.class);

    @BeforeEach
    void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        /* #region creation Commande */

        commande = new Commande();
        commande.setDateCommande(LocalDate.of(2025, 11, 11));
        commande.setDateLivraison(LocalDate.of(2025,11,16));
        commande.setPrixFacture(50);


        when(mockStatutCommande.getId()).thenReturn(1L);
        commande.setFkSatutCommande(mockStatutCommande);


        when(mockUtilistateur.getId()).thenReturn(1L);
        commande.setFkUtilisateur(mockUtilistateur);

        /* #endregion creation Commande */
    }

    @AfterEach
    void tearDown(){
        commande = null;
        validator = null;
    }

    /* #region test getter */

    @Test
    @DisplayName("test getter dateCommande")
    void testGetDateCommande(){
        assertEquals(LocalDate.of(2025, 11, 11), commande.getDateCommande());
    }

    @Test
    @DisplayName("test getter dateLivraison")
    void testGetDateLivraison(){
        assertEquals(LocalDate.of(2025,11,16), commande.getDateLivraison());
    }

    @Test
    @DisplayName("test getter prix commande")
    void testGetPrixCommande(){
        assertEquals(50, commande.getPrixFacture());
    }

    @Test
    @DisplayName("test getter FkStatutCommande")
    void testGetFkUtilisateur(){
        when(mockStatutCommande.getId()).thenReturn(1L);
        assertEquals(mockStatutCommande, commande.getFkSatutCommande());
    }

    @Test
    @DisplayName("test getter FkUtilisateur")
    void testFkUtilisateur(){
        when(mockUtilistateur.getId()).thenReturn(1L);
        assertEquals(mockUtilistateur, commande.getFkUtilisateur());
    }

    /* #endregion test getter */

    /* #region test creation */

    @Test
    @DisplayName("test de creation d'une commande valide")
    void testCreationCommandeValide(){
        assertNotNull(commande);
        assertEquals(LocalDate.of(2025, 11, 11), commande.getDateCommande());
        assertEquals(LocalDate.of(2025,11,16), commande.getDateLivraison());
        assertEquals(50, commande.getPrixFacture());
        assertEquals(mockStatutCommande, commande.getFkSatutCommande());
        assertEquals(mockStatutCommande, commande.getFkSatutCommande());
        when(mockUtilistateur.getId()).thenReturn(1L);
        assertEquals(mockUtilistateur, commande.getFkUtilisateur());
    }

    @Test
    @DisplayName("test creation d'une commande pare defaut")
    void testConstructeurParDefaut(){
        Commande newCommande = new Commande();
        assertNotNull(newCommande);
        assertNull(newCommande.getDateCommande());
        assertNull(newCommande.getDateLivraison());
        assertEquals(0, newCommande.getPrixFacture());
        assertNull(newCommande.getFkSatutCommande());
        assertNull(newCommande.getFkSatutCommande());
    }

    /* #endregion test creation */
    
    /* #region setter correct*/
    
    @Test
    @DisplayName("test setter dateCommande")
    void testSetDateCommande(){
        commande.setDateCommande(LocalDate.of(2025, 1,9));
        assertEquals(LocalDate.of(2025, 1,9), commande.getDateCommande());
    }
    
    /* #endregion setter */
    
}
