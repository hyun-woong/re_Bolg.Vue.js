<template>
  <div>
    <h1>Today I Learned</h1>
    <LoadingSpinner v-if="isLoading"></LoadingSpinner>
    <div v-else>
      <MainContentListForm
        v-for="item in postItems"
        :key="item.boardId"
        :item="item"
      ></MainContentListForm>
    </div>
  </div>
</template>

<script>
import { fetchPosts } from '@/api/board.js';
import MainContentListForm from '@/components/MainContentListForm.vue';
import LoadingSpinner from '@/components/common/LoadingSpinner.vue';
export default {
  components: {
    MainContentListForm,
    LoadingSpinner,
  },
  data() {
    return {
      postItems: [],
      isLoading: false,
    };
  },
  methods: {
    async fetchData() {
      this.isLoading = true;
      const { data } = await fetchPosts();
      this.isLoading = false;
      this.postItems = data;
    },
  },
  created() {
    this.fetchData();
  },
};
</script>

<style></style>
