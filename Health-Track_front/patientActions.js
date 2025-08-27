const apiUrl = "http://localhost:8080/patient";
import { showMessage } from './messageUtil.js';

// Verifica token
const token = localStorage.getItem("token");
if (!token) {
    alert("Você precisa estar logado!");
    window.location.href = "login.html";
}

// Função para headers com token
function getHeaders() {
    return {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${token}`
    };
}

//log out
document.getElementById("logoutBtn").addEventListener("click", () => {
    localStorage.removeItem("token");
    window.location.href = "auth.html";
});

// Registrar paciente
document.getElementById("patientForm").addEventListener("submit", async (e) => {
    e.preventDefault();
    const data = {
        name: document.getElementById("name").value,
        cpf: document.getElementById("cpf").value,
        age: document.getElementById("age").value,
        email: document.getElementById("email").value,
        telephone: document.getElementById("telephone").value
    };

    try {
        const res = await fetch(apiUrl, {
            method: "POST",
            headers: getHeaders(),
            body: JSON.stringify(data)
        });
        if (res.ok) {
            showMessage("Paciente registrado com sucesso!");
            loadPatients();
        } else {
            showMessage("Erro ao registrar paciente", "danger");
        }
    } catch {
        showMessage("Erro de conexão com servidor", "danger");
    }
});

// Atualizar paciente
document.getElementById("updateForm").addEventListener("submit", async (e) => {
    e.preventDefault();
    const data = {
        id: document.getElementById("updateId").value,
        email: document.getElementById("updateEmail").value || null,
        telephone: document.getElementById("updateTelephone").value || null,
        age: document.getElementById("updateAge").value || null
    };

    try {
        const res = await fetch(apiUrl, {
            method: "PUT",
            headers: getHeaders(),
            body: JSON.stringify(data)
        });
        if (res.ok) {
            showMessage("Paciente atualizado com sucesso!");
            loadPatients();
        } else {
            showMessage("Erro ao atualizar paciente", "danger");
        }
    } catch {
        showMessage("Erro de conexão com servidor", "danger");
    }
});

// Deletar paciente
document.getElementById("deleteForm").addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = document.getElementById("deleteId").value;

    try {
        const res = await fetch(`${apiUrl}/${id}`, {
            method: "DELETE",
            headers: getHeaders()
        });
        if (res.ok) {
            showMessage("Paciente deletado com sucesso!");
            loadPatients();
        } else {
            showMessage("Erro ao deletar paciente", "danger");
        }
    } catch {
        showMessage("Erro de conexão com servidor", "danger");
    }
});

let currentPage = 0;
const pageSize = 5;

// Listar pacientes
async function loadPatients(page = 0) {
    currentPage = page;
    const tableBody = document.getElementById("patientsTable");
    const paginationDiv = document.getElementById("pagination");
    tableBody.innerHTML = `<tr><td colspan="6" class="text-center">Carregando...</td></tr>`;

    try {
        const res = await fetch(`${apiUrl}?page=${page}&size=${pageSize}`, {
            headers: getHeaders()
        });
        if (res.ok) {
            const data = await res.json();
            const patients = data.content;

            if (!patients || patients.length === 0) {
                tableBody.innerHTML = `<tr><td colspan="6" class="text-center">Nenhum paciente encontrado.</td></tr>`;
                paginationDiv.innerHTML = "";
                return;
            }

            tableBody.innerHTML = patients.map(p => `
                <tr>
                    <td>${p.id}</td>
                    <td>${p.name}</td>
                    <td>${p.cpf}</td>
                    <td>${p.age}</td>
                    <td>${p.email}</td>
                    <td>${p.telephone}</td>
                </tr>
            `).join("");

            let buttons = "";
            if (!data.first) buttons += `<button class="btn btn-sm btn-outline-secondary me-2" onclick="loadPatients(${currentPage - 1})">⬅ Anterior</button>`;
            if (!data.last) buttons += `<button class="btn btn-sm btn-outline-secondary" onclick="loadPatients(${currentPage + 1})">Próxima ➡</button>`;
            paginationDiv.innerHTML = buttons;
        } else {
            tableBody.innerHTML = `<tr><td colspan="6" class="text-center text-danger">Erro ao carregar pacientes</td></tr>`;
        }
    } catch {
        tableBody.innerHTML = `<tr><td colspan="6" class="text-center text-danger">Falha na conexão</td></tr>`;
    }
}

document.getElementById("refreshList").addEventListener("click", () => loadPatients(currentPage));
document.getElementById("list-tab").addEventListener("shown.bs.tab", () => loadPatients(0));
