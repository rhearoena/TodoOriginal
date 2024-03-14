<!-- <template>
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
  </template> -->
  
  <template>
    <ValidationObserver v-slot="{ invalid }">
      <form @submit.prevent="onSubmit">
        <ValidationProvider name="E-mail" rules="required|email" v-slot="{ errors }">
          <input v-model="email" type="email">
          <span>{{ errors[0] }}</span>
        </ValidationProvider>
  
        <ValidationProvider name="First Name" rules="required|alpha" v-slot="{ errors }">
          <input v-model="firstName" type="text">
          <span>{{ errors[0] }}</span>
        </ValidationProvider>
  
        <ValidationProvider name="Last Name" rules="required|alpha" v-slot="{ errors }">
          <input v-model="lastName" type="text">
          <span>{{ errors[0] }}</span>
        </ValidationProvider>
  
        <button type="submit" :disabled="invalid">Submit</button>
      </form>
    </ValidationObserver>
  </template>
  
  <script>
import { ValidationProvider } from 'vee-validate';

  export default {
    data: () => ({
      email: '',
      firstName: '',
      lastName: ''
    }),
    methods: {
      onSubmit () {
        alert('Form has been submitted!');
      }
    },
    components: {
    ValidationProvider
  }
  };
  </script>
  
  <style lang="stylus" scoped>
  span {
    display: block;
  }
</style>
  
