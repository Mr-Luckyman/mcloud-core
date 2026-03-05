package ru.mentee.power;

public class Calculator {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Использование: java Calculator <число1> <операция> <число2>");
            System.out.println("Пример: java Calculator 10 + 5");
            return;
        }

        double num1 = Double.parseDouble(args[0]);
        String operation = args[1];
        double num2 = Double.parseDouble(args[2]);
        double result = 0;

        switch (operation) {
            case "+": result = num1 + num2; break;
            case "-": result = num1 - num2; break;
            case "*": result = num1 * num2; break;
            case "/": result = num1 / num2; break;
            default:
                System.out.println("Неизвестная операция: " + operation);
                return;
        }

        System.out.println("Результат: " + result);
    }
}