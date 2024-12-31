## EndPoints

1. Create Comment
- URL: /api/comments
- Method: POST
- Body:
   {
   "content": "댓글 내용",
   "authorId": 1,
   "postId": 1,
   "parentCommentId": null
   }
- Response: 생성된 Comment 객체

2. Get All Comments
- URL: /api/comments
- Method: GET
- Response: Comment 객체 리스트
   
3. Get Comment by ID
- URL: /api/comments/{id}
- Method: GET
- Response: 요청된 ID의 Comment 객체

4. Get Comments by Post ID
- URL: /api/comments/post/{postId}
- Method: GET
- Response: 해당 Post에 대한 Comment 객체 리스트

5. Update Comment
- URL: /api/comments/{id}
- Method: PUT
- Body:
    {
   "content": "수정된 댓글 내용"
   }
   Response: 수정된 Comment 객체

6. Delete Comment
- URL: /api/comments/{id}
- Method: DELETE

7. Object Structure
   {
   "id": 1,
   "content": "댓글 내용",
   "createdAt": "2024-12-31T12:00:00",
   "authorId": 1,
   "postId": 1,
   "parentCommentId": null,
   "replies": []
   }