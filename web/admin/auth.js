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
  });
