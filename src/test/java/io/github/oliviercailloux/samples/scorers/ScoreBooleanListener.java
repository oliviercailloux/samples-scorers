package io.github.oliviercailloux.samples.scorers;

/**
 *
 * Just keeps an integer value that counts how often it has been called, for
 * test purposes.
 *
 */
public class ScoreBooleanListener implements ScoreListener {
	private int countCalled;

	public static ScoreBooleanListener newInstance() {
		return new ScoreBooleanListener();
	}

	private ScoreBooleanListener() {
		countCalled = 0;
	}

	@Override
	public void scoreIncremented() {
		++countCalled;
	}

	public int getCountCalled() {
		return countCalled;
	}
}
