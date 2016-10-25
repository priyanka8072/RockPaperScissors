package com.dev.refree;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Result extends Remote {

	public void registerPlayer(PlayerInterface player) throws RemoteException;

	public void unRegisterPlayer(PlayerInterface player) throws RemoteException;

	public void enterChoice(String name, String choice) throws RemoteException, InterruptedException;

	public void setTournamentLen(int turn) throws RemoteException;

	public int getTournamentLen() throws RemoteException;
}
