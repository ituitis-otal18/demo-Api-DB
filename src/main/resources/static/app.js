const API = "https://postgres-db-v1.herokuapp.com/api/v1/person";
let table, addForm, updateForm, deleteForm;

function addToTable(id, name, age){
  let row = table.insertRow(-1);
  let cell1 = row.insertCell(0);
  let cell2 = row.insertCell(1);
  let cell3 = row.insertCell(2);

  let a = document.createElement("P");
  let t1 = document.createTextNode(id);
  a.appendChild(t1);
  cell1.appendChild(a);

  let b = document.createElement("P");
  let t2 = document.createTextNode(name);
  b.appendChild(t2);
  cell2.appendChild(b);

  let c = document.createElement("P");
  let t3 = document.createTextNode(age);
  c.appendChild(t3);
  cell3.appendChild(c);
}

function getData(){
  fetch(API, {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
  })
    .then(data => {
      return data.json();
    })
    .then(res => {
      for(person of res){
        addToTable(person.id, person.name, person.age);
      }
    })
}

document.addEventListener("DOMContentLoaded", function() {
  table = document.getElementById('people');
  addForm = document.getElementById("add");
  updateForm = document.getElementById("update");
  deleteForm = document.getElementById("delete");

  getData();
});

function addButton(){
  let name = addForm.elements.addName.value;
  let age = addForm.elements.addAge.value;

  fetch(API, {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({"name": name, "age": parseInt(age)})
  })
    .then(data => {
      console.log(data);
      if(data.ok) window.location.reload(true); 
    })
}

function updateButton(){
  let id = updateForm.elements.updateId.value.trim();
  let name = updateForm.elements.updateName.value;
  let age = updateForm.elements.updateAge.value;

  fetch(API+"/"+id, {
    method: 'PUT',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({"name": name, "age": parseInt(age)})
  })
    .then(data => {
      console.log(data);
      if(data.ok) window.location.reload(true); 
    })
}

function deleteButton(){
  let id = deleteForm.elements.deleteId.value.trim();

  fetch(API+"/"+id, {
    method: 'DELETE',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
  })
    .then(data => {
      console.log(data);
      if(data.ok) window.location.reload(true); 
    })
}