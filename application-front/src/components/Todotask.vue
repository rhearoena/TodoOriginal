<template>
   <h2>{{ msg }}</h2>
<!-- 
  <div class="card">
      <div v-if="currentTodotask" class="edit-form">
        <div class="modal" role="dialog" id="my_modal_8">
        <div class="modal-box">
          <h3 class="font-bold text-lg">Hello!</h3>
          <p class="py-4">This modal works with anchor links</p>
          <div class="modal-action">
          <a href="#" class="btn">Yay!</a>
          </div>
        </div>
      </div>
        <h4>Todotask</h4>
        <form>
          <div class="form-group">
            <label for="title">Title</label>
            <input type="text" class="form-control" id="title"
              v-model="currentTodotask.title"
            />
          </div>
          <div class="form-group">
            <label for="description">Description</label>
            <input type="text" class="form-control" id="description"
              v-model="currentTodotask.description"
            />
          </div>
          <div class="form-group">
            <label for="user_id">User</label>
            <input type="text" class="form-control" id="user_id"
              v-model="currentTodotask.user_id"
            />
          </div>
          <div class="form-group">
            <label for="planned_start_date">Planned Start Date</label>
            <input type="text" class="form-control" id="planned_start_date"
              v-model="currentTodotask.planned_start_date"
            />
          </div>
          <div class="form-group">
            <label for="planned_end_date">Planned End Date</label>
            <input type="text" class="form-control" id="planned_end_date"
              v-model="currentTodotask.planned_end_date"
            />
          </div>
        </form>
    
        <button class="badge badge-danger mr-2"
          @click="deleteTodotask()"
        >
          Delete
        </button>
    
        <button type="submit" class="badge badge-success"
          @click="updateTodotask()"
        >
          Update
        </button>
        <p>{{ message }}</p>
      </div>
    
      <div v-else>
        <br />
        <p>Please click on a Todotask...</p>
      </div>
    </div>  -->
<div class="max-w-sm rounded overflow-hidden shadow-lg">
  <div class="px-6 py-4">
    <div class="font-bold text-xl mb-2">The Coldest Sunset</div>
    <p class="text-gray-700 text-base">
      Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptatibus quia, nulla! Maiores et perferendis eaque, exercitationem praesentium nihil.
    </p>
  </div>
  <div class="px-6 pt-4 pb-2">
    <span class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">#photography</span>
    <span class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">#travel</span>
    <span class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">#winter</span>
  </div>
</div>

  </template>
  
  <script>
  import TodotaskService from "../services/TodotaskService";
  
  export default {
    name: "todotask-details",
    data() {
      return {
        currentTodotask: null,
        message: ''
      };
    },
    methods: {
      get(id) {
        TodotaskService.get(id)
          .then(response => {
            this.currentTodotask = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
      },
      updateTodotask() {
        TodotaskService.update(this.currentTodotask.id, this.currentTodotask)
          .then(response => {console.log(response.data);
            this.message = 'The todotask was updated successfully!';
          })
          .catch(e => {
            console.log(e);
          });
      },
  
      deleteTodotask() {
        TodotaskService.delete(this.currentTodotask.id)
          .then(response => {
            console.log(response.data);
            this.$router.push({ name: "todotasks" });
          })
          .catch(e => {
            console.log(e);
          });
      }
    },
    mounted() {
      this.message = '';
      this.get(this.$route.params.id);
    }
  };
  </script>
  
  <style>
  .edit-form {
    max-width: 300px;
    margin: auto;
  }
  </style>