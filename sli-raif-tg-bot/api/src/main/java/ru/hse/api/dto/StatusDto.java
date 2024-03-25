package ru.hse.api.dto;


import java.time.LocalDate;


public record StatusDto(LocalDate eventDate, String status) {
}

