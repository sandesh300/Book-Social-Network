# 📚 Book Management API Documentation

The **Book Management API** allows users to register, authenticate, and manage a library of books with features such as borrowing, returning, sharing, and archiving. Every endpoint requires user authentication using JWT tokens.

---
## Features

- **User Registration:** Users can register for a new account.
- **Email Validation:** Accounts are activated using secure email validation codes.
- **User Authentication:** Existing users can log in securely.
- **Book Management:** Users can create, update, share, and archive their books.
- **Book Borrowing:** Necessary checks to determine if a book can be borrowed.
- **Book Returning:** Users can return borrowed books.
- **Book Return Approval:** Functionality to approve book returns.

## Technologies Used

- Spring Boot 3
- Spring Security 6
- JWT Token Authentication
- Spring Data JPA
- JSR-303 and Spring Validation
- OpenAPI and Swagger UI Documentation
- Docker
------

## 📌 API Endpoints

### **User Authentication**

1. **Register User**
   - **Method**: `POST`  
   - **URL**: `http://localhost:8080/api/v1/auth/register`  
   - **Request Body**:
     ```json
     {
       "firstname": "sandesh",
       "lastname": "bhujbal",
       "email": "bhujbalsandesh52@gmail.com",
       "password": "Password@123"
     }
     ```
   - **Response**: `202 Accepted`

2. **Activate Account**
   - **Method**: `GET`  
   - **URL**: `http://localhost:8080/api/v1/auth/activate-account?token=<activation_token>`  
   - **Response**: `200 OK`

3. **Login User**
   - **Method**: `POST`  
   - **URL**: `http://localhost:8080/api/v1/auth/authenticate`  
   - **Request Body**:
     ```json
     {
       "email": "bhujbalsandesh52@gmail.com",
       "password": "Password@123"
     }
     ```
   - **Response**:
     ```json
     {
       "token": "<JWT_access_token>"
     }
     ```
     - **Success Code**: `200 OK`

---

### **Book Management**

## 🔒 Authorization
All endpoints require a JWT token.  
Include the following header in all requests:
```http
Authorization: Bearer <JWT_access_token>
```

---

## Endpoints

### 1. **Save Book**
- **Method**: `POST`  
- **URL**: `http://localhost:8088/books`  
- **Request Body**:  
  ```json
  {
    "title": "Java Spring Boot Book",
    "authorName": "James Gosling",
    "isbn": "4512",
    "synopsis": "Book about Java programming",
    "shareable": true
  }
  ```
- **Response Body**:  
  ```json
  {
    "id": 58
  }
  ```
- **Success Code**: `200 OK`

---

### 2. **Find Book by ID**
- **Method**: `GET`  
- **URL**: `http://localhost:8088/books/{book-id}`  
- **Response Body**:  
  ```json
  {
    "id": 58,
    "title": "Java Spring Boot Book",
    "authorName": "James Gosling",
    "isbn": "4512",
    "synopsis": "Book about Java programming",
    "rate": 4.5,
    "bookCover": "http://example.com/cover.jpg",
    "shareable": true,
    "archived": false
  }
  ```
- **Success Code**: `200 OK`

---

### 3. **Find All Books**
- **Method**: `GET`  
- **URL**: `http://localhost:8088/books`  
- **Query Params**:  
  - `page`: Page number (default: `0`)  
  - `size`: Number of items per page (default: `10`)  
- **Response Body**:  
  ```json
  {
    "content": [
      {
        "id": 58,
        "title": "Java Spring Boot Book",
        "authorName": "James Gosling",
        "rate": 4.5
      }
    ],
    "number": 0,
    "size": 10,
    "totalElements": 1,
    "totalPages": 1,
    "first": true,
    "last": true
  }
  ```
- **Success Code**: `200 OK`

---

### 4. **Find Owner's Books**
- **Method**: `GET`  
- **URL**: `http://localhost:8088/books/owner`  
- **Query Params**: Same as Find All Books  
- **Response Body**:  
  ```json
  {
    "content": [
      {
        "id": 58,
        "title": "Java Spring Boot Book",
        "authorName": "James Gosling",
        "rate": 4.5
      }
    ],
    "number": 0,
    "size": 10,
    "totalElements": 1,
    "totalPages": 1,
    "first": true,
    "last": true
  }
  ```
