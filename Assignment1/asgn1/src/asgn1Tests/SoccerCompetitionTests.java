package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1Exceptions.CompetitionException;
import asgn1Exceptions.LeagueException;
import asgn1Exceptions.TeamException;
import asgn1SoccerCompetition.SoccerCompetition;
import asgn1SoccerCompetition.SoccerLeague;
import asgn1SoccerCompetition.SoccerTeam;

/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerCompetition class
 *
 * @author Alan Woodley
 *
 */
public class SoccerCompetitionTests {
	SoccerCompetition Park;
	SoccerTeam[][] Teams;

	@Before
	public void Setup_game() throws CompetitionException, LeagueException, TeamException {
		Park = new SoccerCompetition("YEONGJE", 3, 3);

		Teams = new SoccerTeam[3][];
		for (int i = 0; i < 3; i++) {
			Teams[i] = new SoccerTeam[3];
		}
		// League 1
		Teams[0][0] = new SoccerTeam("SKT1", "Faker");
		Teams[0][1] = new SoccerTeam("Samsung", "Cuvee");
		Teams[0][2] = new SoccerTeam("Afreeca", "Marin");
		// League 2
		Teams[1][0] = new SoccerTeam("Rox", "Shy");
		Teams[1][1] = new SoccerTeam("MVP", "Score");
		Teams[1][2] = new SoccerTeam("Jinair", "Kuro");
		// League 3
		Teams[2][0] = new SoccerTeam("BBQ", "Chi");
		Teams[2][1] = new SoccerTeam("Longzu", "Bang");
		Teams[2][2] = new SoccerTeam("Kongdu", "Kong");

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Park.getLeague(i).registerTeam(Teams[i][j]);
			}
		}
	}

	@Test
	public void testGetLeague() throws CompetitionException {
		Park.getLeague(0);
		Park.getLeague(1);
		Park.getLeague(2);
	}

	@Test(expected = CompetitionException.class)
	public void TestGetLeague_throw() throws CompetitionException {
		Park.getLeague(-3); // negative
		Park.getLeague(5); // over the league number
		Park.getLeague(3); // same with the league number
	}

	@Test
	public void teststartSeason() throws CompetitionException {
		Park.startSeason();
		// check the number of registered teams
		assertEquals(3, Park.getLeague(0).getRegisteredNumTeams());
		assertEquals(3, Park.getLeague(1).getRegisteredNumTeams());
		assertEquals(3, Park.getLeague(2).getRegisteredNumTeams());
		// check the number of required teams
		assertEquals(3, Park.getLeague(0).getRequiredNumTeams());
		assertEquals(3, Park.getLeague(1).getRequiredNumTeams());
		assertEquals(3, Park.getLeague(2).getRequiredNumTeams());
		// check whether league is running or not
		assertEquals(false, Park.getLeague(0).isOffSeason());
		assertEquals(false, Park.getLeague(1).isOffSeason());
		assertEquals(false, Park.getLeague(2).isOffSeason());
	}

	@Test
	public void testendSeason() throws CompetitionException, LeagueException, TeamException {
		Park.startSeason();
		// Make example game
		SoccerLeague A = Park.getLeague(0);
		SoccerLeague B = Park.getLeague(1);
		SoccerLeague C = Park.getLeague(2);

		A.playMatch("SKT1", 5, "Samsung", 2);
		A.playMatch("Samsung", 1, "Afreeca", 4);
		A.playMatch("Afreeca", 0, "SKT1", 3);
		// A rank -> Top : SKT1 Bottom : Samsung
		B.playMatch("Rox", 4, "MVP", 1);
		B.playMatch("MVP", 2, "Jinair", 0);
		B.playMatch("Jinair", 3, "Rox", 3);
		// B rank -> Top : Rox Bottom : Jinair
		C.playMatch("BBQ", 2, "Longzu", 4);
		C.playMatch("Longzu", 0, "Kongdu", 2);
		C.playMatch("Kongdu", 3, "BBQ", 2);
		// C rank -> Top: Kongdu Bottom : BBQ

		Park.endSeason();
		// Check promotion and relegation occur
		assertEquals(false, Park.getLeague(0).containsTeam("Samsung"));
		assertEquals(true, Park.getLeague(1).containsTeam("Samsung"));
		assertEquals(true, Park.getLeague(0).containsTeam("Rox"));
		assertEquals(false, Park.getLeague(1).containsTeam("Rox"));
		assertEquals(false, Park.getLeague(1).containsTeam("Jinair"));
		assertEquals(true, Park.getLeague(2).containsTeam("Jinair"));
		assertEquals(false, Park.getLeague(2).containsTeam("Kongdu"));
		assertEquals(true, Park.getLeague(1).containsTeam("Kongdu"));
	}

}
