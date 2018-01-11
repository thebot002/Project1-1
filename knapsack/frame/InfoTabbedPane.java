package knapsack.frame;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class InfoTabbedPane extends JPanel {

	public InfoTabbedPane() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblAngle = new JLabel("Angle:");
		GridBagConstraints gbc_lblAngle = new GridBagConstraints();
		gbc_lblAngle.insets = new Insets(0, 0, 5, 5);
		gbc_lblAngle.gridx = 1;
		gbc_lblAngle.gridy = 1;
		add(lblAngle, gbc_lblAngle);
		
		JLabel lblAngleDisplay = new JLabel("---");
		GridBagConstraints gbc_lblAngleDisplay = new GridBagConstraints();
		gbc_lblAngleDisplay.insets = new Insets(0, 0, 5, 0);
		gbc_lblAngleDisplay.gridx = 2;
		gbc_lblAngleDisplay.gridy = 1;
		add(lblAngleDisplay, gbc_lblAngleDisplay);
		
		JLabel lblElevation = new JLabel("Elevation:");
		GridBagConstraints gbc_lblElevation = new GridBagConstraints();
		gbc_lblElevation.insets = new Insets(0, 0, 0, 5);
		gbc_lblElevation.gridx = 1;
		gbc_lblElevation.gridy = 2;
		add(lblElevation, gbc_lblElevation);
		
		JLabel lblElevationDisplay = new JLabel("---");
		GridBagConstraints gbc_lblElevationDisplay = new GridBagConstraints();
		gbc_lblElevationDisplay.gridx = 2;
		gbc_lblElevationDisplay.gridy = 2;
		add(lblElevationDisplay, gbc_lblElevationDisplay);

	}

}
