package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1Exceptions.LeagueException;
import asgn1Exceptions.TeamException;
import asgn1SoccerCompetition.SoccerLeague;
import asgn1SoccerCompetition.SoccerTeam;

/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerLeage class
 *
 * @author Alan Woodley
 *
 */
public class SoccerLeagueTests {
	SoccerLeague Park;
	SoccerLeague Kim;

	SoccerTeam A;
	SoccerTeam B;
	SoccerTeam C;
	SoccerTeam D;
	SoccerTeam E;

	SoccerTeam AB;

	@Before
	public void testSoccerLeague() throws LeagueException {
		Park = new SoccerLeague(5);

		try {
			A = new SoccerTeam("A", "1");
		} catch (TeamException e) {
			e.printStackTrace();
		}
		try {
			B = new SoccerTeam("B", "2");
		} catch (TeamException e) {
			e.printStackTrace();
		}
		try {
			C = new SoccerTeam("C", "3");
		} catch (TeamException e) {
			e.printStackTrace();
		}
		try {
			D = new SoccerTeam("D", "4");
		} catch (TeamException e) {
			e.printStackTrace();
		}
		try {
			E = new SoccerTeam("E", "5");
		} catch (TeamException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInitial_value() {
		assertEquals(0, Park.getRegisteredNumTeams());
		assertEquals(5, Park.getRequiredNumTeams());
		assertEquals(true, Park.isOffSeason());
	}

	@Test
	public void testCheckRegisterTeam_number() throws LeagueException {
		Park.registerTeam(A);
		assertEquals(1, Park.getRegisteredNumTeams());
		Park.registerTeam(B);
		assertEquals(2, Park.getRegisteredNumTeams());
		Park.registerTeam(C);
		Park.registerTeam(D);
		Park.registerTeam(E);
		assertEquals(5, Park.getRegisteredNumTeams());
	}

	@Test
	public void testCheckRegisterTeam() throws LeagueException {
		// return true when the name is correct
		Park.registerTeam(A);
		assertEquals(true, Park.containsTeam("A"));
		Park.registerTeam(B);
		assertEquals(true, Park.containsTeam("B"));
		// return false when the name is wrong and input nick name
		Park.registerTeam(C);
		assertEquals(false, Park.containsTeam("c"));
		assertEquals(false, Park.containsTeam("3"));
		Park.registerTeam(D);
		assertEquals(false, Park.containsTeam("d"));
		assertEquals(false, Park.containsTeam("4"));

		// Check remove team
		Park.removeTeam(A);
		assertEquals(false, Park.containsTeam("A"));
		assertEquals(3, Park.getRegisteredNumTeams()); // 4 teams > 3 teams
		Park.removeTeam(B);
		assertEquals(false, Park.containsTeam("B"));
		Park.removeTeam(C);
		assertEquals(false, Park.containsTeam("C"));
		Park.removeTeam(D);
		assertEquals(false, Park.containsTeam("D"));
		assertEquals(0, Park.getRegisteredNumTeams()); // 3 teams > 0 team
	}

	@Test(expected = LeagueException.class)
	public void testCheckRegisterTeam_throw() throws LeagueException {
		// LeagueException
		Park.registerTeam(A);
		Park.registerTeam(B);
		Park.registerTeam(C);
		// if the season has not ended
		Park.removeTeam(D); // Not registered this team into league
		// if the team is not registered into the league.
		Park.startNewSeason();// offSeason = false -> not end game
		// if both teams have the same official name.

	}

	@Test
	public void testStartNewSeason_EndSeason() throws LeagueException {
		Park.registerTeam(A);
		Park.registerTeam(B);
		Park.registerTeam(C);
		Park.registerTeam(D);
		Park.registerTeam(E);
		// Start
		Park.startNewSeason();
		assertEquals(false, Park.isOffSeason());
		// End
		Park.endSeason();
		assertEquals(true, Park.isOffSeason());
	}

	@Test(expected = LeagueException.class)
	public void testCheckNew_EndSeason_throw() throws LeagueException {
		// if season has not started
		Park.endSeason(); // the season is not started
		assertEquals(true, Park.isOffSeason());
		// if the number of registered teams does not equal
		// the required number of teams
		Park.startNewSeason(); // No registered team
		assertEquals(false, Park.isOffSeason());
		// if the season has already started
		Park.startNewSeason(); // already stared the season

	}

	public void testgetTeamByOfficalName() throws LeagueException {
		Park.registerTeam(A);
		Park.registerTeam(B);
		Park.registerTeam(C);
		Park.registerTeam(D);
		Park.registerTeam(E);

		assertEquals(A, Park.getTeamByOfficalName("A"));
		assertEquals(B, Park.getTeamByOfficalName("B"));
		assertEquals(C, Park.getTeamByOfficalName("C"));
		assertEquals(D, Park.getTeamByOfficalName("D"));
		assertEquals(E, Park.getTeamByOfficalName("E"));
	}

	@Test(expected = LeagueException.class)
	public void testgetTeamByOfficalName_throw() throws LeagueException {
		// if no team has that official name.
		Park.registerTeam(A);
		Park.registerTeam(B);
		Park.registerTeam(C);
		// There is no ABC official name
		A = Park.getTeamByOfficalName("ABC");
		// if official name is blank
		B = Park.getTeamByOfficalName("");
		C = Park.getTeamByOfficalName("   ");
	}

	@Test
	public void testplayMatch() throws LeagueException {
		Park.registerTeam(A);
		Park.registerTeam(B);
		Park.registerTeam(C);
		Park.registerTeam(D);
		Park.registerTeam(E);
		Park.startNewSeason();

		Park.playMatch("A", 4, "B", 2); // A win
		assertEquals(3, Park.getTeamByOfficalName("A").getCompetitionPoints());
		assertEquals(4, Park.getTeamByOfficalName("A").getGoalsScoredSeason());
		Park.playMatch("B", 1, "C", 2); // B lose ,
		assertEquals(0, Park.getTeamByOfficalName("B").getCompetitionPoints());
		// the number of goals during the season is 3 / 2(the first game) + 1
		assertEquals(3, Park.getTeamByOfficalName("B").getGoalsScoredSeason());
		Park.playMatch("D", 2, "C", 2); // D draw
		assertEquals(1, Park.getTeamByOfficalName("D").getCompetitionPoints());
		assertEquals(2, Park.getTeamByOfficalName("D").getGoalsScoredSeason());
	}

	@Test(expected = LeagueException.class)
	public void testplayMatch_throw() throws LeagueException {
		Park.registerTeam(A);
		Park.registerTeam(B);
		Park.registerTeam(C);
		Park.registerTeam(D);
		Park.registerTeam(E);
		// offSeason is true -> not start
		Park.playMatch("A", 4, "B", 2);
		// start season
		Park.startNewSeason();
		// Same team with away team
		Park.playMatch("A", 4, "A", 2);
		// No team in home or away team
		Park.playMatch(" ", 4, "B", 2);
		Park.playMatch("A", 4, " ", 2);
		Park.playMatch(" ", 4, " ", 2);
	}

	@Test
	public void testgetTopTeam_BottomTeam() throws LeagueException {
		Park.registerTeam(A);
		Park.registerTeam(B);
		Park.registerTeam(C);
		Park.registerTeam(D);
		Park.registerTeam(E);
		Park.startNewSeason();

		Park.playMatch("A", 1, "B", 2); // B win / A lose
		Park.playMatch("C", 3, "D", 2); // C win / D lose
		Park.playMatch("B", 4, "D", 4); // B and D draw
		assertEquals(B, Park.getTopTeam());
		assertEquals(A, Park.getBottomTeam());
		Park.playMatch("E", 6, "D", 4); // E win / D lose
		Park.playMatch("A", 2, "E", 5); // E win / A lose
		assertEquals(E, Park.getTopTeam());
		assertEquals(A, Park.getBottomTeam());

		Park.sortTeams();
		Park.displayLeagueTable(); //show result of league	
	}

	@Test(expected = LeagueException.class)
	public void testgetTopTeam_BottomTeam_throw() throws LeagueException {
		// The size of Kim league is less than required number
		Kim = new SoccerLeague(3);

		try {
			AB = new SoccerTeam("AB", "11");
		} catch (TeamException e) {
			e.printStackTrace();
		}

		Kim.registerTeam(AB);
		Kim.registerTeam(AB);
		Kim.registerTeam(AB);

	}
}
