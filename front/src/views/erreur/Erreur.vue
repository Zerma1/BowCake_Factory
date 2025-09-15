<template>
  <div class="erreur-page">
    <h1>
      <a
        class="erreur-code color_dominante_rouge"
        @click="goToHttpCat"
        href="javascript:void(0)"
      >
        {{ code }}
      </a>
      - {{ message }}
    </h1>
    <div v-if="description" class="erreur-details">
      <img :src="`https://http.cat/${code}`" :alt="`Erreur ${code}`" />
      <p>{{ description }}</p>
    </div>
    <RouterLink to="/">Retour à l’accueil</RouterLink>
  </div>
</template>

<script setup lang="ts">
  import { computed } from 'vue'

  const props = defineProps<{code?: string}>()
  const code = props.code ?? '404'

  // Liste des messages connus
  const messages: Record<string, string> = {
    '400': "Mauvaise requette 🖕",
    '402': "Paiement requis 💰",
    '403': 'Accès refusé 🔒',
    '404': 'Page non trouvée 🚧',
    '450': "Bloquer par le control parentale 🧒",
    '500': 'Erreur interne du serveur 💥',
    '418': "Je suis une théière 🫖 (I’m a teapot)"
  }

  // Descriptions optionnelles
  const descriptions: Record<string, string> = {
    '400': "🖕 reformule ta requette 🖕",
    '402': "Un paiement est nécessaire 💰",
    '403': "Vous n’avez pas la permission d’accéder à cette ressource 🔒",
    '404': "La page que vous cherchez est introuvable 🚧",
    '450': "Vois avec tes parents 🧒",
    '500': "Le serveur a rencontré une erreur inattendue 💥",
    '418': "Ceci est une blague du protocole HTTP : je suis une théière 🫖"
  }

  // Message dynamique
  const message = computed(() => messages[code as string] || 'Une erreur inconnue est survenue ❓')
  const description = computed(() => descriptions[code as string] || 'Une erreur inconnue est survenue 🙈 🙉 🙊')

  // Lien http.cat
  const goToHttpCat = () => {
    window.open(`https://http.cat/status/${code}`, '_blank')
  }
</script>

<style scoped>
  .erreur-page {
    text-align: center;
    padding: 50px;
    color: black;
  }

  .erreur-code {
    font-size: 3rem;
    margin-bottom: 20px;
    cursor: pointer;
  }

  .erreur-details {
    margin: 20px auto;
  }

  .erreur-details img {
    max-width: 400px;
    border-radius: 8px;
    margin-bottom: 15px;
  }
</style>
