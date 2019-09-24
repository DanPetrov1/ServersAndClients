package first;

import java.rmi.Remote;
import java.rmi.RemoteException;

interface Factorial extends Remote {
    int factorial(int number) throws RemoteException;
}
