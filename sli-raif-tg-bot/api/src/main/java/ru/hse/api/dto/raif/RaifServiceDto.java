package ru.hse.api.dto.raif;


import java.util.List;

import ru.hse.api.dto.StatusDto;

public record RaifServiceDto(
        String externalId,
        String name,
        List<StatusDto> statuses
) {
}
