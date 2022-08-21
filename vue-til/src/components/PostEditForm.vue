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
      <button type="submit">수정하기</button>
    </article>
  </form>
</template>

<script>
import { fetchPost, editPost } from '@/api/Board.js';

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
      const id = this.$route.params.id;
      try {
        await editPost(id, {
          title: this.title,
          content: this.content,
        });
        await this.$router.push('/main');
      } catch (e) {
        console.log(e);
      }
    },
  },
  async created() {
    const id = this.$route.params.id;
    // const id = this.$props.item.boardId;
    console.log(id);
    const { data } = await fetchPost(id);
    this.title = data.title;
    this.content = data.content;
    console.log(data);
  },
};
</script>

<style></style>
