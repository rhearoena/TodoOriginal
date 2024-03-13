<template>
    <!-- <div class="grid grid-cols-4 gap-4">
            
            <h1 class = "text-center"> Todotasks List</h1>

            <table class = "table table-striped">
                <thead>
                    <tr>
                        <th> Task ID</th>
                        <th> Task Title</th>
                        <th> Description</th>
                        <th> User</th>
                        <th> Action</th>
                    </tr>

                </thead>
                <tbody>
                    <tr v-for="todotask in todotasks" v-bind:key="todotask.id">
                        <td> {{todotask.id }}</td>
                        <td> {{todotask.title}}</td>    
                        <td> {{todotask.description}}</td>
                        <td> {{todotask.user_id}}</td>
                        <td> <router-link :to="{ name: 'todotask-details', params: { id: todotask.id }}">View</router-link>              </td>
                    </tr>
                </tbody>
            </table>
        </div> -->
        
  <div class="flex flex-wrap justify-center">
    <div v-for="todotask in todotasks" v-bind:key="todotask.id"> 
        <div class="max-w-sm rounded overflow-hidden py-2 px-2">
            <div class="card w-80 bg-neutral text-neutral-content shadow-lg">
                <div class="card-body items-start text-center">
                    <h3 class="card-title">{{todotask.title}}</h3>
                    <p>{{todotask.description}}</p>
                    <div class="card-actions justify-items-end">
                        <button class="btn btn-sm btn-info" onclick="my_modal_3.showModal()">open modal</button>
                        
                        <button class="btn btn-sm btn-success">Deny</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<dialog id="my_modal_3" class="modal">
    <div class="modal-box">
        <!-- <form method="dialog">
          <button class="btn btn-sm btn-circle btn-ghost absolute right-2 top-2">✕</button>
        </form>
        <h3 class="font-bold text-lg">Hello!</h3>
        <p class="py-4">Press ESC key or click on ✕ button to close</p> -->
        <Todotask msg="Todo Application"/>
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
            todotasks: []
        }
    },
    methods: {
        getAll(){
            TodotaskService.getAll().then((response) => {
                this.todotasks = response.data;   
            });
        },
    },
    created() {
        this.getAll();
    },
}


</script>