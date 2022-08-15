<template>
  <form @submit.prevent="submitForm">
    <article class="message">
      <div class="message-header">
        <input v-model="title" type="text" placeholder="HELLOW WORLD" />
        <button class="delete" aria-label="delete"></button>
      </div>
      <textarea rows="5" v-model="content" placeholder="content..." class="message-body" />
      <p class="validation-text warning" v-if="!isContentValid">
        Contents length must be less than 200
      </p>
      <button type="submit">저 장</button>
    </article>
  </form>
</template>

<script>
import { createPost } from '@/api/Board.js';

export default {
  data() {
    return {
      title: '',
      content: '',
    };
  },
  computed: {
    isContentValid() {
      return this.content.length <= 200;
    },
  },
  methods: {
    async submitForm() {
      try {
        const postDate = {
          title: this.title,
          content: this.content,
        };
        console.log(postDate);
        const response = await createPost(postDate);
        console.log(response);
        await this.$router.push('/main');
      } catch (e) {
        console.log(e);
      }
    },
  },
};
</script>

<style scoped>
@import 'https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css';

.message {
  width: 600px;
  height: 400px;

  margin: 200px 0px 0px 700px;
}
</style>
