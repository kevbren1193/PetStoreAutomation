# PetStoreAutomation

# Test Cases

## Pet API

### Positive Test Cases
1. Add a new pet with valid data.
2. Update an existing pet with valid data.
3. Get all pets by a status.
4. Retrieve a pet with a valid ID.
5. Delete a pet with a valid ID.

### Negative Test Cases
1. Add a pet with missing required fields.
2. Update a pet with an inexistent ID.
3. Get pets with an invalid status.
4. Retrieve a pet with an inexistent ID.
5. Delete a pet with an inexistent ID.

### End-to-End Test Case
1. Add a new pet, retrieve it by ID, update its details, and finally delete it.

---

## User API

### Positive Test Cases
1. Create a new user with valid data.
2. Log in with valid credentials.
3. Log out after logging in.
4. Retrieve user details by username.
5. Update user details with valid data.
6. Delete a user by username.

### Negative Test Cases
1. Create a user with missing required fields (e.g., username or password).
2. Log in with incorrect credentials.
3. Retrieve details of a non-existent user.
4. Update a user with invalid data.
5. Delete a user with a non-existent username.

### End-to-End Test Case
1. Create a user, log in, retrieve user details, update the user, and then logout.

---

## Store API

### Positive Test Cases
1. Place an order with valid data.
2. Retrieve an order by a valid ID.
3. Delete an order by a valid ID.

### Negative Test Cases
1. Place an order with an inexistent pet ID.
2. Retrieve an order with a non-existent ID.
3. Delete an order with a non-existent ID.

### End-to-End Test Case
1. Place an order, retrieve it by ID, and delete it.