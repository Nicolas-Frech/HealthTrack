export function getBadgeClass(status) {
    switch (status) {
        case "SCHEDULED":
            return "bg-warning";
        case "CONCLUIDA":
            return "bg-success";
        case "CANCELED":
            return "bg-danger";
        default:
            return "bg-secondary";
    }
}

export function translateStatus(status) {
    switch (status) {
        case "SCHEDULED":
            return "AGENDADA";
        case "CONCLUIDA":
            return "CONCLUÍDA";
        case "CANCELED":
            return "CANCELADA";
        case "NO_SHOW":
            return "PACIENTE FALTOU";
        default:
            return status;
    }
}