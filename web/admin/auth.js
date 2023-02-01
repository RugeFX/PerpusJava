axios
  .get(
    "/PerpusJava/AuthController?" +
      new URLSearchParams({
        page: "cek",
      })
  )
  .then((data) => {
    if (data.data.status === "NO") {
      window.location.href = "/PerpusJava/admin/pages/samples/login.html";
    }
  });
