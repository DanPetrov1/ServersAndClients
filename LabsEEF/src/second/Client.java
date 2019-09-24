package second;

import FilesApp.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

import java.io.IOException;
import java.util.Scanner;

public class Client {
    private static FilesApp.Files files;
    private static Scanner sc = new Scanner(System.in);

    private static void printMenu() {
        System.out.println("\n1) Connect files");
        System.out.println("2) Exit");
        System.out.print("Enter your choice: ");
    }

    private static void callTask(String taskNumber) throws IOException {

        if (taskNumber.equalsIgnoreCase("1")) {
            files.connectFiles();
            System.out.println("Files connected successfully!");
        } else {
            System.out.println("Incorrect task number: choose 1-2");
        }
    }

    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            String name = "Files";
            files = FilesHelper.narrow(ncRef.resolve_str(name));

            String choice = "";
            while (true) {
                printMenu();

                choice = sc.nextLine();
                if (choice.equals("2")) break;

                callTask(choice);
            }
            files.shutdown();
        } catch (Exception e) {
            System.out.println("ERROR : " + e);
        }
    }
}