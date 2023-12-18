package ru.hse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Service {
    private String externalId;

    private String name;

    private String description;

    private List<Status> statuses;

    // неизвестно какого типа объект
    private Object sli;
}
