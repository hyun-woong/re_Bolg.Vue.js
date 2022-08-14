<template>
  <div>
    <h1>Today I Learned</h1>
  </div>
</template>

<script>
import { fetchPosts } from '@/api/index.js';
export default {
  methods: {
    async fetchData() {
      const response = await fetchPosts();
      console.log(response);
    },
  },
  created() {
    this.fetchData();
  },
};
</script>

<style></style>
