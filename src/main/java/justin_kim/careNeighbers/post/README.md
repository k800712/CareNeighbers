## END POINT

1. Create Post
- URL: /api/posts
- Method: POST
- Body:
{
"title": "게시물 제목",
"content": "게시물 내용",
"authorId": 1,
"noticeBoardId": 1
}
- Response: 생성된 Post 객체

2. Get All Posts
- URL: /api/posts
- Method: GET
- Response: Post 객체 리스트

3. Get Post by ID
- URL: /api/posts/{id}
- Method: GET
- Response: 요청된 ID의 Post 객체
4. Update Post
- URL: /api/posts/{id}
- Method: PUT
- Body:
{
"title": "수정된 게시물 제목",
"content": "수정된 게시물 내용"
}
- Response: 수정된 Post 객체

5. Delete Post
- URL: /api/posts/{id}
- Method: DELETE

6. Object Structure
   {
   "id": 1,
   "title": "게시물 제목",
   "content": "게시물 내용",
   "createdAt": "2024-12-31T12:00:00",
   "authorId": 1,
   "views": 0,
   "comments": []
   }