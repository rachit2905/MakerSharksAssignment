# User Management API

This repository contains a Spring Boot application that provides RESTful API endpoints for user registration and fetching user details.

## API Endpoints

### 1. User Registration

- **Endpoint**: `/api/user/register`
- **Method**: POST
- **Description**: Register a new user by accepting user details in the JSON request body.
- **Request Body**:
  ```json
  {
    "username": "johndoe",
    "email": "johndoe@example.com",
    "password": "yourpassword"
  }
Response Types:
201 Created: User registered successfully.

{
  "id": 1,
  "username": "johndoe",
  "email": "johndoe@example.com"
}
400 Bad Request: Invalid input.

{
  "error": "Invalid input data"
}
409 Conflict: User already exists.
json
Copy code
{
  "error": "User already exists"
}
2. Fetch User Details
Endpoint: /api/user/{id}
Method: GET
Description: Fetch the details of a user by their ID.
Response Types:
200 OK: User details retrieved successfully.

{
  "id": 1,
  "username": "johndoe",
  "email": "johndoe@example.com"
}
404 Not Found: User not found.

{
  "error": "User not found"
}
Documentation
Swagger is used for API documentation. You can access the Swagger UI to explore and test the API endpoints.
