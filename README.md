# Java Spring Security JWT

Java Spring Security JWT is an authentication and authorization service developed using Spring Boot, Spring Security, and JWT (JSON Web Token).

## Features

- User registration and login
- JWT-based authentication and authorization
- Role-based authorization
- API access control based on user roles
- CORS (Cross-Origin Resource Sharing) support

## API Endpoints

### Authentication

- `POST /api/auth/login`: Endpoint for user login. Login is performed with username and password. The returned JWT token is used for user authorization.

### Test Endpoints

These endpoints can only be accessed by authorized users.

- `POST /api/admin`: Admin endpoint accessible only by users with admin privileges.
- `POST /api/user`: User endpoint accessible by users with admin or user privileges.

The following endpoint is a special endpoint accessible only by users with admin privileges:

- `POST /api/public`: Public endpoint accessible only by users with admin privileges.

### Authorized Endpoints

The following endpoints can only be accessed by authorized users.

- `POST /api/admin`: Admin endpoint accessible only by users with admin privileges.
- `POST /api/user`: User endpoint accessible by users with admin or user privileges.

## Usage

1. Start the application:

2. Send a request with username and password to the `POST /api/auth/login` endpoint to log in.

3. Use the received JWT token for authorization in other endpoints.

