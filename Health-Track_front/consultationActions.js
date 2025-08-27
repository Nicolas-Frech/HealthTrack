const apiUrl = "http://localhost:8080/consultation";
import { showMessage } from './messageUtil.js';

const token = localStorage.getItem("token");
if (!token) {
  alert("Usuário não autenticado!");
  window.location.href = "login.html";
}

// função para criar headers com token
function authHeaders() {
  return {
    "Content-Type": "application/json",
    "Authorization": `Bearer ${token}`
  };
}

// Agendar consulta
document.getElementById("bookForm").addEventListener("submit", async (e) => {
  e.preventDefault();
  const data = {
    medicCRM: document.getElementById("bookMedicCRM").value,
    patientCPF: document.getElementById("bookPatientCPF").value,
    date: document.getElementById("bookDate").value
  };

  try {
    const res = await fetch(apiUrl, {
      method: "POST",
      headers: authHeaders(),
      body: JSON.stringify(data)
    });
    if (res.ok) {
      showMessage("Consulta agendada com sucesso!");
      loadConsultations();
    } else showMessage("Erro ao agendar consulta", "danger");
  } catch {
    showMessage("Erro de conexão com servidor", "danger");
  }
});

// Atualizar Data
document.getElementById("updateDateForm").addEventListener("submit", async (e) => {
  e.preventDefault();
  const data = { date: document.getElementById("updateDateInput").value };
  const id = document.getElementById("updateDateId").value;

  try {
    const res = await fetch(`${apiUrl}/${id}/date`, {
      method: "PUT",
      headers: authHeaders(),
      body: JSON.stringify(data)
    });
    if (res.ok) {
      showMessage("Data da consulta atualizada com sucesso!");
      loadConsultations();
    } else showMessage("Erro ao atualizar data", "danger");
  } catch {
    showMessage("Erro de conexão com servidor", "danger");
  }
});

// Atualizar Status
document.getElementById("updateStatusForm").addEventListener("submit", async (e) => {
  e.preventDefault();
  const data = { status: document.getElementById("updateStatusSelect").value };
  const id = document.getElementById("updateStatusId").value;

  try {
    const res = await fetch(`${apiUrl}/${id}/status`, {
      method: "PUT",
      headers: authHeaders(),
      body: JSON.stringify(data)
    });
    if (res.ok) {
      showMessage("Status da consulta atualizado com sucesso!");
      loadConsultations();
    } else showMessage("Erro ao atualizar status", "danger");
  } catch {
    showMessage("Erro de conexão com servidor", "danger");
  }
});

// Listar consultas
let currentPage = 0;
const pageSize = 5;

async function loadConsultations(page = 0) {
  currentPage = page;
  const tableBody = document.getElementById("consultationsTable");
  const paginationDiv = document.getElementById("pagination");
  tableBody.innerHTML = `<tr><td colspan="5" class="text-center">Carregando...</td></tr>`;

  try {
    const res = await fetch(`${apiUrl}?page=${page}&size=${pageSize}`, {
      headers: authHeaders()
    });
    if (res.ok) {
      const data = await res.json();
      const consultations = data.content;

      if (!consultations || consultations.length === 0) {
        tableBody.innerHTML = `<tr><td colspan="5" class="text-center">Nenhuma consulta encontrada.</td></tr>`;
        paginationDiv.innerHTML = "";
        return;
      }

      tableBody.innerHTML = consultations.map(c => `
        <tr>
          <td>${c.id}</td>
          <td>${c.medicId}</td>
          <td>${c.patientId}</td>
          <td>${c.date}</td>
          <td>${c.status}</td>
        </tr>
      `).join("");

      let buttons = "";
      if (!data.first) buttons += `<button class="btn btn-sm btn-outline-secondary me-2" onclick="loadConsultations(${currentPage - 1})">⬅ Anterior</button>`;
      if (!data.last) buttons += `<button class="btn btn-sm btn-outline-secondary" onclick="loadConsultations(${currentPage + 1})">Próxima ➡</button>`;
      paginationDiv.innerHTML = buttons;

    } else {
      tableBody.innerHTML = `<tr><td colspan="5" class="text-center text-danger">Erro ao carregar consultas</td></tr>`;
    }
  } catch {
    tableBody.innerHTML = `<tr><td colspan="5" class="text-center text-danger">Falha na conexão</td></tr>`;
  }
}

document.getElementById("refreshList").addEventListener("click", () => loadConsultations(currentPage));
document.getElementById("list-tab").addEventListener("shown.bs.tab", () => loadConsultations(0));
