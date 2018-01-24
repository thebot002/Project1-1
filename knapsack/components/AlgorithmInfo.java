package knapsack.components;

/**
 * Class for defining objects that carry information the user inputted to the actual algorithms.
 */
public class AlgorithmInfo {
	private String[] settings;
	private int[][] parcels;
	private String type;
	
	public AlgorithmInfo(String[] settings, int[][] parcels, String type) {
		this.settings = settings;
		this.parcels = parcels;
		this.type = type;
	}
	
	public String[] getSettings() {
		return settings;
	}

	public void setSettings(String[] settings) {
		this.settings = settings;
	}

	public int[][] getParcels() {
		return parcels;
	}

	public void setParcels(int[][] parcels) {
		this.parcels = parcels;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
