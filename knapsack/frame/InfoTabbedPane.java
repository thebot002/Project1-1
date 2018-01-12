package knapsack.frame;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class InfoTabbedPane extends JPanel {

	private JLabel lblCamera;
	private JLabel lblControls;
	private JLabel lblKeys;
	private JLabel lblElevation;
	private JLabel lblElevationDisp;
	private JLabel lblAngle;
	private JLabel lblAngleDisp;
	private JLabel lblZoom;
	private JLabel lblZoomDisp;
	private JLabel lblDownwardRoll;
	private JLabel lblDownArrow;
	private JLabel lblUpwardsRoll;
	private JLabel lblD;
	private JLabel lblToggleDebug;
	private JLabel lblPlus;
	private JLabel lblZoomIn;
	private JLabel lblMinus;
	private JLabel lblZoomOut;
	private JLabel lblLeftArrow;
	private JLabel lblRotateLeft;
	private JLabel lblRightArrow;
	private JLabel lblRotateRight;
	private JLabel lblUpArrow;


	public InfoTabbedPane() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 88, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		lblCamera = new JLabel("Camera");
		lblCamera.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblCamera = new GridBagConstraints();
		gbc_lblCamera.insets = new Insets(0, 0, 5, 5);
		gbc_lblCamera.gridx = 1;
		gbc_lblCamera.gridy = 1;
		add(lblCamera, gbc_lblCamera);

		lblControls = new JLabel("Controls");
		lblControls.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblControls = new GridBagConstraints();
		gbc_lblControls.insets = new Insets(0, 0, 5, 5);
		gbc_lblControls.gridx = 5;
		gbc_lblControls.gridy = 1;
		add(lblControls, gbc_lblControls);

		lblKeys = new JLabel("Keys");
		lblKeys.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblKeys = new GridBagConstraints();
		gbc_lblKeys.insets = new Insets(0, 0, 5, 5);
		gbc_lblKeys.gridx = 6;
		gbc_lblKeys.gridy = 1;
		add(lblKeys, gbc_lblKeys);

		lblElevation = new JLabel("Elevation:");
		GridBagConstraints gbc_lblElevation = new GridBagConstraints();
		gbc_lblElevation.insets = new Insets(0, 0, 5, 5);
		gbc_lblElevation.gridx = 1;
		gbc_lblElevation.gridy = 2;
		add(lblElevation, gbc_lblElevation);

		lblElevationDisp = new JLabel("---");
		lblElevationDisp.setFont(new Font("Tahoma", Font.ITALIC, 13));
		GridBagConstraints gbc_lblElevationDisp = new GridBagConstraints();
		gbc_lblElevationDisp.insets = new Insets(0, 0, 5, 5);
		gbc_lblElevationDisp.gridx = 2;
		gbc_lblElevationDisp.gridy = 2;
		add(lblElevationDisp, gbc_lblElevationDisp);

		lblDownwardRoll = new JLabel("Downwards Roll:");
		GridBagConstraints gbc_lblDownwardRoll = new GridBagConstraints();
		gbc_lblDownwardRoll.insets = new Insets(0, 0, 5, 5);
		gbc_lblDownwardRoll.gridx = 5;
		gbc_lblDownwardRoll.gridy = 2;
		add(lblDownwardRoll, gbc_lblDownwardRoll);

		lblDownArrow = new JLabel("Down Arrow");
		lblDownArrow.setFont(new Font("Tahoma", Font.ITALIC, 13));
		GridBagConstraints gbc_lblDownArrow = new GridBagConstraints();
		gbc_lblDownArrow.insets = new Insets(0, 0, 5, 5);
		gbc_lblDownArrow.gridx = 6;
		gbc_lblDownArrow.gridy = 2;
		add(lblDownArrow, gbc_lblDownArrow);
	
		lblAngle = new JLabel("Angle:");
		GridBagConstraints gbc_lblAngle = new GridBagConstraints();
		gbc_lblAngle.insets = new Insets(0, 0, 5, 5);
		gbc_lblAngle.gridx = 1;
		gbc_lblAngle.gridy = 3;
		add(lblAngle, gbc_lblAngle);
	
		lblAngleDisp = new JLabel("---");
		lblAngleDisp.setFont(new Font("Tahoma", Font.ITALIC, 13));
		GridBagConstraints gbc_lblAngleDisp = new GridBagConstraints();
		gbc_lblAngleDisp.insets = new Insets(0, 0, 5, 5);
		gbc_lblAngleDisp.gridx = 2;
		gbc_lblAngleDisp.gridy = 3;
		add(lblAngleDisp, gbc_lblAngleDisp);
	
		lblUpwardsRoll = new JLabel("Upwards Roll:");
		GridBagConstraints gbc_lblUpwardsRoll = new GridBagConstraints();
		gbc_lblUpwardsRoll.insets = new Insets(0, 0, 5, 5);
		gbc_lblUpwardsRoll.gridx = 5;
		gbc_lblUpwardsRoll.gridy = 3;
		add(lblUpwardsRoll, gbc_lblUpwardsRoll);
	
		lblUpArrow = new JLabel("Up Arrow");
		lblUpArrow.setFont(new Font("Tahoma", Font.ITALIC, 13));
		GridBagConstraints gbc_lblUpArrow = new GridBagConstraints();
		gbc_lblUpArrow.insets = new Insets(0, 0, 5, 5);
		gbc_lblUpArrow.gridx = 6;
		gbc_lblUpArrow.gridy = 3;
		add(lblUpArrow, gbc_lblUpArrow);
	
		lblZoom = new JLabel("Zoom:");
		GridBagConstraints gbc_lblZoom = new GridBagConstraints();
		gbc_lblZoom.insets = new Insets(0, 0, 5, 5);
		gbc_lblZoom.gridx = 1;
		gbc_lblZoom.gridy = 4;
		add(lblZoom, gbc_lblZoom);
	
		lblZoomDisp = new JLabel("---");
		lblZoomDisp.setFont(new Font("Tahoma", Font.ITALIC, 13));
		GridBagConstraints gbc_lblZoomDisp = new GridBagConstraints();
		gbc_lblZoomDisp.insets = new Insets(0, 0, 5, 5);
		gbc_lblZoomDisp.gridx = 2;
		gbc_lblZoomDisp.gridy = 4;
		add(lblZoomDisp, gbc_lblZoomDisp);
	
		lblRotateRight = new JLabel("Rotate Right:");
		GridBagConstraints gbc_lblRotateRight = new GridBagConstraints();
		gbc_lblRotateRight.insets = new Insets(0, 0, 5, 5);
		gbc_lblRotateRight.gridx = 5;
		gbc_lblRotateRight.gridy = 4;
		add(lblRotateRight, gbc_lblRotateRight);
	
		lblRightArrow = new JLabel("Right Arrow");
		lblRightArrow.setFont(new Font("Tahoma", Font.ITALIC, 13));
		GridBagConstraints gbc_lblRightArrow = new GridBagConstraints();
		gbc_lblRightArrow.insets = new Insets(0, 0, 5, 5);
		gbc_lblRightArrow.gridx = 6;
		gbc_lblRightArrow.gridy = 4;
		add(lblRightArrow, gbc_lblRightArrow);
	
		lblRotateLeft = new JLabel("Rotate Left:");
		GridBagConstraints gbc_lblRotateLeft = new GridBagConstraints();
		gbc_lblRotateLeft.insets = new Insets(0, 0, 5, 5);
		gbc_lblRotateLeft.gridx = 5;
		gbc_lblRotateLeft.gridy = 5;
		add(lblRotateLeft, gbc_lblRotateLeft);
	
		lblLeftArrow = new JLabel("Left Arrow");
		lblLeftArrow.setFont(new Font("Tahoma", Font.ITALIC, 13));
		GridBagConstraints gbc_lblLeftArrow = new GridBagConstraints();
		gbc_lblLeftArrow.insets = new Insets(0, 0, 5, 5);
		gbc_lblLeftArrow.gridx = 6;
		gbc_lblLeftArrow.gridy = 5;
		add(lblLeftArrow, gbc_lblLeftArrow);
	
		lblZoomOut = new JLabel("Zoom out:");
		GridBagConstraints gbc_lblZoomOut = new GridBagConstraints();
		gbc_lblZoomOut.insets = new Insets(0, 0, 5, 5);
		gbc_lblZoomOut.gridx = 5;
		gbc_lblZoomOut.gridy = 6;
		add(lblZoomOut, gbc_lblZoomOut);
	
		lblMinus = new JLabel("Minus");
		lblMinus.setFont(new Font("Tahoma", Font.ITALIC, 13));
		GridBagConstraints gbc_lblMinus = new GridBagConstraints();
		gbc_lblMinus.fill = GridBagConstraints.VERTICAL;
		gbc_lblMinus.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinus.gridx = 6;
		gbc_lblMinus.gridy = 6;
		add(lblMinus, gbc_lblMinus);
	
		lblZoomIn = new JLabel("Zoom in:");
		GridBagConstraints gbc_lblZoomIn = new GridBagConstraints();
		gbc_lblZoomIn.insets = new Insets(0, 0, 5, 5);
		gbc_lblZoomIn.gridx = 5;
		gbc_lblZoomIn.gridy = 7;
		add(lblZoomIn, gbc_lblZoomIn);
	
		lblPlus = new JLabel("Plus");
		lblPlus.setFont(new Font("Tahoma", Font.ITALIC, 13));
		GridBagConstraints gbc_lblPlus = new GridBagConstraints();
		gbc_lblPlus.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlus.gridx = 6;
		gbc_lblPlus.gridy = 7;
		add(lblPlus, gbc_lblPlus);
	
		lblToggleDebug = new JLabel("Toggle Debug:");
		GridBagConstraints gbc_lblToggleDebug = new GridBagConstraints();
		gbc_lblToggleDebug.insets = new Insets(0, 0, 5, 5);
		gbc_lblToggleDebug.gridx = 5;
		gbc_lblToggleDebug.gridy = 8;
		add(lblToggleDebug, gbc_lblToggleDebug);
	
		lblD = new JLabel("D");
		lblD.setFont(new Font("Tahoma", Font.ITALIC, 13));
		GridBagConstraints gbc_lblD = new GridBagConstraints();
		gbc_lblD.insets = new Insets(0, 0, 5, 5);
		gbc_lblD.gridx = 6;
		gbc_lblD.gridy = 8;
		add(lblD, gbc_lblD);
	}
	
	public void setAngle(int angle) {
		lblAngleDisp.setText(String.valueOf(angle));
	}
	
	public void setElevation(int elevation) {
		lblElevationDisp.setText(String.valueOf(elevation));
	}
	
	public void setZoom(int zoom) {
		lblZoomDisp.setText(String.valueOf(zoom));
	}
}