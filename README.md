# Advocare Project

Full‑stack legal aid application with a Spring Boot backend and a frontend (to be added).

## Backend (Spring Boot)

**Folder:** `backend/`  
**Tech:** Java 17, Spring Boot 3, Spring Security 6 (JWT), Spring Data JPA, MySQL.

### Run backend locally

1. Configure `backend/src/main/resources/application.properties` (MySQL URL, username, password).
2. Start MySQL and create the database.
3. From the `backend` folder, run:
   mvn spring-boot:run
   
Backend will run on:
http://localhost:8080

### Auth API

`POST /auth/login`

Request body:
{
"email": "test@example.com",
"password": "password123"
}

Response:
{
"accessToken": "<JWT access token>",
"refreshToken": "<JWT refresh token>"
}

Use the access token in all protected requests:
Authorization: Bearer <accessToken>

### User APIs

`GET /users/me` – returns current logged‑in user.  
`PUT /users/me` – updates current user profile (e.g. username) with JSON body:

{
"username": "newname"
}

## Frontend

- Frontend code should live in the `frontend/` folder.
- For local development, configure the frontend to call the backend at `http://localhost:8080`.
- Login flow:
  1. Call `POST /auth/login`.
  2. Store `accessToken`.
  3. Send `Authorization: Bearer <accessToken>` header on protected API calls.







