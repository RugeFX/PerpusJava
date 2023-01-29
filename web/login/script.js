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
let pageType = "login";
//const loginBtn = document.getElementById("submit");

showAllPetugas();
showAllAnggotas();


formLogin.addEventListener("submit", (e) => {
  e.preventDefault();
  const dataUser = Login(idEl.value, pwEl.value);
  if(dataUser == null){
    Swal.fire({
      title: "Id Atau Password Salah",
      text: `Silahkan Login Kembali`,
      icon: "error",
    });
    return;
  }
  Swal.fire({
    title: "Login Berhasil",
    text: `Selamat Anda Telah Berhasil Login`,
    icon: "success",
  });

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
      childEl.innerHTML = `ID : ${petugas.idpetugas} Nama : ${petugas.namapetugas}`;
      petugasEl.append(childEl);
    });
  });
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
   const resAnggota =  
   await fetch(
     "/PerpusJava/AnggotaController?" + new URLSearchParams({
       page: pageType,
       id: id,
       password: password,
     }),
   {
       method: "POST",
       headers: {
           Accept: "application/json",
          "Content-Type": "application/json",
       },
   });

    const postAnggota = await resAnggota.json();
    
  if (postAnggota.status == "NO"){
      const resPetugas = 
        await fetch(
          "/PerpusJava/PetugasController?" + new URLSearchParams({
            page: pageType,
            id: id,
            password: password,
          }),
          {
            method: "POST",
            headers: {
              Accept: "application/json",
              "Content-Type": "application/json",
            },
          });
        const postPetugas = await resPetugas.json();
        console.log(postPetugas)
        if (postPetugas.status == "NO"){
          console.log(postAnggota);
            return null;
        }
        return postPetugas.data;
    }
    return postAnggota.data;
}