- **Success Code**: `200 OK`

---

### 5. **Find Borrowed Books**
- **Method**: `GET`  
- **URL**: `http://localhost:8088/books/borrowed`  
- **Query Params**: Same as Find All Books  
- **Response Body**:  
  ```json
  {
    "content": [
      {
        "bookId": 58,
        "title": "Java Spring Boot Book",
        "authorName": "James Gosling",
        "isbn": "4512",
        "borrowed": true,
        "returned": false,
        "returnApproved": false
      }
    ],
    "number": 0,
    "size": 10,
    "totalElements": 1,
    "totalPages": 1,
    "first": true,
    "last": true
  }
  ```
- **Success Code**: `200 OK`

---

### 6. **Find Returned Books**
- **Method**: `GET`  
- **URL**: `http://localhost:8088/books/returned`  
- **Query Params**: Same as Find All Books  
- **Response Body**:  
  ```json
  {
    "content": [
      {
        "bookId": 58,
        "title": "Java Spring Boot Book",
        "authorName": "James Gosling",
        "isbn": "4512",
        "borrowed": false,
        "returned": true,
        "returnApproved": true
      }
    ],
    "number": 0,
    "size": 10,
    "totalElements": 1,
    "totalPages": 1,
    "first": true,
    "last": true
  }
  ```
- **Success Code**: `200 OK`

---

### 7. **Update Book Shareable Status**
- **Method**: `PATCH`  
- **URL**: `http://localhost:8088/books/shareble/{book-id}`  
- **Response Body**:  
  ```json
  {
    "id": 58,
    "shareable": false
  }
  ```
- **Success Code**: `200 OK`

---

### 8. **Update Book Archived Status**
- **Method**: `PATCH`  
- **URL**: `http://localhost:8088/books/archived/{book-id}`  
- **Response Body**:  
  ```json
  {
    "id": 58,
    "archived": true
  }
  ```
- **Success Code**: `200 OK`

---

### 9. **Borrow Book**
- **Method**: `POST`  
- **URL**: `http://localhost:8088/books/borrow/{book-id}`  
- **Response Body**:  
  ```json
  {
    "id": 1,
    "bookId": 58,
    "borrowedBy": "sandesh",
    "borrowedAt": "2024-10-22T10:15:30Z"
  }
  ```
- **Success Code**: `200 OK`

---

### 10. **Return Borrowed Book**
- **Method**: `PATCH`  
- **URL**: `http://localhost:8088/books/borrow/return/{book-id}`  
- **Response Body**:  
  ```json
  {
    "id": 1,
    "bookId": 58,
    "returnedAt": "2024-10-22T10:16:36Z"
  }
  ```
- **Success Code**: `200 OK`

---

### 11. **Approve Book Return**
- **Method**: `PATCH`  
- **URL**: `http://localhost:8088/books/borrow/return/approve/{book-id}`  
- **Response Body**:  
  ```json
  {
    "id": 1,
    "bookId": 58,
    "returnApproved": true,
    "approvedAt": "2024-02-22T10:15:30Z"
  }
  ```
- **Success Code**: `200 OK`

---

### 12. **Upload Book Cover**
- **Method**: `POST`  
- **URL**: `http://localhost:8088/books/cover/{book-id}`  
- **Request**: Multipart file upload  
- **Response Body**:  
  ```json
  {
    "bookId": 58,
    "coverUrl": "http://example.com/book-covers/58.jpg",
    "uploadedAt": "2024-02-22T10:15:30Z"
  }
  ```
- **Success Code**: `202 Accepted`
-----
## Setup Instructions

To set up the backend of the Book Social Network project, follow these steps:

1. Clone the repository:

```bash
   git clone https://github.com/matnrocha/Book-Social-Network.git
```

2. Run the docker-compose file

```bash
  docker-compose up -d
```

3. Navigate to the book-social-network directory:

```bash
  cd book-social-network
```

4. Install dependencies (assuming Maven is installed):

```bash
  mvn clean install
```

4. Run the application

```bash
  java -jar target/book-network-0.0.1.jar
```

5. Access the API documentation using Swagger UI:

  Open a web browser and go to `http://localhost:8088/api/v1/swagger-ui/index.html

6. Access MailDev to receive emails (token receiving):

  Open a web browser and go to `http://localhost:1080/#/

--- 

