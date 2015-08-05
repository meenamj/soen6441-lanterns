package project.strategy;

import java.io.Serializable;

import project.Game;

public interface Strategy extends Serializable{
	public enum Name{
		START, MAINMENU, CHOOSE_LANTERN, MAKE_DEDICATION, SELECT_LAKE, SELECT_BOARD_POSITION, SELECT_LAKE_ROTATION
	}
	public int inputOption(int number_options, Strategy.Name status, Game game);
}
