package knapsack.frame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import java.text.ParseException;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;

public class SwitchTabbedPane extends JPanel {

	private JLabel optionlabel;
	private JLabel algoLabel;
	private JComboBox comboBoxAlgo;
	private JLabel lblAmnt;
	private JLabel lblVal;
	private JLabel lblDim;
	private JLabel lblFR;
	private JLabel lblSR;
	private JLabel lblTR;
	private JLabel lblDimFR;
	private JLabel lblDimSR;
	private JLabel lblDimTR;
	private JSpinner spnnrAmntDispFR;
	private JSpinner spnnrAmntDispSR;
	private JSpinner spnnrAmntDispTR;
	private JSpinner spnnrValDispFR;
	private JSpinner spnnrValDispSR;
	private JSpinner spnnrValDispTR;
	private JRadioButton rdbtnGreedy;
	private JRadioButton rdbtnBacktracking;
	private JRadioButton rdbtnSimulatedAnnealing;
	private JComboBox comboBoxOptions;
	private ButtonGroup algorithmSwitch;
	private String listOrder;
	
	public SwitchTabbedPane(String firstRow, String secondRow, String thirdRow, String dim, String dimFirstRow, String dimSecondRow, String dimThirdRow ) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 61, 15, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		lblAmnt = new JLabel("Amount");
		GridBagConstraints gbc_lblAmnt = new GridBagConstraints();
		gbc_lblAmnt.insets = new Insets(0, 0, 5, 5);
		gbc_lblAmnt.gridx = 2;
		gbc_lblAmnt.gridy = 1;
		add(lblAmnt, gbc_lblAmnt);

		lblVal = new JLabel("Value");
		GridBagConstraints gbc_lblVal = new GridBagConstraints();
		gbc_lblVal.insets = new Insets(0, 0, 5, 5);
		gbc_lblVal.gridx = 4;
		gbc_lblVal.gridy = 1;
		add(lblVal, gbc_lblVal);

		lblDim = new JLabel(dim);
		GridBagConstraints gbc_lblDim = new GridBagConstraints();
		gbc_lblDim.insets = new Insets(0, 0, 5, 5);
		gbc_lblDim.gridx = 6;
		gbc_lblDim.gridy = 1;
		//add(lblDim, gbc_lblDim);

		lblFR = new JLabel(firstRow);
		GridBagConstraints gbc_lblFR = new GridBagConstraints();
		gbc_lblFR.insets = new Insets(0, 0, 5, 5);
		gbc_lblFR.gridx = 1;
		gbc_lblFR.gridy = 2;
		add(lblFR, gbc_lblFR);

		spnnrAmntDispFR = new JSpinner();
		GridBagConstraints gbc_spnnrAmntDispFR = new GridBagConstraints();
		gbc_spnnrAmntDispFR.fill = GridBagConstraints.HORIZONTAL;
		gbc_spnnrAmntDispFR.insets = new Insets(0, 0, 5, 5);
		gbc_spnnrAmntDispFR.gridx = 2;
		gbc_spnnrAmntDispFR.gridy = 2;
		add(spnnrAmntDispFR, gbc_spnnrAmntDispFR);

		spnnrValDispFR = new JSpinner();
		spnnrValDispFR.setFocusable(false);
		GridBagConstraints gbc_spnnrValDispFR = new GridBagConstraints();
		gbc_spnnrValDispFR.fill = GridBagConstraints.HORIZONTAL;
		gbc_spnnrValDispFR.insets = new Insets(0, 0, 5, 5);
		gbc_spnnrValDispFR.gridx = 4;
		gbc_spnnrValDispFR.gridy = 2;
		add(spnnrValDispFR, gbc_spnnrValDispFR);

		lblDimFR = new JLabel(dimFirstRow);
		GridBagConstraints gbc_lblDimFR = new GridBagConstraints();
		gbc_lblDimFR.insets = new Insets(0, 0, 5, 5);
		gbc_lblDimFR.gridx = 6;
		gbc_lblDimFR.gridy = 2;
		//add(lblDimFR, gbc_lblDimFR);

		lblSR = new JLabel(secondRow);
		GridBagConstraints gbc_lblSR = new GridBagConstraints();
		gbc_lblSR.insets = new Insets(0, 0, 5, 5);
		gbc_lblSR.gridx = 1;
		gbc_lblSR.gridy = 4;
		add(lblSR, gbc_lblSR);

