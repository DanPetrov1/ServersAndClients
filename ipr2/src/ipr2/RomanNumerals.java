package ipr2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RomanNumerals extends Remote {
    String toRoman(int number) throws RemoteException;
}
