Student Management System

The Student Management System is a console-based Java application designed to efficiently manage student records.
It leverages object-oriented programming (OOP) principles to provide a robust and user-friendly interface for performing CRUD operations (Create, Read, Update, Delete) on student data.

The system includes a file I/O component to persist student data in a text file named IO_File.txt and uses Swing for basic GUI elements, like pop-up dialog boxes for user greetings and input.

Features
Feature	Description
Add New Student	Input details for a new student, including name, age, roll number, GPA, department, and courses.
View All Students	Displays a formatted list of all students currently in the system, showing full details.
Edit Existing Student	Find and update a student's name, age, GPA, and courses using their unique roll number.
Delete Student Record	Remove a student's record from the system by roll number.
Data Persistence	Automatically saves and loads student data from IO_File.txt, preserving records between sessions.
Add via File Upload	Users can bulk add or update student records by placing a properly formatted text file in the project root (IO_File.txt). The system will read it automatically at startup.
User Interface	Menu-driven console navigation and initial GUI pop-ups for greetings and input.
Getting Started
Prerequisites

Java Development Kit (JDK): Ensure JDK 8 or newer is installed.

How to Run

Clone the repository:

git clone https://github.com/YourUsername/Student-Management-System-Java.git


Navigate to the project directory:

cd Student-Management-System-Java


Compile the source files:

javac src/*.java


Run the application:

java -cp src StudentManagementSystem

Data File

The project includes IO_File.txt in the project root.

You can edit it directly or use the program interface to add, update, or delete student records.

This ensures flexibility for both manual and programmatic data management.
