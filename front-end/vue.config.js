const path = require('path')

module.exports = {
  outputDir: path.resolve(__dirname, "../src/main/resources/static"),
  devServer: {
    port: 3030,
    proxy: {
      '/api*': {
        target: 'http://localhost:8080',
        ws: true,
        changeOrigin: true
      }
    }
  },
  configureWebpack: {
    entry: {
      app: './src/main.js',
      style: [
        'bootstrap/dist/css/bootstrap.min.css'
      ]
    }
  }
}
