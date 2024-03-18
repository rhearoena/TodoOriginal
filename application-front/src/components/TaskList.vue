<template>     
    <div class="flex flex-wrap justify-center">
      <div v-for="todotask in todotasks" v-bind:key="todotask.id"> 
          <!-- <div class="max-w-sm rounded overflow-hidden py-2 px-2 ring-offset-2 ring-2">
              <div class="card w-80 bg-neutral text-neutral-content shadow-lg">
                  <div class="card-body items-start text-center">
                      <h3 class="card-title">{{todotask.title}}</h3>
                      <h3 class="card-title">{{todotask.priority}}</h3>
                      <p>{{todotask.progress}}%</p><p>{{todotask.status}}</p>
                      <div class="card-actions justify-items-end">
                          <button class="btn btn-sm btn-info" @click="getTaskDetails(todotask)" onclick="my_modal_3.showModal()">Update</button>
                          <button class="btn btn-sm btn-success">Complete</button>
                      </div>
                  </div>
              </div>
          </div> -->
          <table class="table-fixed">
            <thead>
                <tr>
                <th class="text-xl">Title</th>
                <th class="text-xl">Priority</th>
                <th class="text-xl">Progress</th>
                <th class="text-xl">Status</th>
                </tr>
            </thead>
            <tbody v-for="todotask in todotasks" v-bind:key="todotask.id">
                <tr>
                <td>{{todotask.title}}</td>
                <td>{{todotask.priority}}</td>
                <td>{{todotask.progress}}%</td>
                <td>{{todotask.priority}}%</td>
                </tr>
                <!-- <tr>
                <td>Witchy Woman</td>
                <td>The Eagles</td>
                <td>1972</td>
                </tr>
                <tr>
                <td>Shining Star</td>
                <td>Earth, Wind, and Fire</td>
                <td>1975</td>
                </tr> -->
            </tbody>
        </table>
      </div>
  </div>
  <dialog id="my_modal_3" class="modal">
      <div class="modal-box">
          <form method="dialog">
            <button class="btn btn-sm btn-circle btn-ghost absolute right-2 top-2">✕</button>
          </form>
          <Todotask :data="this.taskDetails"/>
      </div>
  </dialog>
  
  </template>
  <script>
  
  import TodotaskService from '../services/TodotaskService';
  import Todotask from './Todotask.vue';
  
  export default {
      name: 'todo-tasks',
      data(){
          return {
              taskDetails: [],
              showModal: false,
              todotasks: [],
              modalData:{}
          }
      },
      methods: {
          getAll(){
              TodotaskService.getAll().then((response) => {
                  this.todotasks = response.data;   
              });
          },
          getTaskDetails(todotask) {
             this.taskDetails = todotask
          },
          openModal() {
          this.showModal = true;
          },
          closeModal() {
          this.showModal = false;
          }
          },
          created() {
              this.getAll();
          },
          components:{
              Todotask
          }
  }
  
  
  </script>