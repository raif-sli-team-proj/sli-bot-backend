package ru.hse.api.dto;

import java.util.List;

public record ServiceDto(
        String serviceId,
        String serviceName,
        List<Status> statuses,
        Double sli,
        Status currentStatus
) {
}
