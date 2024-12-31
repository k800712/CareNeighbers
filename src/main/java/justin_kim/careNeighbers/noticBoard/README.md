## END POINT

1. Create Notice Board
- URL: /api/noticeboards
- Method: POST
- Body:
{
"name": "공지사항 제목",
"title": "공지 제목",
"description": "공지 설명"
}
- Response: 생성된 NoticeBoard 객체

2. Get All Notice Boards
- URL: /api/noticeboards
- Method: GET
- Response: NoticeBoard 객체 리스트

3. Get Notice Board by ID
- URL: /api/noticeboards/{id}
- Method: GET
- Response: 요청된 ID의 NoticeBoard 객체

4. Update Notice Board
- URL: /api/noticeboards/{id}
- Method: PUT
- Body:
{
"name": "수정된 공지사항 제목",
"title": "수정된 공지 제목",
"description": "수정된 공지 설명"
}
- Response: 수정된 NoticeBoard 객체

5. Delete Notice Board
- URL: /api/noticeboards/{id}
- Method: DELETE

6. Object Structure

{
"id": 1,
"name": "공지사항 제목",
"title": "공지 제목",
"description": "공지 설명",
"createdAt": "2024-12-31T12:00:00",
"posts": []
}
 