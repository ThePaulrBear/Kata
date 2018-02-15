package paul.wintz.kata4;


import static org.hamcrest.CoreMatchers.*;

import java.util.Optional;

import org.junit.Test;

import static org.junit.Assert.assertThat;

import paul.wintz.kata4.*;

public class ATeamDataParser {

	DataParser<TeamData> parser = new TeamDataParser();

	@Test
	public final void canParseAValidLine() {
		String line = " 1. Arsenal         38    26   9   3    79  -  36    87";

		Optional<TeamData> result = parser.parse(line);

		assertThat(result.get(), is(equalTo(new TeamData("Arsenal", 79, 36))));
	}

	@Test
	public final void canParseAnotherValidLine() {
		String line = "    2. Liverpool       38    24   8   6    67  -  30    80";

		Optional<TeamData> result = parser.parse(line);

		assertThat(result.get(), is(equalTo(new TeamData("Liverpool", 67, 30))));
	}



}
