<template>
  <div class="employee-list">

    <h2>Employees</h2>

    <div v-if="loading">
      Loading employees...
    </div>

    <div
        v-for="employee in employees"
        :key="employee.id"
        class="employee-card"
        @click="$emit('select', employee)"
    >

      <strong>
        {{ employee.firstName }} {{ employee.lastName }}
      </strong>

      <br>

      {{ employee.email }}

      <br>

      {{ employee.city }}

    </div>

  </div>
</template>

<script setup>

import { ref, onMounted } from 'vue'
import api from '../api/api'

const employees = ref([])
const loading = ref(true)

const emit = defineEmits(['select'])

async function loadEmployees() {

  try {

    const response = await api.get('/employees')

    employees.value = response.data

  } catch (error) {

    console.error(error)

  } finally {

    loading.value = false

  }

}

onMounted(loadEmployees)

defineExpose({

    reload: loadEmployees

})

</script>

<style scoped>

.employee-list{

    width:350px;

}

.employee-card{

    border:1px solid #ddd;

    border-radius:8px;

    padding:15px;

    margin-bottom:10px;

    cursor:pointer;

}

.employee-card:hover{

    background:#f5f5f5;

}

</style>