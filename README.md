# Help Desk Ticket System

A Java console application that simulates a technical support help desk.
This project is being developed as a personal practice project focused on object-oriented programming, data structures, and console-based application design.


## Current Status
🚧 Work in progress

The project currently includes the basic structure for models, repositories, and services, utilities, and console menus. The main application is being built step by step.

## Current Features
- Ticket model with ID, requester name, description, priority, status, and creation timestamp
- Ticket priority levels using an enum
- Ticket status tracking using an enum
- In-memory ticket storage using a HashMap
- Service layer for ticket creation, lookup, updates, and technician workflows
- Menu printer for client and technician console menus
- Input validation helper methods
- Main application setup started


## Planned Features
- Client menu workflow
  - Create a new ticket
  - View tickets created by the current client

- Technician menu workflow
  - View pending tickets
  - View all tickets
  - Search ticket by ID
  - Update ticket status
  - Update ticket priority
  - Resolve tickets

- Priority-based ticket sorting
  - High-priority tickets shown first
  - Older tickets shown before newer tickets when priority is the same

- Stack-based undo functionality for selected technician actions

- Improved console formatting and user experience


## My Goal

My goal is to grow this console-based application into a runnable desktop application using JavaFX. I also plan to add a database layer so tickets and system data can be stored persistently and accessed by the application.