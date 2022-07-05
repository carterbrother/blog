import { createApp } from 'vue'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import App from './App.vue'
// import store  from '@/store'

import VueNativeSock from "vue-native-websocket-vue3";
import router from '@/router'



const app= createApp(App);

// 挂载api
// app.config.globalProperties.$api = api;

app.use(ElementPlus)
.use(router)
.mount('#app')

// app.use(VueNativeSock,'ws://127.0.0.1:8080/websocket/111',{
//         // 启用Vuex集成
//         store: store,
//         // 数据发送/接收使用使用json
//         format: "json",
//         // 开启手动调用 connect() 连接服务器
//         connectManually: false,
//         // 开启自动重连
//         reconnection: true,
//         // 尝试重连的次数
//         reconnectionAttempts: 5,
//         // 重连间隔时间
//         reconnectionDelay: 3000
// })

export default app