package teine;

public class Functions {
	/** Skeleton function for displaying the blocks
	 * Input formated as string, eg. 'A,2;B,3;A,-;C,4;D,5;B,-;E,15'
	 * Output formated as blocks that should be displayed in a 2D array
	 * On full memory return inner list as null, see GUI:184
	 **/
	public static String[][] generateDisplay(String in){
		String[][] out = {{"A", "A"}, {"B", "B"}, {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "-", "A"}};
		return out;
	}

	public static int[] calculateFragmentation(String[] in){
		int[] out = {0,1};
		return out;
	}

}
