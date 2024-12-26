## EndPoint
1. 할 일 생성
- URL : `/api/todos`
- Method : POST
- Body : {
  "title": "할 일 제목",
  "description": "할 일 설명",
  "dueDate": "2023-12-31T23:59:59",
  "priority": "HIGH"
  }
- Response : 생성된 Todo 객체

2. 모든 할 일 조회
- URL : `/api/todos`
- Method : GET
- Response : Todo 객체 리스트

3. 특정 할 일 조회
- URL : `/api/todos/{id}`
- Method : GET
- Response : 요청된 id의 Todo 객체 리스트

4. 할 일 수정
- URL : `/api/todos/{id}`
- Method : PUT
- Body : 수정할 Todo 객체 정보
- Response : 수정된  Todo 객체 정보

5. 할 일 삭제
- URL : `/api/todos/{id}`
- Method : DELETE

6. 할 일 완료 처리
   URL: `/api/todos/{id}/complete`
   Method: PATCH
   Response: 완료 처리된 Todo 객체
7. 할 일 검색
   URL: `/api/todos/search?keyword={keyword}`
   Method: GET
   Response: 검색 결과에 해당하는 Todo 객체 리스트
8. 오늘의 할 일 조회
   URL: `/api/todos/today`
   Method: GET
   Response: 오늘 날짜의 Todo 객체 리스트
9. 완료된 할 일 조회
   URL: `/api/todos/completed`
   Method: GET
   Response: 완료된 상태의 Todo 객체 리스트
10. Todo 객체 구조
    {
    "id": 5,
    "title": "할 일 제목5",
    "description": "수업 준비하기",
    "createdAt": "2024-12-26T11:58:18.543684",
    "dueDate": "2023-12-31T23:59:59",
    "completedAt": null,
    "status": "PENDING",
    "priority": "HIGH"
    }
- status: "PENDING" 또는 "COMPLETED"
- priority: "LOW", "MEDIUM", 또는 "HIGH"
    
    






