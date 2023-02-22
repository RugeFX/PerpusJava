const tabelEl = document.getElementById("pinjaman");
const filterEl = document.getElementById("filterpinjaman");

showAllDatas();

filterEl.addEventListener("change", (e) => {
  const value = e.target.value;
  if (value === "All") {
    tabelEl.innerHTML = "";
    showAllDatas();
    return;
  }
  getAllDatas().then((datas) => {
    const filtered = datas.filter((data) => data.keterangan === value);
    console.log(filtered);
    if (filtered.length === 0) {
      tabelEl.innerHTML = `<tr><td style="text-align:center;" colspan=8>No data for filter ${value}</td></tr>`;
      return;
    }
    tabelEl.innerHTML = "";
    filtered.forEach((data) => {
      const childEl = document.createElement("tr");

      Object.keys(data).forEach((key) => {
        const childTd = document.createElement("td");
        // console.log(val);
        childTd.innerText = data[key];
        childEl.appendChild(childTd);
      });
      //   console.log("========================");

      // TD for action buttons
      const actionEl = document.createElement("td");

      // Define edit button element
      const editBtn = makeEditBtn(data.idpinjaman);

      // Define delete button element
      const removeBtn = makeDeleteBtn(data.idpinjaman);

      // Code element for inner container of action element
      const codeEl = document.createElement("code");
      codeEl.append(editBtn);
      codeEl.insertAdjacentHTML("beforeend", " | ");
      codeEl.append(removeBtn);

      actionEl.append(codeEl);
      childEl.append(actionEl);

      tabelEl.append(childEl);
    });
  });
});

document.addEventListener("click", (e) => {
  const kode = e.target.dataset.kode;
  if (e.target.id == "editBtn") {
    window.location.href =
      "/PerpusJava/admin/pages/forms/editpinjaman.html?id=" + kode;
  }
  if (e.target.id == "deleteBtn") {
    Swal.fire({
      title: "Apakah anda yakin?",
      text: `Anda akan menghapus data pinjaman dengan id ${kode}`,
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      cancelButtonText: "Batal",
      confirmButtonText: "Hapus",
    }).then((result) => {
      if (result.isConfirmed) {
        fetch(
          "/PerpusJava/PinjamanController?" +
            new URLSearchParams({
              page: "delete",
              id: kode,
            }),
          { method: "POST" }
        )
          .then((res) => res.json())
          .then((data) => {
            if (data.status === "OK") {
              showAllDatas();
              Swal.fire(
                "Dihapus!",
                `Pinjaman dengan id ${kode} telah dihapus`,
                "success"
              );
            }
          });
      }
    });
    console.log(kode);
  }
});

function showAllDatas() {
  tabelEl.innerHTML = "";
  getAllDatas().then((datas) => {
    datas.forEach((data) => {
      const childEl = document.createElement("tr");

      Object.keys(data).forEach((key) => {
        const childTd = document.createElement("td");
        // console.log(val);
        childTd.innerText = data[key];
        childEl.appendChild(childTd);
      });
      //   console.log("========================");

      // TD for action buttons
      const actionEl = document.createElement("td");

      // Define edit button element
//      const editBtn = makeEditBtn(data.idpinjaman);

      // Define delete button element
//      const removeBtn = makeDeleteBtn(data.idpinjaman);

      // Code element for inner container of action element
      const codeEl = document.createElement("code");
//      codeEl.append(editBtn);
//      codeEl.insertAdjacentHTML("beforeend", " | ");
//      codeEl.append(removeBtn);

      actionEl.append(codeEl);
      childEl.append(actionEl);

      tabelEl.append(childEl);
    });
  });
}

//function makeEditBtn(kode) {
//  const button = document.createElement("button");
//  button.classList = "btn btn-warning btn-sm";
//  button.innerText = "Edit";
//  button.setAttribute("type", "button");
//  button.setAttribute("id", "editBtn");
//  button.setAttribute("data-kode", kode);
//  return button;
//}
//
//function makeDeleteBtn(kode) {
//  const button = document.createElement("button");
//  button.classList = "btn btn-danger btn-sm";
//  button.innerText = "Hapus";
//  button.setAttribute("type", "button");
//  button.setAttribute("id", "deleteBtn");
//  button.setAttribute("data-kode", kode);
//  return button;
//}

// Fetches Functions
async function getAllDatas() {
  const res = await fetch("/PerpusJava/PinjamanController");
  const datas = await res.json();
  console.log(datas);
  return datas;
}

// async function getByID(id) {
//   const res = await fetch(
//     "/PerpusJava/AnggotaController?" +
//       new URLSearchParams({
//         page: "show",
//         idbuku: id,
//       })
//   );
//   const bukus = await res.json();
//   return bukus;
// }
