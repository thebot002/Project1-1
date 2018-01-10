import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ModeSwitcher extends JPanel {

	public ModeSwitcher() {
		BaseTabbedPane rectangleTab = new BaseTabbedPane("A", "B", "C", "Dimensions", "1.0 x 1.0 x 2.0", "1.0 x 1.5 x 2.0", "1.5 x 1.5 x 1.5");
		BaseTabbedPane pentominoTab = new BaseTabbedPane("L", "P", "T", "", "", "", "");
		JTabbedPane modeSwitcher = new JTabbedPane();

		modeSwitcher.addTab("Rectangle", rectangleTab);
		modeSwitcher.addTab("Pentomino", pentominoTab);
	}
}
