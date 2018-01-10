package knapsack.frame;

import knapsack.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JSpinner;

public class BaseTabbedPane extends JPanel {

	public BaseTabbedPane(String firstRow, String secondRow, String thirdRow, String dim, String dimFirstRow, String dimSecondRow, String dimThirdRow ) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 61, 15, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JLabel lblAmount = new JLabel("Amount");
		GridBagConstraints gbc_lblAmount = new GridBagConstraints();
		gbc_lblAmount.insets = new Insets(0, 0, 5, 5);
		gbc_lblAmount.gridx = 2;
		gbc_lblAmount.gridy = 1;
		add(lblAmount, gbc_lblAmount);

		JLabel lblValue = new JLabel("Value");
		GridBagConstraints gbc_lblValue = new GridBagConstraints();
		gbc_lblValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblValue.gridx = 4;
		gbc_lblValue.gridy = 1;
		add(lblValue, gbc_lblValue);

		JLabel lblDimensions = new JLabel(dim);
		GridBagConstraints gbc_lblDimensions = new GridBagConstraints();
		gbc_lblDimensions.insets = new Insets(0, 0, 5, 5);
		gbc_lblDimensions.gridx = 6;
		gbc_lblDimensions.gridy = 1;
		add(lblDimensions, gbc_lblDimensions);

		JLabel lblFirstRow = new JLabel(firstRow);
		GridBagConstraints gbc_lblFirstRow = new GridBagConstraints();
		gbc_lblFirstRow.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstRow.gridx = 1;
		gbc_lblFirstRow.gridy = 2;
		add(lblFirstRow, gbc_lblFirstRow);

		JSpinner amount1 = new JSpinner();
		GridBagConstraints gbc_amount1 = new GridBagConstraints();
		gbc_amount1.fill = GridBagConstraints.HORIZONTAL;
		gbc_amount1.insets = new Insets(0, 0, 5, 5);
		gbc_amount1.gridx = 2;
		gbc_amount1.gridy = 2;
		add(amount1, gbc_amount1);

		JSpinner value1 = new JSpinner();
		GridBagConstraints gbc_value1 = new GridBagConstraints();
		gbc_value1.fill = GridBagConstraints.HORIZONTAL;
		gbc_value1.insets = new Insets(0, 0, 5, 5);
		gbc_value1.gridx = 4;
		gbc_value1.gridy = 2;
		add(value1, gbc_value1);

		JLabel lblDimFirstRow = new JLabel(dimFirstRow);
		GridBagConstraints gbc_lblDimFirstRow = new GridBagConstraints();
		gbc_lblDimFirstRow.insets = new Insets(0, 0, 5, 5);
		gbc_lblDimFirstRow.gridx = 6;
		gbc_lblDimFirstRow.gridy = 2;
		add(lblDimFirstRow, gbc_lblDimFirstRow);

		JLabel lblSecondRow = new JLabel(secondRow);
		GridBagConstraints gbc_lblSecondRow = new GridBagConstraints();
		gbc_lblSecondRow.insets = new Insets(0, 0, 5, 5);
		gbc_lblSecondRow.gridx = 1;
		gbc_lblSecondRow.gridy = 4;
		add(lblSecondRow, gbc_lblSecondRow);

		JSpinner amount2 = new JSpinner();
		GridBagConstraints gbc_amount2 = new GridBagConstraints();
		gbc_amount2.fill = GridBagConstraints.HORIZONTAL;
		gbc_amount2.insets = new Insets(0, 0, 5, 5);
		gbc_amount2.gridx = 2;
		gbc_amount2.gridy = 4;
		add(amount2, gbc_amount2);

		JSpinner value2 = new JSpinner();
		GridBagConstraints gbc_value2 = new GridBagConstraints();
		gbc_value2.fill = GridBagConstraints.HORIZONTAL;
		gbc_value2.insets = new Insets(0, 0, 5, 5);
		gbc_value2.gridx = 4;
		gbc_value2.gridy = 4;
		add(value2, gbc_value2);

		JLabel lblDimSecondRow = new JLabel(dimSecondRow);
		GridBagConstraints gbc_lblDimSecondRow = new GridBagConstraints();
		gbc_lblDimSecondRow.insets = new Insets(0, 0, 5, 5);
		gbc_lblDimSecondRow.gridx = 6;
		gbc_lblDimSecondRow.gridy = 4;
		add(lblDimSecondRow, gbc_lblDimSecondRow);

		JLabel lblThirdRow = new JLabel(thirdRow);
		GridBagConstraints gbc_lblThirdRow = new GridBagConstraints();
		gbc_lblThirdRow.insets = new Insets(0, 0, 5, 5);
		gbc_lblThirdRow.gridx = 1;
		gbc_lblThirdRow.gridy = 6;
		add(lblThirdRow, gbc_lblThirdRow);

		JSpinner amount3 = new JSpinner();
		GridBagConstraints gbc_amount3 = new GridBagConstraints();
		gbc_amount3.fill = GridBagConstraints.HORIZONTAL;
		gbc_amount3.insets = new Insets(0, 0, 5, 5);
		gbc_amount3.gridx = 2;
		gbc_amount3.gridy = 6;
		add(amount3, gbc_amount3);

		JSpinner value3 = new JSpinner();
		GridBagConstraints gbc_value3 = new GridBagConstraints();
		gbc_value3.fill = GridBagConstraints.HORIZONTAL;
		gbc_value3.insets = new Insets(0, 0, 5, 5);
		gbc_value3.gridx = 4;
		gbc_value3.gridy = 6;
		add(value3, gbc_value3);

		JLabel lblDimThirdRow = new JLabel(dimThirdRow);
		GridBagConstraints gbc_lblDimThirdRow = new GridBagConstraints();
		gbc_lblDimThirdRow.insets = new Insets(0, 0, 5, 5);
		gbc_lblDimThirdRow.gridx = 6;
		gbc_lblDimThirdRow.gridy = 6;
		add(lblDimThirdRow, gbc_lblDimThirdRow);
	}
}
