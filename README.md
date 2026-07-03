# 🐶 Pet Store API Automation Framework

A scalable and maintainable REST Assured API Automation Framework developed using **Java**, **TestNG**, and **Maven** following industry-standard automation practices.

This project automates CRUD operations on the Swagger Pet Store User APIs and demonstrates end-to-end API automation, data-driven testing, reporting, logging, reusable utilities, and framework design suitable for enterprise QA projects.

---

# 🚀 Tech Stack

- Java 17
- REST Assured
- TestNG
- Maven
- Apache POI
- Java Faker
- Log4j2
- Extent Reports
- JSON Schema Validator
- Git & GitHub

---

# 📁 Project Structure

```
PetStoreAutomation
│
├── src
│   ├── test
│   │
│   ├── api
│   │   ├── endpoints
│   │   ├── payload
│   │   ├── test
│   │   └── utilities
│   │
│   └── resources
│       ├── RestAssuredExcelData.xlsx
│       ├── config.properties
│       └── log4j2.xml
│
├── reports
├── logs
├── testng.xml
├── pom.xml
└── README.md
```

---

# 🏗 Framework Architecture

The framework is designed using a modular architecture where every component has a dedicated responsibility.

- Test Layer
- Endpoint Layer
- Payload Layer
- Utility Layer
- Reporting Layer
- Logging Layer
- Configuration Layer

This separation makes the framework scalable, reusable, and easy to maintain.

---

# 🔄 Framework Workflow

The automation framework follows the execution flow shown below.

```
                 testng.xml
                      │
                      ▼
                TestNG Execution
                      │
                      ▼
              Test Class (UserTests)
                      │
                      ▼
         DataProvider reads Excel Data
                      │
                      ▼
         Apache POI converts Excel to Object[][]
                      │
                      ▼
           Payload Object Creation (POJO)
                      │
                      ▼
      REST Assured sends HTTP Request
                      │
                      ▼
          Pet Store Swagger API Server
                      │
                      ▼
              HTTP Response Received
                      │
        ┌─────────────┴─────────────┐
        ▼                           ▼
 Response Validation          JSON Schema Validation
        │                           │
        └─────────────┬─────────────┘
                      ▼
              Logging (Log4j2)
                      │
                      ▼
          Extent Report Generation
```

---

# 📌 Step-by-Step Execution

## 1. TestNG Suite Execution

Execution starts from **testng.xml**.

The suite controls:

- Test classes to execute
- Execution order
- Listeners
- Reports
- Parallel execution (if configured)

```
testng.xml

↓

UserTests
```

---

## 2. Reading Test Data

Instead of hardcoding data inside test scripts, the framework follows a **Data-Driven Testing** approach.

Test data is stored in an Excel file.

Example:

| ID | Username | First Name | Last Name |
|----|----------|------------|-----------|
|101|john01|John|Smith|
|102|alex22|Alex|Brown|

Apache POI reads every row from the Excel sheet.

The DataProvider converts the data into:

```java
Object[][]
```

Each row represents one complete test execution.

---

## 3. DataProvider

The DataProvider supplies one dataset at a time to the test method.

Example:

Iteration 1

```
101
john01
John
Smith
```

↓

Runs

```java
testPostUser()
```

Iteration 2

```
102
alex22
Alex
Brown
```

↓

Runs the same test again.

This allows a single test method to execute multiple times using different datasets.

---

## 4. Payload Creation

The framework uses a POJO class (`User.java`) to build the request payload.

Example:

```java
User payload = new User();

payload.setId(...);
payload.setUsername(...);
payload.setFirstName(...);
payload.setLastName(...);
```

Using POJOs improves readability, type safety, and maintainability compared to creating raw JSON strings.

---

## 5. Endpoint Management

All API endpoints are maintained separately inside the **Endpoints** class.

Example:

```java
POST /user

GET /user/{username}

PUT /user/{username}

DELETE /user/{username}
```

Centralizing endpoints ensures that URL changes only need to be updated in one place.

---

## 6. Sending API Requests

REST Assured is used to send HTTP requests.

Typical flow:

```
Request Specification

↓

Headers

↓

Payload

↓

HTTP Method

↓

Server
```

Supported HTTP methods include:

- GET
- POST
- PUT
- DELETE

---

## 7. Response Validation

After receiving the response, the framework validates:

- HTTP Status Code
- Response Body
- Response Headers
- Response Time
- JSON Values

Example:

```java
response.then().statusCode(200);
```

This ensures the API behaves as expected.

---

## 8. JSON Schema Validation

The response payload is validated against a predefined JSON Schema.

This confirms that:

- Required fields exist
- Data types are correct
- API contract remains unchanged

Schema validation helps detect breaking API changes early.

---

## 9. Logging

Log4j2 records execution details throughout the test lifecycle.

Example log entries include:

- Test Started
- Payload Created
- Request Sent
- Response Received
- Validation Successful
- Test Completed

Logs assist in debugging failures and understanding execution flow.

---

## 10. Extent Reports

An interactive HTML report is generated after execution.

The report includes:

- Test Name
- Execution Status
- Pass/Fail Results
- Execution Time
- Exception Details
- System Information

This provides a comprehensive view of test execution for stakeholders.

---

# 📊 Framework Features

✔ Modular Framework Design

✔ Data-Driven Testing using Excel

✔ Apache POI Integration

✔ REST Assured API Automation

✔ POJO Request Payloads

✔ JSON Schema Validation

✔ Log4j2 Logging

✔ Extent HTML Reports

✔ Reusable Utility Classes

✔ Configuration Management using Properties File

✔ Maven Dependency Management

✔ TestNG DataProviders

✔ Java Faker for Dynamic Test Data

---

# ▶ How to Execute

Clone the repository:

```bash
git clone https://github.com/yourusername/PetStoreAutomation.git
```

Navigate to the project:

```bash
cd PetStoreAutomation
```

Execute the test suite:

```bash
mvn clean test
```

Or run directly using:

```
testng.xml
```

---

# 📈 Future Enhancements

- Jenkins CI/CD Integration
- Docker Support
- GitHub Actions Workflow
- Parallel Test Execution
- Environment Profiles (QA/UAT/Production)
- Allure Reporting
- Retry Analyzer for Failed Tests
- API Request/Response Filters
- Slack & Email Notifications

---

# 👨‍💻 Author

**Soham Chakraborty**

QA Engineer | Automation Testing | API Testing | Selenium | REST Assured | Java | TestNG

📧 Email: sohamofficial801@gmail.com

💼 LinkedIn: [Soham Chakraborty](https://www.linkedin.com/in/soham-chakraborty-a5207620b/)

🐙 GitHub: [soham12543](https://github.com/soham12543)
