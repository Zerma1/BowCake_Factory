import { createRouter, createWebHistory } from 'vue-router'
import axios from 'axios'
import { setDevtoolsHook } from 'vue'
// import { useAuthStore } from '@/stores/AuthStore' // si tu as un store Pinia

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    /* #region page accecibles*/
    {
      path: '/',
      name: 'Home',
      component: () => import('@/views/Home.vue') // crée un vrai composant Home
    },
    {
      path: '/log-in',
      name: 'LogIn',
      component: () => import('@/views/LoginForm.vue')
    },
    {
      path: '/sing-in',
      name: 'sing-in',
      component: () => import('@/views/SingIn.vue')
    },
    {
      path: '/bowl',
      name: 'nos bowls',
      component: () => import('@/views/BolShop.vue')
    },

    //TODO : remplacer lien vers par vraie page
    {
      path: '/accessoirs',
      name: 'accessoirs',
      component: () => import('@/views/Accessoires.vue'),
    },
    {
      path: '/blog',
      name: 'blog',
      component: () => import('@/views/erreur/Erreur.vue'),
    },
    /* #endregion page accecibles */

    /* #region erreurs */

    {
      path: '/erreur/:code',
      name: 'Page Erreur',
      component: () => import('@/views/erreur/Erreur.vue'),
      props: true
    },
    {
      path: '/:pathMatch(.*)*',
      name: 'NotFound',
      component: () => import('@/views/erreur/Erreur.vue'),
      props: {code: '404'}
    },

    /* #endregion erreurs */

    /* #region footer */
    //TODO : remplacer lien vers par vraie page
    {
      path: '/promotions',
      name: 'promotions',
     component: () => import('@/views/erreur/Erreur.vue')
    },
    {
      path: '/nouveaux-produits',
      name: 'nouveaux-produits',
      component: () => import('@/views/erreur/Erreur.vue')
    },
    {
      path: '/meilleures-ventes',
      name: 'meilleures-ventes',
      component: () => import('@/views/erreur/Erreur.vue')
    },

    {
      path: '/livraison',
      name: 'livraison',
      component: () => import('@/views/erreur/Erreur.vue')
    },
    {
      path: '/mentions-legales',
      name: 'mentions-legales',
      component: () => import('@/views/erreur/Erreur.vue')
    },
    {
      path: '/conditions',
      name: 'conditions',
      component: () => import('@/views/erreur/Erreur.vue')
    },
    {
      path: '/a-propos',
      name: 'a-propos',
      component: () => import('@/views/erreur/Erreur.vue')
    },
    {
      path: '/paiement-securise',
      name: 'paiement-securise',
      component: () => import('@/views/erreur/Erreur.vue')
    },
    {
      path: '/contact',
      name: 'contact',
      component: () => import('@/views/erreur/Erreur.vue')
    },
    {
      path: '/sitemap',
      name: 'sitemap',
      component: () => import('@/views/erreur/Erreur.vue')
    },
    {
      path: '/magasins',
      name: 'magasins',
      component: () => import('@/views/erreur/Erreur.vue')
    },

    {
      path: '/compte',
      name: 'compte',
      component: () => import('@/views/erreur/Erreur.vue')
    },
    {
      path: '/commandes',
      name: 'commandes',
      component: () => import('@/views/erreur/Erreur.vue')
    },
    {
      path: '/avoirs',
      name: 'avoirs',
      component: () => import('@/views/erreur/Erreur.vue')
    },
    {
      path: '/adresses',
      name: 'adresses',
      component: () => import('@/views/erreur/Erreur.vue')
    },
    {
      path: '/bons',
      name: 'bons',
      component: () => import('@/views/erreur/Erreur.vue')
    }


    /* #endregion footer */
  ]
})
export default router
