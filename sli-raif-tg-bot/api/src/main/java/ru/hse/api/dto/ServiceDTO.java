package ru.hse.api.dto;

import java.util.List;

public record ServiceDto(
        String serviceId,
        String serviceName,
        List<StatusDto> statuses,
        Double sli,
        StatusDto currentStatus
) {
}
