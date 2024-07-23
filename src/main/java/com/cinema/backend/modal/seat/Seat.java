package com.cinema.backend.modal.seat;
import seatsio.SeatsioClient;
import seatsio.Region;
import seatsio.charts.Chart;
import seatsio.events.Event;
import seatsio.events.EventObjectInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Seat {
    private SeatsioClient client;
    private Chart chart;
    private Event event;

    public Seat() {
        try {
            client = new SeatsioClient(Region.OC, "b82a0d82-a8a4-4790-ade9-32a408c2de61");
//            client.events.book("5950c978-ab54-4149-a33b-d6aea15b9201", Arrays.asList("A-1", "A-2"));
            client.events.hold("5950c978-ab54-4149-a33b-d6aea15b9201", Arrays.asList("A-3", "A-5"), "wvXbB9MlHt");
//            client.events.book("5950c978-ab54-4149-a33b-d6aea15b9201", Arrays.asList("A-1", "A-2"), "unavailable");
//            client.events.release("5950c978-ab54-4149-a33b-d6aea15b9201", Arrays.asList("A-1", "A-2"));


//            Map<String, EventObjectInfo> eventObjectInfos = client.events.retrieveObjectInfos("5950c978-ab54-4149-a33b-d6aea15b9201", Arrays.asList("A-1", "A-2"));
//
//            System.out.println(eventObjectInfos.get("A-1").categoryLabel);
//            System.out.println(eventObjectInfos.get("A-1").categoryKey);
//            System.out.println(eventObjectInfos.get("A-1").status);
//
//            System.out.println(eventObjectInfos.get("A-2").categoryLabel);
//            System.out.println(eventObjectInfos.get("A-2").categoryKey);
//            System.out.println(eventObjectInfos.get("A-2").status);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception properly (logging, rethrowing, etc.)
        }
    }

    public static void main(String[] args) {
        new Seat();
    }
}
