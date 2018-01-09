package knapsack.frame;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ShapeColors extends ArrayList<Color> {
	public ShapeColors() {
		add(new Color(255, 102, 255)); //pink
		add(new Color(0  , 255, 255)); //cyan
		add(Color.BLUE);
		add(new Color(255, 79 , 0  )); //orange
		add(new Color(218, 165, 32)); //goldenrod
		add(new Color(0  , 204, 0  )); //free speech green
		add(new Color(102, 255, 102)); //lime
		add(Color.RED);
		add(new Color(255, 102, 102)); //bittersweet
		add(new Color(137, 107, 255)); //light slate blue
		add(new Color(236, 209, 141 )); //double colonial white
		add(new Color(0  , 102, 0  )); //green
		add(new Color(153, 0  , 153)); //dark magenta
		add(new Color(127, 35, 17  )); //falu red
		add(Color.YELLOW);
		add(new Color(255, 255, 153)); //canary
		add(new Color(128, 128, 128)); //gray
	}

	public Color getRand() {
		return get(ThreadLocalRandom.current().nextInt(0, size()));

	}
}
