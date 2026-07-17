<template>

  <div class="detail">

    <h2>Employee Detail</h2>

    <div v-if="loading">
      Loading...
    </div>

    <div v-else-if="employeeDetail">

      <p>
        <strong>Name:</strong>
        {{ employeeDetail.firstName }} {{ employeeDetail.lastName }}
      </p>

      <p>
        <strong>Email:</strong>
        {{ employeeDetail.email }}
      </p>

      <p>
        <strong>City:</strong>
        {{ employeeDetail.city }}
      </p>

      <hr>

      <h3>Location</h3>

      <p>
        <strong>Latitude:</strong>
        {{ employeeDetail.location.latitude }}
      </p>

      <p>
        <strong>Longitude:</strong>
        {{ employeeDetail.location.longitude }}
      </p>

      <hr>

      <h3>Benefits</h3>

      <ul>

        <li
            v-for="benefit in employeeDetail.benefits"
            :key="benefit.id"
        >

          {{ benefit.benefitName }}

          -

          $ {{ benefit.amount }}

        </li>

      </ul>

    </div>

    <div v-else>

      <p>Select an employee from the list.</p>

    </div>

  </div>

</template>

<script setup>

import { ref, watch } from 'vue'
import api from '../api/api'

const props = defineProps({

    employee: Object

})

const employeeDetail = ref(null)

const loading = ref(false)

async function loadEmployee() {

    if (!props.employee) {

        employeeDetail.value = null

        return

    }

    loading.value = true

    try {

        const response =
            await api.get(`/employees/${props.employee.id}/details`)

        employeeDetail.value = response.data

    } catch (error) {

        console.error(error)

    } finally {

        loading.value = false

    }

}

watch(

    () => props.employee,

    loadEmployee,

    { immediate: true }

)

defineExpose({

    reload: loadEmployee

})

</script>

<style scoped>

.detail{

    flex:1;

    padding-left:30px;

}

</style>