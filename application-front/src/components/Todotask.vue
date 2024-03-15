<template>
  <div class="card">
    <div v-if="data" class="edit-form">
      <h4 class="text-black">Todotask</h4>
      <form>
        <div class="form-group text-black">
          <label for="title" class="block text-sm font-medium text-black-700">Title</label>
          <input type="text" class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md text-black" id="title" v-model="data.title" />
        </div>
        <div class="form-group text-black">
          <label for="description" class="block text-sm font-medium text-white-700">Description</label>
          <input type="text" class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md text-black" id="description" v-model="data.description" />
        </div>
        <div class="form-group text-black">
          <label for="assignee" class="block text-sm font-medium text-white-700">Assignee</label>
          <input type="text" class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md text-black" id="assignee" v-model="data.assignee" />
        </div>
        <div class="grid grid-cols-2 gap-4 text-black">
          <div class="col-span-1">
              <label for="planned_start_date" class="block text-sm font-medium">Planned Start Date</label>
              <input type="text" id="planned_start_date" class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md text-black">
          </div>
          <div class="col-span-1">
              <label for="actual_start_date" class="block text-sm font-medium">Actual Start Date</label>
              <input type="text" id="actual_start_date" class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md text-black">
          </div>
          <div class="col-span-1">
              <label for="planned_end_date" class="block text-sm font-medium">Planned End Date</label>
              <input type="text" id="planned_end_date" class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md text-black">
          </div>
          <div class="col-span-1">
              <label for="actual_end_date" class="block text-sm font-medium">Actual End Date</label>
              <input type="text" id="actual_end_date" class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md text-black">
          </div>
        </div>
      </form>
      <div class="grid grid-cols-2 gap-4">
        <!-- Delete button -->
        <button class="button badge badge-danger text-black" @click="deleteTodotask()">
          <svg viewBox="0 0 448 512" class="svgIcon"><path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z"></path></svg>
        </button>
        <!-- Update button -->
        <button type="submit" class="button badge badge-success text-black" @click="updateTodotask()">
          <svg viewBox="0 0 448 512" class="svgIcon">
          <path d="m13.498.795.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001"/>
          </svg>
        </button>
      </div>
      
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
  
  <style scoped>
  .edit-form {
    max-width: 300px;
    margin: auto;
  }

  .edit-form .form-group {
    margin: 15px;
  }

/* Button Styles */
.button {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  background-color: rgb(20, 20, 20);
  border: none;
  font-weight: 600;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0px 0px 20px rgba(218, 143, 143, 0.164);
  cursor: pointer;
  transition-duration: .3s;
  overflow: hidden;
  position: relative;
  margin: 10px;
}

.svgIcon {
  width: 12px;
  transition-duration: .3s;
}

.svgIcon path {
  fill: white;
}

.button:hover {
  width: 140px;
  border-radius: 50px;
  transition-duration: .3s;
  background-color: rgb(3, 0, 0);
  align-items: center;
}

.button:hover .svgIcon {
  width: 50px;
  transition-duration: .3s;
  transform: translateY(60%);
}

.button::before {
  position: absolute;
  top: -20px;
  content: "Delete";
  color: white;
  transition-duration: .3s;
  font-size: 2px;
}

button[type="submit"]::before {
  position: absolute;
  top: -20px;
  content: "Update";
  color: white;
  transition-duration: .3s;
  font-size: 2px;
}

.button:hover::before {
  font-size: 13px;
  opacity: 1;
  transform: translateY(30px);
  transition-duration: .3s;
}
  </style>