<template>
  <div class="wrap">
    <form @submit.prevent="submitForm" class="box">
      <div class="field">
        <label class="label" for="userId">Email</label>
        <div class="control">
          <input
            v-model="userId"
            id="email"
            class="input"
            type="email"
            placeholder="alex@example.com"
          />
        </div>
      </div>

      <div class="field">
        <label class="label" for="password">Password</label>
        <div class="control">
          <input
            v-model="password"
            id="password"
            class="input"
            type="password"
            placeholder="********"
          />
        </div>
      </div>

      <div class="btn-wrap">
        <button
          :disabled="!isUserEmailValid || !checkPw"
          class="button is-primary"
          type="submit"
        >
          Login
        </button>
      </div>
    </form>
  </div>
</template>

<script>
import { validEmail } from '@/utils/validation.js';

export default {
  data() {
    return {
      userId: '',
      password: '',
    };
  },
  computed: {
    isUserEmailValid() {
      return validEmail(this.userId);
    },
    checkPw() {
      if (this.password === '') {
        return false;
      } else {
        return true;
      }
    },
  },
  methods: {
    async submitForm() {
      try {
        const userData = {
          userId: this.userId,
          password: this.password,
        };
        await this.$store.dispatch('LOGIN', userData);
        this.$router.push('/main');
      } catch (e) {
        console.log(e.response.data.description);
        alert(e.response.data.description);
      } finally {
        this.initForm();
      }
    },
    initForm() {
      this.userId = '';
      this.password = '';
    },
  },
};
</script>

<style scoped>
@import 'https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css';

.box {
  width: 500px;
  height: 370px;
  position: absolute;
  left: 50%;
  top: 46%;
  margin-left: -250px;
  margin-top: -150px;
}

.btn-wrap > button {
  width: 460px;
  margin-top: 25px;
  font-weight: bold;
}
</style>
