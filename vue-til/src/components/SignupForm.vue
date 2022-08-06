<template>
  <div class="wrap">
    <form @submit.prevent="submitForm" class="box">
      <div class="field">
        <label class="label" for="email">Email</label>
        <div class="control">
          <input v-model="email" id="email" class="input" type="email" placeholder="e.g. alex@example.com">
        </div>
      </div>

      <div class="field">
        <label class="label" for="password">Password</label>
        <div class="control">
          <input v-model="password" id="password" class="input" type="password" placeholder="********">
        </div>
      </div>

      <div class="field">
        <label class="label" for="nickname">NickName</label>
        <div class="control">
          <input v-model="nickname" id="nickname" class="input" type="text" placeholder="nickname">
        </div>
      </div>

      <div class="btn-wrap">
        <button class="button is-primary" type="submit">Sign in</button>
      </div>
    </form>

  </div>
</template>

<script>
import {registerUser} from "@/api/index";

export default {
  data() {
    return {
      email: '',
      password: '',
      nickname: '',
    }
  },
  methods: {
    async submitForm() {
      const userData = {
        email: this.email,
        password: this.password,
        nickname: this.nickname,
      };
      // const response = await registerUser(userData);
      const {data} = await registerUser(userData);
      alert(`${data.nickname} 님이 가입되었습니다.`)
      // this.logMessage = `${data.nickname} 님이 가입되었습니다.`;
      // console.log(response)
      this.initForm();
    },
    initForm() {
      this.email = '';
      this.password = '';
      this.nickname = '';
    },
  }
};
</script>

<style scoped>
@import "https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css";

.box {
  width:500px;
  height:370px;
  position: absolute;
  left:50%;
  top:46%;
  margin-left:-250px;
  margin-top:-150px;
}

.btn-wrap > button {
  width: 460px;
  margin-top: 25px;
  font-weight: bold;
}
</style>