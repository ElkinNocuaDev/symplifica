<template>

  <div class="form">

    <h2>Create Benefit</h2>

    <div v-if="!employee" class="info">

      <p>Please select an employee from the Employees view first.</p>

    </div>

    <div v-else>

      <div class="employee-info">

        <strong>Employee</strong>

        <p>{{ employee.firstName }} {{ employee.lastName }}</p>

      </div>

      <input
          v-model="benefit.benefitName"
          placeholder="Benefit Name"
      />

      <input
          v-model.number="benefit.amount"
          type="number"
          placeholder="Amount"
      />

      <button @click="createBenefit">

        Create Benefit

      </button>

      <p
          v-if="success"
          class="success"
      >

        Benefit created successfully.

      </p>

    </div>

  </div>

</template>

<script setup>

import { reactive, ref } from 'vue'
import api from '../api/api'

const props = defineProps({

    employee: Object

})

const benefit = reactive({

    benefitName: '',
    amount: null

})

const success = ref(false)

const emit = defineEmits(['created'])

async function createBenefit(){

    if(!props.employee){

        return

    }

    try{

        await api.post('/benefits',{

            employeeId: props.employee.id,
            benefitName: benefit.benefitName,
            amount: benefit.amount

        })

        success.value = true

        emit('created')

        benefit.benefitName=''

        benefit.amount=null

        setTimeout(()=>{

            success.value=false

        },2000)

    }
    catch(error){

        console.error(error)

    }

}

</script>

<style scoped>

.form{

    background:white;
    padding:25px;
    border-radius:10px;
    box-shadow:0 2px 8px rgba(0,0,0,.08);
    max-width:450px;

}

.employee-info{

    margin-bottom:20px;
    padding:15px;
    background:#f5f6fa;
    border-radius:8px;

}

input{

    width:100%;
    margin-bottom:15px;
    padding:12px;
    border:1px solid #ddd;
    border-radius:6px;
    box-sizing:border-box;

}

button{

    width:100%;
    padding:12px;
    border:none;
    background:#2c3e50;
    color:white;
    border-radius:6px;
    cursor:pointer;

}

button:hover{

    background:#34495e;

}

.success{

    margin-top:15px;
    color:green;
    font-weight:bold;

}

.info{

    color:#666;

}

</style>