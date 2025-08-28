import { Auth } from './authUtils.js';
import { showMessage } from './messageUtil.js';

const auth = new Auth();
const apiUrl = "http://localhost:8080/consultation";
const patientApiUrl = "http://localhost:8080/patient";

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

// Busca dados do paciente pelo ID
async function getPatientInfo(id) {
    try {
        const res = await fetch(`${patientApiUrl}/${id}`, { headers: auth.headers() });
        if (!res.ok) throw new Error("Erro ao buscar paciente");
        return await res.json();
    } catch {
        return { name: "Desconhecido", cpf: "-" };
    }
}

// Carrega consultas do médico logado
export async function loadConsultations(page = 0) {
    currentPage = page;
    const tableBody = document.getElementById("consultationsTable");
    const paginationDiv = document.getElementById("pagination");
    tableBody.innerHTML = `<tr><td colspan="5" class="text-center">Carregando...</td></tr>`;

    try {
        const medic = await getLoggedMedic();
        document.getElementById("doctorInfo").textContent = `Dr. ${medic.name} (CRM ${medic.crm})`;

        const res = await fetch(`${apiUrl}/medic/${medic.id}?page=${page}&size=${pageSize}`, { headers: auth.headers() });
        if (!res.ok) throw new Error("Erro ao carregar consultas");
        const data = await res.json();
        const consultations = data.content;

        if (!consultations || consultations.length === 0) {
            tableBody.innerHTML = `<tr><td colspan="5" class="text-center">Nenhuma consulta encontrada.</td></tr>`;
            paginationDiv.innerHTML = "";
            return;
        }

        // Busca dados dos pacientes em paralelo
        const consultationsWithPatients = await Promise.all(
            consultations.map(async c => {
                const patient = await getPatientInfo(c.patientId);
                return { ...c, patientName: patient.name, patientCPF: patient.cpf };
            })
        );

        tableBody.innerHTML = consultationsWithPatients.map(c => `
            <tr>
                <td>${c.patientName}</td>
                <td>${c.patientCPF}</td>
                <td>${new Date(c.date).toLocaleString()}</td>
                <td><span class="badge ${c.status === 'CONCLUIDA' ? 'bg-success' : 'bg-warning'}">${c.status}</span></td>
                <td>
                  <a href="medicNotes.html?consultationId=${c.id}" class="btn btn-sm ${c.status === 'CONCLUIDA' ? 'btn-secondary disabled' : 'btn-primary'}">
                    ${c.status === 'CONCLUIDA' ? 'Finalizada' : 'Abrir'}
                  </a>
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

// Event listeners
document.getElementById("refreshList")?.addEventListener("click", () => loadConsultations(currentPage));
document.getElementById("list-tab")?.addEventListener("shown.bs.tab", () => loadConsultations(0));

// Inicializa tabela
loadConsultations(0);