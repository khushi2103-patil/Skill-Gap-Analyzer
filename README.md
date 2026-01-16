# Skill Gap Analyzer

A comprehensive web application that helps students identify the gap between their current skills and the skills required for selected career roles. The system compares user skills with industry-required skills and generates personalized learning roadmaps.

## 🎯 Project Overview

Skill Gap Analyzer is a full-stack web application that enables users to:
- Register and login with secure JWT authentication
- Select from predefined career roles (Frontend Developer, Java Developer, Data Analyst, Backend Developer)
- Input their current skills
- Analyze the gap between their skills and role requirements
- Generate personalized learning roadmaps with beginner, intermediate, and advanced stages

## ✨ Features

- **User Authentication**: Secure JWT-based authentication with role-based access control
- **Career Role Management**: Predefined roles with required skills stored in database
- **Skill Gap Analysis**: Compare user skills with role requirements and calculate match percentage
- **Learning Roadmap**: Generate structured learning paths for missing skills
- **Responsive UI**: Modern, Bootstrap 5-based responsive interface
- **RESTful API**: Clean REST API architecture with proper error handling

## 🛠️ Tech Stack

### Frontend
- **HTML5**: Semantic markup
- **Bootstrap 5**: Responsive CSS framework
- **JavaScript (ES6)**: Modern JavaScript with async/await
- **LocalStorage**: JWT token management

### Backend
- **Java 17**: Programming language
- **Spring Boot 3.2.0**: Application framework
- **Spring Security**: Authentication and authorization
- **Spring Data JPA**: Database abstraction layer
- **JWT (jjwt 0.12.3)**: Token-based authentication
- **BCrypt**: Password hashing

### Database
- **MySQL**: Primary database (PostgreSQL also supported)

## 📂 Project Structure

```
skill-gap-analyzer/
├── backend/                          # Spring Boot backend
│   ├── src/main/java/com/skillgapanalyzer/
│   │   ├── controller/               # REST controllers
│   │   │   ├── AuthController.java
│   │   │   ├── CareerRoleController.java
│   │   │   └── SkillController.java
│   │   ├── service/                  # Business logic
│   │   │   ├── AuthService.java
│   │   │   ├── CareerRoleService.java
│   │   │   └── SkillService.java
│   │   ├── repository/               # Data access layer
│   │   │   ├── UserRepository.java
│   │   │   ├── RoleRepository.java
│   │   │   ├── SkillRepository.java
│   │   │   └── CareerRoleRepository.java
│   │   ├── model/                    # Entity classes
│   │   │   ├── User.java
│   │   │   ├── Role.java
│   │   │   ├── ERole.java
│   │   │   ├── Skill.java
│   │   │   └── CareerRole.java
│   │   ├── dto/                      # Data Transfer Objects
│   │   │   ├── LoginRequest.java
│   │   │   ├── RegisterRequest.java
│   │   │   ├── JwtResponse.java
│   │   │   ├── SkillAnalysisRequest.java
│   │   │   ├── SkillAnalysisResponse.java
│   │   │   ├── RoadmapItem.java
│   │   │   └── RoadmapResponse.java
│   │   ├── security/                 # Security components
│   │   │   ├── JwtUtils.java
│   │   │   ├── JwtAuthenticationFilter.java
│   │   │   ├── UserPrincipal.java
│   │   │   └── UserDetailsServiceImpl.java
│   │   ├── config/                   # Configuration
│   │   │   └── SecurityConfig.java
│   │   └── SkillGapAnalyzerApplication.java
│   ├── src/main/resources/
│   │   ├── application.properties    # Application configuration
│   │   └── data.sql                  # Sample data
│   └── pom.xml                       # Maven dependencies
│
├── frontend/                         # Frontend application
│   ├── pages/
│   ├── css/
│   │   └── style.css                 # Custom styles
│   ├── js/
│   │   ├── auth.js                   # Authentication utilities
│   │   ├── login.js                  # Login page logic
│   │   ├── register.js               # Registration logic
│   │   └── dashboard.js              # Dashboard functionality
│   ├── index.html                    # Home page
│   ├── login.html                    # Login page
│   ├── register.html                 # Registration page
│   └── dashboard.html                # Main dashboard
│
├── README.md                         # This file
└── .gitignore                        # Git ignore rules
```

## 🚀 Getting Started

### Prerequisites

Before running the application, ensure you have the following installed:

