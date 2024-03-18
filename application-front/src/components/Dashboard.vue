<template>
    <div class="py-5">
    <!-- <h1>Todo Application</h1> -->
    <div class="navbar bg-base-100 w-500 rounded-lg outline mb-4">
        <div class="navbar-start flex-grow"> <!-- Use flex-grow to make this div grow and take up available space -->
            <!-- Content for navbar-start -->
        </div>
        <div class="navbar-center flex-grow"> <!-- Use flex-grow to make this div grow and take up available space -->
            <a class="btn btn-ghost text-2xl bg-white text-[#163057] ml-2">Todo List</a>
        </div>
        <div class="navbar-end flex-grow"> <!-- Use flex-grow to make this div grow and take up available space -->
            <button class="btn btn-ghost btn-circle">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" /></svg>
            </button>
            <button class="btn btn-ghost btn-circle">
            <div class="indicator">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 17h5l-1.405-1.405A2.032 2.032 0 0118 14.158V11a6.002 6.002 0 00-4-5.659V5a2 2 0 10-4 0v.341C7.67 6.165 6 8.388 6 11v3.159c0 .538-.214 1.055-.595 1.436L4 17h5m6 0v1a3 3 0 11-6 0v-1m6 0H9" /></svg>
                <span class="badge badge-xs badge-primary indicator-item"></span>
            </div>
            </button>
        </div>
    </div>
    <TodotasksList/>
    
</div>
</template>
<script setup>
import TodotasksList from "./TodotasksList.vue";
import AddTodoTask from "./AddTodoTask.vue";
</script>
<style>
.background-base{
  background-color: #f8f7e8;
}
</style>