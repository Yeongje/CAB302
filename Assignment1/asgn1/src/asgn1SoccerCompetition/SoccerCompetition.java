/**
 * 
 */
package asgn1SoccerCompetition;

import java.util.ArrayList;

import asgn1Exceptions.CompetitionException;
import asgn1Exceptions.LeagueException;

/**
 * A class to model a soccer competition. The competition contains one or more
 * number of leagues, each of which contain a number of teams. Over the course a
 * season matches are played between teams in each league. At the end of the
 * season a premier (top ranked team) and wooden spooner (bottom ranked team)
 * are declared for each league. If there are multiple leagues then relegation
 * and promotions occur between the leagues.
 * 
 * @author Alan Woodley
 * @version 1.0
 *
 */
public class SoccerCompetition implements SportsCompetition {

	String name;

	int numLeagues;
	int numTeams;

	private ArrayList<SoccerLeague> competition;
	private ArrayList<SoccerTeam> upteam;
	private ArrayList<SoccerTeam> downteam;

	/**
	 * Creates the model for a new soccer competition with a specific name,
	 * number of leagues, and number of games to display to indicate the teams
	 * form.
	 * 
	 * @param name
	 *            The name of the competition.
	 * @param numLeagues
	 *            The number of leagues in the competition.
	 * @param numTeams
	 *            The number of teams in each league.
	 */
	public SoccerCompetition(String name, int numLeagues, int numTeams) {
		this.name = name;
		// TO DO Complete

		this.numLeagues = numLeagues;
		this.numTeams = numTeams;
		this.competition = new ArrayList<SoccerLeague>();

		for (int i = 0; i < numLeagues; i++) {
			competition.add(new SoccerLeague(numTeams));
		}
	}

	/**
	 * Retrieves a league with a specific number (indexed from 0). Returns an
	 * exception if the league number is invalid.
	 * 
	 * @param leagueNum
	 *            The number of the league to return.
	 * @return A league specified by leagueNum.
	 * @throws CompetitionException
	 *             if the specified league number is less than 0. or equal to or
	 *             greater than the number of leagues in the competition.
	 */
	public SoccerLeague getLeague(int leagueNum) throws CompetitionException {

		// TO DO

		if (leagueNum < 0 || leagueNum >= numLeagues) {
			throw new CompetitionException("The league number is not valiad");
		}

		return competition.get(leagueNum);
	}

	/**
	 * Starts a new soccer season for each league in the competition.
	 */
	public void startSeason() {

		// TO DO

		for (int i = 0; i < competition.size(); i++) {
			try {
				competition.get(i).startNewSeason();
			} catch (LeagueException e) {
				System.out.println(e.getMessage());
			}
		}

	}

	/**
	 * Ends the season of each of the leagues in the competition. If there is
	 * more than one league then it handles promotion and relegation between the
	 * leagues.
	 * 
	 */
	public void endSeason() {

		// TO DO

		upteam = new ArrayList<SoccerTeam>();
		downteam = new ArrayList<SoccerTeam>();

		SoccerTeam bottomteam = null; // the bottom team
		SoccerTeam topteam = null; // first place team

		for (int i = 0; i < competition.size(); i++) {
			try {
				competition.get(i).endSeason();
			} catch (LeagueException e) {
				System.out.println(e.getMessage());
			}
		}
		// At least two teams
		if (competition.size() > 1) { 

			for (int i = 0; i < competition.size(); i++) {

				try {
					topteam = competition.get(i).getTopTeam();
					bottomteam = competition.get(i).getBottomTeam();
				} catch (LeagueException e) {
					System.out.println(e.getMessage());
				}
				// In first league, do not need top team
				if (i >= 1) { 
					upteam.add(topteam);

					try {
						competition.get(i).removeTeam(topteam);
					} catch (LeagueException e) {
						System.out.println(e.getMessage());
					}
				}   // In last league, do not need bottom team
				if (i <= competition.size() - 2) { 			
					downteam.add(bottomteam);
					try {
						competition.get(i).removeTeam(bottomteam);
					} catch (LeagueException e) {
						System.out.println(e.getMessage());
					}
				}

			}   // From top team in the lower league will promote to other division
			for (int i = competition.size(); i >= 0; i--) {
				if (i < competition.size() - 1) { 
					try { // top team will promote to the higher league
						competition.get(i).registerTeam(upteam.get(i));
					} catch (LeagueException e) {
						System.out.println(e.getMessage());
					}
				}
			}    // From bottom team in first league will relegate to the other division
			for (int i = 0; i < competition.size(); i++) {
				if (i > 0) {
					try { // bottom team will relegate to the lower league
						competition.get(i).registerTeam(downteam.get(i - 1));
					} catch (LeagueException e) {
						System.out.println(e.getMessage());
					}
				}

			}

		}

	}

	/**
	 * For each league displays the competition standings.
	 */
	public void displayCompetitionStandings() {
		System.out.println("+++++" + this.name + "+++++");

		for (int i = 0; i < competition.size(); i += 1) {
			System.out.println("---- League" + (i + 1) + " ----");
			System.out.println("Official Name" + '\t' + "Nick Name" + '\t' + "Form" + '\t' + "Played" + '\t' + "Won"
					+ '\t' + "Lost" + '\t' + "Drawn" + '\t' + "For" + '\t' + "Against" + '\t' + "GlDiff" + '\t'
					+ "Points");
			competition.get(i).displayLeagueTable();

		}

		// TO DO (optional)
		// HINT The heading for each league is
	}

}
