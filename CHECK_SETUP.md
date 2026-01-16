# Setup Verification Checklist

Run these commands to verify your setup:

## 1. Check Java Installation
```powershell
java -version
```
**Expected:** Should show Java version 17 or higher

## 2. Check Maven Installation
```powershell
mvn -version
```
**Expected:** Should show Maven version 3.6 or higher

## 3. Check MySQL Connection
```powershell
# Test MySQL connection (if mysql client is installed)
mysql -u root -p -e "SHOW DATABASES;"
```
**Expected:** Should list databases including `skill_gap_analyzer` (or create it)

## 4. Verify Project Structure
```powershell
# Check if all key files exist
Test-Path "backend\pom.xml"
Test-Path "backend\src\main\java\com\skillgapanalyzer\SkillGapAnalyzerApplication.java"
Test-Path "frontend\index.html"
Test-Path "frontend\js\auth.js"
```

## 5. Build Backend (Test Compilation)
```powershell
cd backend
mvn clean compile
```
**Expected:** Should compile successfully without errors

## 6. Check Database Configuration
Verify `backend\src\main\resources\application.properties`:
- `spring.datasource.username=root` (or your MySQL username)
- `spring.datasource.password=root` (or your MySQL password)
- `spring.datasource.url` points to correct database

## Common Issues & Solutions

### Issue: "mvn: command not found"
**Solution:** 
1. Download Maven from https://maven.apache.org/download.cgi
2. Extract to a folder (e.g., `C:\Program Files\Apache\maven`)
3. Add to PATH: `C:\Program Files\Apache\maven\bin`
4. Restart terminal

### Issue: "java: command not found"
**Solution:**
1. Download JDK 17+ from https://adoptium.net/
2. Install JDK
3. Set JAVA_HOME: `C:\Program Files\Java\jdk-17`
4. Add to PATH: `%JAVA_HOME%\bin`
5. Restart terminal

### Issue: Database connection failed
**Solution:**
1. Ensure MySQL service is running
2. Check MySQL credentials in `application.properties`
3. Create database: `CREATE DATABASE skill_gap_analyzer;`
4. Verify MySQL is listening on port 3306

### Issue: Port 8080 already in use
**Solution:**
1. Change port in `application.properties`: `server.port=8081`
2. Update frontend API URL in `frontend/js/auth.js`: `const API_BASE_URL = 'http://localhost:8081/api';`

## Ready to Run?

Once all checks pass:
1. **Backend:** Run `backend\run.bat` or `cd backend && mvn spring-boot:run`
2. **Frontend:** Open `frontend\index.html` in browser
3. **Test:** Visit http://localhost:8080/api/roles to verify backend is running
