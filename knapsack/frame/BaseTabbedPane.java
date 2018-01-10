package knapsack.frame;

import knapsack.frame.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BaseTabbedPane extends JPanel {

	private JTextField amountFirstRow; // refers to amount textfields for A & L
	private JTextField amountSecondRow; // refers to amount textfields for B & P
	private JTextField amountThirdRow; // refers to amount textfields for C & T
	private JTextField valueFirstRow; // refers to value textfields for A & L
	private JTextField valueSecondRow; // refers to value textfields for B & P/
	private JTextField valueThirdRow; // refers to value textfields for C & T
	private JLabel lblDimFirstRow; //refers to dimensions JLabel for A
	private JLabel lblDimSecondRow; //refers to dimensions JLabel for B
	private JLabel lblDimThirdRow; //refers to dimensions JLabel for C

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

		amountFirstRow = new JTextField();
		GridBagConstraints gbc_amountFirstRow = new GridBagConstraints();
		gbc_amountFirstRow.fill = GridBagConstraints.HORIZONTAL;
		gbc_amountFirstRow.insets = new Insets(0, 0, 5, 5);
		gbc_amountFirstRow.gridx = 2;
		gbc_amountFirstRow.gridy = 2;
		add(amountFirstRow, gbc_amountFirstRow);
		amountFirstRow.setColumns(10);

		valueFirstRow = new JTextField();
		GridBagConstraints gbc_valueFirstRow = new GridBagConstraints();
		gbc_valueFirstRow.insets = new Insets(0, 0, 5, 5);
		gbc_valueFirstRow.fill = GridBagConstraints.HORIZONTAL;
		gbc_valueFirstRow.gridx = 4;
		gbc_valueFirstRow.gridy = 2;
		add(valueFirstRow, gbc_valueFirstRow);
		valueFirstRow.setColumns(10);

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

		amountSecondRow = new JTextField();
		GridBagConstraints gbc_amountSecondRow = new GridBagConstraints();
		gbc_amountSecondRow.fill = GridBagConstraints.HORIZONTAL;
		gbc_amountSecondRow.insets = new Insets(0, 0, 5, 5);
		gbc_amountSecondRow.gridx = 2;
		gbc_amountSecondRow.gridy = 4;
		add(amountSecondRow, gbc_amountSecondRow);
		amountSecondRow.setColumns(10);

		valueSecondRow = new JTextField();
		GridBagConstraints gbc_valueSecondRow = new GridBagConstraints();
		gbc_valueSecondRow.insets = new Insets(0, 0, 5, 5);
		gbc_valueSecondRow.fill = GridBagConstraints.HORIZONTAL;
		gbc_valueSecondRow.gridx = 4;
		gbc_valueSecondRow.gridy = 4;
		add(valueSecondRow, gbc_valueSecondRow);
		valueSecondRow.setColumns(10);

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

		amountThirdRow = new JTextField();
		GridBagConstraints gbc_amountThirdRow = new GridBagConstraints();
		gbc_amountThirdRow.fill = GridBagConstraints.HORIZONTAL;
		gbc_amountThirdRow.insets = new Insets(0, 0, 5, 5);
		gbc_amountThirdRow.gridx = 2;
		gbc_amountThirdRow.gridy = 6;
		add(amountThirdRow, gbc_amountThirdRow);
		amountThirdRow.setColumns(10);

		valueThirdRow = new JTextField();
		GridBagConstraints gbc_valueThirdRow = new GridBagConstraints();
		gbc_valueThirdRow.insets = new Insets(0, 0, 5, 5);
		gbc_valueThirdRow.fill = GridBagConstraints.HORIZONTAL;
		gbc_valueThirdRow.gridx = 4;
		gbc_valueThirdRow.gridy = 6;
		add(valueThirdRow, gbc_valueThirdRow);
		valueThirdRow.setColumns(10);

		JLabel lblDimThirdRow = new JLabel(dimThirdRow);
		GridBagConstraints gbc_lblDimThirdRow = new GridBagConstraints();
		gbc_lblDimThirdRow.insets = new Insets(0, 0, 5, 5);
		gbc_lblDimThirdRow.gridx = 6;
		gbc_lblDimThirdRow.gridy = 6;
		add(lblDimThirdRow, gbc_lblDimThirdRow);
	}
}
