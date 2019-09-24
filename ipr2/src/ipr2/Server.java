package ipr2;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.TreeMap;

public class Server implements RomanNumerals {
    private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();

    static {
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    }

    public static void main(String[] args) {
        try {
            Server obj = new Server();
            RomanNumerals stub = (RomanNumerals) UnicastRemoteObject.exportObject(obj, 2099);

            Registry registry = LocateRegistry.createRegistry(2099);
            registry.bind("RomanNumerals", stub);

            System.err.println("Server is ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
        }
    }

    @Override
    public String toRoman(int number) {
        int l =  map.floorKey(number);
        if ( number == l ) {
            return map.get(number);
        }
        return map.get(l) + toRoman(number-l);
    }
}
