# 🎫 Help Desk Ticket System

A Java application that simulates a technical support help desk.

The project started as a console application to practice Java, object-oriented programming, data structures, and application design. It is now being expanded into a desktop application using **JavaFX**.

## 🚧 Current Status

The core console application is functional, and development of the JavaFX interface has begun.

The project now uses **Maven** for dependency management and builds successfully with JavaFX.

## ✨ Features

- Create and manage support tickets
- Automatic ticket IDs and creation timestamps
- Ticket priority and status tracking
- Search tickets by ID
- Update ticket status and priority
- View pending and completed tickets
- Client and technician workflows
- In-memory storage using `HashMap`
- Ticket organization by priority and creation time
- Input validation

## 🏗️ Project Structure

```text
src/
├── app/
├── model/
├── repository/
├── service/
├── ui/
└── utils/
```

The project follows a layered structure that separates models, data storage, business logic, user interface, and utility methods.

## 🖥️ JavaFX Progress

The project is currently transitioning from a console interface to a graphical desktop application.

Completed so far:

- Maven project configuration
- JavaFX dependencies and plugin setup
- Successful Maven compilation
- Initial JavaFX application class
- First JavaFX window successfully launched

## 🛠️ Technologies

- Java
- JavaFX
- Maven
- Git & GitHub
- Java Collections
- Object-Oriented Programming

## 🗺️ Roadmap

- Complete the JavaFX client and technician interfaces
- Add ticket creation and management screens
- Add persistent SQL database storage
- Add user authentication and technician accounts
- Add ticket history and technician notes

Long term, the project may evolve into a full-stack web application using **Spring Boot**, **REST APIs**, and **React**.

## 🎯 Project Goal

This is a long-term learning project built to strengthen my Java and software engineering skills while applying new concepts such as GUI development, databases, APIs, and backend development.