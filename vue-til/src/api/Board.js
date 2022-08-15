// 학습노트 관련 API
// 학습 노트 데이터를 조회하는 API
import { board } from '@/api/index.js';

function fetchPosts() {
  return board.get();
}

// 학습 노트 데이터를 생성하는 API
function createPost(postData) {
  return board.post(postData);
}

export { fetchPosts, createPost };
