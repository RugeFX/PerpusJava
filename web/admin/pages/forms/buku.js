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
    if (!buku || !buku.hasOwnProperty("kodebuku")) {
      console.log("buku no eksisto");
      idEl.innerHTML = `Buku doesn't exist!`;
      return;
    }
    idEl.innerHTML = `ID : ${buku.kodebuku} Judul : ${buku.judulbuku}`;
  });
});

formInsert.addEventListener("submit", (e) => {
  e.preventDefault();
  const body = getAllFormData();
  if (
    Object.values(body).every((value) => {
      if (value === null || value === "") {
        console.log(value);
        return false;
      }
      return true;
    })
  ) {
    insertBuku(body).then((res) => {
      if (res) {
        console.log("Berhasil POST");
      }
    });
  } else {
    console.log("Tolong isi data dengan benar!");
  }
});

function showAllBukus() {
  getAllBukus().then((bukus) => {
    bukus.forEach((buku) => {
      const childEl = document.createElement("tr");

      Object.keys(buku).forEach((val) => {
        if (val == "urlgambar") {
          return;
        }
        const childTd = document.createElement("td");
        // console.log(val);
        childTd.innerText = buku[val];
        childEl.appendChild(childTd);
      });
      //   console.log("========================");

      // TD for action buttons
      const actionEl = document.createElement("td");

      // Define edit button element
      const editBtn = document.createElement("button");
      editBtn.classList = "btn btn-warning btn-sm";
      editBtn.innerText = "Edit";
      editBtn.setAttribute("type", "button");
      editBtn.setAttribute("id", "editBtn");
      editBtn.setAttribute("data-kode", buku.kodebuku);

      // Define edit button element
      const removeBtn = document.createElement("button");
      removeBtn.classList = "btn btn-danger btn-sm";
      removeBtn.innerText = "Hapus";
      removeBtn.setAttribute("type", "button");
      removeBtn.setAttribute("id", "deleteBtn");
      removeBtn.setAttribute("data-kode", buku.kodebuku);

      // Code element for inner container of action element
      const codeEl = document.createElement("code");
      codeEl.append(editBtn);
      codeEl.insertAdjacentHTML("beforeend", " | ");
      codeEl.append(removeBtn);

      actionEl.append(codeEl);
      childEl.append(actionEl);

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
  return bukus;
}
