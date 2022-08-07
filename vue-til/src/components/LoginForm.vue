<template>
  <form @submit.prevent="submitForm">
    <div>
      <label for="userId">ID : </label>
      <input id="userId" type="text" v-model="userId" />

      <label for="password">Password : </label>
      <input id="password" type="text" v-model="password" />
    </div>
    <button type="submit">Login</button>
  </form>
</template>

<script>
import { loginUser } from '@/api/index.js';

export default {
  data() {
    return {
      userId: '',
      password: '',
    };
  },
  methods: {
    async submitForm() {
      const userData = {
        userId: this.userId,
        password: this.password,
      };
      const response = await loginUser(userData);
      alert(`${userData.userId} 님 로그인 되었습니다.`);
      console.log(response);
      this.initForm();
    },
    initForm() {
      this.userId = '';
      this.password = '';
    },
  },
};
</script>

<style scoped></style>
