const bukuEl = document.getElementById("buku");

showAllBukus();

document.addEventListener("click", (e) => {
  if (e.target.id == "editBtn") {
    const kodeBuku = e.target.dataset.kode;

    console.log(kodeBuku);
  }
  if (e.target.id == "deleteBtn") {
    const kodeBuku = e.target.dataset.kode;

    Swal.fire({
      title: "Apakah anda yakin?",
      text: `Anda akan menghapus data buku dengan kode ${kodeBuku}`,
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      cancelButtonText: "Batal",
      confirmButtonText: "Hapus",
    }).then((result) => {
      if (result.isConfirmed) {
        showAllBukus();
        Swal.fire(
          "Dihapus!",
          `Buku dengan kode ${kodeBuku} telah dihapus`,
          "success"
        );
      }
    });
    console.log(kodeBuku);
  }
});

function showAllBukus() {
  bukuEl.innerHTML = "";
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

// Fetches Functions
async function getAllBukus() {
  const res = await fetch("/PerpusJava/BukuController");
  const bukus = await res.json();
  console.log(bukus);
  return bukus;
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
