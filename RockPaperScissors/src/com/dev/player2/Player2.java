package com.dev.player2;
import java.rmi.Naming;
import java.rmi.RemoteException;

import com.dev.refree.Result;

public class Player2 {
	public static void main(String args[]) {
		try {
			int port = 16790;
			String host = "localhost";
			String registryURL = "rmi://" + host + ":" + port + "/hello";
			PlayerChoice player = new PlayerChoice();
			Result result = (Result) Naming.lookup(registryURL);
			result.registerPlayer(player);
			System.out.println("Registered Player 2.");
			int size = 1;
			while (size <= result.getTournamentLen()) {
				result.enterChoice("Player2", player.choice());
				size++;
			}
			Thread.sleep(10000);
			result.unRegisterPlayer(player);
			System.out.println("Unregistered Player 2.");
		} catch (RemoteException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in Player 2: " + e);
		}
	}
}
