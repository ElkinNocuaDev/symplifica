<template>

  <div class="form">

    <h2>Create Employee</h2>

    <input
        v-model="employee.firstName"
        placeholder="First Name"
    >

    <input
        v-model="employee.lastName"
        placeholder="Last Name"
    >

    <input
        v-model="employee.email"
        placeholder="Email"
    >

    <input
        v-model="employee.city"
        placeholder="City"
    >

    <button @click="createEmployee">
        Create Employee
    </button>

  </div>

</template>

<script setup>

import { reactive } from 'vue'
import api from '../api/api'

const emit = defineEmits(['created'])

const employee = reactive({

    firstName:'',
    lastName:'',
    email:'',
    city:''

})

async function createEmployee(){

    try{

        await api.post('/employees', employee)

        emit('created')

        employee.firstName=''
        employee.lastName=''
        employee.email=''
        employee.city=''

    }
    catch(error){

        console.error(error)

    }

}

</script>

<style scoped>

.form{

    margin-top:40px;

    display:flex;

    flex-direction:column;

    width:300px;

    gap:10px;

}

input{

    padding:10px;

}

button{

    padding:10px;

    cursor:pointer;

}

</style>