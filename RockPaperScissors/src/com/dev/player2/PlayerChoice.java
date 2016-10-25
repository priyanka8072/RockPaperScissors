package com.dev.player2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import com.dev.refree.PlayerInterface;

public class PlayerChoice extends UnicastRemoteObject implements PlayerInterface {

	private static final long serialVersionUID = 1L;

	public PlayerChoice() throws RemoteException {
		super();
	}

	@Override
	public String retrieveMessage(String message) throws RemoteException {
		System.out.println(message);
		return message;
	}

	public String choice() {
		Scanner keyboard = new Scanner(System.in);
		String msg = keyboard.next();
		return msg;
	}

}
