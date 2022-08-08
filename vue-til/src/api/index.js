import axios from 'axios';
import store from '@/store';
import { setInterceptors } from '@/api/common/interceptors.js';

function createInstance() {
  const instance = axios.create({
    // baseURL: 'http://localhost:8000/api',
    baseURL: process.env.VUE_APP_API_URL,
    headers: {
      Authorization: store.state.token,
    },
  });
  return setInterceptors(instance);
}

const instance = createInstance;

function registerUser(userData) {
  return instance.post('/signup', userData);
}

function loginUser(userData) {
  return instance.post('/login', userData);
}

export { registerUser, loginUser };
