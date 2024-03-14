<template>
    <div class="py-5">
    <h1>Todo Application</h1>
    <button class="btn" onclick="my_modal_4.showModal()">Add Task</button>
        <dialog id="my_modal_4" class="modal">
        <div class="modal-box w-11/12 max-w-5xl">
            <AddTodoTask/>
            <div class="modal-action">
            <form method="dialog">
                <button class="btn">Close</button>
            </form>
            </div>
        </div>
        </dialog>
    <TodotasksList/>
    
</div>
</template>
<script setup>
import TodotasksList from "./TodotasksList.vue";
import AddTodoTask from "./AddTodoTask.vue";
</script>
