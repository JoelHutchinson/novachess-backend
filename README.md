# Novachess: A Chess Training Web Application
Welcome to the `Novachess` repository. Novachess is a sophisticated full-stack web application designed to offer a comprehensive suite of tools for chess training and tournament preparation. This document will guide you through the features, architecture, and steps to build and run the project.

## Overview
Novachess integrates advanced web technologies and chess-specific packages to deliver an immersive experience for chess enthusiasts. The application includes:

- A dynamic and responsive chessboard interface.
- Tools for solving chess puzzles and analyzing opening variations.
- A RESTful API to manage resources effectively.

## Key Features
- Front-end: Built with ReactJS, incorporating react-chessboard and chess.js for an interactive chessboard experience.
- Back-end: Java Spring Boot framework implements database interaction and RESTful API.
- Database: H2 embedded database paired with JPA for efficient data persistence, storing resources like puzzles and opening variations.
- RESTful API: Implemented with Spring Boot Web, allowing seamless interactions with various resources.
- HATEOAS Integration: Utilizes Spring HATEOAS’ EntityModel to enrich API responses with conditional links, adhering to HATEOAS principles.
Repository Details

## Prerequisites
Before building and running the project, ensure you have the following installed:

- Node.js and npm
- Java Development Kit (JDK) 17
- Maven

## Build and Run
To build and run Novachess, follow these steps:

1. Install dependencies:
    ```npm install```
2. Build front-end:
    ```npx webpack --config webpack.config.js```
3. Build back-end:
    ```mvn clean install```
4. Execution:
    ```mvn spring-boot:run```

After executing these commands, Novachess should be up and running on your local server.

## Contribution
Contributions to Novachess are welcome! If you're interested in enhancing the application or fixing bugs, please fork the repository, make your changes, and create a pull request.

## License
This project is licensed under the MIT License.