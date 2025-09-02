export class Auth {
  constructor(logoutBtnId = "logoutBtn", redirectIfNoToken = "login.html") {
    this.token = localStorage.getItem("token");
    if (!this.token) {
      alert("Usuário não autenticado!");
      window.location.href = redirectIfNoToken;
    }
    this.setupLogout(logoutBtnId);
  }

  headers() {
    return {
      "Content-Type": "application/json",
      "Authorization": `Bearer ${this.token}`
    };
  }

  setupLogout(buttonId) {
    const btn = document.getElementById(buttonId);
    if (!btn) return;
    btn.addEventListener("click", () => {
      localStorage.removeItem("token");
      window.location.href = "index.html";
    });
  }
}
