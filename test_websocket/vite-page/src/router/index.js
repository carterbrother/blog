import { createRouter, createWebHistory } from 'vue-router'


import Home from '@/pages/Home.vue'
import About from '@/pages/About.vue'


const router= createRouter({
    history: createWebHistory(),
    routes: [
        {path: '/', name: 'Home' , component: Home},
        {path: '/about', name: 'About' , component: About}

    ]
})

export default router