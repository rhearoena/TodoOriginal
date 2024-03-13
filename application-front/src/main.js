import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from './router'

// createApp(App).mount('#app')

// import { createApp } from 'vue'
// import App from './App.vue'
// import './style.css'
// import 'bootstrap'
// import 'bootstrap/dist/css/bootstrap.min.css'

createApp(App).use(router).mount('#app')