import axios from 'axios';
import { setInterceptors } from './common/interceptors.js';

function createInstance() {
  const instance = axios.create({
    // baseURL: 'http://localhost:8000/api',
    baseURL: process.env.VUE_APP_API_URL,
  });
  return setInterceptors(instance);
}

const instance = createInstance();

function registerUser(userData) {
  return instance.post('/signup', userData);
}

function loginUser(userData) {
  return instance.post('/login', userData);
}

export { registerUser, loginUser };
