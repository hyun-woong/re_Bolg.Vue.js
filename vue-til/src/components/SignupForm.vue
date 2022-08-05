<template>
  <!--.prevent 는 제출하고, 페이지를 다시 이동하지 않음-->
  <form @submit.prevent="submitForm">
    <div>
      <label for="username">ID : </label>
      <input id="username" type="text" v-model="username"/>
    </div>
    <div>
      <label for="password">Password : </label>
      <input id="password" type="text" v-model="password"/>
    </div>
    <div>
      <label for="nickname">Nickname : </label>
      <input id="nickname" type="text" v-model="nickname"/>
    </div>
    <button type="submit">Login</button>
  </form>
</template>

<script>
import {registerUser} from "@/api/index";

export default {
  data() {
    return {
      username: '',
      password: '',
      nickname: '',
    }
  },
  methods: {
    submitForm() {
      alert('성공!')
      console.log('Form submit')
      registerUser();
    },
  }
};
</script>

<style></style>