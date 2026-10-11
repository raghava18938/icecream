#!/bin/bash
# Start MySQL container using Podman

echo "Starting MySQL container with Podman..."

# Check if Podman is installed
if ! command -v podman &> /dev/null; then
    echo "Podman is not installed. Please install Podman first."
    exit 1
fi

# Check if container is already running
if podman ps | grep -q icecream-mysql; then
    echo "Container is already running."
    exit 0
fi

# Check if container exists but is stopped
if podman ps -a | grep -q icecream-mysql; then
    echo "Starting existing container..."
    podman start icecream-mysql
    sleep 10
    exit 0
fi

# Create and run container
echo "Creating MySQL container..."
podman run -d \
  --name icecream-mysql \
  -e MYSQL_ROOT_PASSWORD=rootpassword \
  -e MYSQL_DATABASE=icecream_db \
  -e MYSQL_USER=icecream_user \
  -e MYSQL_PASSWORD=icecream_password \
  -p 3306:3306 \
  mysql:8.0

echo "Waiting for MySQL to be ready (this may take a moment)..."
sleep 10

echo "MySQL container is ready!"
echo ""
echo "Connection details:"
echo "  Host: localhost"
echo "  Port: 3306"
echo "  Database: icecream_db"
echo "  Username: icecream_user"
echo "  Password: icecream_password"
echo ""

