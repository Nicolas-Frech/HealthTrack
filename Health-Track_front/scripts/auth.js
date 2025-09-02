import { showMessage } from "./messageUtil.js";

const loginForm = document.getElementById("loginForm");

function getUserRoleFromToken(token) {
  try {
    const payloadBase64 = token.split(".")[1];
    const decodedJson = atob(payloadBase64);
    const payload = JSON.parse(decodedJson);
    return payload.role || null;
  } catch (e) {
    return null;
  }
}

if (loginForm) {
  loginForm.addEventListener("submit", async (e) => {
    e.preventDefault();

    const username = document.getElementById("loginUsername").value;
    const password = document.getElementById("loginPassword").value;

    try {
      const response = await fetch(`${CONFIG.API_URL}/auth`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password }),
      });

      if (!response.ok) {
        throw new Error("Falha no login. Verifique suas credenciais.");
      }

      const data = await response.json();
      localStorage.setItem("token", data.token);

      const role = getUserRoleFromToken(data.token);

      showMessage("Login realizado com sucesso!", "success");

      setTimeout(() => {
        if (role === "ADMIN") {
          window.location.href = "consultationActions.html";
        } else if (role === "MEDIC") {
          window.location.href = "medicConsultations.html";
        } else {
          showMessage("Perfil não reconhecido.", "danger");
        }
      }, 1200);

    } catch (error) {
      showMessage(error.message, "danger");
    }
  });
}