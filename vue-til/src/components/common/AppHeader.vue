<template>
  <header>
    <nav class="navbar" role="navigation" aria-label="main navigation">
      <div class="navbar-brand">
        <router-link :to="loginLink" class="navbar-item">
          <img
            src="https://bulma.io/images/bulma-logo.png"
            width="112"
            height="28"
          />
        </router-link>

        <a
          role="button"
          class="navbar-burger"
          aria-label="menu"
          aria-expanded="false"
          data-target="navbarBasicExample"
        >
          <span aria-hidden="true"></span>
          <span aria-hidden="true"></span>
          <span aria-hidden="true"></span>
        </a>
      </div>

      <div id="navbarBasicExample" class="navbar-menu">
        <!--        상단 네비게이션 바-->
        <div class="navbar-start">
          <router-link to="/" class="navbar-item">
            Home
          </router-link>

          <router-link to="/main" class="navbar-item">
            Board
          </router-link>

          <!--          카테고리-->
          <div class="navbar-item has-dropdown is-hoverable">
            <a class="navbar-link">
              More
            </a>

            <div class="navbar-dropdown">
              <a class="navbar-item">
                <router-link to="/post">
                  Add
                </router-link>
              </a>
              <a class="navbar-item">
                Jobs
              </a>
              <a class="navbar-item">
                Contact
              </a>
              <hr class="navbar-divider" />
              <a class="navbar-item">
                Report an issue
              </a>
            </div>
          </div>
        </div>

        <!--        회원가입, 로그인-->
        <div class="navbar-end">
          <template v-if="isUserLogin">
            <span>{{ getLoginUser }}</span>
            <a href="javascript:;" @click="logoutUser">Logout</a>
          </template>
          <template v-else>
            <div class="navbar-item">
              <div class="buttons">
                <router-link to="/signup" class="button is-primary">
                  <strong>Sign up</strong>
                </router-link>
                <router-link to="/login" class="button is-light" id="login-btn">
                  Log in
                </router-link>
              </div>
            </div>
          </template>
        </div>
      </div>
    </nav>
  </header>
</template>

<script>
import { deleteCookie } from '@/utils/cookies.js';
export default {
  computed: {
    isUserLogin() {
      return this.$store.getters.isLogin;
    },
    getLoginUser() {
      return this.$store.state.username;
    },
    loginLink() {
      return this.$store.getters.isLogin ? '/main' : '/login';
    },
  },
  methods: {
    logoutUser() {
      this.$store.commit('clearUsername');
      this.$store.commit('clearToken');
      deleteCookie('til_auth');
      deleteCookie('til_user');
      this.$router.push('/login');
    },
  },
};
</script>

<style scoped>
@import 'https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css';

#login-btn {
  font-weight: bold;
}
</style>
