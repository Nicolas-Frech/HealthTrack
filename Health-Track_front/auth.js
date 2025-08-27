import { showMessage } from "./messageUtil.js";

const apiBaseUrl = "http://localhost:8080";

const loginForm = document.getElementById("loginForm");
const registerForm = document.getElementById("registerForm");
const messageDiv = document.getElementById("message");


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
      const response = await fetch(`${apiBaseUrl}/auth`, {
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
          window.location.href = "medicDashboard.html";
        } else {
          showMessage("Perfil não reconhecido.", "danger");
        }
      }, 1200);

    } catch (error) {
      showMessage(error.message, "danger");
    }
  });
}


if (registerForm) {
  registerForm.addEventListener("submit", async (e) => {
    e.preventDefault();

    const username = document.getElementById("registerUsername").value;
    const password = document.getElementById("registerPassword").value;
    const role = document.getElementById("registerRole").value;

    try {
      const response = await fetch(`${apiBaseUrl}/users`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password, role }),
      });

      if (!response.ok) {
        throw new Error("Falha no cadastro. Verifique os dados.");
      }

      showMessage("Usuário cadastrado com sucesso!", "success");
      registerForm.reset();

    } catch (error) {
      showMessage(error.message, "danger");
    }
  });
}