1. **JDK 17 or higher**
   - Download from [Oracle](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or [OpenJDK](https://adoptium.net/)
   - Verify installation: `java -version`

2. **Maven 3.6+**
   - Download from [Apache Maven](https://maven.apache.org/download.cgi)
   - Verify installation: `mvn -version`

3. **MySQL 8.0+** (or PostgreSQL)
   - Download from [MySQL](https://dev.mysql.com/downloads/mysql/)
   - Or use PostgreSQL from [PostgreSQL](https://www.postgresql.org/download/)

4. **VS Code** (or any IDE)
   - Recommended extensions:
     - Extension Pack for Java
     - Spring Boot Extension Pack
     - MySQL (for database management)

### Step-by-Step Setup in VS Code

#### Step 1: Install JDK

1. Download and install JDK 17
2. Set `JAVA_HOME` environment variable:
   - Windows: System Properties → Environment Variables → Add `JAVA_HOME` = `C:\Program Files\Java\jdk-17`
   - Add `%JAVA_HOME%\bin` to PATH
3. Verify: Open terminal and run `java -version`

#### Step 2: Install Maven

1. Download Maven and extract to a folder (e.g., `C:\Program Files\Apache\maven`)
2. Set `MAVEN_HOME` environment variable:
   - Windows: `MAVEN_HOME` = `C:\Program Files\Apache\maven`
   - Add `%MAVEN_HOME%\bin` to PATH
3. Verify: Open terminal and run `mvn -version`

#### Step 3: Create MySQL Database

1. Open MySQL Command Line or MySQL Workbench
2. Create database:
   ```sql
   CREATE DATABASE skill_gap_analyzer;
   ```
3. Note your MySQL username and password (default: root/root)

#### Step 4: Configure Database Connection

1. Open `backend/src/main/resources/application.properties`
2. Update database credentials if needed:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/skill_gap_analyzer?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=root
   ```
   - Change `username` and `password` to match your MySQL credentials

#### Step 5: Build and Run Spring Boot Application

1. Open VS Code in the project root directory
2. Open terminal in VS Code (Ctrl + `)
3. Navigate to backend directory:
   ```bash
   cd backend
   ```
4. Build the project:
   ```bash
   mvn clean install
   ```
5. Run the application:
   ```bash
   mvn spring-boot:run
   ```
   Or run `SkillGapAnalyzerApplication.java` directly from VS Code

6. Verify backend is running:
   - Open browser: `http://localhost:8080/api/roles`
   - You should see JSON response with career roles

#### Step 6: Open Frontend in Browser

1. Install a local server extension in VS Code (e.g., "Live Server")
2. Right-click on `frontend/index.html` → "Open with Live Server"
   - Or use any local server (Python: `python -m http.server 5500`)
3. The application will open in your browser at `http://localhost:5500`

#### Step 7: Test the Application

1. **Register a new user:**
   - Navigate to Register page
   - Fill in name, email, and password (min 6 characters)
   - Click Register

2. **Login:**
   - Use registered credentials to login
   - You'll be redirected to dashboard

3. **Analyze Skills:**
   - Select a career role (e.g., "Java Developer")
   - Check your known skills
   - Click "Analyze Skill Gap"
   - View matched and missing skills
   - Click "Get Learning Roadmap" for personalized learning path
  
## Process:
![Home Page](https://github.com/user-attachments/assets/7ce6c87a-6d21-4334-8f83-a160147837b6)

![login page](https://github.com/user-attachments/assets/0125d9fc-74fb-4986-b01d-73cf5c391b91)

![Register page](https://github.com/user-attachments/assets/39ca0234-6367-45c5-a08c-7e8cc66b0bea)

![DashBoard](https://github.com/user-attachments/assets/fdfe1705-1516-42b2-956e-e67fea913aed)

![DataBase](https://github.com/user-attachments/assets/13aea254-4e85-4336-8040-078baa615eaf)


## 🌐 API 
Endpoints

### Authentication Endpoints

#### Register User
```
POST /api/auth/register
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "password123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "roles": ["ROLE_USER"]
}
```

#### Login
```
POST /api/auth/login
Content-Type: application/json

{
  "email": "john@example.com",
  "password": "password123"
}
```

**Response:** Same as register response

### Career Role Endpoints

#### Get All Roles
```
GET /api/roles
Authorization: Bearer {token}
```

**Response:**
```json
[
  {
    "id": 1,
    "name": "Frontend Developer",
    "description": "Develops user-facing web applications...",
    "requiredSkills": [...]
  }
]
```

#### Get Role by ID
```
GET /api/roles/{id}
Authorization: Bearer {token}
```

### Skill Analysis Endpoints

#### Analyze Skill Gap
```
POST /api/skills/analyze
Authorization: Bearer {token}
Content-Type: application/json

{
  "roleId": 2,
  "userSkillIds": [1, 3, 5, 7]
}
```

**Response:**
```json
{
  "roleName": "Java Developer",
  "matchedSkills": ["Java", "Spring Boot"],
  "missingSkills": ["MySQL", "JWT", "Docker"],
  "matchPercentage": 40.0,
  "totalRequiredSkills": 5,
  "matchedCount": 2
}
```

#### Get Learning Roadmap
```
GET /api/skills/roadmap/{roleId}
Authorization: Bearer {token}
```

**Response:**
```json
{
  "roleName": "Java Developer",
  "beginner": [
    {
      "skill": "Java",
      "stage": "BEGINNER",
      "description": "Learn Java fundamentals",
      "resources": ["Official documentation: Java", ...]
    }
  ],
  "intermediate": [...],
  "advanced": [...]
}
```

#### Get All Skills
```
GET /api/skills
Authorization: Bearer {token}
```

## 🔐 Security Features

- **JWT Authentication**: Stateless token-based authentication
- **Password Hashing**: BCrypt password encryption
- **Role-Based Access**: USER and ADMIN roles
- **CORS Configuration**: Configured for frontend access
- **Secure Headers**: Spring Security default security headers

## 📊 Database Schema

### Tables
- **users**: User accounts
- **roles**: User roles (ROLE_USER, ROLE_ADMIN)
- **user_roles**: Many-to-many relationship
- **skills**: Available skills
- **career_roles**: Job roles
- **career_role_skills**: Required skills for each role
- **user_skills**: User's known skills

### Sample Data

The `data.sql` file includes:
- 2 roles (USER, ADMIN)
- 25+ skills (Java, Spring Boot, React, Python, etc.)
- 4 career roles (Frontend Developer, Java Developer, Data Analyst, Backend Developer)
- Skill associations for each role

## 🧪 Testing the Application

### Using Postman/Thunder Client

1. **Register:**
   - POST `http://localhost:8080/api/auth/register`
   - Body: `{"name": "Test User", "email": "test@test.com", "password": "test123"}`

2. **Login:**
   - POST `http://localhost:8080/api/auth/login`
   - Body: `{"email": "test@test.com", "password": "test123"}`
   - Copy the token from response

3. **Get Roles:**
   - GET `http://localhost:8080/api/roles`
   - No authentication required

4. **Analyze Skills:**
   - POST `http://localhost:8080/api/skills/analyze`
   - Headers: `Authorization: Bearer {token}`
   - Body: `{"roleId": 2, "userSkillIds": [1, 2, 3]}`

## 🐛 Troubleshooting

### Common Issues

1. **Port 8080 already in use:**
   - Change port in `application.properties`: `server.port=8081`

2. **Database connection error:**
   - Verify MySQL is running
   - Check credentials in `application.properties`
   - Ensure database exists

3. **CORS errors in browser:**
   - Verify CORS origins in `SecurityConfig.java`
   - Check frontend URL matches allowed origins

4. **JWT token expired:**
   - Token expires after 24 hours (configurable in `application.properties`)
   - Login again to get new token

5. **Maven build fails:**
   - Check JDK version: `java -version` (should be 17+)
   - Clear Maven cache: `mvn clean`
   - Update Maven: `mvn -U clean install`

## 🚀 Future Enhancements

- [ ] User profile management
- [ ] Skill progress tracking
- [ ] Integration with learning platforms (Coursera, Udemy)
- [ ] Skill recommendations based on career goals
- [ ] Social features (share roadmaps, compare with peers)
- [ ] Admin panel for managing roles and skills
- [ ] Export roadmap as PDF
- [ ] Email notifications for roadmap updates
- [ ] Multi-language support
- [ ] Advanced analytics and reporting

## 📝 License

This project is open source and available for educational purposes.

## 👥 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📧 Support

For issues and questions, please open an issue on the project repository.

---

**Built with ❤️ using Spring Boot and Bootstrap**

