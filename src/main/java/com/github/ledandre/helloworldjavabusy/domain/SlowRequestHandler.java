package com.github.ledandre.helloworldjavabusy.domain;

import static java.util.Objects.isNull;

import java.time.Instant;

public class SlowRequestHandler {
    private static final long ONE_MINUTE_IN_SECONDS = 20;
    private static Instant lastRequestTime;
    private static SlowRequestHandler handler;

    private SlowRequestHandler() {}

    public static SlowRequestHandler getInstance() {
        if (isNull(handler)) {
            handler = new SlowRequestHandler();
        }

        return handler;
    }

    public void handle() {
        if (isNull(lastRequestTime)) {
            lastRequestTime = Instant.now();
            return;
        }

        processRequest();
    }

    private void processRequest() {
        long lastRequestTimeInSeconds = lastRequestTime.getEpochSecond();
        long nowInSeconds = Instant.now().getEpochSecond();
        long timeBetweenTwoLastCalls = nowInSeconds - lastRequestTimeInSeconds;

        if (timeBetweenTwoLastCalls < ONE_MINUTE_IN_SECONDS) {
            throw new RuntimeException(String.format("Servidor ocupado, porfavor aguarde %d segundos.", ONE_MINUTE_IN_SECONDS - timeBetweenTwoLastCalls));
        }
        lastRequestTime = Instant.now();
    }
}
