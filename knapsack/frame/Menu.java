package knapsack.frame;

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

	public Menu() {
		setBackground(SystemColor.menu);
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 29, 0, 0, 0, 0, 0, 0, 17, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		btnFillTruck = new JButton("Fill Truck");
		btnFillTruck.addActionListener(this);
		GridBagConstraints gbc_btnFillTruck = new GridBagConstraints();
		gbc_btnFillTruck.gridwidth = 2;
		gbc_btnFillTruck.insets = new Insets(0, 0, 5, 5);
		gbc_btnFillTruck.gridx = 1;
		gbc_btnFillTruck.gridy = 1;
		add(btnFillTruck, gbc_btnFillTruck);

		btnClearTruck = new JButton("Clear Truck");
		GridBagConstraints gbc_btnClearTruck = new GridBagConstraints();
		gbc_btnClearTruck.gridwidth = 2;
		gbc_btnClearTruck.insets = new Insets(0, 0, 5, 5);
		gbc_btnClearTruck.gridx = 3;
		gbc_btnClearTruck.gridy = 1;
		add(btnClearTruck, gbc_btnClearTruck);

		btnResetCamera = new JButton("Reset Camera");
		GridBagConstraints gbc_btnResetCamera = new GridBagConstraints();
		gbc_btnResetCamera.gridwidth = 2;
		gbc_btnResetCamera.insets = new Insets(0, 0, 5, 5);
		gbc_btnResetCamera.gridx = 5;
		gbc_btnResetCamera.gridy = 1;
		add(btnResetCamera, gbc_btnResetCamera);

		lblGapsFound = new JLabel("Gaps Found");
		GridBagConstraints gbc_lblGapsFound = new GridBagConstraints();
		gbc_lblGapsFound.gridwidth = 2;
		gbc_lblGapsFound.insets = new Insets(0, 0, 5, 5);
		gbc_lblGapsFound.gridx = 2;
		gbc_lblGapsFound.gridy = 11;
		add(lblGapsFound, gbc_lblGapsFound);

		lblCurrentValue = new JLabel("Current Value");
		GridBagConstraints gbc_lblCurrentValue = new GridBagConstraints();
		gbc_lblCurrentValue.gridwidth = 2;
		gbc_lblCurrentValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentValue.gridx = 4;
		gbc_lblCurrentValue.gridy = 11;
		add(lblCurrentValue, gbc_lblCurrentValue);

		lblGapsFoundDisp = new JLabel("---");
		GridBagConstraints gbc_lblGapsFoundDisp = new GridBagConstraints();
		gbc_lblGapsFoundDisp.gridwidth = 2;
		gbc_lblGapsFoundDisp.insets = new Insets(0, 0, 5, 5);
		gbc_lblGapsFoundDisp.gridx = 2;
		gbc_lblGapsFoundDisp.gridy = 12;
		add(lblGapsFoundDisp, gbc_lblGapsFoundDisp);

		lblCurrentValueDisp = new JLabel("---");
		GridBagConstraints gbc_lblCurrentValueDisp = new GridBagConstraints();
		gbc_lblCurrentValueDisp.gridwidth = 2;
		gbc_lblCurrentValueDisp.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentValueDisp.gridx = 4;
		gbc_lblCurrentValueDisp.gridy = 12;
		add(lblCurrentValueDisp, gbc_lblCurrentValueDisp);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.gridheight = 7;
		gbc_tabbedPane.gridwidth = 6;
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 1;
		gbc_tabbedPane.gridy = 3;
		add(tabbedPane, gbc_tabbedPane);

		SwitchTabbedPane rectangleTab = new SwitchTabbedPane("A", "B", "C", "Dimensions", "1.0 x 1.0 x 2.0", "1.0 x 1.5 x 2.0", "1.5 x 1.5 x 1.5");
		SwitchTabbedPane pentominoTab = new SwitchTabbedPane("L", "P", "T", "", "", "", "");
		tabbedPane.addTab("Rectangle", rectangleTab);
		tabbedPane.addTab("Pentomino", pentominoTab);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnFillTruck) {
			if(tabbedPane.getSelectedComponent() == rectangleTab) {
	        	try {
					rectangleTab.collectParcels();
					int[][] parcelsRectangle = rectangleTab.collectParcels();

				} catch (ParseException e1) {
					System.out.println("The currently edited value couldn't be commited.");
					JOptionPane.showMessageDialog(tabbedPane, "The currently edited value couldn't be commited.");
				}
	        }
	        else if(tabbedPane.getSelectedComponent() == pentominoTab) {
	        	try {
					int[][] parcelsPentomino = pentominoTab.collectParcels();

				} catch (ParseException e1) {
					System.out.println("The currently edited value couldn't be commited.");
					JOptionPane.showMessageDialog(tabbedPane, "The currently edited value couldn't be commited.");
				}
	        }
	        else {
	        	JOptionPane.showMessageDialog(tabbedPane, "Please switch to the tab that corresponds to the type of parcel you would want to fill the truck with (Rectangle or Pentomino).");
	        }
		}
		else if(e.getSource() == btnClearTruck) {

		}
		else if(e.getSource() == btnResetCamera) {

		}
	}
	
	public void setGapsFound(int found) {
		lblGapsFoundDisp.setText(String.valueOf(found));
	}

	public void setCurrentValue(int value) {
		lblGapsFoundDisp.setText(String.valueOf(value));
	}
}
