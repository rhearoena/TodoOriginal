<template>
  <div class="w-full" >
    <div v-if="!submitted" >
      <div class="form-group pt-2">
        <label class="block uppercase tracking-wide text-color-blue text-base font-bold mb-2 text-left" for="title">Title</label>
        <input
        required
        type="text"
        class="form-control appearance-none block w-3/6 tracking-wide text-color-blue border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
        id="title"
        v-model="todotask.title"
        name="title"
        />
      </div>
      
      <div class="form-group pt-2">
        <label  class="block uppercase tracking-wide text-color-blue text-base font-bold mb-2 text-left" for="description">Description</label>
        <textarea type="text"
        class="form-control appearance-none block w-full tracking-wide text-color-blue border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
        id="description"
        v-model="todotask.description"
        name="description">
        </textarea>
      </div>
      
      <div class="form-group flex flex-wrap column-4">
        <div class="pr-6 py-2">
        <label class="block uppercase tracking-wide text-color-blue text-base font-bold mb-2 text-left" for="assignee">Assignee</label>
        <input 
          type="text"
          class="form-control appearance-none block w-64 tracking-wide text-color-blue border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
          id="assignee"
          v-model="todotask.assignee"
          name="assignee"
        />
        </div>
        <div class="pr-7 py-2">
          <label class="block uppercase tracking-wide text-color-blue text-base font-bold mb-2 text-left" for="priority">Priority</label> 
          <select class="form-control appearance-none block w-64 tracking-wide text-color-blue border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" 
          id="priority" v-model="todotask.priority" name="priority">
          
            <option value="" disabled selected hidden>Please select priority</option>
            <option>on hold</option>
            <option>on progress</option>
            <option>not yet started</option>
          </select>
        </div>
        <div class="pr-7 py-2">
          <label class="block uppercase tracking-wide text-color-blue text-base font-bold mb-2 text-left" for="planned_start_date">Planned Start Date</label>
          <input
          type="date"
          class="form-control appearance-none block w-48 tracking-wide text-color-blue border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
          id="planned_start_date"
          v-model="todotask.planned_start_date"
          name="planned_start_date"
          />  
       </div>  
       <div class="py-2">
        <label class="block uppercase tracking-wide text-color-blue text-base font-bold mb-2 text-left" for="planned_end_date">Planned End Date</label> 
         <input
        type="date"
        class="form-control appearance-none block w-48 tracking-wide text-color-blue border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
        id="planned_end_date"
        v-model="todotask.planned_end_date"
        name="planned_end_date"
        />
      </div>
      
      <div class="py-2 px-3 w-full">
        <div v-if="error.title == true" role="alert" class="alert alert-error w-1/2 shadow-lg text-color-blue py-2 ">
          <svg xmlns="http://www.w3.org/2000/svg" class="stroke-current shrink-0 h-7 w-7 " fill="none" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z" /></svg>
          <span>Failed to save task. Task title is empty.</span>
        </div>
      </div>
     </div>    
     
     <div class="py-2 w-full">
      <div class="flex flex-row-reverse">
      <button type="reset" @click="resetInput" class="btn btn-warning text-color-blue text-base my-3 mx-3 w-1/6">Clear</button>
      <button @click="saveTodotask" class="btn btn-success text-color-blue text-base my-3 mx-3 w-1/6">Submit</button>
    </div>
    </div>
    </div>
    <div v-else class="font-bold">
      <h3>You submitted successfully!</h3>
      <button class="btn btn-success py-3" @click="newTodotask">Add</button>
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
        progress:'',
        status:'',
        complete_flg:''
      },
      error: {
        title: false
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
          progress: "0",
          status: '-',
          complete_flg: "0",
      } 

      this.error.title = this.todotask.title === '' ? true : false

      if(this.error.title == false) {
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
        console.log("Title field is empty");
      }
    },
    
    newTodotask() {
      this.submitted = false;
      this.todotask = {};
    },
    resetInput() {      
      this.error.title = this.todotask.title = false
      this.todotask.title= "";
      this.todotask.description= "";
      this.todotask.assignee= "";
      this.todotask.planned_start_date= "";
      this.todotask.planned_end_date= "";
      this.todotask.priority= "";
    },
  }
};
</script>

<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
.text-color-blue{
      color: #163057;
    }
</style>

