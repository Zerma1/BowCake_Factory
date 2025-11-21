package fr.cepn.testspringpo84.models;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

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
        assertNotNull(newCommande.getDateCommande());
        assertNull(newCommande.getDateLivraison());
        assertEquals(0, newCommande.getPrixFacture());
        //noinspection DataFlowIssue
        assertNull(newCommande.getFkSatutCommande());//erreur : "assertNull" verifi que "FkSatutCommande" est null or,
                                                     //         "FkSatutCommande" est marque comme "@NonNull".
        assertNull(newCommande.getFkSatutCommande());
    }

    /* #endregion test creation */
    
    /* #region setter correct*/
    
    @Test
    @DisplayName("test setter dateCommande correct")
    void testSetDateCommande(){
        commande.setDateCommande(LocalDate.of(2025, 1,9));
        assertEquals(LocalDate.of(2025, 1,9), commande.getDateCommande());
    }
    //TODO : rest test setter correct
    
    /* #endregion setter */

    /* #region test null et non null */
    //TODO : test non null - Donne
    @Test
    @DisplayName("test validation non null dateCommande")
    void testValidationNonNullDateCommande(){
        assertThrows(NullPointerException.class, ()-> commande.setDateCommande(null));
    }

    @Test
    @DisplayName("test validation null dateLivraison")
    void testValidationNonNullDateLivraison(){
        //verifier que commande.setDateLivraison(null) ne renvoi pas d'erreur
        Set<ConstraintViolation<Commande>> violations = validator.validate(commande);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("test validation non null prixFacture")
    void testValidationNonNullPrixFacture(){
        assertThrows(NullPointerException.class, ()-> commande.setPrixFacture(null));
    }

    @Test
    @DisplayName("test validation non null fkSatutCommande")
    void testValidationNonNullFkSatutCommande(){
        assertThrows(NullPointerException.class, ()-> commande.setFkSatutCommande(null));
    }

    @Test
    @DisplayName("test validation non null fkUtilisateur")
    void testValidationNonNullFkUtilisateur(){
        assertThrows(NullPointerException.class, ()-> commande.setFkUtilisateur(null));
    }

    /* #endregion test non null */

    /* #region test equals hashcode */
        //TODO : test equals hashcode

        @Test
        @DisplayName("Test equal avec meme obj")
        void testEqual(){
            assertEquals(commande, commande);
        }

        @Test
        @DisplayName("test avec obj identique")
        void testEqualEquivalent(){
            /* #region creation commande identique */
            Commande commande2;
            commande2 = new Commande();
            commande2.setDateCommande(LocalDate.of(2025, 11, 11));
            commande2.setDateLivraison(LocalDate.of(2025,11,16));
            commande2.setPrixFacture(50);
            when(mockStatutCommande.getId()).thenReturn(1L);
            commande2.setFkSatutCommande(mockStatutCommande);
            when(mockUtilistateur.getId()).thenReturn(1L);
            commande2.setFkUtilisateur(mockUtilistateur);
            /* #endregion creation commande identique */

            assertNotEquals(commande, commande2);
        }

        @Test
        @DisplayName("test avec obj dif date facture")
        void testEqualDifDatCom(){
            /* #region creation commande different */
            Commande commande2;
            commande2 = new Commande();
            commande2.setDateCommande(LocalDate.of(2025, 11, 9));
            commande2.setDateLivraison(LocalDate.of(2025,11,16));
            commande2.setPrixFacture(50);
            when(mockStatutCommande.getId()).thenReturn(1L);
            commande2.setFkSatutCommande(mockStatutCommande);
            when(mockUtilistateur.getId()).thenReturn(1L);
            commande2.setFkUtilisateur(mockUtilistateur);
            /* #endregion creation commande identique */

            assertNotEquals(commande, commande2);
        }

        @Test
        @DisplayName("test avec obj dif prix facture")
        void testEqualDifPrixFact(){
            /* #region creation commande different */
            Commande commande2;
            commande2 = new Commande();
            commande2.setDateCommande(LocalDate.of(2025, 11, 11));
            commande2.setDateLivraison(LocalDate.of(2025,11,16));
            commande2.setPrixFacture(80);
            when(mockStatutCommande.getId()).thenReturn(1L);
            commande2.setFkSatutCommande(mockStatutCommande);
            when(mockUtilistateur.getId()).thenReturn(1L);
            commande2.setFkUtilisateur(mockUtilistateur);
            /* #endregion creation commande different */

            assertNotEquals(commande, commande2);
        }

        @Test
        @DisplayName("test avec obj dif statu commande")
        void testEqualDifFkStatutCommande(){
            /* #region creation commande different */

            Commande commande2;

            commande2 = new Commande();
            commande2.setDateCommande(LocalDate.of(2025, 11, 11));
            commande2.setDateLivraison(LocalDate.of(2025,11,16));
            commande2.setPrixFacture(50);


            when(mockStatutCommande.getId()).thenReturn(2L);
            commande2.setFkSatutCommande(mockStatutCommande);


            when(mockUtilistateur.getId()).thenReturn(1L);
            commande2.setFkUtilisateur(mockUtilistateur);

            /* #endregion creation commande different */

            assertNotEquals(commande, commande2);
        }

        @Test
        @DisplayName("test avec obj dif fkUtilisateur")
        void testEqualDifFkUtilisateur(){
            /* #region creation commande different */
            Commande commande2;
            commande2 = new Commande();
            commande2.setDateCommande(LocalDate.of(2025, 11, 11));
            commande2.setDateLivraison(LocalDate.of(2025,11,16));
            commande2.setPrixFacture(50);
            when(mockStatutCommande.getId()).thenReturn(1L);
            commande2.setFkSatutCommande(mockStatutCommande);
            when(mockUtilistateur.getId()).thenReturn(2L);
            commande2.setFkUtilisateur(mockUtilistateur);
            /* #endregion creation commande different */

            assertNotEquals(commande, commande2);
        }
        
        //TODO : test les hashCode

    /* #endregion test equals hashcode */

    /* #region test ToString */
    //TODO : test ToString


    /* #endregion test ToString */

    /* #region test validation */
    //TODO : test validation


    /* #endregion test validation */

    /* #region test abstractPersistable */
    //TODO : test abstractPersistable


    /* #endregion test abstractPersistable */

    /* #region test scenario metier */
    //TODO : test scenario metier


    /* #endregion test scenario metier */

}

