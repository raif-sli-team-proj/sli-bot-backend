package ru.hse.statistics.model;

import java.util.List;


public record ProductOnlineStatusesResponse(String externalId, List<RaifServiceStatus> services) {
}
