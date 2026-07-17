<template>

  <div class="layout">

    <!-- Sidebar -->
    <Sidebar
        @change="changeView"
    />

    <!-- Main Content -->
    <div class="content">

      <AppHeader />

      <!-- Employees -->
      <div
          v-if="currentView === 'employees'"
          class="dashboard"
      >

        <EmployeeList
            ref="employeeList"
            @select="selectEmployee"
        />

        <EmployeeDetail
            ref="employeeDetail"
            :employee="selectedEmployee"
        />

      </div>

      <!-- Create Employee -->
      <EmployeeForm
          v-if="currentView === 'employee-form'"
          @created="employeeCreated"
      />

      <!-- Create Benefit -->
      <BenefitForm
        v-if="currentView === 'benefit-form'"
        :employee="selectedEmployee"
        @created="benefitCreated"
      />

    </div>

  </div>

</template>

<script setup>

import { ref } from 'vue'

import Sidebar from '../components/Sidebar.vue'
import AppHeader from '../components/AppHeader.vue'

import EmployeeList from '../components/EmployeeList.vue'
import EmployeeDetail from '../components/EmployeeDetail.vue'
import EmployeeForm from '../components/EmployeeForm.vue'
import BenefitForm from '../components/BenefitForm.vue'

const currentView = ref('employees')

const employeeList = ref(null)

const selectedEmployee = ref(null)

const employeeDetail = ref(null)

function changeView(view) {

    currentView.value = view

}

function selectEmployee(employee) {

    selectedEmployee.value = employee

}

function employeeCreated() {

    employeeList.value.reload()

    currentView.value = 'employees'

}

function benefitCreated(){

    employeeDetail.value.reload()

    currentView.value='employees'

}

</script>

<style scoped>

.layout{

    display:flex;
    min-height:100vh;
    background:#f5f6fa;

}

.content{

    flex:1;
    padding:30px;

}

.dashboard{

    display:flex;
    gap:30px;
    align-items:flex-start;

}

</style>