package ru.hse.api.dto.raif;

import java.util.List;

public record RaifProductDto(
        String externalId,
        String name,
        String description,
        List<RaifServiceDto> services
) {
}
