/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
const anggotaEl = document.getElementById("Anggota");
const petugasEl = document.getElementById("Petugas");
const formLogin = document.getElementById("testlogin");
const idEl = document.getElementById("id");
const pwEl = document.getElementById("password");
//const loginBtn = document.getElementById("submit");

showAllPetugas();
showAllAnggotas();


formLogin.addEventListener("submit", (e) => {
  e.preventDefault();
  const body = getAllFormData();
  if (body != null) {
        Login(idEl, pwEl).then((res) => {
      if (res.status === "OK") {
        console.log("Berhasil POST");
      }
    });
  } else {
    console.log("Tolong");
  }
});

function showAllAnggotas() {
    getAllAnggotas().then((anggotas) => {
    anggotas.forEach((anggota) => {
      const childEl = document.createElement("h3");
      childEl.innerHTML = `ID : ${anggota.nik} Nama : ${anggota.namaanggota}`;
      anggotaEl.append(childEl);
    });
  });
}

function showAllPetugas() {
    getAllPetugas().then((petugass) => {
    petugass.forEach((petugas) => {
      const childEl = document.createElement("h3");
      childEl.innerHTML = `ID : ${petugas.id} Nama : ${petugas.namapetugas}`;
      petugasEl.append(childEl);
    });
  });
}

function getAllFormData() {
  const body = {};
  for (const element of formLogin.elements) {
    if (element.name) {
      body[element.name] = element.value;
    }
  }
  console.log(body);
  return body;
}

// Fetches Functions

async function getAllAnggotas() {
  const res = await fetch("/PerpusJava/AnggotaController");
  const anggotas = await res.json();
  console.log(anggotas);
  return anggotas;
}

async function getAllPetugas() {
  const res = await fetch("/PerpusJava/PetugasController");
  const petugas = await res.json();
  console.log(petugas);
  return petugas;
}


async function Login(id, password){
    const res =  
    await fetch(
    "/PerpusJava/AnggotaController",
    {
        method: "POST",
        headers: {
            Accept: "application/json",
           "Content-Type": "application/json",
        },
        body: JSON.stringify(id, password),
    });
    const postBuku = await res.json();
    console.log(postBuku);
    return postBuku;
}


