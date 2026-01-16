# Quick Setup Guide

## Prerequisites Check

Before running the project, ensure you have:

1. **JDK 17 or higher**
   ```bash
   java -version
   ```

2. **Maven 3.6+**
   ```bash
   mvn -version
   ```

3. **MySQL Server running**
   - Default port: 3306
   - Default credentials in `application.properties`: root/root

## Quick Start

### Option 1: Using Batch Script (Windows)

1. **Create MySQL Database:**
   ```sql
   CREATE DATABASE skill_gap_analyzer;
   ```

2. **Update Database Credentials** (if needed):
   - Edit `backend/src/main/resources/application.properties`
   - Update `spring.datasource.username` and `spring.datasource.password`

3. **Run Backend:**
   - Double-click `backend/run.bat`
   - Or run: `cd backend && run.bat`

4. **Run Frontend:**
   - Open `frontend/index.html` in a browser
   - Or use Live Server extension in VS Code

### Option 2: Manual Setup

#### Backend:
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

#### Frontend:
- Open `frontend/index.html` in browser
- Or use any local server (e.g., Python: `python -m http.server 5500`)

## Verify Installation

1. **Backend is running:**
   - Open: http://localhost:8080/api/roles
   - Should return JSON with career roles

2. **Frontend is accessible:**
   - Open: `frontend/index.html`
   - Should see the home page

## Troubleshooting

### Maven not found
- Install Maven from https://maven.apache.org/download.cgi
- Add to PATH: `%MAVEN_HOME%\bin`

### Java not found
- Install JDK 17+ from https://adoptium.net/
- Set JAVA_HOME environment variable
- Add to PATH: `%JAVA_HOME%\bin`

### Database connection error
- Ensure MySQL is running
- Check credentials in `application.properties`
- Verify database exists: `CREATE DATABASE skill_gap_analyzer;`

### Port 8080 already in use
- Change port in `application.properties`: `server.port=8081`
- Update frontend API URL in `frontend/js/auth.js`

## Next Steps

1. Register a new user
2. Login with your credentials
3. Select a career role
4. Analyze your skill gap
5. Get your learning roadmap!
