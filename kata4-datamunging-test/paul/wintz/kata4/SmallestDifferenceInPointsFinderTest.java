package paul.wintz.kata4;


import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import static org.junit.Assert.assertThat;

import paul.wintz.kata4.*;

public class SmallestDifferenceInPointsFinderTest {

	SmallestDifferenceInPointsFinder finder = new SmallestDifferenceInPointsFinder();

	@Test(expected = IllegalStateException.class)
	public final void throwsIfReadResultWhenDoTeamsAdded() {
		finder.getExtremeObject();
	}

	@Test
	public void returnsOnlyTeamAdded() {
		finder.addDataObject(new TeamData("Galaxy", 45, 21));

		TeamData result = finder.getExtremeObject();

		assertThat(result, is(equalTo(new TeamData("Galaxy", 45, 21))));
	}

	@Test
	public void returnsTeamWithSmallestSpread() {
		finder.addDataObject(new TeamData("Nanotube", 45, 46));
		finder.addDataObject(new TeamData("Galaxy", 45, 21));

		TeamData result = finder.getExtremeObject();

		assertThat(result, is(equalTo(new TeamData("Nanotube", 45, 46))));
	}

}
