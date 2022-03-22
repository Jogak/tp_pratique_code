package org.sam.mines.examples.patterns;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

interface RequestHandler {
    String handle(String request);

    default RequestHandler append(RequestHandler requestHandler) {
        return request -> handle(request) + "," + requestHandler.handle(request);
    }
}

public class ChainOfResponsibilityTest {

    @Test
    void shouldChainRequest() {
        RequestHandler requestHandler =
                ((RequestHandler) request1 -> "First " + request1)
                        .append(request -> "Second " + request)
                        .append(request -> "Third " + request)
                        .append(request -> {
                            // The request can be handled conditionally
                            if (request.equals("request")) {
                                return "Fourth " + request;
                            } else {
                                throw new IllegalArgumentException();
                            }
                        });

        assertEquals("First request,Second request,Third request,Fourth request", requestHandler.handle("request"));

        assertThrows(IllegalArgumentException.class, () -> requestHandler.handle("otherRequest"));
    }
}
