module.exports = {
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8090',  // 스프링 서버의 포트로 변경
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''
        }
      }
    }
  }
}