const containerBook = document.getElementById("containerbook");
showAllBukus();

function showAllBukus() {
  getAllBukus().then((bukus) => {
    bukus.forEach((buku) => {
      const artikelEl = document.createElement("div");
      artikelEl.classList.add("artikel");
      const imgBookEl = document.createElement("img");
      imgBookEl.classList.add("imgbuku");
      imgBookEl.height = 280;
      imgBookEl.width = 230;
      imgBookEl.src = buku.urlgambar;
      imgBookEl.style.objectFit = "clip";
      const kontenEl = document.createElement("div");
      kontenEl.classList.add("konten");
      const judulEl = document.createElement("h5");
      judulEl.style.textDecoration = "underline";
      judulEl.innerHTML = buku.judulbuku;
      const pengarangEl = document.createElement("p");
      pengarangEl.innerHTML = buku.pengarang;
      const hrEl = document.createElement("hr");
      hrEl.size = "5px";
      hrEl.color = "black";
      artikelEl.append(imgBookEl, kontenEl);
      kontenEl.append(judulEl, pengarangEl, hrEl);
      if (buku.urlebook) {
        const link = document.createElement("a");
        link.innerText = "Download EBook";
        link.style.color = "white";
        link.style.padding = ".5rem 1rem";
        link.style.borderRadius = "10px";
        link.style.backgroundColor = "#005555";
        link.href = buku.urlebook;
        artikelEl.append(link);
      }
      containerBook.append(artikelEl);
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
