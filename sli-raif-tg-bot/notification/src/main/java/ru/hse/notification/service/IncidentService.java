package ru.hse.notification.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.hse.core.telegram.TgBot;
import ru.hse.statistics.model.Status;
import ru.hse.statistics.service.StatusService;

@Service
@Slf4j
@RequiredArgsConstructor
public class IncidentService {

    private final StatusService statusService;
    private final TgBot bot;

    public void checkStatusesAndNotify() {
        Status status = statusService.getServiceStatus("QRC");
        if (status == Status.FAIL) {
            log.info("Service failed.");
            bot.sendMessage("493978992", "❌❌❌ QRC service failed.");
        }
        statusService.addStatus(status);
    }
}
