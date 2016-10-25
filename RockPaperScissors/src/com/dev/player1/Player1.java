package com.dev.player1;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

import com.dev.player2.PlayerChoice;
import com.dev.refree.Result;

public class Player1 {
	public static void main(String args[]) {
		try {
			int port = 16790;
			String host = "localhost";
			String registryURL = "rmi://" + host + ":" + port + "/hello";
			Result result = (Result) Naming.lookup(registryURL);
			PlayerChoice player = new PlayerChoice();
			result.registerPlayer(player);
			System.out.println("Registered Player 1.");

			int size = 1;
			Scanner keyboard = new Scanner(System.in);
			System.out.println("Enter the length of the tournament : ");
			int num = keyboard.nextInt();
			result.setTournamentLen(num);
			while (size <= num) {
				result.enterChoice("Player1", player.choice());
				size++;
			}

			Thread.sleep(10000);
			result.unRegisterPlayer(player);
			System.out.println("Unregistered Player 1.");
			keyboard.close();

		} catch (RemoteException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in Player1: " + e);
		}
	}
}
