package com.dev.refree;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Referee {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		try {
			int port = 16790;
			String host = "localhost";
			ResultImpl result = new ResultImpl();
			String registryURL = "rmi://" + host + ":" + port + "/hello";
			LocateRegistry.createRegistry(port);
			Naming.rebind(registryURL, result);
			System.out.println("Hello Referee ready.");
			while (true) {
				if (result.flagP1 && result.flagP2)
					result.winner();
				Thread.sleep(5000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
