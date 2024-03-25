package ru.hse.api.dto;


import java.time.LocalDate;


public record Status(LocalDate eventDate, String status) {
}

