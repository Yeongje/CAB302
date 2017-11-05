package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1SoccerCompetition.SportsTeamForm;
import asgn1SportsUtils.WLD;

/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerTeamForm class
 *
 * @author Alan Woodley
 *
 */
public class SportsTeamFormTests {

	 SportsTeamForm Park;
		
		@Before
		public void testSportsTeamForm() {
			 Park = new SportsTeamForm();
		}
		
		@Test
		public void testAddResultToForm_toString() {
            assertEquals("-----", Park.toString());
			// Win
			WLD result = WLD.WIN;
			Park.addResultToForm(result);
			assertEquals("W----", Park.toString());
			// Loss
			WLD result2 = WLD.LOSS;
			Park.addResultToForm(result2);
			assertEquals("LW---", Park.toString());
			//Draw
			WLD result3 = WLD.DRAW;
			Park.addResultToForm(result3);
			assertEquals("DLW--", Park.toString());
			// Add results of 2 wins			
			for(int i =0;i<2;i++){
				Park.addResultToForm(result);
			}
			assertEquals("WWDLW", Park.toString());
			// Add Loss and Draw			
			Park.addResultToForm(result2);
			Park.addResultToForm(result3);
			assertEquals("DLWWD", Park.toString());	
		}

		@Test
		public void testgetNumGames() {
			// Check the number of games
			WLD result = WLD.WIN;
			Park.addResultToForm(result);
			assertEquals(1, Park.getNumGames());
			
			Park.addResultToForm(result);
			Park.addResultToForm(result);
			assertEquals(3, Park.getNumGames());
			// 5 is limit of the number of games 
			for(int i =0;i<5;i++){
				Park.addResultToForm(result);
			}
			assertEquals(5, Park.getNumGames());
			
		}

		@Test
		public void testresetForm() {
			for(int i =0;i<5;i++){
				WLD result = WLD.WIN;
				Park.addResultToForm(result);
			}
			// Clear every results
			Park.resetForm();
			assertEquals(0, Park.getNumGames());
			assertEquals("-----", Park.toString());
			
		}
}
 
