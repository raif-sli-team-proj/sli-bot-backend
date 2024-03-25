package ru.hse.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hse.api.dto.raif.RaifServiceDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FPSStatusInfo {
    private String externalId;

    private String name;

    private String description;

    private List<RaifServiceDto> raifServices;

    // неизвестно какого типа объект
    private Object sli;
}
