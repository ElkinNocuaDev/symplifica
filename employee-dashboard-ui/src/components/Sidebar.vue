<template>

<div class="sidebar">

    <h2>Dashboard</h2>

    <button @click="$emit('change','employees')">

        👥 Employees

    </button>

    <button @click="$emit('change','employee-form')">

        ➕ Create Employee

    </button>

    <button @click="$emit('change','benefit-form')">

        🎁 Create Benefit

    </button>

</div>

</template>

<style scoped>

.sidebar{

    width:220px;

    background:#2c3e50;

    color:white;

    min-height:100vh;

    padding:25px;

}

button{

    width:100%;

    margin-top:15px;

    padding:12px;

    border:none;

    cursor:pointer;

    background:#34495e;

    color:white;

    border-radius:6px;

    text-align:left;

}

button:hover{

    background:#4a647d;

}

</style>