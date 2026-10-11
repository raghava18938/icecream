# 🔐 Security & Password Guidelines

## Environment Variables (Never Commit Passwords!)

### Create `.env.local` (LOCAL ONLY - Don't commit!)

```bash
# Database Config
DB_HOST=localhost
DB_PORT=3306
DB_NAME=icecream_db
DB_USER=icecream_user
DB_PASSWORD=your_local_password

# Server Config
SERVER_PORT=8080

# Spring Profile
SPRING_PROFILES_ACTIVE=local
```

**Add to `.gitignore`:**
```
.env.local
.env
*.env
```

---

## Railway-Specific Security

### Option 1: Railway Auto-Generated Secrets ✅ RECOMMENDED

Railway automatically:
- Generates secure passwords
- Encrypts them
- Injects via environment variables
- No manual password entry needed

**Steps:**
1. Add MySQL in Railway dashboard
2. Railway shows connection string
3. Use these auto-generated values
4. They're automatically set as env vars

### Option 2: Manual Environment Variables in Railway

If you want to set custom values:

1. Go to Railway project dashboard
2. Click "Variables" tab
3. Add your secrets:
   ```
   DB_HOST: your-mysql-host
   DB_PORT: 3306
   DB_NAME: icecream_db
   DB_USER: icecream_user
   DB_PASSWORD: your_secure_password
   ```
4. Railway encrypts them
5. Never shown in logs

---

## What NOT to Do ❌

- ❌ Don't hardcode passwords in `application.yml`
- ❌ Don't commit `.env` files to Git
- ❌ Don't share API keys/tokens in chat
- ❌ Don't use simple passwords like "password123"
- ❌ Don't log sensitive data

---

## What to DO ✅

- ✅ Use environment variables for all secrets
- ✅ Let Railway generate passwords automatically
- ✅ Use strong random passwords (12+ chars with special chars)
- ✅ Store secrets in Railway dashboard, not in code
- ✅ Rotate passwords regularly
- ✅ Use `.gitignore` to exclude sensitive files

---

## For Your Current App

Since your app currently works without a database (products are hardcoded), you don't need password management yet.

**When you add a database later:**

1. Add MySQL in Railway
2. Copy the auto-generated connection string
3. Railway handles password automatically
4. Your app reads it from environment variables

---

## Spring Security (Future Enhancement)

When you add login/user authentication, add Spring Security:

```xml
<!-- Add to pom.xml -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

Then use password encoding:
```java
@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

---

## Checklist Before Deploying

- ✅ No passwords in source code
- ✅ `.env` files in `.gitignore`
- ✅ Use environment variables for DB config
- ✅ All secrets stored in Railway dashboard
- ✅ No sensitive data in logs

You're all set for secure deployment! 🔒

