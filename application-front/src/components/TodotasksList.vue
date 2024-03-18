<template>
    <div class="Margin">
      <div class="flex items-center justify-between mt-7">
      <!-- Dropdown for selecting display mode -->
      <select class="rounded outline" v-model="displayMode">
          <option value="card">Card View</option>
          <option value="table">Table View</option>
      </select>
  
      <!-- Button to add a task -->
      <button class="btn bg-white text-[#163057] outline" onclick="my_modal_4.showModal()">Add Task</button>
  
      <!-- Dropdown for filtering tasks by priority -->
      <select class="rounded outline" v-model="filterPriority">
          <option value="">Filter by Status</option>
          <option v-for="priority in priorities" :value="priority">{{ priority }}</option>
      </select>
      </div>
  
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
          
  
      <!-- Input for filtering tasks by title -->
      <!-- <input type="text" v-model="filterTitle" placeholder="Filter by Title"> -->
  
      <!-- Display either the card or the table based on the selected mode -->
      <div class="card-container rounded" v-if="displayMode === 'card'">
        <!-- <div v-for="todotask in todotasks" :key="todotask.id" class="max-w-sm mt-2 rounded inline-block overflow-hidden py-2 px-2"> -->
          <!-- Card content -->
          <!-- <div class="card w-80 bg-neutral text-neutral-content shadow-lg"> -->
          <div v-for="todotask in filteredTasks" :key="todotask.id" class="card max-w-sm mt-10 rounded inline-block overflow-hidden py-2 px-2">
          <!-- Card content -->
            <!-- <div class="card-body items-start text-center">
              <h3 class="card-title">{{todotask.title}}</h3>
              <h3 class="card-title">{{todotask.priority}}</h3>
              <p>{{todotask.progress}}%</p><p>{{todotask.status}}</p>
              <div class="card-actions justify-items-end">
                <button class="btn btn-sm btn-info" @click="getTaskDetails(todotask); showModal = true">Update</button>
                <button class="btn btn-sm btn-success">Complete</button>
              </div>
            </div> -->
            <div div class="bg-white  text-black-content shadow-lg border-double border-4 border-dark-500 border-opacity-100 high-priority" id="tile">
              <div class="card-body items-start text-center">
                  <h3 class="card-title">{{todotask.title}}</h3>
                  <h3 class="card-title">{{todotask.priority}}</h3>
                  <p>{{todotask.progress}}%</p>
                  <!-- Progress bar -->
                  <progress class="progress" :value="todotask.progress" max="100"></progress> 
                  <p><span :class="{ 'text-red-500': todotask.status === 'Low', 'text-yellow-500': todotask.status === 'Medium', 'text-green-500': todotask.status === 'High' }">{{todotask.status}}</span></p>
                  <div class="card-actions justify-items-center">
                      <button class="btn btn-sm btn-info" @click="getTaskDetails(todotask); showModal = true">Update</button>
                      <button class="btn btn-sm btn-success">Complete</button>
                  </div>
              </div>
          </div>
        </div>
      </div>
  
      <div v-else-if="displayMode === 'table'">
        <!-- Table content -->
        <table class="table-fixed mt-7 table-rounded">
          <!-- Table headers -->
          <thead>
            <tr>
              <th>User</th>
              <th>Title</th>
              <th>Status</th>
              <th>Progress</th>
              <th>Priority</th>
              <th>Actions</th> <!-- Added for clarity -->
            </tr>
          </thead>
          <!-- Table body -->
          <tbody>
            <tr v-for="todotask in filteredTasks" :key="todotask.id"  class="task-row">
              <td class="border-2 px-4 py-2">{{todotask.assignee}}</td>
              <td class="border-2 px-4 py-2">{{todotask.title}}</td>
              <td class="border-2 px-4 py-2">{{todotask.priority}}</td>
              <td class="border-2 px-4 py-2">{{todotask.progress}}</td>
              <td class="border-2 px-4 py-2">{{todotask.status}}</td>
              <td class="border-2 px-4 py-2">
                <div class="mr-2 ml-2 mt-2 card-actions inline-block">
                  <button class="btn mr-2 btn-sm btn-info" @click="getTaskDetails(todotask); showModal = true">Update</button>
                  <button class="btn btn-sm btn-success" @click="completeTask(todotask)">Complete</button>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
  
      <!-- Modal -->
      <dialog id="my_modal_3" class="modal" v-show="showModal">
        <!-- Modal content -->
        <div class="modal-box">
          <form method="dialog">
            <button class="btn btn-sm btn-circle btn-ghost absolute right-2 top-2" @click="showModal = false">✕</button>
          </form>
          <Todotask :data="taskDetails"/>
        </div>
      </dialog>
    </div>
  </template>
  
  <script>
  import TodotaskService from '../services/TodotaskService';
  import Todotask from './Todotask.vue';
  import AddTodoTask from "./AddTodoTask.vue";
  
  export default {
    name: 'todo-tasks',
    data() {
      return {
        taskDetails: [],
        showModal: false,
        todotasks: [],
        displayMode: 'card', // Default display mode
        filterPriority: '', // Filter by Priority
        filterTitle: '', // Filter by Title
        priorities: [], // List of unique priorities
      };
    },
    computed: {
      // Filtered tasks based on priority and title
      filteredTasks() {
        let filteredTasks = this.todotasks;
        if (this.filterPriority !== '') {
          filteredTasks = filteredTasks.filter(task => task.priority === this.filterPriority);
        }
        if (this.filterTitle.trim() !== '') {
          filteredTasks = filteredTasks.filter(task => task.title.toLowerCase().includes(this.filterTitle.toLowerCase()));
        }
        return filteredTasks;
      }
    },
    methods: {
      getAll() {
        TodotaskService.getAll().then((response) => {
          this.todotasks = response.data;   
          this.getPriorities(); // Get unique priorities
        });
      },
      getTaskDetails(todotask) {
        this.taskDetails = todotask;
        this.showModal = true;

      },
      completeTask(todotask) {
        // Implement completeTask functionality
      },
      // Get unique priorities
      getPriorities() {
        this.priorities = [...new Set(this.todotasks.map(task => task.priority))];
      }
    },
    created() {
      this.getAll();
    },
    components: {
      Todotask
    }
  }
  </script>
  
  <style>
  .card-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
  }
  
  .card {
    width: calc(50% - 20px); /* Adjust the width and margin as needed */
    margin-bottom: 20px;
  }
  
  .margin{
      margin-top: 5rem
  }
  
  </style>

