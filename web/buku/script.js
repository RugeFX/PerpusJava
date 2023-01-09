const bukuEl = document.getElementById("buku");
const formInsert = document.getElementById("testinsert");
const submitBtn = document.getElementById("submit");

document.addEventListener("readystatechange", (ready) => {
  showAllBukus();
});

formInsert.addEventListener("submit", (e) => {
  e.preventDefault();
  const body = getAllFormData();
  insertBuku(body).then((res) => {
    if (res.status === "OK") {
      console.log("Berhasil POST");
    }
  });
});

function showAllBukus() {
  getAllBukus().then((bukus) => {
    bukus.forEach((buku) => {
      const childEl = document.createElement("div");
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
