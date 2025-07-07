import axios from "axios";

const API = axios.create({
  baseURL: 'http://localhost:8080', // Podrazumeva da tvoj Spring Boot server radi na 8080
  headers: {
    'Content-Type': 'application/json',
  },
});

export default API;