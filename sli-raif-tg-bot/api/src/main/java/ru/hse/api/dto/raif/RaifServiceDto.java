package ru.hse.api.dto.raif;


import java.util.List;

import ru.hse.api.dto.Status;

public record RaifServiceDto(
        String externalId,
        String name,
        List<Status> statuses
) {
}
