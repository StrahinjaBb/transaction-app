# Transaction Application

A full-stack application with Spring Boot backend and React frontend for processing financial transactions.

## Backend Service

Built with Java 21 and Spring Boot 3.5.3.

### Backend Features
- REST API endpoints for transaction operations
- CSV data processing capabilities
- Unit and integration testing support
- Modern Java 21 features

## Frontend Service

Built with React 18, Vite

### Frontend Features
- Modern React hooks architecture
- Axios for API communication
- Vite for fast development builds

## Prerequisites

### For Backend
- Java 21 JDK ([Installation guide](https://www.oracle.com/java/technologies/downloads/))
- Apache Maven 3.6.3+ (`mvn -v` to check)

### For Frontend
- Node.js v18.18.2 ([Download](https://nodejs.org/))
- npm (comes with Node.js) or yarn

### Common
- Git (for version control)

## Getting Started

### 1. Clone the Repository and build backend service

- git clone https://github.com/StrahinjaBb/transaction-app.git
- cd transaction-backend
- mvn clean install

This will:

Download all dependencies

Compile the source code

Run all tests

Package the application as a JAR file

### 2. Run the backend application

- mvn spring-boot:run

### 3. Backend testing

- mvn test

### 4. Frontend setup

- cd ../transaction-frontend
- npm install
- npm run build

### 5. Run frontend development server

- npm run dev

### 6. Running the full application

First terminal:
- cd transaction-backend
- mvn spring-boot:run

Second terminal:
cd transaction-frontend
- (if package.json is changed - npm install); npm run build; npm run dev

Backend: http://localhost:8080
Frontend: http://localhost:5173

Last but not least..
Storage is implemented via internal CSV file in transaction-backend/src/main/resources/transactions.csv
Changes to the data will be lost if not commited.