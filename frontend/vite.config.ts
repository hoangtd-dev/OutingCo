import { fileURLToPath, URL } from 'node:url'

import tailwindcss from '@tailwindcss/vite'
import react from '@vitejs/plugin-react'
import { defineConfig } from 'vite'

const src = (path: string) => fileURLToPath(new URL(path, import.meta.url))

export default defineConfig({
  plugins: [react(), tailwindcss()],
  resolve: {
    alias: {
      '@': src('./src'),
      '@app': src('./src/app'),
      '@components': src('./src/components'),
      '@features': src('./src/features'),
      '@hooks': src('./src/hooks'),
      '@lib': src('./src/lib'),
      '@pages': src('./src/pages'),
      '@types': src('./src/types'),
    },
  },
  server: {
    port: 5173,
  },
})
