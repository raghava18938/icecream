# Podman MySQL Setup Guide

This guide explains how to configure and run MySQL with Podman for the Ice Cream application.

## Prerequisites

1. **Install Podman**
   - Windows: Download from https://podman.io/docs/installation/windows
   - Or use WSL2 with Linux distribution that has Podman
   - Or use Docker instead (same commands work with `docker`)

2. **Java 21** - Already required for the project

## Quick Start

### Option 1: Using Podman (Recommended)

#### Windows (PowerShell/CMD):
```bash
# Start MySQL container
.\start-mysql.bat

# Or manually:
podman run -d `
  --name icecream-mysql `
  -e MYSQL_ROOT_PASSWORD=rootpassword `
  -e MYSQL_DATABASE=icecream_db `
  -e MYSQL_USER=icecream_user `
  -e MYSQL_PASSWORD=icecream_password `
  -p 3306:3306 `
  mysql:8.0
```

#### Linux/Mac:
```bash
# Start MySQL container
chmod +x start-mysql.sh
./start-mysql.sh

# Or manually:
podman run -d \
  --name icecream-mysql \
  -e MYSQL_ROOT_PASSWORD=rootpassword \
  -e MYSQL_DATABASE=icecream_db \
  -e MYSQL_USER=icecream_user \
  -e MYSQL_PASSWORD=icecream_password \
  -p 3306:3306 \
  mysql:8.0
```

### Option 2: Using Podman Compose

```bash
# Start services
podman-compose -f podman-compose.yml up -d

# Stop services
podman-compose -f podman-compose.yml down

# View logs
podman-compose -f podman-compose.yml logs -f mysql
```

### Option 3: Using Docker Compose (if Docker is installed)

```bash
# Start services
docker-compose up -d

# Stop services
docker-compose down

# View logs
docker-compose logs -f mysql
```

## Configuration

### Local Development (Default)

The application automatically uses `application-local.yml` when running locally.

- **Active Profile**: spring.profiles.active=local
- **Connection**: MySQL at localhost:3306
- **Database**: icecream_db
- **Username**: icecream_user
- **Password**: icecream_password
- **DDL Strategy**: update (auto-creates/updates schema)

**application-local.yml:**
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/icecream_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
    username: icecream_user
    password: icecream_password
```

### Production

For production deployment, use `application-prod.yml` with environment variables:

```bash
# Set environment variables
export DB_HOST=prod-mysql-server
export DB_PORT=3306
export DB_NAME=icecream_db
export DB_USER=prod_user
export DB_PASSWORD=secure_password

# Run with prod profile
java -jar icecream-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

## Useful Commands

### Check container status:
```bash
podman ps          # List running containers
podman ps -a       # List all containers
```

### View MySQL logs:
```bash
podman logs icecream-mysql -f   # Follow logs
```

### Connect to MySQL from command line:
```bash
podman exec -it icecream-mysql mysql -u icecream_user -p icecream_db
```

### Stop/Remove container:
```bash
podman stop icecream-mysql      # Stop container
podman rm icecream-mysql        # Remove container
```

### Reset everything:
```bash
podman stop icecream-mysql && podman rm icecream-mysql
# Then run start-mysql.bat (or .sh) again
```

## Running the Spring Boot Application

### From IDE (IntelliJ):
1. Set active profile: Run → Edit Configurations → VM options: `-Dspring.profiles.active=local`
2. Or set Environment variables: `SPRING_PROFILES_ACTIVE=local`
3. Click Run

### From Command Line (Maven):
```bash
# Build
mvn clean package

# Run with local profile
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=local"

# Or run the JAR
java -jar target/icecream-0.0.1-SNAPSHOT.jar --spring.profiles.active=local
```

## Database Migrations

The application uses Flyway for migrations. Place migration scripts in:
```
src/main/resources/db/migration/
```

Migration scripts should follow the naming convention:
- V1__initial_schema.sql
- V2__add_users_table.sql

**Note:** In local development, Flyway is enabled with `ddl-auto: update`.

## Troubleshooting

### Port 3306 already in use:
```bash
# Find what's using port 3306
netstat -ano | findstr :3306   # Windows
lsof -i :3306                   # Mac/Linux

# Change port in podman run command: -p 3307:3306
# Then update application-local.yml to use port 3307
```

### MySQL container won't start:
```bash
# Check logs
podman logs icecream-mysql

# Remove and restart
podman rm -f icecream-mysql
# Then run start-mysql script again
```

### Connection refused:
- Ensure MySQL container is running: `podman ps`
- Wait a few seconds for MySQL to fully initialize
- Check credentials in application-local.yml match container env vars

### Driver not found error:
- Ensure MySQL connector dependency is in pom.xml (already configured)
- Run: `mvn clean install` to download dependencies
- Restart IntelliJ and rebuild project

## Default Credentials

| Property | Value |
|----------|-------|
| Host | localhost |
| Port | 3306 |
| Database | icecream_db |
| Root Password | rootpassword |
| App User | icecream_user |
| App Password | icecream_password |

**⚠️ Important:** Change these credentials in production!

## Next Steps

1. Start MySQL container: `.\start-mysql.bat`
2. Run the Spring Boot application (local profile)
3. Access the application at: `http://localhost:8080`
4. View database console if available at: `/h2-console` (changed to MySQL)

---

**Files Created:**
- `podman-compose.yml` - Podman compose configuration
- `docker-compose.yml` - Docker compose configuration (compatible with Docker)
- `start-mysql.bat` - Startup script for Windows
- `start-mysql.sh` - Startup script for Linux/Mac
- `application-local.yml` - Updated with MySQL configuration
- `application-prod.yml` - Production configuration with env vars

