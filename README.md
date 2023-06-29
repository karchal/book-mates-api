# Book Mates - Group Final Fullstack Project

## Introduction
Welcome to the Book Mates project! This is a fullstack application built using Java with Spring Boot for the backend and React for the frontend. The project focuses on providing book lovers with a platform to search for books, organize them in personal shelves, participate in discussions, and join events. The backend connects to the Google Books API to fetch book data, while the frontend utilizes JavaScript, MUI components, and Styled Components for an enhanced user interface.

## Features
The Book Mates application offers the following features:

### 1. Book Search
Users can search for books by title and author, leveraging the Google Books API. The search results will display relevant book information, allowing users to explore and select books of interest.

### 2. Shelves
Users can save books to personalized shelves, such as "Favorites," "Read," "To Read," "As Gift," and "Saved." This feature enables users to keep track of their reading preferences and manage their book collections efficiently.

### 3. Forum
Book Mates includes a forum where users can create and participate in discussions on various topics related to books.

### 4. Events
Users can also create events and join existing ones, fostering a vibrant community of book enthusiasts.

### 5. Secure Login
The application ensures secure user authentication using Spring Security. Users can create accounts, log in securely, and access their personalized profiles.

### 6. Profile
Each user has a dedicated profile that displays their saved books, events, and topics. Users can manage their book collection, review past events, and contribute to the forum conveniently from their profile page.

## Technology Stack
The Book Mates project utilizes the following technologies:

- Backend:
    - Java: The backend is written in Java, leveraging the Spring Boot framework.
    - Spring Boot: Spring Boot provides a robust foundation for building Java-based applications, simplifying the development process.
    - Spring Security: This module is used for secure authentication and authorization within the application.
    - Hibernate: Hibernate is an Object-Relational Mapping (ORM) tool used for mapping Java objects to database tables. It facilitates database interactions in the project.
    - PostgreSQL: The backend employs PostgreSQL as the database to store user information, book data, and forum-related content.

- Frontend:
    - React: The frontend is built using React, a JavaScript library for building user interfaces.
    - JavaScript: JavaScript is extensively used to enhance the interactivity and functionality of the frontend components.
    - Material-UI (MUI): MUI components are utilized to create a visually appealing and responsive user interface.
    - Styled Components: Styled Components are used for applying custom styles to the React components, enhancing the overall design and user experience.

## Getting Started
To get started with the Book Mates project, follow these steps:

1. Clone the repository from GitHub:
    - Backend: git clone https://github.com/karchal/book-mates-api
    - Frontend: git clone https://github.com/karchal/book-mates

2. Set up the Backend:
- Open the project in your preferred Java IDE.
- Configure the PostgreSQL database connection, details in the `application.properties` file.
- Get your Google Books Api key and save it as environment variable (see `application.properties`).
- Configure email (see `application.properties`).
- Build and run the Spring Boot application.

3. Set up the Frontend:
- Navigate to the `frontend` directory within the project.
- Install the required dependencies by running the following command:
  ```
  npm install
  ```
- Start the React development server:
  ```
  npm start
  ```

4. Access the Book Mates application:
- Open your web browser and visit `http://localhost:3000`.
- You should see the Book Mates application up and running!

## Contributing
We welcome contributions to the Book Mates project. If you would like to contribute, please follow these steps:

1. Fork the repository on GitHub.
2. Create a new branch for your feature/bug fix.
3. Implement your changes and ensure that the code compiles successfully.
4. Write tests to cover your changes, if applicable.
5. Push your changes to your forked repository.
6. Create a pull request detailing your changes and submit it for review.

Thank you for considering contributing to Book Mates!