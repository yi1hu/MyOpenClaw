#!/bin/bash

echo "正在启动 MyOpenClaw 项目..."

# 启动后端服务
echo "启动后端 Spring Boot 应用..."
cd /workspaces/MyOpenClaw
nohup mvn spring-boot:run -pl ruoyi-admin > backend.log 2>&1 &

echo "等待后端服务启动..."
sleep 30

# 启动前端服务
echo "启动前端 Vue 应用..."
cd /workspaces/MyOpenClaw/ruoyi-ui
npm install
nohup npm run dev > frontend.log 2>&1 &

echo "项目已启动！"
echo "后端访问地址: http://localhost:8080"
echo "前端访问地址: http://localhost:80"