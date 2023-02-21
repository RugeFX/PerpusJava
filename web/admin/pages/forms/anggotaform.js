const formInsert = document.getElementById("anggotaform");

const pageURL = new URL(window.location.href);
// console.log(pageURL);
let pageType = "insert";

if (pageURL.pathname === "/PerpusJava/admin/pages/forms/editanggota.html") {
  if (!pageURL.searchParams.get("nik")) {
    Swal.fire({
      title: "Unauthorized",
      text: `Akses dilarang`,
      icon: "error",
    }).then(() => {
      window.location.href = "/PerpusJava/admin/pages/tables/anggota.html";
    });
  }

  const kode = pageURL.searchParams.get("nik");
  console.log(kode);
  pageType = "update";
  fetch(
    "/PerpusJava/AnggotaController?" +
      new URLSearchParams({
        page: "show",
        nik: kode,
      })
  )
    .then((res) => res.json())
    .then((data) => {
      console.log(data);
      Object.keys(data).forEach((key) => {
        formInsert.elements[key].value = data[key];
      });
    });
}

formInsert.addEventListener("submit", (e) => {
  e.preventDefault();
  const body = getAllFormData(formInsert);
  for (const key in body) {
    const value = body[key]
    if (value === null || value === "") {
      console.log(key)
      console.log(value);
      Swal.fire(
        "Data Invalid",
        "Tolong masukkan data dengan lengkap!",
        "warning"
      );
      return;
    }
    // if (key === 'nik') {
    //   console.log(key)
    //   console.log(value);
    //   if (cekNikAnggota(value) !== null) {
    //     Swal.fire(
    //       "NIK Sudah Ada",
    //       "Tolong masukkan NIK yang baru!",
    //       "warning"
    //     );
    //   }
    //   return;
    // }
  }
  // if (
  //   !Object.values(body).every((value) => {
  //     if (value === null || value === "") {
  //       console.log(value);
  //       return false;
  //     }
  //     return true;
  //   })
  // ) {
  //   console.log("Tolong isi data dengan benar!");
  //   Swal.fire(
  //     "Data Invalid",
  //     "Tolong masukkan data dengan lengkap!",
  //     "warning"
  //   );
  //   return;
  // }
  if (pageType === "insert") {
    insertData(body).then((data) => {
      console
      if (data.status == "OK") {
        window.location.href = "/PerpusJava/admin/pages/tables/anggota.html";
      }else{
        Swal.fire(
          "NIK Sudah Ada",
          "Tolong masukkan NIK yang baru!",
          "warning"
        );
      }
    });
  } else if (pageType === "update") {
    updateData(body).then((data) => {
      console.log(data);
      if (data.status == "OK") {
        window.location.href = "/PerpusJava/admin/pages/tables/anggota.html";
      }
    });
  }
});

function getAllFormData(form) {
  const body = {};
  for (const element of form.elements) {
    if (element.name) {
      body[element.name] = element.value;
    }
  }
  console.log(body);
  return body;
}

// async function cekNikAnggota(nik) {
//   const res =
//     await fetch(
//       "/PerpusJava/AnggotaController?" +
//       new URLSearchParams({
//         page: "show",
//         nik: nik,
//       }),
//       {
//         method: "GET",
//         headers: {
//           Accept: "application/json",
//           "Content-Type": "application/json",
//         },
//       });
//   const anggota = await res.json()
//   console.log(anggota)
//   return anggota;
// }

async function updateData(body) {
  const res = await fetch(
    "/PerpusJava/AnggotaController?" + new URLSearchParams({ page: "update" }),
    {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(body),
    }
  );
  const json = await res.json();
  return json;
}

async function insertData(body) {
  const res = await fetch(
    "/PerpusJava/AnggotaController?" + new URLSearchParams({ page: "insert" }),
    {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(body),
    }
  );
  const json = await res.json();
  return json;
}

// async function getSelectOptions() {
//   const res = await fetch(
//     "/PerpusJava/BukuController?" + new URLSearchParams({ page: "attributes" }),
//     {
//       method: "GET",
//       headers: {
//         Accept: "application/json",
//         "Content-Type": "application/json",
//       },
//     }
//   );
//   console.log(res);
//   return await res.json();
// }
