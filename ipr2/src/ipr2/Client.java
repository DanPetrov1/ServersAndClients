package ipr2;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {

            Registry registry = LocateRegistry.getRegistry(2099);
            RomanNumerals stub = (RomanNumerals) registry.lookup("RomanNumerals");

            if (args.length == 1) {
                String result = stub.toRoman(Integer.valueOf(args[0]));
                System.out.println("The roman numeral of " + Integer.valueOf(args[0]) + " is " + result);
            } else {
                System.out.println("You must pass one argument!");
            }

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
        }
    }
}
