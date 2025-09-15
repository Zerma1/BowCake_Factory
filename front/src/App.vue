<template>
  <div id="app">
    <header>
      <div class="container">

      </div>
      <div class="title">
        <RouterLink to="/">
          <div class="logo">
              <img src="@/resources/logo/logoBCF-2.png" height="125" width="125"/>
          </div>
        </RouterLink>
      </div>
      <div class="logIn">
        <RouterLink to="/log-in">log-in</RouterLink> /
        <RouterLink to="/sign-in">sign-in</RouterLink>
      </div>
    </header>

    <nav>
      <RouterLink to="/bowl">Bowl</RouterLink>
      <RouterLink to="/accessoirs">Accessoires</RouterLink>
      <RouterLink to="/blog">Blog</RouterLink>
    </nav>

    <div class="bande-deroulante">
<!--      les 3 item les plus vendu defile automatiquement sur une bande-->
<!--      https://super10.anvanto.com/super/themes/sushi_bar/fr/#-->
    </div>

    <main class="container">
      <RouterView />
    </main>

    <div class="about-us">
      <div class="about-overlay">
        <h2>ABOUT US</h2>
        <p>
          Découvrez notre passion pour les bowlcakes : des recettes uniques,
          savoureuses et équilibrées, préparées avec soin pour régaler vos papilles 🍴
        </p>

        <div class="about-icons">
          <div class="icon-item">
            <span>💳</span>
            <p>Système de réduction</p>
          </div>
          <div class="icon-item">
            <span>🛎️</span>
            <p>Support 24/7</p>
          </div>
          <div class="icon-item">
            <span>🚚</span>
            <p>Livraison gratuite</p>
          </div>
        </div>
      </div>
    </div>

    <footer class="footer">
      <div class="footer-container">

        <!-- Colonne Produits -->
        <div class="footer-col">
          <h3>PRODUITS</h3>
          <ul>
            <li><RouterLink to="/promotions">Promotions</RouterLink></li>
            <li><RouterLink to="/nouveaux-produits">Nouveaux produits</RouterLink></li>
            <li><RouterLink to="/meilleures-ventes">Meilleures ventes</RouterLink></li>
          </ul>
        </div>

        <!-- Colonne Société -->
        <div class="footer-col">
          <h3>NOTRE SOCIÉTÉ</h3>
          <ul>
            <li><RouterLink to="/livraison">Livraison</RouterLink></li>
            <li><RouterLink to="/mentions-legales">Mentions légales</RouterLink></li>
            <li><RouterLink to="/conditions">Conditions d'utilisation</RouterLink></li>
            <li><RouterLink to="/a-propos">À propos de nous</RouterLink></li>
            <li><RouterLink to="/paiement-securise">Paiement sécurisé</RouterLink></li>
            <li><RouterLink to="/contact">Contactez-nous</RouterLink></li>
            <li><RouterLink to="/sitemap">Sitemap</RouterLink></li>
            <li><RouterLink to="/magasins">Magasins</RouterLink></li>
          </ul>
        </div>

        <!-- Colonne Compte -->
        <div class="footer-col">
          <h3>VOTRE COMPTE</h3>
          <ul>
            <li><RouterLink to="/compte">Informations personnelles</RouterLink></li>
            <li><RouterLink to="/commandes">Commandes</RouterLink></li>
            <li><RouterLink to="/avoirs">Avoirs</RouterLink></li>
            <li><RouterLink to="/adresses">Adresses</RouterLink></li>
            <li><RouterLink to="/bons">Bons de réduction</RouterLink></li>
          </ul>
        </div>

        <!-- Colonne Contact -->
        <div class="footer-col">
          <h3>INFORMATIONS DE MAGASIN</h3>
          <p>The Bol Cake Factory</p>
          <p>France</p>
          <p>+33 0 00 00 00 00</p>
          <p>Email : hello@bowlcake.com</p>
          <div class="payment-icons">
            <img src="@/resources/icons/visa.png" alt="Visa" />
            <img src="@/resources/icons/mastercard.png" alt="MasterCard" />
            <img src="@/resources/icons/paypal.png" alt="PayPal" />
          </div>
        </div>

      </div>
    </footer>
  </div>

</template>

<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router';
</script>

<style scoped>
.title{
  display: flex;
  justify-content: center;
  flex: auto;
}

.about-us {
  position: relative;
  background: url('@/resources/images/about-bg.jpg') no-repeat center center/cover;
  color: #000000;
  padding: 80px 20px;
  text-align: center;
}

.about-overlay {
  background: rgba(255, 255, 255, 0.6); /* effet foncé transparent */
  padding: 40px;
  border-radius: 10px;
  display: inline-block;
}

.about-us h2 {
  font-size: 2.5rem;
  margin-bottom: 20px;
  text-transform: uppercase;
}

.about-us p {
  max-width: 100%;
  margin: 0 auto 30px;
  font-size: 1.2rem;
  line-height: 1.6;
}

.about-icons {
  display: flex;
  justify-content: center;
  gap: 40px;
  margin-top: 20px;
}

.icon-item {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.icon-item span {
  font-size: 2rem;
  margin-bottom: 10px;
}

.icon-item p {
  text-transform: uppercase;
  font-size: 0.9rem;
  letter-spacing: 1px;
}

header {
  width: 100%; /* plein écran */
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
  background-color: #ffffff;
  color: #000000;
}

nav {
  width: 100%; /* plein écran */
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  gap: 20px;
  padding: 10px 20px;
  background-color: #0056b3;
}

main {
  max-width: 80%;
  margin: 20px auto;
  padding: 20px;
  background-color: #d8d2d2;
  min-height: calc(100vh - 120px);
}

.footer {
  background-color: #222;
  color: #ddd;
  padding: 40px 20px;
}

.footer-container {
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  max-width: 1200px;
  margin: auto;
}

.footer-col {
  flex: 1;
  min-width: 200px;
  margin: 20px;
}

.footer-col h3 {
  font-size: 16px;
  margin-bottom: 15px;
  color: #fff;
  text-transform: uppercase;
}

.footer-col ul {
  list-style: none;
  padding: 0;
}

.footer-col ul li {
  margin: 8px 0;
}

.footer-col ul li a {
  color: #aaa;
  text-decoration: none;
  transition: color 0.3s;
}

.footer-col ul li a:hover {
  color: #fff;
}

.payment-icons {
  margin-top: 15px;
  display: flex;
  gap: 10px;
}

.payment-icons img {
  height: 30px;
}
</style>
