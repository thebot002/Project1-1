package knapsack.frame;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ShapeColors extends ArrayList<Color> {
	public static void main(String[] args){}
	public ShapeColors() {
		add(new Color(255, 102, 255));
		add(new Color(0  , 255, 255));
		add(Color.BLUE);
		add(new Color(255, 79 , 0  ));
		add(new Color(255, 178, 102));
		add(new Color(0  , 204, 0  ));
		add(new Color(102, 255, 102));
		add(Color.RED);
		add(new Color(255, 102, 102));
		add(new Color(137, 107, 255));
		add(new Color(218, 165, 32 ));
		add(new Color(0  , 102, 0  ));
		add(new Color(153, 0  , 153));
		add(new Color(127, 35, 17  ));
		add(Color.YELLOW);
		add(new Color(255, 255, 153));
		add(new Color(128, 128, 128)); 
	}

	public Color getRand() {
		return get(ThreadLocalRandom.current().nextInt(0, size()));
		
	}
}
	
