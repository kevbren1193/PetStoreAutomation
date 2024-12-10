# PetStore

## Test Cases
This part of the document describes the test cases categorized by APIs, their priority, automation status, and whether they are performance tested.

### Pet API

| **Test Case**                                           | **Priority** | **Automation Test** | **Performance Test** |
|---------------------------------------------------------|--------------|----------------------|-----------------------|
| **Positive Test Cases**                                 |              |                      |                       |
| Add a new pet with valid data                           | High         | Yes                  | Yes                   |
| Update an existing pet with valid data                 | Medium       | No                   | No                    |
| Get all pets by a status                                | High         | Yes                  | Yes                   |
| Retrieve a pet with a valid ID                         | High         | Yes                  | No                    |
| Delete a pet with a valid ID                           | Low          | No                   | No                    |
| **Negative Test Cases**                                 |              |                      |                       |
| Add a pet with missing required fields                 | High         | Yes                  | No                    |
| Update a pet with an inexistent ID                     | Medium       | No                   | No                    |
| Get pets with an invalid status                        | High         | Yes                  | No                    |
| Retrieve a pet with an inexistent ID                   | Medium       | Yes                  | No                    |
| Delete a pet with an inexistent ID                     | Low          | No                   | No                    |
| **End-to-End Test Case**                                |              |                      |                       |
| Add a new pet, retrieve it by ID, update its details, and finally delete it | Low | No        | No                    |

---

### **User API**

| **Test Case**                                           | **Priority** | **Automation Test** | **Performance Test** |
|---------------------------------------------------------|--------------|----------------------|-----------------------|
| **Positive Test Cases**                                 |              |                      |                       |
| Create a new user with valid data                      | High         | Yes                  | No                    |
| Log in with valid credentials                          | High         | Yes                  | Yes                   |
| Log out after logging in                               | Medium       | No                   | No                    |
| Retrieve user details by username                      | Medium       | Yes                  | No                    |
| Update user details with valid data                    | Medium       | No                   | No                    |
| Delete a user by username                              | Low          | No                   | No                    |
| **Negative Test Cases**                                 |              |                      |                       |
| Create a user with missing required fields             | High         | No                   | No                    |
| Log in with incorrect credentials                      | High         | Yes                  | No                    |
| Retrieve details of a non-existent user                | Medium       | Yes                  | No                    |
| Update a user with invalid data                        | Medium       | No                   | No                    |
| Delete a user with a non-existent username             | Low          | No                   | No                    |
| **End-to-End Test Case**                                |              |                      |                       |
| Create a user, log in, retrieve user details, update the user, and then logout | Low | No      |No                    |

---

### **Store API**

| **Test Case**                                           | **Priority** | **Automation Test** | **Performance Test** |
|---------------------------------------------------------|--------------|----------------------|-----------------------|
| **Positive Test Cases**                                 |              |                      |                       |
| Place an order with valid data                         | High         | Yes                  | Yes                   |
| Retrieve an order by a valid ID                        | High         | Yes                  | No                    |
| Delete an order by a valid ID                          | Low          | No                   | No                    |
| **Negative Test Cases**                                 |              |                      |                       |
| Place an order with an inexistent pet ID               | High         | Yes                  | No                    |
| Retrieve an order with a non-existent ID               | High         | No                   | No                    |
| Delete an order with a non-existent ID                 | Low          | No                   | No                    |
| **End-to-End Test Case**                                |              |                      |                       |
| Place an order, retrieve it by ID, and delete it       | Low          | No                   | No                    |


---
## Automation Project Description
This project automates the API testing of Pet Store API v3. For acomlishing this I used Java, Cucumber and Rest-Assured.
Automated test cases cover the critical functionalities separated by Pet API, User API and Store API. Pet Store API documentation can be found in https://github.com/swagger-api/swagger-petstore

### Tools and Technologies
- **Java**: Version 21
- **Cucumber**: Version 7.14.0
- **Maven**: For building and dependecy management.
- **Rest-Assured**: Version 5.3.0

---
### Features
- Positive and Negative test cases for User API, Pet API and Store API.
- Uses Cucumber for a BDD with human readable test scenarios.
- Uses Java for implementing scenarios in step definition classes.
- Uses predefined JSON schemas for validating response structures.
- Supports parallel execution of test cases for faster feedback.

