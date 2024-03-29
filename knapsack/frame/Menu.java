package knapsack.frame;

import knapsack.*;
import knapsack.components.AlgorithmInfo;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JSlider;

public class Menu extends JPanel implements ActionListener {

	private JButton btnFillTruck;
	private JButton btnClearTruck;
	private JButton btnResetCamera;
	private JLabel lblGapsFound;
	private JLabel lblCurrentValue;
	private JLabel lblGapsFoundDisp;
	private JLabel lblCurrentValueDisp;
	private JTabbedPane tabbedPane;
	private SwitchTabbedPane rectangleTab;
	private SwitchTabbedPane pentominoTab;
	private InfoPanel infoPanel;
	private JLabel lblTimeTook;
	private JLabel lblTimeTookDisp;
	private CubeDrawer cubeDrawer;
	private JPanel infoJPanel;
	private Knapsack knapsack;

	public Menu(Knapsack knapsack) {
		this.knapsack = knapsack;
		setBackground(SystemColor.menu);
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 30, 0, 0, 0, 0, 0, 74, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 30, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		btnFillTruck = new JButton("Fill Truck");
		btnFillTruck.addActionListener(this);
		GridBagConstraints gbc_btnFillTruck = new GridBagConstraints();
		gbc_btnFillTruck.gridwidth = 2;
		gbc_btnFillTruck.insets = new Insets(0, 0, 5, 5);
		gbc_btnFillTruck.gridx = 1;
		gbc_btnFillTruck.gridy = 2;
		add(btnFillTruck, gbc_btnFillTruck);

		btnClearTruck = new JButton("Clear Truck");
		btnClearTruck.addActionListener(this);
		GridBagConstraints gbc_btnClearTruck = new GridBagConstraints();
		gbc_btnClearTruck.gridwidth = 2;
		gbc_btnClearTruck.insets = new Insets(0, 0, 5, 5);
		gbc_btnClearTruck.gridx = 3;
		gbc_btnClearTruck.gridy = 2;
		add(btnClearTruck, gbc_btnClearTruck);

		btnResetCamera = new JButton("Reset Camera");
		btnResetCamera.addActionListener(this);
		GridBagConstraints gbc_btnResetCamera = new GridBagConstraints();
		gbc_btnResetCamera.gridwidth = 2;
		gbc_btnResetCamera.insets = new Insets(0, 0, 5, 5);
		gbc_btnResetCamera.gridx = 5;
		gbc_btnResetCamera.gridy = 2;
		add(btnResetCamera, gbc_btnResetCamera);

		infoJPanel = new JPanel();
		infoPanel = new InfoPanel(knapsack);
		GridBagLayout gridBagLayout_1 = (GridBagLayout) infoPanel.getLayout();
		gridBagLayout_1.columnWidths = new int[]{0, 111, 0, 0, 0, 0, 0, 0};
		GridBagConstraints gbc_infoJPanel = new GridBagConstraints();
		gbc_infoJPanel.gridheight = 9;
		gbc_infoJPanel.gridwidth = 6;
		gbc_infoJPanel.insets = new Insets(0, 0, 5, 5);
		gbc_infoJPanel.fill = GridBagConstraints.BOTH;
		gbc_infoJPanel.gridx = 1;
		gbc_infoJPanel.gridy = 18;
		infoJPanel.add(infoPanel);
		add(infoJPanel, gbc_infoJPanel);

		lblGapsFound = new JLabel("Gaps Found");
		GridBagConstraints gbc_lblGapsFound = new GridBagConstraints();
		gbc_lblGapsFound.gridwidth = 2;
		gbc_lblGapsFound.insets = new Insets(0, 0, 5, 5);
		gbc_lblGapsFound.gridx = 1;
		gbc_lblGapsFound.gridy = 28;
		add(lblGapsFound, gbc_lblGapsFound);

		lblCurrentValue = new JLabel("Current Value");
		GridBagConstraints gbc_lblCurrentValue = new GridBagConstraints();
		gbc_lblCurrentValue.gridwidth = 2;
		gbc_lblCurrentValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentValue.gridx = 3;
		gbc_lblCurrentValue.gridy = 28;
		add(lblCurrentValue, gbc_lblCurrentValue);

		lblTimeTook = new JLabel("Time Took (in s)");
		GridBagConstraints gbc_lblTimeTook = new GridBagConstraints();
		gbc_lblTimeTook.gridwidth = 2;
		gbc_lblTimeTook.insets = new Insets(0, 0, 5, 5);
		gbc_lblTimeTook.gridx = 5;
		gbc_lblTimeTook.gridy = 28;
		add(lblTimeTook, gbc_lblTimeTook);

		lblGapsFoundDisp = new JLabel("---");
		lblGapsFoundDisp.setFont(new Font("Verdana", Font.ITALIC, 13));
		GridBagConstraints gbc_lblGapsFoundDisp = new GridBagConstraints();
		gbc_lblGapsFoundDisp.gridwidth = 2;
		gbc_lblGapsFoundDisp.insets = new Insets(0, 0, 5, 5);
		gbc_lblGapsFoundDisp.gridx = 1;
		gbc_lblGapsFoundDisp.gridy = 30;
		add(lblGapsFoundDisp, gbc_lblGapsFoundDisp);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.gridheight = 14;
		gbc_tabbedPane.gridwidth = 6;
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
		gbc_tabbedPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_tabbedPane.gridx = 1;
		gbc_tabbedPane.gridy = 3;
		add(tabbedPane, gbc_tabbedPane);

		rectangleTab = new SwitchTabbedPane("A", "B", "C", "Dimensions", "1.0 x 1.0 x 2.0", "1.0 x 1.5 x 2.0", "1.5 x 1.5 x 1.5");
		pentominoTab = new SwitchTabbedPane("L", "P", "T", "", "", "", "");
		tabbedPane.addTab("Rectangle", rectangleTab);
		tabbedPane.addTab("Pentomino", pentominoTab);

		lblCurrentValueDisp = new JLabel("---");
		lblCurrentValueDisp.setFont(new Font("Verdana", Font.ITALIC, 13));
		GridBagConstraints gbc_lblCurrentValueDisp = new GridBagConstraints();
		gbc_lblCurrentValueDisp.gridwidth = 2;
		gbc_lblCurrentValueDisp.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentValueDisp.gridx = 3;
		gbc_lblCurrentValueDisp.gridy = 30;
		add(lblCurrentValueDisp, gbc_lblCurrentValueDisp);

		lblTimeTookDisp = new JLabel("---");
		lblTimeTookDisp.setFont(new Font("Verdana", Font.ITALIC, 13));
		GridBagConstraints gbc_lblTimeTookDisp = new GridBagConstraints();
		gbc_lblTimeTookDisp.gridwidth = 2;
		gbc_lblTimeTookDisp.insets = new Insets(0, 0, 5, 5);
		gbc_lblTimeTookDisp.gridx = 5;
		gbc_lblTimeTookDisp.gridy = 30;
		add(lblTimeTookDisp, gbc_lblTimeTookDisp);
		setVisible(true);
	}

