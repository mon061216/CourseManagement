# Course Management System

A comprehensive Java Web Application designed to manage courses, classes, users, enrollments, attendance, assignments, and grades. This project was developed as the Final Project for the PRJ301 (Java Web Development) course by Group 3.

## 🌟 Key Features

- **Role-Based Access Control**: Secure login with distinct interfaces and permissions for `Admin`, `Teacher`, and `Student`.
- **Course & Class Management**: Administrators can easily create, update, and manage courses and classes.
- **Enrollment System**: Students can browse and enroll in available classes.
- **Schedule & Timetable**: Users can view their assigned schedules for courses.
- **Attendance Tracking**: Teachers can record attendance for their scheduled classes.
- **Assignments & Submissions**: Teachers can assign tasks, and students can upload their submissions seamlessly.
- **Grading System**: Teachers can grade student submissions, and students can view their academic performance in real-time.

## 🛠️ Technologies Used

- **Backend**: Java, Servlets, JSP (JavaServer Pages)
- **Frontend**: HTML, CSS, JavaScript (via JSP templates)
- **Database**: SQL Server / MySQL (JDBC API)
- **IDE**: NetBeans

## 📁 Project Structure

- `src/java/controller/`: Servlet controllers handling request routing and business logic (divided into `admin/`, `teacher/`, `student/`, and `common/`).
- `src/java/model/`: Data Access Objects (DAO) and Data Transfer Objects (DTO) for database interaction.
- `src/java/utils/`: Utility classes including database connection helpers.
- `web/`: Contains all frontend views (`.jsp` files), static assets (`.css`, images), and context configurations.

## 🚀 Setup and Installation

1. **Database Setup**: 
   - Locate the provided database script (`CourseProject.sql`) in the root directory.
   - Execute the script in your SQL Server / MySQL environment to initialize the database schema and sample data.
   - Configure the database connection string and credentials in `src/java/utils/DBUtils.java`.
2. **Open the Project**:
   - Open NetBeans IDE.
   - Select `File > Open Project` and navigate to this repository's directory.
3. **Run the Application**:
   - Right-click the project in NetBeans and select **Clean and Build**.
   - Deploy and run the project on a supported application server (e.g., **Apache Tomcat** or **GlassFish**).

## 👥 Contributors
- **Group 3 - Class SE1920**