		spnnrAmntDispSR = new JSpinner();
		spnnrAmntDispSR.setFocusable(false);
		GridBagConstraints gbc_spnnrAmntDispSR = new GridBagConstraints();
		gbc_spnnrAmntDispSR.fill = GridBagConstraints.HORIZONTAL;
		gbc_spnnrAmntDispSR.insets = new Insets(0, 0, 5, 5);
		gbc_spnnrAmntDispSR.gridx = 2;
		gbc_spnnrAmntDispSR.gridy = 4;
		add(spnnrAmntDispSR, gbc_spnnrAmntDispSR);

		spnnrValDispSR = new JSpinner();
		spnnrValDispSR.setFocusable(false);
		GridBagConstraints gbc_spnnrValDispSR = new GridBagConstraints();
		gbc_spnnrValDispSR.fill = GridBagConstraints.HORIZONTAL;
		gbc_spnnrValDispSR.insets = new Insets(0, 0, 5, 5);
		gbc_spnnrValDispSR.gridx = 4;
		gbc_spnnrValDispSR.gridy = 4;
		add(spnnrValDispSR, gbc_spnnrValDispSR);

		lblDimSR = new JLabel(dimSecondRow);
		GridBagConstraints gbc_lblDimSR = new GridBagConstraints();
		gbc_lblDimSR.insets = new Insets(0, 0, 5, 5);
		gbc_lblDimSR.gridx = 6;
		gbc_lblDimSR.gridy = 4;
		//add(lblDimSR, gbc_lblDimSR);

		lblTR = new JLabel(thirdRow);
		GridBagConstraints gbc_lblTR = new GridBagConstraints();
		gbc_lblTR.insets = new Insets(0, 0, 5, 5);
		gbc_lblTR.gridx = 1;
		gbc_lblTR.gridy = 6;
		add(lblTR, gbc_lblTR);

		spnnrAmntDispTR = new JSpinner();
		spnnrAmntDispTR.setFocusable(false);
		GridBagConstraints gbc_spnnrAmntDispTR = new GridBagConstraints();
		gbc_spnnrAmntDispTR.fill = GridBagConstraints.HORIZONTAL;
		gbc_spnnrAmntDispTR.insets = new Insets(0, 0, 5, 5);
		gbc_spnnrAmntDispTR.gridx = 2;
		gbc_spnnrAmntDispTR.gridy = 6;
		add(spnnrAmntDispTR, gbc_spnnrAmntDispTR);

		spnnrValDispTR = new JSpinner();
		spnnrValDispTR.setFocusable(false);
		GridBagConstraints gbc_spnnrValDispTR = new GridBagConstraints();
		gbc_spnnrValDispTR.fill = GridBagConstraints.HORIZONTAL;
		gbc_spnnrValDispTR.insets = new Insets(0, 0, 5, 5);
		gbc_spnnrValDispTR.gridx = 4;
		gbc_spnnrValDispTR.gridy = 6;
		add(spnnrValDispTR, gbc_spnnrValDispTR);

		lblDimTR = new JLabel(dimThirdRow);
		GridBagConstraints gbc_lblDimTR = new GridBagConstraints();
		gbc_lblDimTR.insets = new Insets(0, 0, 5, 5);
		gbc_lblDimTR.gridx = 6;
		gbc_lblDimTR.gridy = 6;
		//add(lblDimTR, gbc_lblDimTR);
		
//		rdbtnGreedy = new JRadioButton("Greedy");
//		GridBagConstraints gbc_rdbtnGreedy = new GridBagConstraints();
//		gbc_rdbtnGreedy.insets = new Insets(0, 0, 5, 5);
//		gbc_rdbtnGreedy.gridx = 1;
//		gbc_rdbtnGreedy.gridy = 8;
//		rdbtnGreedy.setSelected(true);
//		rdbtnGreedy.setActionCommand("Greedy");
//		add(rdbtnGreedy, gbc_rdbtnGreedy);
//
//		rdbtnBacktracking = new JRadioButton("Backtracking");
//		GridBagConstraints gbc_rdbtnBacktracking = new GridBagConstraints();
//		gbc_rdbtnBacktracking.insets = new Insets(0, 0, 5, 5);
//		gbc_rdbtnBacktracking.gridx = 2;
//		gbc_rdbtnBacktracking.gridy = 8;
//		rdbtnBacktracking.setActionCommand("Backtracking");
//		add(rdbtnBacktracking, gbc_rdbtnBacktracking);
//
//		rdbtnSimulatedAnnealing = new JRadioButton("Simulated Annealing");
//		GridBagConstraints gbc_rdbtnSimulatedAnnealing = new GridBagConstraints();
//		gbc_rdbtnSimulatedAnnealing.insets = new Insets(0, 0, 5, 5);
//		gbc_rdbtnSimulatedAnnealing.gridx = 3;
//		gbc_rdbtnSimulatedAnnealing.gridy = 8;
//		rdbtnSimulatedAnnealing.setActionCommand("Simulated Annealing");
//		//add(rdbtnSimulatedAnnealing, gbc_rdbtnSimulatedAnnealing);
//
//		algorithmSwitch = new ButtonGroup();
//		algorithmSwitch.add(rdbtnGreedy);
//		algorithmSwitch.add(rdbtnBacktracking);
//		algorithmSwitch.add(rdbtnSimulatedAnnealing);
		
