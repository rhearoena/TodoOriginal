<template>
<h1>Add New Task</h1>
  <div class="w-full max-w-md" >
    <div v-if="!submitted" >
      <div class="form-group">
        <label  class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="assignee">User</label>
        <input 
          required
          type="text"
          class="form-control appearance-none block w-full tracking-wide text-gray-700 border border-red-500 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-whiteappearance-none block w-full tracking-wide text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
          id="assignee"
          v-model="todotask.assignee"
          name="assignee"
        />
      </div>
      <div v-if="error.assignee == true" style="color:red">Please input User ID.</div>
      
      <div class="form-group">
        <label  class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="title">Title</label>
        <input
        type="text"
        class="form-control appearance-none block w-full tracking-wide text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
        id="title"
        required
        v-model="todotask.title"
        name="title"
        />
      </div>
      <div v-if="error.title == true" style="color:red">Please input Title.</div>
      
      <div class="form-group">
        <label  class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="description">Description</label>
        <input
        class="form-control appearance-none block w-full tracking-wide text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
        id="description"
        required
        v-model="todotask.description"
        name="description"
        />
      </div>
      <div v-if="error.description == true" style="color:red">Please input Description.</div>
      
      <div class="form-group">
        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="planned_start_date">Planned Start Date</label>
        <input
        class="form-control appearance-none block w-full tracking-wide text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
        id="planned_start_date"
        required
        v-model="todotask.planned_start_date"
        name="planned_start_date"
        />    
        <div v-if="error.planned_start_date == true" style="color:red">Please input Planned Start Date.</div>
        
      </div>    
      <div class="form-group">
        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="planned_end_date">Planned End Date</label> 
        <input
        class="form-control appearance-none block w-full tracking-wide text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
        id="planned_end_date"
        required
        v-model="todotask.planned_end_date"
        name="planned_end_date"
        />
      </div>
      <div v-if="error.planned_end_date == true" style="color:red">Please input Planned End Date.</div>

      <div class="form-group">
        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="priority">Priority</label> 
        <select class="block appearance-none w-full bg-white border border-gray-400 hover:border-gray-500 px-4 py-2 pr-8 rounded shadow leading-tight focus:outline-none focus:shadow-outline" 
        id="priority" v-model="todotask.priority" name="priority" placeholder="Please select priority">
          <option>on hold</option>
          <option>on progress</option>
          <option>not yet started</option>
        </select>
      </div>
      
      <button @click="saveTodotask" class="btn btn-success">Submit</button>
    </div>
    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newTodotask">Add</button>
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
        title: '',
        description: '',
        assignee: '',
        planned_start_date:'',
        planned_end_date:'',
        priority:'',
      },
      error: {
        title: false,
        description: false,
        assignee: false,
        planned_start_date:false,
        planned_end_date:false
      },
      submitted: false
    };
  },
  methods: {
    saveTodotask() {
      var data = {
          title: this.todotask.title,
          description: this.todotask.description,
          assignee: this.todotask.assignee,
          planned_start_date: this.todotask.planned_start_date,
          planned_end_date: this.todotask.planned_end_date,
          priority: this.todotask.priority,
      } 

      this.error.title = this.todotask.title === '' ? true : false
      this.error.assignee = this.todotask.assignee === '' ? true : false
      this.error.description = this.todotask.description === '' ? true : false
      this.error.planned_start_date = this.todotask.planned_start_date === '' ? true : false
      this.error.planned_end_date = this.todotask.planned_end_date === '' ? true : false

      if(this.error.title == false && 
         this.error.assignee == false &&
         this.error.description == false &&
         this.error.planned_start_date == false &&
         this.error.planned_end_date == false) {
        TodotaskService.create(data)
          .then(response => {
            this.todotask.id = response.data.id;
            console.log(response.data);
            this.submitted = true;
          })
          .catch(e => {
            console.log(e);
          });
      } else {
        console.log("Title, assignee, description, planned_start_date, planned_end_date fields are empty");
      }

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

