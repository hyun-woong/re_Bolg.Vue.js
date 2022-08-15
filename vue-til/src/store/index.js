import Vue from 'vue';
import Vuex from 'vuex';
import { getUserFromCookie, getAuthFromCookie, saveAuthToCookie, saveUserToCookie } from '@/utils/cookies';
import { loginUser } from '@/api/auth.js';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    username: getUserFromCookie() || '',
    token: getAuthFromCookie() || '',
  },
  getters: {
    isLogin(state) {
      return state.username !== '';
    },
  },
  mutations: {
    setUsername(state, username) {
      state.username = username;
    },
    clearUsername(state) {
      state.username = '';
      state.token = '';
    },
    setToken(state, token) {
      state.token = token;
    },
  },
  actions: {
    //LOGIN(context.commit)
    async LOGIN({ commit }, userData) {
      const response = await loginUser(userData);
      console.log(response.headers['authorization']);
      commit('setToken', response.headers['authorization']);
      commit('setUsername', userData.userId);
      saveAuthToCookie(response.headers['authorization']);
      saveUserToCookie(userData.userId);
      alert(`${userData.userId} 님 로그인 되었습니다.`);
      return response.data;
    },
  },
});
