# 🚀 Deployment Guide - Firefly Ice Cream Shop

## Quick Start - Choose Your Method

### ⚡ **Method 1: Railway.app (EASIEST - Recommended)**

Railway automatically deploys Docker containers. It's the fastest way!

**Steps:**

1. **Commit and push to GitHub**
   ```bash
   git init
   git add .
   git commit -m "Initial commit"
   git branch -M main
   git remote add origin https://github.com/YOUR_USERNAME/icecream.git
   git push -u origin main
   ```

2. **Create Railway account**
   - Go to https://railway.app
   - Sign up with GitHub

3. **Create new project**
   - Click "New Project"
   - Select "Deploy from GitHub"
   - Connect your GitHub and select `icecream` repo
   - Railway auto-detects the Dockerfile and builds it

4. **Add MySQL Database (Optional - for future features)**
   - In Railway dashboard, click "Add Service"
   - Select "MySQL"
   - Railway auto-creates the database
   - Exposes connection variables

5. **Set Environment Variables (if using custom MySQL)**
   - `SPRING_PROFILES_ACTIVE=prod`
   - `DB_HOST=your-db-host`
   - `DB_PORT=3306`
   - `DB_NAME=icecream_db`
   - `DB_USER=icecream_user`
   - `DB_PASSWORD=your-password`

6. **Share the link**
   - Railway gives you a public URL like: `https://icecream-production.railway.app`
   - Share with your friend!

**Cost:** Free tier available ($5/month credits)

**Note:** If you see Docker build errors, make sure you've committed all files including:
- `Dockerfile`
- `railway.json`
- `pom.xml`
- `src/` folder

---

### 🔥 **Method 2: Ngrok (QUICKEST - For Testing)**

Perfect for quick demos to your friend!

**Steps:**

1. **Download ngrok**
   - Windows: https://ngrok.com/download
   - Extract to a folder

2. **Run your app locally**
   ```bash
   mvn clean install -DskipTests
   java -jar target/icecream-0.0.1-SNAPSHOT.jar
   ```

3. **Open another terminal, run ngrok**
   ```bash
   .\ngrok.exe http 8080
   ```

4. **Copy the public URL**
   ```
   Forwarding: https://xxxx-xx-xxx-xxx.ngrok.io -> http://localhost:8080
   ```

5. **Share with friend**
   - Send: `https://xxxx-xx-xxx-xxx.ngrok.io/`
   - ⚠️ URL changes every time you restart ngrok

**Cost:** Free (limited hours)

---

### 🌊 **Method 3: Render.com**

Similar to Railway, very easy.

**Steps:**

1. Push to GitHub (same as Railway)
2. Go to https://render.com
3. Click "New" → "Web Service"
4. Connect GitHub repo
5. Select branch: `main`
6. Runtime: `Java`
7. Build command: `mvn clean install -DskipTests`
8. Start command: `java -jar target/icecream-0.0.1-SNAPSHOT.jar`
9. Click "Create Web Service"

**Cost:** Free tier (hibernates after 15 min of inactivity, wakes on request)

---

### ☁️ **Method 4: Docker + Push to Cloud**

If you want to use Docker directly:

```bash
# Build Docker image
docker build -t icecream:latest .

# Run locally to test
docker run -p 8080:8080 icecream:latest

# Push to Docker Hub (optional)
docker tag icecream:latest YOUR_DOCKERHUB_USERNAME/icecream:latest
docker push YOUR_DOCKERHUB_USERNAME/icecream:latest
```

Then deploy using:
- AWS ECS
- Google Cloud Run
- Azure Container Instances

---

### 💎 **Method 5: DigitalOcean (Low Cost VPS)**

Good if you want full control.

**Steps:**

1. Create DigitalOcean account ($5/month droplet)
2. Create a Ubuntu droplet
3. SSH into the droplet
4. Install Java & MySQL:
   ```bash
   sudo apt update
   sudo apt install openjdk-21-jre-headless
   sudo apt install mysql-server
   ```
5. Clone your GitHub repo
6. Build and run the app

---

## 📊 **Comparison Table**

| Method | Cost | Setup Time | URL Type | Keeps Running |
|--------|------|-----------|----------|---------------|
| **Railway** | Free ($5 credits) | 5 min | Permanent | ✅ Always |
| **Ngrok** | Free | 2 min | Temporary | ✅ While running |
| **Render** | Free | 5 min | Permanent | ⏸️ Hibernates |
| **Docker Hub** | Free | 10 min | Custom | Depends |
| **DigitalOcean** | $5/month | 15 min | Permanent | ✅ Always |
| **AWS** | Variable | 30 min | Permanent | ✅ Yes |

---

## 🎯 **My Recommendation**

### For showing your friend NOW → **Use Ngrok** (2 minutes)
```bash
# Terminal 1: Run app
java -jar target/icecream-0.0.1-SNAPSHOT.jar

# Terminal 2: Expose to internet
ngrok http 8080

# Share the https URL with your friend!
```

### For permanent hosting → **Use Railway** (5 minutes)
- Push to GitHub once
- Railway auto-deploys
- Friend can access 24/7
- Free tier includes $5 credits

---

## 🔧 **Troubleshooting**

**App fails to start on hosting platform?**
- Check MySQL is running/configured
- Ensure environment variables are set
- Check logs in hosting dashboard

**Database connection errors?**
- Cloud MySQL might be needed
- Use environment variables for connection strings
- Configure firewall rules

**Port issues?**
- Most platforms auto-expose port 8080
- Some use port 3000 or dynamic ports

---

## 📝 **Next Steps**

1. **For quick demo:** Use Ngrok
2. **For production:** Set up Railway
3. **Add features** and deploy automatically (git push auto-deploys!)

Enjoy! 🍦