		//String[] options = {"Decreasing value/volume", "Decreasing value", "Decreasing volume", "Genetic Algorithm - Minimize Gaps", "Genetic Algorithm - Maximize Value"};
		String[] options = {"Decreasing value/volume", "Decreasing value", "Decreasing volume"};


		optionlabel = new JLabel("Fill Type: ");
		GridBagConstraints gbc_optionlabel = new GridBagConstraints();
		gbc_optionlabel.insets = new Insets(0, 0, 5, 5);
		gbc_optionlabel.gridx = 2;
		gbc_optionlabel.gridy = 9;
		add(optionlabel, gbc_optionlabel);
		
		comboBoxOptions = new JComboBox(options);
		GridBagConstraints gbc_comboBoxBacktrackingOptions = new GridBagConstraints();
		gbc_comboBoxBacktrackingOptions.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxBacktrackingOptions.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxBacktrackingOptions.gridx = 4;
		gbc_comboBoxBacktrackingOptions.gridy = 9;
		add(comboBoxOptions, gbc_comboBoxBacktrackingOptions);

		String[] algos = {"Backtracking", "Greedy", "Simulated Annealing"};

		algoLabel = new JLabel("Filling Algorithm: ");
		GridBagConstraints gbc_algoLabel = new GridBagConstraints();
		gbc_algoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_algoLabel.gridx = 2;
		gbc_algoLabel.gridy = 10;
		add(algoLabel, gbc_algoLabel);

		comboBoxAlgo = new JComboBox(algos);
		GridBagConstraints gbc_comboBoxAlgo = new GridBagConstraints();
		gbc_comboBoxAlgo.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxAlgo.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxAlgo.gridx = 4;
		gbc_comboBoxAlgo.gridy = 10;
		add(comboBoxAlgo, gbc_comboBoxAlgo);
	}
	/**
     * Method to get truck filling settings. First row represents algorithm (Greedy/Backtracking) and second how the list from which the algorithm picks shapes is determined
     * @return String array containing settings for how to fill the truck.
     */
	public String[] collectParcelSettings() {
		listOrder = (String)comboBoxOptions.getSelectedItem();
		String[] settings = {comboBoxAlgo.getSelectedItem().toString(), listOrder};
		return settings;
	}

	 /**
     * Method to get parcels. First column represents amount (of A, B, C or L, P, T) and second column represents the given value of the previous parcels respectively.
     * @return 2D integer array containing Parcel data.
     */
	public int[][] collectParcels() throws ParseException {
		spnnrAmntDispFR.commitEdit();
		int amountFR = (Integer) spnnrAmntDispFR.getValue();
		spnnrAmntDispSR.commitEdit();
		int amountSR = (Integer) spnnrAmntDispSR.getValue();
		spnnrAmntDispTR.commitEdit();
		int amountTR = (Integer) spnnrAmntDispTR.getValue();
		spnnrValDispFR.commitEdit();
		int valFR = (Integer) spnnrValDispFR.getValue();
		spnnrValDispSR.commitEdit();
		int valSR = (Integer) spnnrValDispSR.getValue();
		spnnrValDispTR.commitEdit();
		int valTR = (Integer) spnnrValDispTR.getValue();
		int[][] parcels = {{amountFR, valFR,}, {amountSR, valSR}, {amountTR, valTR}};
		return parcels;
	}
}