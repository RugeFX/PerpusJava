/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
const formregist = document.getElementById("formregister");
let pageType = "register";


formregist.addEventListener("submit", (e) => {
    e.preventDefault();
    const body = getAllFormData(formregist)
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
        return;
    }
    console.log(body)
    registerUser(body).then((data) =>{
        if(data.status == "OK"){
            Swal.fire({
                title: "Sign Up Berhasil",
                text: `Selamat Anda Telah Berhasil Register`,
                icon: "success",
            }).then(() => {
                window.location.href = "/PerpusJava/admin/pages/samples/login.html"
            });
        }else{
            console.log("Ada apa ini cokkk")
        }
    })
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


async function registerUser(body) {
    const res = await fetch(
        "/PerpusJava/AuthController?" + new URLSearchParams({page: "register",}),
        {
            method: "POST",
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
            },
            body: JSON.stringify(body),
        });
    const postBody = await res.json();
    return postBody;
}


