package com.dev.refree;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlayerInterface extends Remote {

	String retrieveMessage(String message) throws RemoteException;

}
