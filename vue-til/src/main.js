import Vue from 'vue';
import App from './App.vue';
import router from '@/routes/index';
import store from '@/store/index.js';
import { formatDate } from '@/utils/filters.js';

Vue.config.productionTip = false;
Vue.filter('formatDate', formatDate);

Vue.config.productionTip = false;

new Vue({
  render: h => h(App),
  store,
  router,
}).$mount('#app');
