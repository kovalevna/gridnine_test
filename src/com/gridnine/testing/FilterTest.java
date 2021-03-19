package com.gridnine.testing;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterTest {

    @Test
    public void before_now() {
        List<Flight> flights = new ArrayList<Flight>();
        flights.add(FlightBuilder.createFlight(LocalDateTime.now().plusDays(3), LocalDateTime.now()));
        flights.add(FlightBuilder.createFlight(LocalDateTime.now().plusDays(2), LocalDateTime.now()));
        flights.add(FlightBuilder.createFlight(LocalDateTime.now().minusDays(3), LocalDateTime.now()));
        List<Flight> expected = new ArrayList<Flight>();
        expected.add(FlightBuilder.createFlight(LocalDateTime.now().plusDays(3), LocalDateTime.now()));
        expected.add(FlightBuilder.createFlight(LocalDateTime.now().plusDays(2), LocalDateTime.now()));
        List<Flight> result = new ArrayList<>(Filter.before_now(flights));
        Assert.assertEquals(expected, result);
    }

    @Test
    public void arrival_earlier_departure() {
        List<Flight> flights = new ArrayList<Flight>();
        flights.add(FlightBuilder.createFlight(LocalDateTime.now().plusDays(3), LocalDateTime.now()));
        flights.add(FlightBuilder.createFlight(LocalDateTime.now().plusDays(2), LocalDateTime.now().minusDays(2)));
        List<Flight> expected = new ArrayList<Flight>();
        expected.add(FlightBuilder.createFlight(LocalDateTime.now().plusDays(3), LocalDateTime.now()));
        List<Flight> result = new ArrayList<>(Filter.arrival_earlier_departure(flights));
        Assert.assertEquals(expected, result);
    }
}