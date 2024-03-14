<template>
  <div class="card">
      <div v-if="data" class="edit-form">
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
              v-model="data.title"
            />
          </div>
          <div class="form-group">
            <label for="description">Description</label>
            <input type="text" class="form-control" id="description"
              v-model="data.description"
            />
          </div>
          <div class="form-group">
            <label for="user_id">User</label>
            <input type="text" class="form-control" id="user_id"
              v-model="data.user_id"
            />
          </div>
          <div class="form-group">
            <label for="planned_start_date">Planned Start Date</label>
            <input type="text" class="form-control" id="planned_start_date"
              v-model="data.planned_start_date"
            />
          </div>
          <div class="form-group">
            <label for="planned_end_date">Planned End Date</label>
            <input type="text" class="form-control" id="planned_end_date"
              v-model="data.planned_end_date"
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
    </div>  
  </template>
  
  <script>
  import TodotaskService from "../services/TodotaskService";
  
  export default {
    props:{
      data:Object
    },
    name: "todotask-details",
    data() {
      return {
        message: ''
      };
    },
    methods: {
      get(id) {
        TodotaskService.get(id)
          .then(response => {
            this.data = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
      },
      updateTodotask() {
        TodotaskService.update(this.data.id, this.data)
          .then(response => {console.log(response.data);
            this.message = 'Todotask was updated successfully!';
          })
          .catch(e => {
            console.log(e);
          });
      },
  
      deleteTodotask() {
        TodotaskService.delete(this.data.id)
          .then(response => {
            console.log(response.data);
            this.$router.push({ name: "todotasks" });
            this.message = 'Todotask was deleted successfully!';
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