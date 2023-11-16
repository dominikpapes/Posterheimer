
import express from "express";
import { createProxyMiddleware } from "http-proxy-middleware";
import dotenv from "dotenv";
import path from "path";

// Load environment variables from .env file
dotenv.config();

const app = express();

// Configuration
const { PORT, HOST, API_BASE_URL } = process.env;

// Proxy
app.use(
  "/api",
  createProxyMiddleware({
    target: API_BASE_URL,
    changeOrigin: true,
  })
);

app.use(express.static(path.join(import.meta.url, 'build')));

app.listen(PORT, HOST, () => {
  console.log(`Starting Proxy at ${HOST}:${PORT}`);
});

app.get("*", async (req, res) => {
  res.sendFile(path.join(import.meta.url, 'build', 'index.html'));
});
