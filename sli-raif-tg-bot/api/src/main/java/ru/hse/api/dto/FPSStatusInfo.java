package ru.hse.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FPSStatusInfo {
    private String externalId;

    private String name;

    private String description;

    private List<Service> services;

    // неизвестно какого типа объект
    private Object sli;
}
