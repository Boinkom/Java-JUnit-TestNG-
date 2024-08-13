package org.example;

public class Calculator {
    public int calculate(Values values) {
        if (values == null || values.getSize() == null || values.getFragility() == null || values.getDistance() == null || values.getWorkload() == null) {
            throw new RuntimeException("Input parameters cannot be null");
        }

        int price = 0;
        String size = values.getSize();
        String fragility = values.getFragility();
        Integer distance = values.getDistance();
        Integer workload = values.getWorkload();

        if (size.equals("big")) {
            price += 200;
        } else if (size.equals("small")) { // Исправление: возможно, имелось в виду "small"
            price += 100;
        } else {
            throw new RuntimeException("Invalid size specified");
        }

        if (fragility.equals("yes")) {
            if (distance > 30) {
                throw new RuntimeException("Fragile goods cannot be transported over 30 km");
            }
            price += 300;
        }

        if (distance > 30) {
            price += 300;
        } else if (distance > 10) {
            price += 200;
        } else if (distance > 2) {
            price += 100;
        } else {
            price += 50;
        }

        switch (workload) {
            case 1:
                price *= 1;
                break;
            case 2:
                price *= 1.2;
                break;
            case 3:
                price *= 1.4;
                break;
            case 4:
                price *= 1.6;
                break;
            default:
                throw new RuntimeException("Invalid workload value");
        }

        if (price < 400) {
            price = 400;
        }

        return price;
    }
}
