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
    <p>{{logMessage}}</p>

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
      logMessage: '',
    }
  },
  methods: {
    async submitForm() {
      const userData = {
        username: this.username,
        password: this.password,
        nickname: this.nickname,
      };
      // const response = await registerUser(userData);
      const {data} = await registerUser(userData);
      this.logMessage = `${data.username} 님이 가입되었습니다.`;
      console.log(data) // 바로 response 의 data 를 꺼낼 수 있음
      // console.log(response)
      this.initForm();
    },
    initForm() {
      this.username = '';
      this.password = '';
      this.nickname = '';
    },
  }
};
</script>

<style></style>