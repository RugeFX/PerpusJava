const formInsert = document.getElementById("mainform");
const tglkembali = document.getElementById("tanggalkembali");
const tglpinjam = document.getElementById("tanggalpinjam");
const selectOptionElements = document.querySelectorAll("#mainform select");

// const pageURL = new URL(window.location.href);
// console.log(pageURL);

tglpinjam.addEventListener('change', () => {
    let dateObj = tglpinjam.valueAsDate;
    console.log(dateObj);
    dateObj.setDate(dateObj.getDate() + 7);
    tglkembali.value = dateObj;
    console.log(tglkembali.value);
})

let pageType = "insert";

getSelectOptions().then(({ data }) => {
    selectOptionElements.forEach((el) => {
        console.log(data);
        console.log(el.id);
        data[el.id].forEach((option) => {
            const optEl = document.createElement("option");
            if (el.id === "anggota") {
                optEl.value = option[Object.keys(option)[0]];
                optEl.text = option[Object.keys(option)[2]];
                el.appendChild(optEl);
                return;
            }
            optEl.value = option[Object.keys(option)[0]];
            optEl.text = option[Object.keys(option)[1]];
            el.appendChild(optEl);
        });
    });
});

formInsert.addEventListener("submit", (e) => {
    e.preventDefault();
    const body = getAllFormData(formInsert);
    if (
        !Object.values(body).every((value) => {
            if (value === null || value === "") {
                console.log(value);
                return false;
            }
            return true;
        })
    ) {
        console.log("Tolong isi data dengan benar!");
        Swal.fire(
            "Data Invalid",
            "Tolong masukkan data dengan lengkap!",
            "warning"
        );
        return;
    }
    if (pageType === "insert") {
        insertData(body).then((data) => {
            console.log(data);
            if (data.status == "OK") {
                window.location.href = "/PerpusJava/pinjam.html";
            }
        });
    }
});

function getAllFormData(form) {
    const body = {};
    for (const element of form.elements) {
        if (element.name) {
            body[element.name] = element.value;
        }
    }
    console.log(body);
    return body;
}

async function insertData(body) {
    const res = await fetch(
        "/PerpusJava/PinjamanController?" + new URLSearchParams({ page: "insert" }),
        {
            method: "POST",
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
            },
            body: JSON.stringify(body),
        }
    );
    const post = await res.json();
    return post;
}

async function getSelectOptions() {
    const res = await fetch(
        "/PerpusJava/PinjamanController?" +
        new URLSearchParams({ page: "attributes" }),
        {
            method: "GET",
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
            },
        }
    );
    console.log(res);
    return await res.json();
}
