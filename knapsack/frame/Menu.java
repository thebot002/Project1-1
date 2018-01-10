import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;

public class Menu extends JPanel {

	public Menu() {
		setBackground(SystemColor.menu);
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 29, 0, 0, 0, 0, 0, 0, 17, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JButton btnFillTruck = new JButton("Fill Truck");
		GridBagConstraints gbc_btnFillTruck = new GridBagConstraints();
		gbc_btnFillTruck.gridwidth = 2;
		gbc_btnFillTruck.insets = new Insets(0, 0, 5, 5);
		gbc_btnFillTruck.gridx = 1;
		gbc_btnFillTruck.gridy = 1;
		add(btnFillTruck, gbc_btnFillTruck);

		JButton btnClearTruck = new JButton("Clear Truck");
		GridBagConstraints gbc_btnClearTruck = new GridBagConstraints();
		gbc_btnClearTruck.gridwidth = 2;
		gbc_btnClearTruck.insets = new Insets(0, 0, 5, 5);
		gbc_btnClearTruck.gridx = 3;
		gbc_btnClearTruck.gridy = 1;
		add(btnClearTruck, gbc_btnClearTruck);

		JButton btnresetCamera = new JButton("Reset Camera");
		GridBagConstraints gbc_btnresetCamera = new GridBagConstraints();
		gbc_btnresetCamera.gridwidth = 2;
		gbc_btnresetCamera.insets = new Insets(0, 0, 5, 5);
		gbc_btnresetCamera.gridx = 5;
		gbc_btnresetCamera.gridy = 1;
		add(btnresetCamera, gbc_btnresetCamera);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.gridheight = 7;
		gbc_tabbedPane.gridwidth = 6;
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 1;
		gbc_tabbedPane.gridy = 3;
		add(tabbedPane, gbc_tabbedPane);

		BaseTabbedPane rectangleTab = new BaseTabbedPane("A", "B", "C", "Dimensions", "1.0 x 1.0 x 2.0", "1.0 x 1.5 x 2.0", "1.5 x 1.5 x 1.5");
		rectangleTab.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		rectangleTab.setBackground(SystemColor.menu);
		BaseTabbedPane pentominoTab = new BaseTabbedPane("L", "P", "T", "", "", "", "");
		tabbedPane.addTab("Rectangle", rectangleTab);
		tabbedPane.addTab("Pentomino", pentominoTab);

		JLabel lblGapsFound = new JLabel("Gaps Found");
		GridBagConstraints gbc_lblGapsFound = new GridBagConstraints();
		gbc_lblGapsFound.gridwidth = 2;
		gbc_lblGapsFound.insets = new Insets(0, 0, 5, 5);
		gbc_lblGapsFound.gridx = 2;
		gbc_lblGapsFound.gridy = 11;
		add(lblGapsFound, gbc_lblGapsFound);

		JLabel lblCurrentValue = new JLabel("Current Value");
		GridBagConstraints gbc_lblCurrentValue = new GridBagConstraints();
		gbc_lblCurrentValue.gridwidth = 2;
		gbc_lblCurrentValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentValue.gridx = 4;
		gbc_lblCurrentValue.gridy = 11;
		add(lblCurrentValue, gbc_lblCurrentValue);

		JLabel gapsFound = new JLabel("---");
		GridBagConstraints gbc_gapsFound = new GridBagConstraints();
		gbc_gapsFound.gridwidth = 2;
		gbc_gapsFound.insets = new Insets(0, 0, 5, 5);
		gbc_gapsFound.gridx = 2;
		gbc_gapsFound.gridy = 12;
		add(gapsFound, gbc_gapsFound);

		JLabel currentValue = new JLabel("---");
		GridBagConstraints gbc_currentValue = new GridBagConstraints();
		gbc_currentValue.gridwidth = 2;
		gbc_currentValue.insets = new Insets(0, 0, 5, 5);
		gbc_currentValue.gridx = 4;
		gbc_currentValue.gridy = 12;
		add(currentValue, gbc_currentValue);
	}
}
