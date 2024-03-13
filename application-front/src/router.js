import { createWebHistory, createRouter } from "vue-router";

const routes =  [
  {
    path: "/",
    alias: "/todotasks",
    name: "todotasks",
    component: () => import("./components/TodotasksList.vue")
   },
  {
    path: "/todotasks/:id",
    name: "todotask-details",
    component: () => import("./components/Todotask.vue")
  },
  {
    path: "/addTodotask",
    name: "addTodotask",
    component: () => import("./components/AddTodoTask.vue")
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;