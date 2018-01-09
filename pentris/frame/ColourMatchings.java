package pentris.frame;

import java.awt.Color;
import java.util.HashMap;

public class ColourMatchings {
	private HashMap<String, Color> colorMap;

	public ColourMatchings() {
		HashMap<String, Color> aMap = new HashMap<>();
		aMap.put("I", Color.BLACK);
		aMap.put("P", Color.RED);
		aMap.put("T", Color.BLUE);
		aMap.put("F", Color.DARK_GRAY);
		aMap.put("U", Color.ORANGE);
		aMap.put("V", Color.YELLOW);
		aMap.put("W", Color.PINK);
		aMap.put("Z", Color.MAGENTA);
		aMap.put("L", Color.LIGHT_GRAY);
		aMap.put("N", Color.GREEN);
		aMap.put("X", Color.GRAY);
		aMap.put("Y", Color.CYAN);
		aMap.put("-",Color.WHITE);

		colorMap=aMap;
	}

	public Color getColor(String a) {
		return colorMap.get(a);
	}
}
