package project;

import java.util.ArrayList;

/**
 * 
 * @author Jide
 *@version 2.0
 */

public class UnfriendlyPlayer extends Player implements PlayerStrategy 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1750409507270062717L;

	public UnfriendlyPlayer(String name, ArrayList<LanternCard> lanternCards,
			ArrayList<LakeTile> lakeTiles,
			ArrayList<DedicationToken> dedicationTokens, int numberOfFavorTokens) 
	{
		super(name, lanternCards, lakeTiles, dedicationTokens, numberOfFavorTokens);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void exchangeLanternCard() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void makeDedication() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void placeLakeTile() 
	{
		// TODO Auto-generated method stub
		
	}



}
