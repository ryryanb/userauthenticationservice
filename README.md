The userauthentication project is a microservice designed to perform the following functionalities in the ShopUpSpot e-commerce system:
•	Users should be able to register, log in, and manage their profiles securely.
•	Implement role-based access control to differentiate between customers, administrators, and other user roles.

It uses the following tech stack:
•	Java
•	Spring Boot
•	Spring Data JPA (for database interaction)
•	MySQL or other databases (for data storage)
•	JWT for securing APIs and user authentication

API Endpoints
1.	User Registration:
•	Endpoint: POST /api/register
•	Description: This endpoint allows users to register for a new account.
•	Request Body: User details (username, email, password, etc.)
•	Response: Status indicating successful registration or error message if registration fails.

2.	User Login:
•	Endpoint: POST /api/login
•	Description: This endpoint handles user login and authentication.
•	Request Body: User credentials (username/email and password)
•	Response: JWT token upon successful login or error message if login fails.

3.	User Logout:
•	Endpoint: POST /api/logout
•	Description: This endpoint logs out the authenticated user and invalidates the authentication token.
•	Request Header: Authorization token (JWT)
•	Response: Status indicating successful logout or error message if logout fails.

4.	Password Reset Request:
•	Endpoint: POST /api/reset-password
•	Description: This endpoint allows users to request a password reset by providing their registered email.
•	Request Body: User's registered email address.
•	Response: Status indicating that a password reset email has been sent or error message if the request fails.

5.	Password Reset Confirmation:
•	Endpoint: POST /api/reset-password/confirm
•	Description: This endpoint confirms the password reset and allows users to set a new password.
•	Request Body: Reset token and new password.
•	Response: Status indicating successful password reset or error message if the confirmation fails.

6.	User Profile Retrieval:
•	Endpoint: GET /api/profile/me
•	Description: This endpoint retrieves the authenticated user's profile information.
•	Request Header: Authorization token (JWT)
•	Response: User profile details (username, email, etc.) or error message if retrieval fails.

7.	User Profile Update:
•	Endpoint: PUT /api/profile/me
•	Description: This endpoint allows the authenticated user to update their profile information.
•	Request Header: Authorization token (JWT)
•	Request Body: Updated user profile details.
•	Response: Status indicating successful update or error message if the update fails.

8.	User Account Deletion:
•	Endpoint: DELETE /api/profile
•	Description: This endpoint allows the authenticated user to delete their account.
•	Request Header: Authorization token (JWT)
•	Response: Status indicating successful account deletion or error message if the deletion fails.


