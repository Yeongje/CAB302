package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1Exceptions.TeamException;
import asgn1SoccerCompetition.SoccerTeam;



/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerLeage class
 *
 * @author Alan Woodley
 *
 */
public class SoccerTeamTests {

	SoccerTeam Park;
	SoccerTeam Kim;
	SoccerTeam Jeong;
	SoccerTeam Jo;
	SoccerTeam Lee;
	SoccerTeam Busan;
	SoccerTeam Seoul;
	
	@Before
	public void testSoccerTeam() throws TeamException {
		Park = new SoccerTeam("Park", "yeong");
		Kim = new SoccerTeam("Kim", "Je");
		Jeong = new SoccerTeam("Jeong", "sun");
		Jo = new SoccerTeam("Jo", "gi");
		Lee = new SoccerTeam("Lee", "jin");
		Busan = new SoccerTeam("Busan", "saha");
		Seoul = new SoccerTeam("Seoul", "gangnam");
	}
	
	@Test
	public void testOfficial_Nick_Name() throws TeamException {
		Park = new SoccerTeam("Park", "yeong");
		assertEquals("Park", Park.getOfficialName());
		assertEquals("yeong", Park.getNickName());
	}
	
	@Test
	public void testGetOfficia_Nick_lName() { 
		assertEquals("Park", Park.getOfficialName());
		assertEquals("yeong", Park.getNickName());
	}
	
	@Test (expected=TeamException.class)
	public void testEmptyNames() throws TeamException {
		Park = new SoccerTeam("", "");
		Park = new SoccerTeam("yeong", "");
		Park = new SoccerTeam("", "yeong");
		Park = new SoccerTeam("   ", "   ");
		Park = new SoccerTeam("yeong", "  ");
		Park = new SoccerTeam("  ", "yeong");
	}
	
	@Test 
	public void testplaymatch_goals() throws TeamException {
		// Check play match
		Park.playMatch(2, 3);
		Park.playMatch(0, 0);
		Park.playMatch(4, 1);
		
		// Check the goals scored by the team and other teams against this team
		Kim.playMatch(3,2); 
		assertEquals(3, Kim.getGoalsScoredSeason());
		assertEquals(2, Kim.getGoalsConcededSeason());
		Kim.playMatch(5,2);
		assertEquals(8, Kim.getGoalsScoredSeason());
		assertEquals(4, Kim.getGoalsConcededSeason());
		
		// Check Win, Draw, and Loss 
		Jeong.playMatch(1, 0);
		assertEquals(1, Jeong.getMatchesWon());
		Jeong.playMatch(1, 1);
		assertEquals(1, Jeong.getMatchesDrawn());
		Jeong.playMatch(0, 1);
		assertEquals(1, Jeong.getMatchesLost());
		
		Jeong.playMatch(4, 2);
		Jeong.playMatch(6, 4);
		assertEquals(3, Jeong.getMatchesWon());
		Jeong.playMatch(3, 3);
		Jeong.playMatch(5, 5);
		Jeong.playMatch(1, 1);
		assertEquals(4, Jeong.getMatchesDrawn());
		Jeong.playMatch(0, 3);
		Jeong.playMatch(0, 7);
		Jeong.playMatch(1, 2);
		Jeong.playMatch(5, 8);
		assertEquals(5, Jeong.getMatchesLost());
		
		// Check the number competition points of the team so far this season
	    Jo.playMatch(3,1); // Win -> +3 point
	    assertEquals(3, Jo.getCompetitionPoints());
	    Jo.playMatch(0,0); // Draw -> +1 point
	    assertEquals(4, Jo.getCompetitionPoints());
	    Jo.playMatch(0,1); // Loss -> +0 point
	    assertEquals(4, Jo.getCompetitionPoints());
	    
	    // Check the goal difference
	    Lee.playMatch(2, 2);
		assertEquals(0, Lee.getGoalDifference());
		Lee.playMatch(2, 0);
		assertEquals(2, Lee.getGoalDifference());
		Lee.playMatch(0, 2);
		assertEquals(0, Lee.getGoalDifference());
		
		Lee.playMatch(3, 2);
		Lee.playMatch(1, 2);
		Lee.playMatch(5, 2);
		assertEquals(3, Lee.getGoalDifference());
		
		
		Busan.playMatch(3, 1);
		Seoul.playMatch(2, 4);
		assertEquals(-3, Busan.compareTo(Seoul));

	}
	@Test
	public void testcompare_different_competitionPoints() throws TeamException {
		// Check the point by comparing with other team
		Lee.playMatch(10, 1);
		assertEquals(0, Lee.compareTo(Lee)); // compare with same team -> retun 0
		
		// compare win_lose
		Busan.playMatch(5, 1);
		Seoul.playMatch(1, 2);
		assertEquals(-3, Busan.compareTo(Seoul));
		// compare lose_win
		Park.playMatch(3, 5);
		Kim.playMatch(4, 2);
		assertEquals(3, Park.compareTo(Kim));
		// compare win_draw
		Jo.playMatch(4,2);
		Jeong.playMatch(3, 3);
		assertEquals(-2, Jo.compareTo(Jeong));
	}

	@Test
	public void testcompare_same_competitionPoints() throws TeamException {
		// win_win -> compare goal difference  
		Busan.playMatch(6, 3); // 3 
		Seoul.playMatch(4, 2); // 2
		assertEquals(-1, Busan.compareTo(Seoul)); //2-3
		//lose_lose -> compare goal difference
		Park.playMatch(4, 7); // -3
		Kim.playMatch(0, 2);  // -2
		assertEquals(1, Park.compareTo(Kim)); // -2-(-3)
		// draw_draw -> same goal difference
		Jo.playMatch(4,4);
		Jeong.playMatch(3, 3);
		assertEquals(10, Jo.compareTo(Jeong));
	}
	
	
	@Test (expected=TeamException.class)
	public void testplaymatch_goals_throw() throws TeamException {
		// The Goal point is less than 0 or greater than 20
		Park.playMatch(2, -3);  
		Park.playMatch(-2, 3);  
		Park.playMatch(-2, -3);  	
		Park.playMatch(21, 3);  
		Park.playMatch(2, 21);  
		Park.playMatch(21, 23); 
	}
	
	
}