	public void setCubeDrawer(CubeDrawer c) {
		cubeDrawer = c;
	}

	/**
	 * Implementation of action listeners of buttons
	 * If "Fill Truck" is clicked the values of the JSpinners are put into an arraylist
	 * If "Clear Truck" is clicked -- has to be defined --
	 * If "Reset  Camera" is clicked -- has to be defined --
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnFillTruck) {
			System.out.println(tabbedPane.getSelectedIndex()); //rect or pent
            SwitchTabbedPane pane = (SwitchTabbedPane)tabbedPane.getSelectedComponent();
            try {
                AlgorithmInfo infoAndSettings = new AlgorithmInfo(pane.collectParcelSettings(), pane.collectParcels(), "Rectangle");
                knapsack.fill(infoAndSettings);
            } catch(ParseException ex) {}
		}
		else if(e.getSource() == btnClearTruck) {
			if(cubeDrawer != null)
				cubeDrawer.emptyScene();
		}
		else if(e.getSource() == btnResetCamera) {
			if(cubeDrawer != null) {
				cubeDrawer.getCamera().reset();
				infoPanel.setAngle(0);
				infoPanel.setElevation(35);
				infoPanel.setZoom(20);
			}

		}
	}

	/**
	 * Used to update label referring to how many gaps are found
	 * @param found Amount of gaps found
	 */
	public void setGapsFound(int found) {
		lblGapsFoundDisp.setText(String.valueOf(found));
	}

	/**
	 * Used to update label referring to current value of truck
	 * @param value Current value
	 */
	public void setCurrentValue(int value) {
		lblCurrentValueDisp.setText(String.valueOf(value));
	}

	/**
	 * Used to update label referring to the time it took to fill the truck
	 * @param time Amount of time took to find curent solution
	 */
	public void setTimeTook(double time) {
		lblTimeTookDisp.setText(String.valueOf(time));
	}
	public InfoPanel getInfoTab() {
		return infoPanel;
	}
}