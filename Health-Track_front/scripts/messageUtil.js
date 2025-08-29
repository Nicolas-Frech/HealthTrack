export function showMessage(msg, type = "success") {
  const messageDiv = document.querySelector("#message"); // ou passe como parâmetro se preferir
  messageDiv.textContent = msg;
  messageDiv.className = "";
  messageDiv.classList.add("mt-3", "text-center", type === "danger" ? "text-danger" : "text-success");

  setTimeout(() => {
    messageDiv.textContent = "";
    messageDiv.className = "mt-3 text-center";
  }, 3000);
}
