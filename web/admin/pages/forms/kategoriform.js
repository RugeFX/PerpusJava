const formInsert = document.getElementById("mainform");

const pageURL = new URL(window.location.href);
// console.log(pageURL);
let pageType = "insert";

if (pageURL.pathname === "/PerpusJava/admin/pages/forms/editkategori.html") {
  if (!pageURL.searchParams.get("id")) {
    Swal.fire({
      title: "Unauthorized",
      text: `Akses dilarang`,
      icon: "error",
    }).then(() => {
      window.location.href = "/PerpusJava/admin/pages/tables/kategori.html";
    });
  }

  const kode = pageURL.searchParams.get("id");
  console.log(kode);
  pageType = "update";
  fetch(
    "/PerpusJava/KategoriController?" +
      new URLSearchParams({
        page: "show",
        idkategori: kode,
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
    // if (key === 'idkategori') {
    //   console.log(key)
    //   console.log(value);
    //   if (cekIdKategori(value) !== null) {
    //     Swal.fire(
    //       "ID Sudah digunakan",
    //       "Tolong masukkan ID yang baru!",
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
      if (data.status == "OK") {
        window.location.href = "/PerpusJava/admin/pages/tables/kategori.html";
      }else{
        Swal.fire(
          "ID Sudah digunakan",
          "Tolong masukkan ID yang baru!",
          "warning"
        );
      }
    });
  } else if (pageType === "update") {
    updateData(body).then((data) => {
      console.log(data);
      if (data.status == "OK") {
        window.location.href = "/PerpusJava/admin/pages/tables/kategori.html";
      }
    });
  }
});

// async function cekIdKategori(id) {
//   const res =
//     await fetch(
//       "/PerpusJava/KategoriController?" +
//       new URLSearchParams({
//         page: "show",
//         idkategori: id,
//       }),
//       {
//         method: "GET",
//         headers: {
//           Accept: "application/json",
//           "Content-Type": "application/json",
//         },
//       });
//   const kategori = await res.json()
//   console.log(kategori)
//   return kategori;
// }

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

async function updateData(body) {
  const res = await fetch(
    "/PerpusJava/KategoriController?" + new URLSearchParams({ page: "update" }),
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
    "/PerpusJava/KategoriController?" + new URLSearchParams({ page: "insert" }),
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
