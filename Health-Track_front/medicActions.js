import { Auth } from './authUtils.js';
import { showMessage } from './messageUtil.js';

const auth = new Auth();
const apiUrl = "http://localhost:8080/medic";

// Registrar médico
document.getElementById("medicForm").addEventListener("submit", async (e) => {
    e.preventDefault();
    const data = {
        name: document.getElementById("name").value,
        crm: document.getElementById("crm").value,
        speciality: document.getElementById("speciality").value,
        telephone: document.getElementById("telephone").value,
        email: document.getElementById("email").value
    };

    try {
        const res = await fetch(apiUrl, { method: "POST", headers: auth.headers(), body: JSON.stringify(data) });
        if (res.ok) { showMessage("Médico registrado com sucesso!"); loadMedics(); }
        else showMessage("Erro ao registrar médico", "danger");
    } catch { showMessage("Erro de conexão com servidor", "danger"); }
});

// Atualizar médico
document.getElementById("updateForm").addEventListener("submit", async (e) => {
    e.preventDefault();
    const data = {
        id: document.getElementById("updateId").value,
        telephone: document.getElementById("updateTelephone").value || null,
        email: document.getElementById("updateEmail").value || null
    };

    try {
        const res = await fetch(apiUrl, { method: "PUT", headers: auth.headers(), body: JSON.stringify(data) });
        if (res.ok) { showMessage("Médico atualizado com sucesso!"); loadMedics(); }
        else showMessage("Erro ao atualizar médico", "danger");
    } catch { showMessage("Erro de conexão com servidor", "danger"); }
});

// Deletar médico
document.getElementById("deleteForm").addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = document.getElementById("deleteId").value;
    try {
        const res = await fetch(`${apiUrl}/${id}`, { method: "DELETE", headers: auth.headers() });
        if (res.ok) { showMessage("Médico deletado com sucesso!"); loadMedics(); }
        else showMessage("Erro ao deletar médico", "danger");
    } catch { showMessage("Erro de conexão com servidor", "danger"); }
});

// Listar médicos
let currentPage = 0;
const pageSize = 5;

async function loadMedics(page = 0) {
    currentPage = page;
    const tableBody = document.getElementById("medicsTable");
    const paginationDiv = document.getElementById("pagination");
    tableBody.innerHTML = `<tr><td colspan="6" class="text-center">Carregando...</td></tr>`;

    try {
        const res = await fetch(`${apiUrl}?page=${page}&size=${pageSize}`, { headers: auth.headers() });
        if (res.ok) {
            const data = await res.json();
            const medics = data.content;
            if (!medics || medics.length === 0) {
                tableBody.innerHTML = `<tr><td colspan="6" class="text-center">Nenhum médico encontrado.</td></tr>`;
                paginationDiv.innerHTML = "";
                return;
            }

            tableBody.innerHTML = medics.map(m => `
                <tr>
                    <td>${m.id}</td>
                    <td>${m.name}</td>
                    <td>${m.crm}</td>
                    <td>${m.speciality}</td>
                    <td>${m.email}</td>
                    <td>${m.telephone}</td>
                </tr>
            `).join("");

            let buttons = "";
            if (!data.first) buttons += `<button class="btn btn-sm btn-outline-secondary me-2" onclick="loadMedics(${currentPage - 1})">⬅ Anterior</button>`;
            if (!data.last) buttons += `<button class="btn btn-sm btn-outline-secondary" onclick="loadMedics(${currentPage + 1})">Próxima ➡</button>`;
            paginationDiv.innerHTML = buttons;

        } else tableBody.innerHTML = `<tr><td colspan="6" class="text-center text-danger">Erro ao carregar médicos</td></tr>`;
    } catch { tableBody.innerHTML = `<tr><td colspan="6" class="text-center text-danger">Falha na conexão</td></tr>`; }
}

document.getElementById("refreshList").addEventListener("click", () => loadMedics(currentPage));
document.getElementById("list-tab").addEventListener("shown.bs.tab", () => loadMedics(0));