---
### Automation Project Setup and Requirements
#### Prerequisites
- **Java 21**: Download and install Java 21 from Oracle website https://www.oracle.com/java/technologies/javase-downloads.html or use OpenJDK.
- **Maven**: Download and install Maven from oficcial site https://maven.apache.org/download.cgi

#### Steps to build
- Download and extract this project, or clone it using git.
- Open terminal, console or cmd and navigate to project folder `PetStoreAutomation/PetStoreAutomation`.
- Build the project: 
```bash
mvn clean install
```

---
### Project Execution
There are two profiles available: 
- **`local`**: Points to the localhost instance of the Pet Store API.
- **`prod`**: Points to the hosted version of Pet Store API v3.

#### Execution Commands
##### Run All Tests
```bash
mvn clean test -Pprod
```

##### Run a Specific Feature by Tag
```bash
mvn clean test -Pprod -Dcucumber.filter.tags="@petAPI"
```
> **_Valid Tags:_** `petAPI`, `userAPI`, `storeAPI`

##### Run Multiple Features by Tag
```bash
mvn clean test -Pprod "-Dcucumber.filter.tags=@storeAPI or @userAPI"
```
> **_Valid Tags:_** `petAPI`, `userAPI`, `storeAPI`

##### Run Tests in Parallel
```bash
mvn clean test -Pprod -DthreadCount=4
```

##### Run Tests with Mixed Options
```bash
mvn clean test -Pprod -DthreadCount=4 "-Dcucumber.filter.tags=@storeAPI or @userAPI"
```

#### View Reports
- **HTML** and **JSON** reports are generated in the `target/reports` folder.
- Open the `cucumber-reports.html` file in a web browser.

---
## Performance Test

### Load Test:
* Measures system behaviour with a predefined number of concurrent users/transactions on stipulated time.

### Stress Test:
* Test system limits by increasing requests/load gradually until failing.

### Indicators:
1. Response time.
2. Error rate.
3. Processed request per second.
4. System/resource utilization.

#### Indicators Importance:
1. Gives important data about system reliability.
2. Gives an understanding of how user experience is going to perfom based on response times.
3. Gives important data for a better resource planing which also impacts the system cost.


---
## Performance Project Description
This project focuses on performance testing a RESTful API for the PetStore v3 API. The goal is to validate the application’s reliability and responsiveness under various load and stress scenarios.

### Tools and Technologies
- **K6**: Open-source loadtesting tool used to write and execute performance tests.
- **k6-reporter**: K6 extension to generate a visual HTML report.

### Test Scenarios
**1. Load Test** : Its purpose is to evaluate the system performance under a consistent load.
- Configuration: 
    * 20 Virtual users.
    * 2m Duration.

- Executor: constant-vus.

**2. Stress Test** : Its purpose is to evaluate the system's behaviour under gradually increasing the load to identify beaking points

- Configuration: 
    * Start with 0 Virtual Users (VUs).
    * Ramp up to 50 VUs in 1m.
    * Sustain 100 VUs for 2m.
    * Ramp down to 0 VUs in 1m.

- Executor: ramping-vus.

### Test Workflow
1. **Login**: call API **GET /user/login** to simulate user login.
2. **Get Pets by Status**: call API **GET /pet/findByStatus?status=available** to simulate a home page grid load of available pets.
3. **Create a Pet**: call API **POST /pet** to create a new pet with a random ID.
4. **Get Pet by ID**: call API **GET /pet/{id}** to simulate loading the newly created pet.

---
### Performance Project Setup and Requirements
#### Prerequisites
- **K6**: Download and install K6 from official website https://k6.io/open-source/

#### Steps to build
- Download and extract this project, or clone it using git.
- Open terminal, console or cmd and navigate to project folder `PetStoreAutomation/PetStoreAutomation`.
- Build the project: 
```bash
mvn clean install
```

---
### Performance Project Execution
- Open terminal, console or cmd and navigate to project folder `PetStoreAutomation/PetStorePerformance`.
- Run load test:
```bash
k6 run --out json=results/loadTestResults.json petStoreLoadTest.js
```
- Run stress test:
```bash
k6 run --out json=results/stressTestResults.json petStoreStressest.js
```

#### View Reports
- **HTML** reports are generated in the `reports` folder, **JSON** results are generated in the `results` folder.
- Open the `stressTestSummary.html` and/ or `loadTestSummary.html` file in a web browser.
    