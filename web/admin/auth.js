
// const res = fetch(
//      "/PerpusJava/AuthController?" + new URLSearchParams({page: "cek"}),
//      {
//          method: "GET",
//          headers: {
//              Accept: "application/json",
//              "Content-Type": "application/json",
//          },
//      });
//      res.json()
//   .then((data) => {
//     console.log(data.status)
//     if (data.status == "NO") {
//       console.log("ditolak")
//       window.location.href = "/PerpusJava/admin/pages/samples/login.html";
//     }
//     else{
//       console.log("ada data: " .data.data)
//     }
//   });



function cek(){
  sessionCek().then((data) => {
    console.log(data.status)
    if (data.status == "NO") {
      console.log("ditolak")
      fetch(
        "/PerpusJava/AuthController?" +
          new URLSearchParams({
            page: "cek",
          })
      )
        .then((res) => res.json())
        .then((data) => {
          if (data.status === "NO") {
            window.location.href = "/PerpusJava/admin/pages/samples/login.html";
          }
          else {
            console.log("ada data: ".data.data)
          }
        })
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

cek();
 
