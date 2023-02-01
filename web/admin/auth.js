fetch(
  "/PerpusJava/AuthController?" +
    new URLSearchParams({
      page: "cek",
    })
)
  .then((res) => {
    console.log("res : " + JSON.stringify(res));
    res.json();
  })
  .then((data) => {
    console.log(data);
    if (data.status === "NO") {
      window.location.href = "/PerpusJava/admin/pages/samples/login.html";
    }
  });
