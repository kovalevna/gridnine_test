package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.*;
import static java.time.temporal.ChronoUnit.HOURS;


class Filter {
    //departure to the current point in time
    public static List<Flight> before_now(List<Flight> flights) {
        flights.removeIf(flight -> flight.getSegments().get(0).getDepartureDate().isBefore(LocalDateTime.now()));
        return flights;
    }
    //there are segments with the arrival date earlier than the departure date
    public static List<Flight> arrival_earlier_departure(List<Flight> flights) {
        for (int i = 0; i < flights.size(); i++) {
            boolean flag = false;
            List<Segment> segments = flights.get(i).getSegments();
            for (Segment value : segments) {
                if (value.getArrivalDate().isBefore(value.getDepartureDate())) {
                    flag = true;
                }
            }
            if (flag) {
                flights.remove(i);
            }
        }
        return flights;
    }
    //time on the ground more than 2 hours
    public static void time_on_ground(List<Flight> flights) {
        for (Flight item: flights) {
            long time = 0;
            List<Segment> segments = item.getSegments();
            for (int i = 0; i < segments.size() - 1; i++) {
                time += HOURS.between(segments.get(i).getArrivalDate(), segments.get(i+1).getDepartureDate());
            }
            if (time <= 2) {
                //flights.remove(item);
                System.out.println(item);
            }
        }
    }
    //input data processing
    public static void input_processing(List<Flight> flights) {
        for (Flight item: flights) {
            boolean flag = true;
            List<Segment> segments = item.getSegments();
            for (Segment value : segments) {
                if (value.getArrivalDate().isBefore(value.getDepartureDate())) {
                    flag = false;
                }
            }
            if (flag && !item.getSegments().get(0).getDepartureDate().isBefore(LocalDateTime.now())) {
                System.out.println(item);
            }
        }
    }
    //show all transfers
    public static void all_transfers(List<Flight> flights) {
        for (Flight item : flights) {
            System.out.println(item);
        }
        //input_processing(flights);
    }
    //sorting by number of transfers
    public static void number_transfers(List<Flight> flights, String sort) {
        flights.sort(new Flight.TransferComporator());
        if (sort.equals("DESC")) {
            Collections.reverse(flights);
        }
        input_processing(flights);
    }
    //sorting by departure date
    public static void departure_date(List<Flight> flights, String sort) {
        flights.sort(new Flight.DepartureDateComporator());
        if (sort.equals("DESC")) {
            Collections.reverse(flights);
        }
        input_processing(flights);
    }
    //sorting by arrival date
    public static void arrival_date(List<Flight> flights, String sort) {
        flights.sort(new Flight.ArrivalDateComporator());
        if (sort.equals("DESC")) {
            Collections.reverse(flights);
        }
        input_processing(flights);
    }
}

