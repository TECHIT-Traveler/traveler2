const bootstrapSassAbstractsImports = require("vue-cli-plugin-bootstrap-vue/sassAbstractsImports.js");
module.exports = {
  buildDir: "nuxt-dist",
  css: {
    loaderOptions: {
      sass: {
        additionalData: bootstrapSassAbstractsImports.join("\n"),
      },
      scss: {
        additionalData: [...bootstrapSassAbstractsImports, ""].join(";\n"),
      },
    },
  },
  devServer: {
    proxy: "http://localhost:8090",
  },
};
