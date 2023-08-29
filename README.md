The userauthentication project is the backend microservice tasked to perform the following functionalities in the ShopUpSpot e-commerce website:
- Users should be able to register, log in, and manage their profiles securely.
- Implement role-based access control to differentiate between customers, administrators, and other user roles.

It uses the following tech stack:
Java
Spring Boot
Spring Data JPA (for database interaction)
MySQL or other databases (for data storage)
JWT for securing APIs and user authentication

API Endpoints
1.	User Registration
   Endpoint: POST /api/register
   This endpoint allows users to register for a new account.
   Request Body: User details (username, email, password, etc.)
	 Response: Status indicating successful registration or error message if registration fails.

2.	User Login
   Endpoint: POST /api/login
   This endpoint handles user login and authentication.
   Request Body: User credentials (username/email and password)
   Response: JWT token upon successful login or error message if login fails.

4.	User Logout
   Endpoint: POST /api/logout
   This endpoint logs out the authenticated user and invalidates the authentication token.
   Request Header: Authorization token (JWT)
   Response: Status indicating successful logout or error message if logout fails.

5.	Password Reset Request
    Endpoint: POST /api/reset-password
    This endpoint allows users to request a password reset by providing their registered email.
    Request Body: User's registered email address.
    Response: Status indicating that a password reset email has been sent or error message if the request fails.

6.	Password Reset Confirmation:
    Endpoint: POST /api/reset-password/confirm
  	This endpoint confirms the password reset and allows users to set a new password.
    Request Body: Reset token and new password.
    Response: Status indicating successful password reset or error message if the confirmation fails.

7.	User Profile Retrieval:
    Endpoint: GET /api/profile/me
    This endpoint retrieves the authenticated user's profile information.
    Request Header: Authorization token (JWT)
    Response: User profile details (username, email, etc.) or error message if retrieval fails.

8.	User Profile Update:
    Endpoint: PUT /api/profile/me
  	This endpoint allows the authenticated user to update their profile information.
    Request Header: Authorization token (JWT)
    Request Body: Updated user profile details.
    Response: Status indicating successful update or error message if the update fails.

9.	User Account Deletion:
    Endpoint: DELETE /api/profile
    Description: This endpoint allows the authenticated user to delete their account.
    Request Header: Authorization token (JWT)
    Response: Status indicating successful account deletion or error message if the deletion fails.


