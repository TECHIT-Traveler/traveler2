const bootstrapSassAbstractsImports = require("vue-cli-plugin-bootstrap-vue/sassAbstractsImports.js");
module.exports = {
  buildDir: 'nuxt-dist',
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
};
module.exports = {
  devServer: {
    proxy: "http://localhost:8090",
  },
};
export default {
  css: [
    // Load a Node.js module directly (here it's a Sass file)
    'bulma',
    // CSS file in the project
    '~/assets/css/main.css',
    // SCSS file in the project
    '~/assets/css/main.scss'
  ]
}
