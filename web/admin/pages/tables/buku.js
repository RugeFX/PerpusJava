const bukuEl = document.getElementById("buku");

// showAllBukus();

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

// function showAllBukus() {
//   bukuEl.innerHTML = "";
//   getAllBukus().then((bukus) => {
//     bukus.forEach((buku) => {
//       const childEl = document.createElement("tr");

//       Object.keys(buku).forEach((key) => {
//         if (key == "urlgambar") {
//           return;
//         } else if (key == "urlebook") {
//           return;
//         }
//         const childTd = document.createElement("td");
//         // console.log(val);
//         childTd.innerText = buku[key];
//         childEl.appendChild(childTd);
//       });
//       //   console.log("========================");

//       // TD for action buttons
//       const actionEl = document.createElement("td");

//       // Define edit button element
//       const editBtn = makeEditBtn(buku.kodebuku);

//       // Define delete button element
//       const removeBtn = makeDeleteBtn(buku.kodebuku);

//       // Code element for inner container of action element
//       const codeEl = document.createElement("code");
//       codeEl.append(editBtn);
//       codeEl.insertAdjacentHTML("beforeend", " | ");
//       codeEl.append(removeBtn);

//       actionEl.append(codeEl);
//       childEl.append(actionEl);

//       bukuEl.append(childEl);
//     });
//   });
// }

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

const nextBtn = document.getElementById("nextBtn");
const prevBtn = document.getElementById("prevBtn");
const pageNumEl = document.getElementById("pageNum");

let currentPage = 1;
let recordsPerPage = 5;

getAllBukus().then((bukus) => {
  function numPages() {
    return Math.ceil(bukus.length / recordsPerPage);
  }
  console.log(numPages());

  function prevPage() {
    if (currentPage > 1) {
      currentPage--;
      changePage(currentPage);
    }
  }

  function nextPage() {
    if (currentPage < numPages()) {
      currentPage++;
      changePage(currentPage);
    }
  }

  function changePage(page) {
    if (page < 1) page = 1;
    if (page > numPages()) page = numPages();

    bukuEl.innerHTML = "";

    for (
      let i = (page - 1) * recordsPerPage;
      i < page * recordsPerPage && i < bukus.length;
      i++
    ) {
      const childEl = document.createElement("tr");
      const buku = bukus[i];

      Object.keys(buku).forEach((key) => {
        if (key == "urlgambar") {
          return;
        } else if (key == "urlebook") {
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
    }
    pageNumEl.innerHTML = page;

    if (page === 1) {
      prevBtn.classList.remove("btn-primary");
      prevBtn.classList.add("btn-secondary");
      prevBtn.setAttribute("disabled", true);

      nextBtn.classList.add("btn-primary");
      nextBtn.classList.remove("btn-secondary");
      nextBtn.removeAttribute("disabled");
      // prevBtn.style.visibility = "hidden";
      // nextBtn.style.visibility = "visible";
    } else if (page === numPages()) {
      nextBtn.classList.remove("btn-primary");
      nextBtn.classList.add("btn-secondary");
      nextBtn.setAttribute("disabled", true);

      prevBtn.classList.add("btn-primary");
      prevBtn.classList.remove("btn-secondary");
      prevBtn.removeAttribute("disabled");

      // prevBtn.style.visibility = "visible";
      // nextBtn.style.visibility = "hidden";
    } else {
      nextBtn.classList.add("btn-primary");
      nextBtn.classList.remove("btn-secondary");
      nextBtn.removeAttribute("disabled");

      prevBtn.classList.add("btn-primary");
      prevBtn.classList.remove("btn-secondary");
      prevBtn.removeAttribute("disabled");
    }

    if (numPages() == 1) {
      nextBtn.classList.remove("btn-primary");
      nextBtn.classList.add("btn-secondary");
      nextBtn.setAttribute("disabled", true);

      prevBtn.classList.remove("btn-primary");
      prevBtn.classList.add("btn-secondary");
      prevBtn.setAttribute("disabled", true);
    }
  }

  nextBtn.addEventListener("click", nextPage);
  prevBtn.addEventListener("click", prevPage);
  changePage(1);
});
