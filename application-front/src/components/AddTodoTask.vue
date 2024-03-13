<template>
  <h1>{{ msg }}</h1>

  <div class="card">
    <div class="submit-form">
      <div v-if="!submitted">
        <div class="form-group">
          <label for="user_id">User</label>
          <input
            type="text"
            class="form-control"
            id="user_id"
            required
            v-model="todotask.user_id"
            name="user_id"
          />
        </div>
        <div class="form-group">
          <label for="title">Title</label>
          <input
            type="text"
            class="form-control"
            id="title"
            required
            v-model="todotask.title"
            name="title"
          />
        </div>
  
        <div class="form-group">
          <label for="description">Description</label>
          <input
            class="form-control"
            id="description"
            required
            v-model="todotask.description"
            name="description"
          />
        </div>
  
        <div class="form-group">
          <label for="description">Planned Start Date</label>
          <input
            class="form-control"
            id="planned_start_date"
            required
            v-model="todotask.planned_start_date"
            name="planned_start_date"
          />    
          
        </div>    
        <div class="form-group">
          <label for="description">Planned End Date</label> 
           <input
            class="form-control"
            id="planned_end_date"
            required
            v-model="todotask.planned_end_date"
            name="planned_end_date"
          />
        </div>
  
        <button @click="saveTodotask" class="btn btn-success">Submit</button>
      </div>
  
      <div v-else>
        <h4>You submitted successfully!</h4>
        <button class="btn btn-success" @click="newTodotask">Add</button>
      </div>
    </div>
  </div>
  </template>
  
  <script>
  import TodotaskService from "../services/TodotaskService";
  
  export default {
    name: "add-todotask",
    data() {
      return {
        todotask: {
          id: null,
          title: "",
          description: "",
          user_id: "",
          planned_start_date:"",
          planned_end_date:""
        },
        submitted: false
      };
    },
    methods: {
      saveTodotask() {
        var data = {
          title: this.todotask.title,
          description: this.todotask.description,
          user_id: this.todotask.user_id,
          planned_start_date: this.todotask.planned_start_date,
          planned_end_date: this.todotask.planned_end_date
        };
  
        TodotaskService.create(data)
          .then(response => {
            this.todotask.id = response.data.id;
            console.log(response.data);
            this.submitted = true;
          })
          .catch(e => {
            console.log(e);
          });
      },
      
      newTodotask() {
        this.submitted = false;
        this.todotask = {};
      }
    }
  };
  </script>
  
  <style>
  .submit-form {
    max-width: 300px;
    margin: auto;
  }
  </style>