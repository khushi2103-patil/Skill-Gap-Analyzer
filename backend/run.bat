@echo off
echo ========================================
echo Skill Gap Analyzer - Backend Server
echo ========================================
echo.

REM Check if Maven is installed
where mvn >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Maven is not installed or not in PATH
    echo Please install Maven and add it to your PATH
    echo.
    pause
    exit /b 1
)

REM Check if Java is installed
where java >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install JDK 17+ and add it to your PATH
    echo.
    pause
    exit /b 1
)

echo Building project...
call mvn clean install -DskipTests
if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ERROR: Build failed!
    pause
    exit /b 1
)

echo.
echo Starting Spring Boot application...
echo Server will be available at: http://localhost:8080
echo.
call mvn spring-boot:run

pause
