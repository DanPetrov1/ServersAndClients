package first;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public static void main(String[] args) {
        try {

            Registry registry = LocateRegistry.getRegistry(2099);
            Factorial stub = (Factorial) registry.lookup("Factorial");

            if (args.length == 1) {
                int result = stub.factorial(Integer.valueOf(args[0]));
                System.out.println("The factorial of " + Integer.valueOf(args[0]) + " is " + result);
            } else {
                System.out.println("You must pass one argument!");
            }

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("second.Client exception: " + e.toString());
        }
    }
}

