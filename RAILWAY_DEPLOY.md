# 🚀 Quick Deploy to Railway

## Step 1: Make sure everything is built locally

```bash
# Clean and build
./mvnw clean install -DskipTests

# Test locally
java -jar target/icecream-0.0.1-SNAPSHOT.jar
```

Visit http://localhost:8080 and verify it works!

## Step 2: Commit to GitHub

```bash
# Initialize git if not already done
git init
git add .
git commit -m "Firefly Ice Cream App - Ready for deployment"

# Add your GitHub repo
git remote add origin https://github.com/YOUR_USERNAME/icecream.git
git branch -M main
git push -u origin main
```

You'll need to replace `YOUR_USERNAME` with your actual GitHub username.

## Step 3: Deploy on Railway

1. Go to https://railway.app (sign up with GitHub)
2. Click "New Project"
3. Click "Deploy from GitHub repo"
4. Select your `icecream` repository
5. Railway will:
   - Detect the Dockerfile
   - Pull the Docker image (maven:3.9-eclipse-temurin-21-alpine)
   - Build your Spring Boot app
   - Deploy automatically
6. Get your public URL when deployment finishes

## Step 4: Share with your friend

Your app will be live at a URL like:
```
https://icecream-production.railway.app/
```

Share this with your friend in India!

## Troubleshooting

**Docker Build Failed?**
- Make sure Dockerfile is committed to GitHub
- Check that pom.xml has correct Maven syntax
- Verify all source files are included

**Port Issues?**
- Railway auto-exposes port 8080
- No configuration needed

**Database Issues?**
- App currently runs without database (products hardcoded)
- To add database later, use Railway's MySQL plugin

## Auto-Deploy on Git Push

After initial deployment:
- Every time you `git push` to main branch
- Railway automatically rebuilds and deploys
- Changes live in ~2 minutes

```bash
# Make changes, commit, and push - that's it!
git add .
git commit -m "Updated product list"
git push
```

---

**Need help?** Check Railway docs: https://docs.railway.app

