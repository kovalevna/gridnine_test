package com.gridnine.testing;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void actions() {
        System.out.println("*************************");
        System.out.println("Choose number:");
        System.out.println("1 - show all transfers");
        System.out.println("2 - sort by number of transfers(ASC)");
        System.out.println("3 - sort by number of transfers(DESC)");
        System.out.println("4 - sort by departure date(ASC)");
        System.out.println("5 - sort by departure date(DESC)");
        System.out.println("6 - sort by arrival date(ASC)");
        System.out.println("7 - sort by arrival date(DESC)");
        System.out.println("or enter 'exit' ");
        System.out.println("*************************");
        System.out.print("Enter your number: ");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Flight> flights = new ArrayList<Flight>();
        flights.addAll(FlightBuilder.createFlights());
        System.out.println("Departure to the current point in time:");
        Filter.before_now(flights);
        Filter.all_transfers(flights);
        System.out.println("*************************");
        System.out.println("Segments with the arrival date earlier than the departure date:");
        Filter.arrival_earlier_departure(flights);
        Filter.all_transfers(flights);
        System.out.println("*************************");
        System.out.println("Less than 2 hours on the ground:");
        Filter.time_on_ground(flights);
        System.out.println("*************************");
        actions();
        String action = input.next();
        while (!action.equals("exit")) {
            switch (action) {
                case "1":
                    Filter.all_transfers(flights);
                    break;
                case "2":
                    Filter.number_transfers(flights, "ASC");
                    break;
                case "3":
                    Filter.number_transfers(flights, "DESC");
                    break;
                case "4":
                    Filter.departure_date(flights, "ASC");
                    break;
                case "5":
                    Filter.departure_date(flights,"DESC");
                    break;
                case "6":
                    Filter.arrival_date(flights, "ASC");
                    break;
                case "7":
                    Filter.arrival_date(flights, "DESC");
                    break;
                default:
                    System.out.println("Incorrect input");
                    break;
            }
            actions();
            action = input.next();
        }
    }
}
