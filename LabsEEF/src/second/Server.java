package second;

import FilesApp.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

import java.io.*;
import java.util.ArrayList;

class FilesImpl extends FilesPOA {
    private ORB orb;

    void setORB(ORB orb_val) {
        orb = orb_val;
    }

    @Override
    public void connectFiles() throws IOException {
        File firstFile = new File("D://1.txt");
        File secondFile = new File("D://2.txt");
        File resultFile = new File("D://result.txt");
        ArrayList<String> arrayList = new ArrayList<>();
        String string;
        BufferedReader in = new BufferedReader(new FileReader(firstFile.getAbsolutePath()));
        while ((string = in.readLine()) != null) {
            arrayList.add(string);
        }
        in.close();
        in = new BufferedReader(new FileReader(secondFile.getAbsolutePath()));
        while ((string = in.readLine()) != null) {
            arrayList.add(string);
        }
        in.close();
        BufferedWriter out = new BufferedWriter(new FileWriter(resultFile.getAbsolutePath()));
        for (String s : arrayList) {
            out.write(s + "\n");
        }
        out.close();
    }

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
            FilesImpl filesImpl = new FilesImpl();
            filesImpl.setORB(orb);

            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(filesImpl);
            Files href = FilesHelper.narrow(ref);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            String name = "Files";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, href);

            System.out.println("second.Server ready and waiting ...");

            orb.run();
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
        }

        System.out.println("second.Server Exiting ...");
    }
}

