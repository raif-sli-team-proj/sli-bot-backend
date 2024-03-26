package ru.hse.api.dto;

import java.util.List;

public record ServiceDTO(
        String serviceId,
        String serviceName,
        List<StatusDto> statuses,
        Double sli,
        StatusDto currentStatus
) {
}
