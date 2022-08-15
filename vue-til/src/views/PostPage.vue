<template>
  <PostForm></PostForm>
</template>

<script>
import PostForm from '@/components/PostForm.vue';
import { createPost } from '@/api/index.js';
export default {
  components: {
    PostForm,
  },
};
</script>

<style></style>