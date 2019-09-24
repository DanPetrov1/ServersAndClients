package lab3;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import java.rmi.RemoteException;

public interface ConverterHome extends EJBHome {
    Converter create() throws RemoteException, CreateException;
}