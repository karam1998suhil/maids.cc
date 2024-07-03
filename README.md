Documentation Running the Application Database Setup:

Create a database named LibraryDB in your database management system MySQL.

Copy code 
CREATE DATABASE LibraryDB; 
paste in mySql

Running the Application:
Clone the repository containing your Spring Boot application. Navigate to the project directory containing pom.xml. Build and run the application using Maven: bash Copy code (mvn spring-boot:run or run java) by VSCode Alternatively, you can run it directly from an integrated development environment (IDE) like IntelliJ IDEA or Eclipse. Interacting with API Endpoints API Endpoints:
example:/
Base URL:
 http://localhost:8080/api/books
 HTTP Method Endpoint Description 
GET / Retrieve all books 
GET /{id} Retrieve a book by ID 
POST / Add a new book 
PUT /{id} Update an existing book by ID 
DELETE /{id} Delete a book by ID Example:

To retrieve all books: GET http://localhost:8080/api/books/ To add a new book:
 POST http://localhost:8080/api/books/ json

Copy code 
{ 
"title": "Book Title", 
"isbn": "978-3-16-148410-0", 
"author": "Author Name", 
"publicationYear": 2023
 }
