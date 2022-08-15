<template>
  <article class="media">
    <figure class="media-left">
      <p class="image is-64x64">
        <img src="https://bulma.io/images/placeholders/128x128.png" />
      </p>
    </figure>
    <div class="media-content">
      <div class="content">
        <p>
          <strong>{{ item.title }}</strong> <small>{{ item.username }}</small> <small>{{ item.date }}</small>
          <br>
          {{ item.title }} {{ item.username }}
        </p>
      </div>
      <nav class="level is-mobile">
        <div class="level-left">
          <a class="level-item">
            <span class="icon is-small"><i class="fas fa-reply"></i></span>
          </a>
          <a class="level-item">
            <span class="icon is-small"><i class="fas fa-retweet"></i></span>
          </a>
          <a class="level-item">
            <span class="icon is-small"><i class="fas fa-heart"></i></span>
          </a>
        </div>
      </nav>
    </div>
    <div class="media-right">
      <button class="delete" @click="deletePost"></button>
    </div>
  </article>
</template>

<script>
import { deletePost } from '@/api/Board.js';
export default {
  props: {
    // 값이 넘어오는지 체크해줌(벨리 데이트)
    item: {
      type: Object,
      required: true,
    },
  },
  methods: {
    async deletePost() {
      if (confirm('You want to delete it?')) {
        await deletePost(this.item.boardId);
        this.$emit('refresh');
        alert('삭제 완료');
      }
    },
  },
};
</script>

<style></style>