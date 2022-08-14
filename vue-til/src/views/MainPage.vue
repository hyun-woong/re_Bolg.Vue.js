<template>
  <div>
    <h1>Today I Learned</h1>
    <div v-if="isLoading">
      Loading...
    </div>
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
import { fetchPosts } from '@/api/index.js';
import MainContentListForm from '@/components/MainContentListForm.vue';
export default {
  components: { MainContentListForm },
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
