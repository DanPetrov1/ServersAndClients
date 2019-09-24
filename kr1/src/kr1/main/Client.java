package kr1.main;

import kr1.NumberApp.*;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.util.Objects;

public class Client {

    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            String name = "Numbers";
            Numbers numbers = NumbersHelper.narrow(ncRef.resolve_str(name));

            int[] map = numbers != null ? numbers.numbersMap() : new int[10];
            for (int value : map) {
                System.out.println("Value: " + value);
            }
            Objects.requireNonNull(numbers).shutdown();
        } catch (Exception e) {
            System.out.println("ERROR : " + e);
        }
    }
}
