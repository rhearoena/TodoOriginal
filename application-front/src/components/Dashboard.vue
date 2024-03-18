<template>
    <div class="py-5 font-bold background-base">
    <h1>Todo Application</h1>
    <button class="btn" onclick="my_modal_4.showModal()">Add Task</button>
        <dialog id="my_modal_4" class="modal">
            <div class="modal-box w-11/12 max-w-5xl background-base">
                <form method="dialog">
                <span class="text-2xl">Add New Task</span>
                <button class="btn btn-sm btn-circle btn-active absolute right-3 top-3">✕</button>
                </form>
                <AddTodoTask/>
            </div>
        </dialog>
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