const containerBook = document.getElementById("containerbook");
showBukuTerlaris();


function showBukuTerlaris() {
    getBukuTerlaris().then((bukus) => {
        bukus.forEach((buku) => {
            const artikelEl = document.createElement("div");
            artikelEl.classList.add("artikel")
            const imgBookEl = document.createElement("img")
            imgBookEl.classList.add("imgbuku")
            imgBookEl.height = 280
            imgBookEl.width = 230
            imgBookEl.src = buku.urlgambar
            const kontenEl = document.createElement("div");
            kontenEl.classList.add("konten")
            const judulEl = document.createElement("h3")
            judulEl.innerHTML = "<u>" + buku.judulbuku + "</u>"
            const pengarangEl = document.createElement("h5")
            pengarangEl.innerHTML = buku.pengarang
            const hrEl = document.createElement("hr")
            hrEl.size = "5px"
            hrEl.color = "black"
            artikelEl.append(imgBookEl, kontenEl)
            kontenEl.append(judulEl, pengarangEl, hrEl)
            containerBook.append(artikelEl)
        });
    });
}



// Fetches Functions
async function getBukuTerlaris() {
    const res = await fetch(
        "/PerpusJava/BukuController?" +
        new URLSearchParams({
            page: "terlaris",
        })
    );
    const bukus = await res.json();
    return bukus;
}