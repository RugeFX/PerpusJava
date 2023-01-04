const getAllBukus = async () => {
  const res = await fetch("/PerpusJava/BukuController");
  const bukus = await res.json();
  return bukus;
};

const bukuEl = document.getElementById("buku");

getAllBukus().then((bukus) => {
  const obData = JSON.parse(bukus);
  obData.forEach((buku) => {
    bukuEl.innerHTML = `Judul : ${buku.judulbuku}`;
  });
});
