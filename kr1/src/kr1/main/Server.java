package kr1.main;

import kr1.NumberApp.*;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.util.Collection;
import java.util.HashMap;

class NumberImpl extends NumbersPOA {
    private ORB orb;
    private final int SIZE = 10;

    void setORB(ORB orb_val) {
        orb = orb_val;
    }

    @Override
    public int[] numbersMap() {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < SIZE; i++) {
            map.put(i, (int) (1 / Math.random()));
        }
        int[] values = new int[10];
        for(int i = 0; i < SIZE; i++) {
            values[i] = map.get(i);
        }
        return values;
    }

    @Override
    public void shutdown() {
        orb.shutdown(false);
    }
}

public class Server {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();
            NumberImpl filesImpl = new NumberImpl();
            filesImpl.setORB(orb);

            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(filesImpl);
            Numbers href = NumbersHelper.narrow(ref);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            String name = "Numbers";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, href);

            System.out.println("Server ready and waiting ...");

            orb.run();
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
        }

        System.out.println("Server Exiting ...");
    }
}
