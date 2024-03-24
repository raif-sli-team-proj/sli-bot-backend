package ru.hse.statistics.model;

import java.util.List;

import ru.hse.statistics.enumeration.RaifProduct;

public record ProductOnlineStatusesResponse(RaifProduct externalId, List<RaifServiceStatus> services) {
}
