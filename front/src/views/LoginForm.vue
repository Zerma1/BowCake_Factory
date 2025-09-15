<template>
  <div class="login-form">
    <form @submit.prevent="handleLogin">
      <div class="login-form__input">
        <label for="username">Username</label>
        <input id="username" v-model="username" type="text" placeholder="Username" required/>
      </div>
      <div class="login-form__input">
        <label for="password">Password</label>
        <input id="password" type="password" placeholder="Password" required/>
      </div>
      <div class="login-form__input">
        <button type="submit">Login</button>
      </div>
    </form>
  </div>

</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'

const router = useRouter();

</script>

<style scoped>

</style>
