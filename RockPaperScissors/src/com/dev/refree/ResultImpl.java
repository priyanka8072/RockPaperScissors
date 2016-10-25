package com.dev.refree;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ResultImpl extends UnicastRemoteObject implements Result {

	private static final long serialVersionUID = 1L;
	private ArrayList<PlayerInterface> players;
	private int turns = 0, i = 1, player1Wins = 0, player2Wins = 0;
	String[] player1, player2;
	String p1Choice, p2Choice;
	boolean flagP1 = false, flagP2 = false;

	protected ResultImpl() throws RemoteException {
		super();
		players = new ArrayList<PlayerInterface>();
	}

	@Override
	public synchronized void registerPlayer(PlayerInterface player) throws RemoteException {
		if (players.contains(player))
			System.out.println("Already Registered!!");
		else
			this.players.add(player);
	}

	@Override
	public synchronized void unRegisterPlayer(PlayerInterface player) throws RemoteException {
		if (players.contains(player))
			this.players.remove(player);
		else
			System.out.println("Player was not resgistered!");
	}

	@Override
	public void setTournamentLen(int turns) throws RemoteException {
		this.turns = turns;
	}

	@Override
	public int getTournamentLen() throws RemoteException {
		return turns;
	}

	@Override
	public synchronized void enterChoice(String name, String choice) throws RemoteException, InterruptedException {

		if ("Player1".equalsIgnoreCase(name)) {
			p1Choice = choice;
			flagP1 = true;
		}
		if ("Player2".equalsIgnoreCase(name)) {
			p2Choice = choice;
			flagP2 = true;
		}
	}

	public String winner() throws RemoteException {

		String msg = "";

		System.out.println(p1Choice + " " + p2Choice);
		if (p1Choice.equalsIgnoreCase(p2Choice)) {
			msg = "It is a draw";
		} else if (p1Choice.equalsIgnoreCase("rock") && p2Choice.equalsIgnoreCase("scissors")) {
			player1Wins++;
			msg = "Player 1 wins!";
		} else if (p2Choice.equalsIgnoreCase("rock") && p1Choice.equalsIgnoreCase("scissors")) {
			player2Wins++;
			msg = "Player 2 wins!";

		} else if (p1Choice.equalsIgnoreCase("scissors") && p2Choice.equalsIgnoreCase("paper")) {
			player1Wins++;
			msg = "Player 1 wins!";

		} else if (p2Choice.equalsIgnoreCase("scissors") && p1Choice.equalsIgnoreCase("paper")) {
			player2Wins++;
			msg = "Player 2 wins!";

		} else if (p1Choice.equalsIgnoreCase("paper") && p2Choice.equalsIgnoreCase("rock")) {
			player1Wins++;
			msg = "Player 1 wins!";

		} else if (p2Choice.equalsIgnoreCase("paper") && p1Choice.equalsIgnoreCase("rock")) {
			player2Wins++;
			msg = "Player 2 wins!";
		}

		if (i == turns) {
			if (player1Wins > player2Wins)
				msg = "Player 1 wins the tournament.";
			else if (player2Wins > player1Wins)
				msg = "Player 2 wins the tournament.";
			else
				msg = "Tournament is a draw.";
		}
		broadcastResult(msg);

		System.out.println("Result is : " + msg);

		return msg;
	}

	public synchronized void broadcastResult(String msg) throws RemoteException {
		int index = 0;
		while (index < players.size()) {
			players.get(index).retrieveMessage("Result :" + msg);
			index++;
		}
		i++;
		flagP1 = false;
		flagP2 = false;
	}

}
