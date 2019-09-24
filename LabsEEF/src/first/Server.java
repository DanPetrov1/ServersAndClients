package first;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Factorial {

    public static void main(String[] args) {
        try {
            Server obj = new Server();
            Factorial stub = (Factorial) UnicastRemoteObject.exportObject(obj, 2099);

            Registry registry = LocateRegistry.createRegistry(2099);
            registry.bind("Factorial", stub);

            System.err.println("second.Server is ready");
        } catch (Exception e) {
            System.err.println("second.Server exception: " + e.toString());
        }
    }

    public int factorial(int number) {
        int result = 1;
        for (int i = number; i > 1; i--) {
            result *= i;
        }
        return result;
    }
}
