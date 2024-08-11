# Instalation guide to BankAPI

Welcome to the BankAPI installation guide. This document will walk you through the steps required to set up and run the BankAPI project on your local machine.

### Prerequisites

Before you begin, ensure you have the following installed:
- Java JDK 17 or newer
- Maven 3.6 or newer
- An IDE like IntelliJ IDEA (recommended)
- Microsoft SQL Server (Ensure it's running and accessible)

### Step-by-step Installation

#### 1. Extract the ZIP File
Unzip the downloaded `BankAPI.zip` file to a directory of your choice.

#### 2. Database Setup
- Open your SQL Server Management Studio (SSMS).
- Connect to your local SQL Server instance.
  - Execute the SQL script provided in the `/src` directory of the extracted project. Open up `SQL Scripts.txt` file. Then execute each command for first initiating database `BankingDB`, then creating tables `users` and `accounts`. 
#### 3. Update Configuration Files
- Navigate to `src/main/resources` in the project directory.
- Open `application.properties` or `application.yml` and update the database connection string with your SQL Server details:
  ```properties
  spring.datasource.url=jdbc:sqlserver://localhost;databaseName=BankingDB;
  spring.datasource.username=yourUsername
  spring.datasource.password=yourPassword
  ```
- Ensure any other configuration settings like port numbers or logging levels are set as per your requirements.

#### 4. Build the Project
- Open your command line interface (CLI).
- Navigate to the root directory of the project (where the `pom.xml` file is located).
- Run the following Maven command to build the project:
  ```bash
  mvn clean install
  ```
  This command compiles the project and runs any tests, creating an executable JAR file in the `/target` directory.

#### 5. Run the Application
- Still in the project's root directory, execute the following command to start the Spring Boot application:
  ```bash
  java -jar target/BankAPI-0.0.1-SNAPSHOT.jar
  ```
- The application should start and be accessible on `http://localhost:8080`.

### Testing the Installation
Once the application is running:
- Open your web browser or use a tool like Postman.
- Try accessing the base URL (e.g., `http://localhost:8080/api/accounts`) to ensure the application responds correctly.
- Perform any additional tests specific to your application's functionality.

## Testing in Postman

### Creating new user (POST)

```declarative
http://localhost:8080/api/users POST

body = {
"firstName": "Dwayne",
"lastName": "Johnson",
"email": "DJ@gmail.com",
"password": "awesome"
}
```

### Endpoint for getting information about user by ID (GET)

```declarative
http://localhost:8080/api/users/6 GET

returns = {
"id": 6,
"firstName": "Dwayne",
"lastName": "Johnson",
"email": "DJ@gmail.com"
}
```

* Explanation: We dont show other people users password and accounts

### Patching current users information (PUT)

```declarative
http://localhost:8080/api/users/6 PUT

body = {
  "firstName": "Dwayne2",
  "lastName": "Johnson2",
  "email": "DJ2@gmail.com",
  "password": "awesome"
}
```

### Deleting user by ID (DELETE)

```declarative
http://localhost:8080/api/users/6 DELETE
```

Result: It deletes the user and all account connected with it.

### Creating new account (POST)

```declarative
http://localhost:8080/api/accounts POST

body = {
    "accountNumber": "1234567890",
    "balance": 1500.00,
    "user_id": "6"
}
```

### Getting all the accounts of one user by its ID (GET)

```declarative
http://localhost:8080/api/accounts/6 GET

returns = [
{
"id": 11,
"accountNumber": "1234567890",
"balance": 1500.0
},
{
"id": 12,
"accountNumber": "1234567891",
"balance": 1700.0
}
]
```