import { Auth } from './authUtils.js';
import { showMessage } from './messageUtil.js';
import { getBadgeClass, translateStatus } from './statusUtil.js';

const auth = new Auth();
const apiUrl = "http://localhost:8080/consultation";

let currentPage = 0;
const pageSize = 5;

// Pega dados do médico logado
async function getLoggedMedic() {
    try {
        const res = await fetch("http://localhost:8080/medic/me", { headers: auth.headers() });
        if (!res.ok) throw new Error("Erro ao buscar médico logado");
        return await res.json();
    } catch (err) {
        showMessage("Erro ao buscar dados do médico", "danger");
        throw err;
    }
}

// Carrega consultas do médico logado
export async function loadConsultations(page = 0) {
    currentPage = page;
    const tableBody = document.getElementById("consultationsTable");
    const paginationDiv = document.getElementById("pagination");
    tableBody.innerHTML = `<tr><td colspan="5" class="text-center">Carregando...</td></tr>`;

    try {
        // Busca dados do médico logado para exibir mesmo sem consultas
        const medic = await getLoggedMedic();
        document.getElementById("doctorInfo").textContent = `Dr. ${medic.name} (CRM ${medic.crm})`;

        // Busca consultas
        const res = await fetch(`${apiUrl}/medic/${medic.id}`, { headers: auth.headers() });
        if (!res.ok) throw new Error("Erro ao carregar consultas");

        const data = await res.json();
        const consultations = data.content;

        if (!consultations || consultations.length === 0) {
            tableBody.innerHTML = `<tr><td colspan="5" class="text-center">Nenhuma consulta encontrada.</td></tr>`;
            paginationDiv.innerHTML = "";
            return;
        }

        // Monta tabela usando os objetos patient que já vêm da API
        tableBody.innerHTML = consultations.map(c => `
            <tr>
                <td>${c.patient ? c.patient.name : "Desconhecido"}</td>
                <td>${c.patient ? c.patient.cpf : "-"}</td>
                <td>${new Date(c.date).toLocaleString()}</td>
                <td><span class="badge ${getBadgeClass(c.status)}">${translateStatus(c.status)}</span></td>
                <td>
                    <button class="btn btn-sm ${c.status === 'CONCLUIDA' ? 'btn-secondary disabled' : 'btn-primary'}"
                            onclick="openNotesModal(${c.id})">
                        ${c.status === 'CONCLUIDA' ? 'Finalizada' : 'Abrir'}
                    </button>
                </td>
            </tr>
        `).join("");

        // Paginação
        let buttons = "";
        if (!data.first) buttons += `<button class="btn btn-sm btn-outline-secondary me-2" onclick="loadConsultations(${currentPage - 1})">⬅ Anterior</button>`;
        if (!data.last) buttons += `<button class="btn btn-sm btn-outline-secondary" onclick="loadConsultations(${currentPage + 1})">Próxima ➡</button>`;
        paginationDiv.innerHTML = buttons;

    } catch (err) {
        tableBody.innerHTML = `<tr><td colspan="5" class="text-center text-danger">Falha ao carregar consultas</td></tr>`;
        console.error(err);
    }
}

export function openNotesModal(consultationId) {
    document.getElementById("modalConsultationId").value = consultationId;
    document.getElementById("modalNotes").value = "";
    document.getElementById("modalPrescription").value = "";

    const notesModal = new bootstrap.Modal(document.getElementById('notesModal'));
    notesModal.show();
}

// Salvar notas e prescrição
document.getElementById("modalSaveBtn").addEventListener("click", async () => {
    const consultationId = document.getElementById("modalConsultationId").value;
    const data = {
        notes: document.getElementById("modalNotes").value,
        prescription: document.getElementById("modalPrescription").value
    };

    try {
        const res = await fetch(`${apiUrl}/${consultationId}/notes`, {
            method: "PUT",
            headers: auth.headers(),
            body: JSON.stringify(data)
        });
        if (res.ok) {
            showMessage("Consulta atualizada com sucesso!");
            bootstrap.Modal.getInstance(document.getElementById('notesModal')).hide();
        } else {
            showMessage("Erro ao atualizar consulta", "danger");
        }
    } catch {
        showMessage("Falha na conexão com servidor", "danger");
    }
});

// Event listeners
document.getElementById("refreshList")?.addEventListener("click", () => loadConsultations(currentPage));
document.getElementById("list-tab")?.addEventListener("shown.bs.tab", () => loadConsultations(0));

// Inicializa tabela
loadConsultations(0);
window.openNotesModal = openNotesModal;
