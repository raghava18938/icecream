@echo off
REM Start MySQL container using Podman
echo Starting MySQL container with Podman...

REM Check if Podman is installed
where podman >nul 2>nul
if errorlevel 1 (
    echo Podman is not installed. Please install Podman first.
    exit /b 1
)

REM Check if container already exists and is running
podman ps | findstr icecream-mysql >nul 2>nul
if errorlevel 0 (
    echo Container is already running.
    goto :end
)

REM Check if container exists but is stopped
podman ps -a | findstr icecream-mysql >nul 2>nul
if errorlevel 0 (
    echo Starting existing container...
    podman start icecream-mysql
    goto :wait
)

REM Create and run container
echo Creating MySQL container...
podman run -d ^
  --name icecream-mysql ^
  -e MYSQL_ROOT_PASSWORD=rootpassword ^
  -e MYSQL_DATABASE=icecream_db ^
  -e MYSQL_USER=icecream_user ^
  -e MYSQL_PASSWORD=icecream_password ^
  -p 3306:3306 ^
  mysql:8.0

:wait
REM Wait for MySQL to be ready
echo Waiting for MySQL to be ready (this may take a moment)...
timeout /t 10

:end
echo MySQL container is ready!
echo.
echo Connection details:
echo   Host: localhost
echo   Port: 3306
echo   Database: icecream_db
echo   Username: icecream_user
echo   Password: icecream_password
echo.

