import Vue from 'vue';
import VueRouter from 'vue-router';
// import LoginPage from '@/views/LoginPage.vue'
// import SignupPage from '@/views/SignupPage.vue'

Vue.use(VueRouter); //플러그인을 실행하기 위해서, 초기화 하기위해서 사용하는 코드

//라우터 인스턴스가 밖으로 나가게 됨(새로운 뷰라우터 인스턴스를 만들고, export 를 통해 밖으로 보내줌)
export default new VueRouter({
  mode: 'history', //URL 의 '#' remove
  routes: [
    //VueRouter 에 의해서 제어되는 페이지 정보를 담는 배열
    {
      path: '/',
      redirect: '/login', //초기화면
    },
    {
      path: '/login',
      //화살표 함수로 import 해주면 이게 바로 코드스플리팅(LazyLoading)
      component: () => import('@/views/LoginPage.vue'),
    },
    {
      path: '/signup',
      component: () => import('@/views/SignupPage.vue'),
    },
    {
      path: '/main',
      component: () => import('@/views/MainPage.vue'),
    },
    {
      path: '/post',
      component: () => import('@/views/PostPage'),
    },
    {
      path: '*',
      component: () => import('@/views/NotFoundPage.vue'),
    },
  ],
});
