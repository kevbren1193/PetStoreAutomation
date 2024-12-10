import http from 'k6/http';
import { check, sleep } from 'k6';
import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js";

export const options = {
  scenarios: {
    end_to_end_load: {
      executor: 'constant-vus',
      vus: 20,
      duration: '2m',
    }
  },
};

const BASE_URL = 'https://petstore3.swagger.io/api/v3';

export default function () {
  // Step 1: Login
  const loginRes = http.get(`${BASE_URL}/user/login?username=testUser&password=testPassword`, {
    headers: { 'Content-Type': 'application/json' },
  });

  check(loginRes, {
    'Login status is 200': (r) => r.status === 200,
    'Login response contains session ID': (r) => r.body.includes('session'),
  });

  if (loginRes.status !== 200) {
    console.error('Login failed:', loginRes.body);
    return; // Exit if login fails
  }
  sleep(1);

  // Step 2: Get Pets by Status
  const getPetsRes = http.get(`${BASE_URL}/pet/findByStatus?status=available`, {
    headers: { 'Content-Type': 'application/json' },
  });

  check(getPetsRes, {
    'Get Pets status is 200': (r) => r.status === 200,
    'Response contains pets': (r) => r.json().length > 0,
  });

  if (getPetsRes.status !== 200) {
    console.error('Get Pets failed:', getPetsRes.body);
    return;
  }
  sleep(1);

  // Step 3: Create a Pet
  const petData = JSON.stringify({
    id: Math.floor(Math.random() * 100000), // Generate a random ID
    name: 'TestPet',
    status: 'available',
  });

  const createPetRes = http.post(`${BASE_URL}/pet`, petData, {
    headers: { 'Content-Type': 'application/json' },
  });

  check(createPetRes, {
    'Create Pet status is 200': (r) => r.status === 200 || r.status === 201,
    'Response contains pet name': (r) => r.json().name === 'TestPet',
  });

  if (createPetRes.status !== 200 && createPetRes.status !== 201) {
    console.error('Create Pet failed:', createPetRes.body);
    return;
  }

  const petId = createPetRes.json().id; // Extract the created pet's ID
  sleep(1);

  // Step 4: Get Pet by ID
  const getPetByIdRes = http.get(`${BASE_URL}/pet/${petId}`, {
    headers: { 'Content-Type': 'application/json' },
  });

  check(getPetByIdRes, {
    'Get Pet by ID status is 200': (r) => r.status === 200,
    'Response contains pet ID': (r) => r.json().id === petId,
  });

  if (getPetByIdRes.status !== 200) {
    console.error('Get Pet by ID failed:', getPetByIdRes.body);
    return;
  }
  sleep(1);
 

}

export function handleSummary(data) {
  return {
    "loadTestSummary.html": htmlReport(data),
  };
}