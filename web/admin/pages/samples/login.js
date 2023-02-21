/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
const formLogin = document.getElementById("formlogin");
const idEl = document.getElementById("inputid");
const pwEl = document.getElementById("password");
const pageURL = new URL(window.location.href);
let pageType = "login";

if (pageURL.pathname === "/PerpusJava/admin/pages/samples/login.html"){
    sessionCek().then((dataUser) => {
        if (dataUser.status == "OK"){
            if (dataUser.data == "Petugas") {
                window.location.href = "/PerpusJava/admin"
            } else {
                window.location.href = "/PerpusJava"
            }
        }
    })
}

async function sessionCek() {
    const res =
        await fetch(
            "/PerpusJava/AuthController?" + new URLSearchParams({ page: "cek" }),
            {
                method: "GET",
                headers: {
                    Accept: "application/json",
                    "Content-Type": "application/json",
                },
            });
    const cekUser = await res.json();
    return cekUser;
}

formLogin.addEventListener("submit", (e) => {
    e.preventDefault();
    Login(idEl.value, pwEl.value).then((dataUser) => {
        console.log(dataUser.data)
        if (dataUser.status === "NO") {
            Swal.fire({
                title: "Id Atau Password Salah",
                text: `Silahkan Login Kembali`,
                icon: "error",
            });
            return;
        }
        Swal.fire({
            title: "Login Berhasil",
            text: `Selamat Anda Telah Berhasil Login`,
            icon: "success",
        }).then(() => {
            if(dataUser.data == "Petugas"){
                window.location.href = "/PerpusJava/admin"
            }else{
                window.location.href = "/PerpusJava"
            }
            
        });

    })

});


async function Login(id, password) {
    const res =
        await fetch(
            "/PerpusJava/AuthController?" + new URLSearchParams({
                page: pageType,
                id: id,
                password: password,
            }),
            {
                method: "POST",
                headers: {
                    Accept: "application/json",
                    "Content-Type": "application/json",
                },
            });
    const post = await res.json();
    return post;
}




