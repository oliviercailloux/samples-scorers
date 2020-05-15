package io.github.oliviercailloux.samples.scorers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MyScoreManagerTests {
	@Test
	void testStartsAtTen() throws Exception {
		final MyScoreManager manager = MyScoreManager.newInstance();
		assertEquals(10, manager.getCurrentScore());
	}

	@Test
	void testIncrement() throws Exception {
		final MyScoreManager manager = MyScoreManager.newInstance();
		final int startScore = manager.getCurrentScore();
		manager.incrementScore();
		assertEquals(startScore + 1, manager.getCurrentScore());
		manager.incrementScore();
		manager.incrementScore();
		assertEquals(startScore + 3, manager.getCurrentScore());
	}

	@Test
	void testMultiplier() throws Exception {
		final MyScoreManager manager = MyScoreManager.newInstance();
		final int startScore = manager.getCurrentScore();

		final ScoreKeeper keeperMultipliesByOne = manager.getScoreMultiplier();
		assertEquals(startScore, keeperMultipliesByOne.getCurrentScore());
		manager.incrementScore();
		assertEquals(startScore + 1, keeperMultipliesByOne.getCurrentScore());
		manager.incrementScore();
		assertEquals(startScore + 2, keeperMultipliesByOne.getCurrentScore());

		final ScoreKeeper keeperMultipliesByTwo = manager.getScoreMultiplier();
		assertEquals((startScore + 2) * 2, keeperMultipliesByTwo.getCurrentScore());
		manager.incrementScore();
		manager.incrementScore();
		manager.incrementScore();
		assertEquals((startScore + 5) * 2, keeperMultipliesByTwo.getCurrentScore());

		final ScoreKeeper keeperMultipliesByThree = manager.getScoreMultiplier();
		assertEquals((startScore + 5) * 3, keeperMultipliesByThree.getCurrentScore());
		manager.incrementScore();
		assertEquals((startScore + 6) * 3, keeperMultipliesByThree.getCurrentScore());
		manager.incrementScore();
		assertEquals((startScore + 7) * 3, keeperMultipliesByThree.getCurrentScore());
	}

	@Test
	void testListener() throws Exception {
		final MyScoreManager manager = MyScoreManager.newInstance();
		final ScoreBooleanListener listener = ScoreBooleanListener.newInstance();
		manager.addListener(listener);
		assertEquals(0, listener.getCountCalled());
		manager.incrementScore();
		assertEquals(1, listener.getCountCalled());
		manager.incrementScore();
		manager.incrementScore();
		assertEquals(3, listener.getCountCalled());

		final ScoreBooleanListener second = ScoreBooleanListener.newInstance();
		manager.addListener(second);
		assertEquals(3, listener.getCountCalled());
		assertEquals(0, second.getCountCalled());
		manager.incrementScore();
		manager.incrementScore();
		assertEquals(5, listener.getCountCalled());
		assertEquals(2, second.getCountCalled());
	}
}
