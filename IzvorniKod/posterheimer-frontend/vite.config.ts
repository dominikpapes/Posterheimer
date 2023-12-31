import { defineConfig } from 'vite'
import { DotenvConfigOptions, configDotenv } from 'dotenv'
import react from '@vitejs/plugin-react'

const API_BASE_URL_LOCAL = "http://localhost:8080"
const API_BASE_URL_DEPLOY = "https://posterheimer-service.onrender.com/"
const API_BASE_URL = process.env.API_BASE_URL

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      "/api": {
        target: API_BASE_URL_DEPLOY,
        changeOrigin: true
      }
    }
  }
})
