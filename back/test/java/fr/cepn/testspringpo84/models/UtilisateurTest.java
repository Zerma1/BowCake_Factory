package fr.cepn.testspringpo84.models;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de l'entité Utilisateur")
class UtilisateurTest {

    private Utilisateur utilisateur;
    private Validator validator;

    @BeforeEach
    void setUp() {
        // Initialiser le validateur pour tester les contraintes Bean Validation
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        /* #region creation Utilisateur */

        utilisateur = new Utilisateur();
        utilisateur.setNom("Pixi");
        utilisateur.setRole("CLIENT");
        utilisateur.setEmail("jean.pixi@example.com");
        utilisateur.setTelephon("0612345678");
        utilisateur.setMotDePasse("motdepasse123");
        utilisateur.setPoints(100);

        /* #endregion creation Utilisateur */

    }

    @AfterEach
    void tearDown() {
        utilisateur = null;
        validator = null;
    }

    /* #region test creation */

    @Test
    @DisplayName("Test de création d'un utilisateur valide")
    void testCreationUtilisateurValide() {
        assertNotNull(utilisateur);
        assertEquals("Pixi", utilisateur.getNom());
        assertEquals("CLIENT", utilisateur.getRole());
        assertEquals("jean.pixi@example.com", utilisateur.getEmail());
    }

    @Test
    @DisplayName("Test création avec constructeur par défaut")
    void testConstructeurParDefaut() {
        Utilisateur newUtilisateur = new Utilisateur();
        assertNotNull(newUtilisateur);
        assertNull(newUtilisateur.getNom());
        assertEquals(0, newUtilisateur.getPoints()); // Valeur par défaut
    }

    /* #endregion test creation */

    /* #region test getter */

    @Test
    @DisplayName("Test getter nom")
    void getNom() {
        assertEquals("Pixi", utilisateur.getNom());
    }

    @Test
    @DisplayName("Test getter role")
    void getRole() {
        assertEquals("CLIENT", utilisateur.getRole());
    }

    @Test
    @DisplayName("Test getter email")
    void getEmail() {
        assertEquals("jean.pixi@example.com", utilisateur.getEmail());
    }

    @Test
    @DisplayName("Test getter telephon")
    void getTelephon() {
        assertEquals("0612345678", utilisateur.getTelephon());
    }

    @Test
    @DisplayName("Test getter motDePasse")
    void getMotDePasse() {
        assertEquals("motdepasse123", utilisateur.getMotDePasse());
    }

    @Test
    @DisplayName("Test getter points")
    void getPoints() {
        assertEquals(100, utilisateur.getPoints());
    }

    @Test
    @DisplayName("Test getter points valeur par défaut")
    void getPointsValeurParDefaut() {
        Utilisateur newUtilisateur = new Utilisateur();
        assertEquals(0, newUtilisateur.getPoints());
    }

    /* #endregion test getter */

    /* #region test setters */

    @Test
    @DisplayName("Test setter nom")
    void setNom() {
        utilisateur.setNom("Martin");
        assertEquals("Martin", utilisateur.getNom());
    }

    @Test
    @DisplayName("Test setter role")
    void setRole() {
        utilisateur.setRole("ADMIN");
        assertEquals("ADMIN", utilisateur.getRole());
    }

    @Test
    @DisplayName("Test setter email")
    void setEmail() {
        utilisateur.setEmail("nouveau@example.com");
        assertEquals("nouveau@example.com", utilisateur.getEmail());
    }

    @Test
    @DisplayName("Test setter telephon")
    void setTelephon() {
        utilisateur.setTelephon("0698765432");
        assertEquals("0698765432", utilisateur.getTelephon());
    }

    @Test
    @DisplayName("Test setter motDePasse")
    void setMotDePasse() {
        utilisateur.setMotDePasse("nouveaumdp1");
        assertEquals("nouveaumdp1", utilisateur.getMotDePasse());
    }

