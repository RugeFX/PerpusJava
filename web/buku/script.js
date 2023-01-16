const bukuEl = document.getElementById("buku");
const formInsert = document.getElementById("testinsert");
const submitBtn = document.getElementById("submit");
const idEl = document.getElementById("bukubyid");
const inputId = document.getElementById("inputid");
const idBtn = document.getElementById("submitid");

showAllBukus();

idBtn.addEventListener("click", () => {
  let id = inputId.value;
  getByID(id).then((buku) => {
    if (!buku) {
      console.log("buku no eksisto");
      return;
    }
  });
});

formInsert.addEventListener("submit", (e) => {
  e.preventDefault();
  const body = getAllFormData();
  if (body != null) {
    insertBuku(body).then((res) => {
      if (res.status === "OK") {
        console.log("Berhasil POST");
      }
    });
  } else {
    console.log("Tolong");
  }
});

function showById(id) {
  getByID(id).then((buku) => {
    idEl.innerHTML = JSON.stringify(buku);
  });
}

function showAllBukus() {
  getAllBukus().then((bukus) => {
    bukus.forEach((buku) => {
      const childEl = document.createElement("h3");
      childEl.innerHTML = `ID : ${buku.kodebuku} Judul : ${buku.judulbuku}`;
      bukuEl.append(childEl);
    });
  });
}

function getAllFormData() {
  const body = {};
  for (const element of formInsert.elements) {
    if (element.name) {
      body[element.name] = element.value;
    }
  }

  console.log(body);
  return body;
}

// Fetches Functions
async function getAllBukus() {
  const res = await fetch("/PerpusJava/BukuController");
  const bukus = await res.json();
  console.log(bukus);
  return bukus;
}

async function insertBuku(body) {
  const res = await fetch("/PerpusJava/BukuController", {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(body),
  });
  const postBuku = await res.json();
  console.log(postBuku);
  return postBuku;
}

async function getByID(id) {
  const res = await fetch(
    "/PerpusJava/BukuController?" +
      new URLSearchParams({
        page: "show",
        idbuku: id,
      })
  );
  const bukus = await res.json();
  console.log(bukus);
  return bukus;
}
