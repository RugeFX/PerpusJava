
// const res = fetch(
//      "/PerpusJava/AuthController?" + new URLSearchParams({page: "cek"}),
//      {
//          method: "GET",
//          headers: {
//              Accept: "application/json",
//              "Content-Type": "application/json",
//          },
//      });
//      res.json()
//   .then((data) => {
//     console.log(data.status)
//     if (data.status == "NO") {
//       console.log("ditolak")
//       window.location.href = "/PerpusJava/admin/pages/samples/login.html";
//     }
//     else{
//       console.log("ada data: " .data.data)
//     }
//   });
const pageURL = new URL(window.location.href);

  sessionCek().then((dt) => {
    console.log(dt)
    console.log(dt.status)
    if (dt.status == "NO") {
      console.log("ditolak")
      window.location.href = "/PerpusJava/admin/pages/samples/login.html";
     }
     console.log(pageURL);
     console.log("ada data: " + dt.data)
     console.log(dt.data.id)
    if (dt.data.level == 0){
      if (pageURL.pathname === "/PerpusJava/admin/") {
        getDataPetugas(dt.data.id).then((dtPetugas) => {
          const containerUser = document.getElementById("containeruser");
          const nameUserEl = document.createElement("h3");
          const welcomeTitleEl = document.createElement("span");
          welcomeTitleEl.classList.add("text-primary");
          welcomeTitleEl.innerHTML = "Perpustakaan"
          const welcomeEl = document.createElement("h6");
          welcomeEl.classList.add("font-weight-normal");
          welcomeEl.innerHTML = "Selamat Datang di " + welcomeTitleEl.innerHTML;
          nameUserEl.classList.add("font-weight-bold");
          nameUserEl.innerHTML = "Welcome, " + dtPetugas.namapetugas;
          containerUser.append(nameUserEl);
          containerUser.append(welcomeEl);
        })
      }
    }
    else if (dt.data.level == 1) {
      if (pageURL.pathname === "/PerpusJava/pinjam.html") {
        getDataAnggota(dt.data.id).then((dtAnggota) => {
          const idanggota = document.getElementById("anggota");
          idanggota.value = dtAnggota.nik;
        })
      }
    } 
  })


async function sessionCek() {
  const res =
    await fetch(
      "/PerpusJava/AuthController?" + new URLSearchParams({ page: "cek" }),
      {
        method: "GET",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
      });
  const cekUser = await res.json();
  return cekUser;
}

async function getDataAnggota(nik) {
  const res =
    await fetch(
      "/PerpusJava/AnggotaController?" +
      new URLSearchParams({
        page: "show",
        nik: nik,
      }),
      {
        method: "GET",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
      });
  const anggota = await res.json()
  console.log(anggota)
  return anggota;
}

async function getDataPetugas(id) {
  const res =
    await fetch(
      "/PerpusJava/PetugasController?" +
      new URLSearchParams({
        page: "show",
        idpetugas: id,
      }),
      {
        method: "GET",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
      });
  const petugas = await res.json()
  console.log(petugas)
  return petugas;
}

 
