## EndPoint
1. 할 일 생성
- URL :`/api/todos`
- Method : POST
- Body : {
  "title": "할 일 제목",
  "description": "할 일 설명",
  "dueDate": "2023-12-31T23:59:59",
  "priority": "HIGH"
  }
- Response : 생성된 Todo 객체

2. 모든 할 일 조회
- URL :`/api/todos`
- Method : GET
- Response : Todo 객체 리스트

4. 작성자 기준 조회
- URL : `/api/todos/by-username?username={username}`
- Method : GET
- Response : Todo 객체 리스트

5. 특정 할 일 조회
- URL :`/api/todos/{id}`
- Method : GET
- Response : 요청된 id의 Todo 객체 리스트

6. 할 일 수정
- URL :`/api/todos/{id}`
- Method : PUT
- Body : 수정할 Todo 객체 정보
- Response : 수정된  Todo 객체 정보

7. 할 일 삭제
- URL :`/api/todos/{id}`
- Method : DELETE

8. 할 일 완료 처리
 -  URL:`/api/todos/{id}/complete`
 -  Method: PATCH
 -  Response: 완료 처리된 Todo 객체
9. 할 일 검색
 -  URL:`/api/todos/search?keyword={keyword}`
 -  Method: GET
 -  Response: 검색 결과에 해당하는 Todo 객체 리스트
9. 오늘의 할 일 조회
 -  URL: `/api/todos/today`
 -  Method: GET
 -  Response: 오늘 날짜의 Todo 객체 리스트
10. 완료된 할 일 조회
 -  URL:`/api/todos/completed`
 -  Method: GET
 -  Response: 완료된 상태의 Todo 객체 리스트
11. 미완료된 할 일 조회
-  URL:`/api/todos/pending`
-  Method: GET
-  Response: 미완료된 상태의 Todo 객체 리스트

12. Todo 객체 구조
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

13. 통계 및 생산성 보고서 조회
- URL:`/api/todos/statistics`
- Method: GET
- Response: 통계 정보와 생산성 보고서를 포함한 Map<String, Object>
  {
  "totalTodos": 100,
  "completedTodos": 75,
  "pendingTodos": 25,
  "highPriorityTodos": 30,
  "overdueTodos": 5,
  "completionRate": 75.0,
  "averageCompletionTime": 120.5
  }

14. 할 일 재개(완료상태에서 미완료 상태로 변경)
- URL:`/api/todos/{id}/reopen`
- Method: PATCH
- Response: 재개된 Todo 객체

15. 첨부 파일 추가
- URL:`/api/todos/{id}/attachments`
- Method: POST
- Body: MultipartFile (form-data)
- Response: 첨부 파일이 추가된 Todo 객체

16. 첨부 파일 다운로드
- URL:`/api/todos/{todoId}/attachments/{attachmentId}`
- Method: GET
- Response: 첨부 파일 데이터

17. 정렬된 할 일 목록 조회
- URL: /api/todos/sorted?sortBy={sortCriteria}
- Method: GET
- Query Parameters:
  sortBy: "newest" (최신순), 
  "mostViewed" (조회순), 
  "mostLiked" (추천순)
- Response: 정렬된 Todo 객체 리스트

18. 할 일 조회수 증가
- URL: /api/todos/{id}/view
- Method: PATCH
- Response: 조회수가 증가된 Todo 객체

19. 할 일 추천수 증가 
- URL: /api/todos/{id}/like
- Method: PATCH
- Response: 추천수가 증가된 Todo 객체

20. Todo 객체 구조(업데이트)
{
    "id": 5,
    "title": "할 일 제목5",
    "description": "수업 준비하기",
    "createdAt": "2024-12-26T11:58:18.543684",
    "dueDate": "2023-12-31T23:59:59",
    "completedAt": null,
    "status": "PENDING",
    "priority": "HIGH",
    "viewCount": 10,
    "likeCount": 5
    }
    status: "PENDING" 또는 "COMPLETED"
    priority: "LOW", "MEDIUM", 또는 "HIGH"
    viewCount: 조회수