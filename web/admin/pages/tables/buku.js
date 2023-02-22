const bukuEl = document.getElementById("buku");

showAllBukus();

document.addEventListener("click", (e) => {
  if (e.target.id == "editBtn") {
    const kodeBuku = e.target.dataset.kode;
    window.location.href =
      "/PerpusJava/admin/pages/forms/editbuku.html?kode=" + kodeBuku;
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
        fetch(
          "/PerpusJava/BukuController?" +
            new URLSearchParams({
              page: "delete",
              kode: kodeBuku,
            }),
          { method: "POST" }
        )
          .then((res) => res.json())
          .then((data) => {
            if (data.status === "OK") {
              showAllBukus();
              Swal.fire(
                "Dihapus!",
                `Buku dengan kode ${kodeBuku} telah dihapus`,
                "success"
              );
            }
          });
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

      Object.keys(buku).forEach((key) => {
        if (key == "urlgambar") {
          return;
        } else if (key == "urlebook") {
          return;
        }
        else if (key == "idkategori") {
          return;
        }
        else if (key == "idpenerbit") {
          return;
        }
        else if (key == "idgenre") {
          return;
        }
        const childTd = document.createElement("td");
        // console.log(val);
        childTd.innerText = buku[key];
        childEl.appendChild(childTd);
      });
      //   console.log("========================");

      // TD for action buttons
      const actionEl = document.createElement("td");

      // Define edit button element
      const editBtn = makeEditBtn(buku.kodebuku);

      // Define delete button element
      const removeBtn = makeDeleteBtn(buku.kodebuku);

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

function makeEditBtn(kode) {
  const button = document.createElement("button");
  button.classList = "btn btn-warning btn-sm";
  button.innerText = "Edit";
  button.setAttribute("type", "button");
  button.setAttribute("id", "editBtn");
  button.setAttribute("data-kode", kode);
  return button;
}

function makeDeleteBtn(kode) {
  const button = document.createElement("button");
  button.classList = "btn btn-danger btn-sm";
  button.innerText = "Hapus";
  button.setAttribute("type", "button");
  button.setAttribute("id", "deleteBtn");
  button.setAttribute("data-kode", kode);
  return button;
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
