const formInsert = document.getElementById("bukuform");
const 

const pageURL = new URL(window.location.href);
// console.log(pageURL);
let pageType = "insert";

getSelectOptions().then((data) => {
})

if (pageURL.pathname === "/PerpusJava/admin/pages/forms/editbuku.html") {
  if (!pageURL.searchParams.get("kode")) {
    Swal.fire({
      title: "Unauthorized",
      text: `Akses dilarang`,
      icon: "error",
    }).then(() => {
      window.location.href = "/PerpusJava/admin/pages/tables/buku.html";
    });
  }

  const kodeBuku = pageURL.searchParams.get("kode");
  console.log(kodeBuku);
  pageType = "update";
  fetch(
    "/PerpusJava/BukuController?" +
      new URLSearchParams({
        page: "show",
        kode: kodeBuku,
      })
  )
    .then((res) => res.json())
    .then((data) => {
      Object.keys(data).forEach((key) => {
        formInsert.elements[key].value = data[key];
      });
    });
}

formInsert.addEventListener("submit", (e) => {
  e.preventDefault();
  const body = getAllFormData(formInsert);
  if (
    !Object.values(body).every((value) => {
      if (value === null || value === "") {
        console.log(value);
        return false;
      }
      return true;
    })
  ) {
    console.log("Tolong isi data dengan benar!");
    return;
  }
  if (pageType === "insert") {
    insertBuku(body).then((data) => {
      if (data.status == "OK") {
        window.location.href = "/PerpusJava/admin/pages/tables/buku.html";
      }
    });
  } else if (pageType === "update") {
    updateBuku(body).then((data) => {
      if (data.status == "OK") {
        window.location.href = "/PerpusJava/admin/pages/tables/buku.html";
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

async function updateBuku(body) {
  const res = await fetch(
    "/PerpusJava/BukuController?" + new URLSearchParams({ page: "update" }),
    {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(body),
    }
  );
  const postBuku = await res.json();
  return postBuku;
}

async function insertBuku(body) {
  const res = await fetch(
    "/PerpusJava/BukuController?" + new URLSearchParams({ page: "insert" }),
    {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(body),
    }
  );
  const postBuku = await res.json();
  return postBuku;
}

async function getSelectOptions() {
  const res = await fetch(
    "/PerpusJava/BukuController?" + new URLSearchParams({ page: "attributes" }),
    {
      method: "GET",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
    }
  );
  return await res.json();
}

// idBtn.addEventListener("click", () => {
//   let id = inputId.value;
//   getByID(id).then((buku) => {
//     if (!buku || !buku.hasOwnProperty("kodebuku")) {
//       console.log("buku no eksisto");
//       idEl.innerHTML = `Buku doesn't exist!`;
//       return;
//     }
//     idEl.innerHTML = `ID : ${buku.kodebuku} Judul : ${buku.judulbuku}`;
//   });
// });
