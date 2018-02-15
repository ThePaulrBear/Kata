package paul.wintz.kata4;

public class SmallestDifferenceInPointsFinder extends ExtremeValueFinder<TeamData> {

	public SmallestDifferenceInPointsFinder() {
		super((canidate, current) -> canidate.pointSpread() < current.pointSpread());
	}
}
