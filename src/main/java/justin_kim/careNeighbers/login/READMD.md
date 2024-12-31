## END POINT

1. User Login
- URL: /api/auth/login
- Method: POST
- Body:
{
"usernameOrEmail": "사용자명 또는 이메일",
"password": "비밀번호"
}
- Response: 인증된 User 객체

2. Create User
- URL: /api/users
- Method: POST
- Body:
{
"username": "사용자명",
"email": "이메일",
"password": "비밀번호"
}
- Response: 생성된 User 객체

3. Get User by ID
- URL : /api/users/{id}
- Method : GET
- Response : 요청된 ID의 User 객체

4. Get All Users
- URL : /api/users
- Method : GET
- Response : User 객체 리스트

5. Update User
- URL : /api/users/{id}
- Method : PUT
- Body : 수정할 User 정보
- Response : 수정된 User 객체

6. Delete User
- URL : /api/users/{id}
- Method : DELETE

7. Object Structure

{
"id": 1,
"username": "사용자명",
"email": "이메일",
// 비밀번호는 해시화되어야 함.
}