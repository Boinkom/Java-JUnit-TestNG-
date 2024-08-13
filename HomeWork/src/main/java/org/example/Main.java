package org.example;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Values values = new Values(23, "big", "no", 3);
        System.out.println(calculator.calculate(values));
    }
}