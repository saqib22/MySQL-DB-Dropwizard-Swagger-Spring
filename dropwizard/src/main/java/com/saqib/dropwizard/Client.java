package com.saqib.dropwizard;

import com.hashirahmad.spring.model.Person;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome!");
        programLoop: while (true) {
            try {
                System.out.println("\n1: Register\n2: Fetch\n3: Delete");
                System.out.println("Choose an option. Write anything else to quit.\n");
                int option = input.nextInt();
                input.nextLine();
                switch (option) {
                    case 1:
                        System.out.println(handleRegister());
                        break;
                    case 2:
                        System.out.println(handleGet());
                        break;
                    case 3:
                        System.out.println(handleDelete());
                        break;
                    default:
                        break programLoop;
                }
            }
            catch (InputMismatchException e) {
                break programLoop;
            }
        }
        input.close();
    }

    private static String handleDelete() {
        System.out.print("Enter id: ");
        long id = input.nextLong();
        input.nextLine();

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        Protocol protocol = new Protocol();
        String response = protocol.delete(id, username, password);
        return response;
    }

    private static Object handleGet() {
        System.out.print("Enter id: ");
        long id = input.nextLong();
        input.nextLine();

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        Protocol protocol = new Protocol();
        Object response = protocol.get(id, username, password);
        return response;
    }

    private static String handleRegister() {
        System.out.println("Please enter person data:\n");
        Person person = inputPersonInfo();
        Protocol protocol = new Protocol();
        String response = protocol.post(person);
        return response;
    }

    private static Person inputPersonInfo() {
        System.out.print("ID: ");
        long id = input.nextLong();
        input.nextLine();

        System.out.print("Username: ");
        String username = input.nextLine();

        System.out.print("Password: ");
        String password = input.nextLine();

        System.out.print("First name: ");
        String firstName = input.nextLine();

        System.out.print("Last name: ");
        String lastName = input.nextLine();

        System.out.print("Mobile: ");
        String mobile = input.nextLine();

        System.out.print("Access level: ");
        String accessLevel = input.nextLine();

        return new Person(id, firstName, lastName, mobile, username, password, accessLevel);
    }
}
