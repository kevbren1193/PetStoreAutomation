# PetStore

## Automation Test Cases

### Pet API

#### Positive Test Cases
1. Add a new pet with valid data. **(H)**
2. Update an existing pet with valid data. **(M)**
3. Get all pets by a status.**(H)**
4. Retrieve a pet with a valid ID.**(H)**
5. Delete a pet with a valid ID.**(L)**

#### Negative Test Cases
1. Add a pet with missing required fields. **(H)**
2. Update a pet with an inexistent ID. **(M)**
3. Get pets with an invalid status.**(H)**
4. Retrieve a pet with an inexistent ID.
5. Delete a pet with an inexistent ID. **(L)**

#### End-to-End Test Case
1. Add a new pet, retrieve it by ID, update its details, and finally delete it. **(L)**

### User API

#### Positive Test Cases
1. Create a new user with valid data. **(H)**
2. Log in with valid credentials. **(H)**
3. Log out after logging in. **(M)**
4. Retrieve user details by username. **(M)**
5. Update user details with valid data. **(M)**
6. Delete a user by username. **(L)**

#### Negative Test Cases
1. Create a user with missing required fields (e.g., username or password). **(H)**
2. Log in with incorrect credentials. **(H)**
3. Retrieve details of a non-existent user.**(M)**
4. Update a user with invalid data. **(M)**
5. Delete a user with a non-existent username. **(L)**

#### End-to-End Test Case
1. Create a user, log in, retrieve user details, update the user, and then logout. **(L)**

### Store API

#### Positive Test Cases
1. Place an order with valid data. **(H)**
2. Retrieve an order by a valid ID. **(H)**
3. Delete an order by a valid ID. **(L)**

#### Negative Test Cases
1. Place an order with an inexistent pet ID. **(H)**
2. Retrieve an order with a non-existent ID. **(H)**
3. Delete an order with a non-existent ID. **(L)**

#### End-to-End Test Case
1. Place an order, retrieve it by ID, and delete it. **(L)**

---
## Performance Test

### Load Test:
* Measures system behaviour with a predefined number of concurrent users/transactions on stipulated time.

### Stress Test:
* Test system limits by increasing requests/load gradually until failing

### Indicators:
1. Response time.
2. Error rate.
3. Processed request per second.
4. System/resource utilization.

#### Indicators Importance:
1. Gives imprtanes data about system reliability.
2. Gives an undesrtanding of how user experience is going to perfom based on response times.
3. Gives important data for a better resource planing which also impacts the system cost