    @Test
    @DisplayName("Test setter points")
    void setPoints() {
        utilisateur.setPoints(500);
        assertEquals(500, utilisateur.getPoints());
    }

    @Test
    @DisplayName("Test setter points à zéro")
    void setPointsZero() {
        utilisateur.setPoints(0);
        assertEquals(0, utilisateur.getPoints());
    }

    /* #endregion test setters */

    /* #region test not blank */

    @Test
    @DisplayName("Test validation nom vide")
    public void testValidationNomVid(){
        utilisateur.setNom("");
        Set<ConstraintViolation<Utilisateur>> violations = validator.validate(utilisateur);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Test validation email vide")
    void testValidationEmailVide() {
        utilisateur.setEmail("");
        Set<ConstraintViolation<Utilisateur>> violations = validator.validate(utilisateur);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Test validation telephon vide")
    void testValidationTelephonVide() {
        utilisateur.setTelephon("");
        Set<ConstraintViolation<Utilisateur>> violations = validator.validate(utilisateur);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Test validation motDePasse vide")
    void testValidationMDPVide(){
        utilisateur.setMotDePasse("");
        Set<ConstraintViolation<Utilisateur>> violations = validator.validate(utilisateur);
        assertFalse(violations.isEmpty());
    }

    /* #endregion test not blank */

    /* #region validation non null */

    @Test
    @DisplayName("Test validation nom null")
    void testValidationNomNull() {
        assertThrows(NullPointerException.class, ()-> utilisateur.setNom(null));
    }

    @Test
    @DisplayName("Test validation role null")
    void testValidationRoleNull() {
        assertThrows(NullPointerException.class, ()-> utilisateur.setRole(null));
    }

    @Test
    @DisplayName("Test validation email null")
    void testValidationEmailNull() {
        assertThrows(NullPointerException.class, ()-> utilisateur.setEmail(null));
    }

    @Test
    @DisplayName("Test validation telephon null")
    void testValidationTelephonNull() {
        assertThrows(NullPointerException.class, ()-> utilisateur.setTelephon(null));
    }

    @Test
    @DisplayName("Test validation motDePasse null")
    void testValidationMotDePasseNull() {
        assertThrows(NullPointerException.class, ()-> utilisateur.setMotDePasse(null));
    }

    /* #endregion validation non null */

    /* #region test validation */

    @Test
    @DisplayName("Test validation utilisateur valide")
    void testValidationUtilisateurValide() {
        Set<ConstraintViolation<Utilisateur>> violations = validator.validate(utilisateur);
        assertTrue(violations.isEmpty(), "Un utilisateur valide ne devrait avoir aucune violation");
    }

    @Test
    @DisplayName("Test validation email trop court (moins de 5 caractères)")
    void testValidationEmailTropCourt() {
        utilisateur.setEmail("a@b");
        Set<ConstraintViolation<Utilisateur>> violations = validator.validate(utilisateur);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Test validation email trop long (plus de 100 caractères)")
    void testValidationEmailTropLong() {
        String emailTropLong = "a".repeat(95) + "@test.com"; // > 100 caractères
        utilisateur.setEmail(emailTropLong);
        Set<ConstraintViolation<Utilisateur>> violations = validator.validate(utilisateur);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Test validation email valide aux limites min (5 caractères)")
    void testValidationEmailLimiteMin() {
        utilisateur.setEmail("a@b.c");
        Set<ConstraintViolation<Utilisateur>> violations = validator.validate(utilisateur);
        assertTrue(violations.stream()
                .noneMatch(v -> v.getMessage().contains("min = 5")));
    }

    @Test
    @DisplayName("Test validation telephon trop court")
    void testValidationTelephonTropCourt() {
        utilisateur.setTelephon("06123456"); // Moins de 10 caractères
        Set<ConstraintViolation<Utilisateur>> violations = validator.validate(utilisateur);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Test validation telephon trop long")
    void testValidationTelephonTropLong() {
        utilisateur.setTelephon("061234567890"); // Plus de 10 caractères
        Set<ConstraintViolation<Utilisateur>> violations = validator.validate(utilisateur);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Test validation telephon exactement 10 caractères")
    void testValidationTelephonValide() {
        utilisateur.setTelephon("0612345678");
        Set<ConstraintViolation<Utilisateur>> violations = validator.validate(utilisateur);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Test validation motDePasse vide")
    void testValidationMotDePasseVide() {
        utilisateur.setMotDePasse("");
        Set<ConstraintViolation<Utilisateur>> violations = validator.validate(utilisateur);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Test validation motDePasse trop court (moins de 10 caractères)")
    void testValidationMotDePasseTropCourt() {
        utilisateur.setMotDePasse("court123"); // 8 caractères
        Set<ConstraintViolation<Utilisateur>> violations = validator.validate(utilisateur);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Test validation motDePasse minimum 10 caractères")
    void testValidationMotDePasseMinimum() {
        utilisateur.setMotDePasse("motdepasse"); // 10 caractères
        Set<ConstraintViolation<Utilisateur>> violations = validator.validate(utilisateur);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Test validation points négatifs")
    void testValidationPointsNegatifs() {
        utilisateur.setPoints(-10);
        Set<ConstraintViolation<Utilisateur>> violations = validator.validate(utilisateur);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("points")));
    }

    @Test
    @DisplayName("Test validation points à zéro")
    void testValidationPointsZero() {
        utilisateur.setPoints(0);
        Set<ConstraintViolation<Utilisateur>> violations = validator.validate(utilisateur);
        assertTrue(violations.isEmpty(), "Points à 0 devrait être valide avec @Min(0)");
    }

    @Test
    @DisplayName("Test validation points positifs")
    void testValidationPointsPositifs() {
        utilisateur.setPoints(1000);
        Set<ConstraintViolation<Utilisateur>> violations = validator.validate(utilisateur);
        assertTrue(violations.isEmpty());
    }

    /* #endregion test validation */

    /* #region test equals hashcode */

    @Test
    @DisplayName("Test equals avec même objet")
    void testEquals() {
        assertEquals(utilisateur, utilisateur);
    }

    @Test
    @DisplayName("Test equals avec objet identique")
    void testEqualsObjetIdentique() {
        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setNom("Pixi");
        utilisateur2.setRole("CLIENT");
        utilisateur2.setEmail("jean.pixi@example.com");
        utilisateur2.setTelephon("0612345678");
        utilisateur2.setMotDePasse("motdepasse123");
        utilisateur2.setPoints(100);

        assertEquals(utilisateur, utilisateur2);
    }

    @Test
    @DisplayName("Test equals avec objet différent (nom différent)")
    void testEqualsObjetDifferentNom() {
        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setNom("Martin"); // Différent
        utilisateur2.setRole("CLIENT");
        utilisateur2.setEmail("jean.pixi@example.com");

        assertNotEquals(utilisateur, utilisateur2);
    }

    @Test
    @DisplayName("Test equals avec objet différent (email différent)")
    void testEqualsObjetDifferentEmail() {
        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setNom("Pixi");
        utilisateur2.setRole("CLIENT");
        utilisateur2.setEmail("autre@example.com"); // Différent

        assertNotEquals(utilisateur, utilisateur2);
    }

    @Test
    @DisplayName("Test equals avec null")
    void testEqualsAvecNull() {
        assertNotEquals(null, utilisateur);
    }

    @Test
    @DisplayName("Test equals avec objet d'une autre classe")
    void testEqualsAutreClasse() {
        assertNotEquals(utilisateur, "Une chaîne de caractères");
    }

    @Test
    @DisplayName("Test hashCode cohérence")
    void testHashCode() {
        int hashCode1 = utilisateur.hashCode();
        int hashCode2 = utilisateur.hashCode();
        assertEquals(hashCode1, hashCode2, "Le hashCode doit être cohérent");
    }

    @Test
    @DisplayName("Test hashCode objets égaux")
    void testHashCodeObjetsEgaux() {
        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setNom("Pixi");
        utilisateur2.setRole("CLIENT");
        utilisateur2.setEmail("jean.pixi@example.com");
        utilisateur2.setTelephon("0612345678");
        utilisateur2.setMotDePasse("motdepasse123");

        assertEquals(utilisateur.hashCode(), utilisateur2.hashCode(),
                "Deux objets égaux doivent avoir le même hashCode");
    }

    @Test
    @DisplayName("Test canEqual")
    void canEqual() {
        Utilisateur utilisateur2 = new Utilisateur();
        assertTrue(utilisateur.canEqual(utilisateur2),
                "canEqual doit retourner true pour une instance de la même classe");
    }

    /* #endregion test equals hashcode */

    /* #region ToString */

    @Test
    @DisplayName("Test toString contient les informations")
    void testToString() {
        String utilisateurString = utilisateur.toString();
        assertNotNull(utilisateurString);
        assertTrue(utilisateurString.contains("Pixi"), "toString devrait contenir le nom");
        assertTrue(utilisateurString.contains("CLIENT"), "toString devrait contenir le role");
    }

    @Test
    @DisplayName("Test toString ne contient pas le mot de passe")
    void testToStringPasDeMotDePasse() {
        String utilisateurString = utilisateur.toString();
        assertFalse(utilisateurString.contains("motdepasse123"),
                "toString ne devrait pas exposer le mot de passe");
    }

    /* #endregion ToString */

    /* #region AbstractPersistable */

    @Test
    @DisplayName("Test ID null avant persistance")
    void testIdNullAvantPersistance() {
        assertNull(utilisateur.getId(), "L'ID doit être null avant la persistance");
    }

    @Test
    @DisplayName("Test isNew avant persistance")
    void testIsNewAvantPersistance() {
        assertTrue(utilisateur.isNew(), "L'entité doit être considérée comme nouvelle avant la persistance");
    }

    /* #endregion AbstractPersistable */

    //demander a l'IA
    /* #region test scenario metier */

    @Test
    @DisplayName("Test ajout de points")
    void testAjoutPoints() {
        int pointsInitiaux = utilisateur.getPoints();
        utilisateur.setPoints(pointsInitiaux + 50);
        assertEquals(pointsInitiaux + 50, utilisateur.getPoints());
    }

    @Test
    @DisplayName("Test modification du role CLIENT vers ADMIN")
    void testChangementRole() {
        utilisateur.setRole("CLIENT");
        assertEquals("CLIENT", utilisateur.getRole());

        utilisateur.setRole("ADMIN");
        assertEquals("ADMIN", utilisateur.getRole());
    }

    @Test
    @DisplayName("Test utilisateur avec email aux limites de taille")
    void testEmailLimites() {
        // Email de 100 caractères exactement (limite max)
        String email100 = "a".repeat(88) + "@example.com"; // 100 caractères
        utilisateur.setEmail(email100);
        Set<ConstraintViolation<Utilisateur>> violations = validator.validate(utilisateur);
        assertTrue(violations.isEmpty(), "Email de 100 caractères devrait être valide");
    }

    @Test
    @DisplayName("Test tous les champs obligatoires présents")
    void testChampsObligatoires() {
        assertAll("Tous les champs obligatoires doivent être non null",
                () -> assertNotNull(utilisateur.getNom(), "nom est obligatoire"),
                () -> assertNotNull(utilisateur.getRole(), "role est obligatoire"),
                () -> assertNotNull(utilisateur.getEmail(), "email est obligatoire"),
                () -> assertNotNull(utilisateur.getTelephon(), "telephon est obligatoire"),
                () -> assertNotNull(utilisateur.getMotDePasse(), "motDePasse est obligatoire"),
                () -> assertNotNull(utilisateur.getPoints(), "points est obligatoire")
        );
    }

    /* #endregion test scenario metier */

}