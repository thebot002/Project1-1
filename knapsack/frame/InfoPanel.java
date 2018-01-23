package knapsack.frame;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class InfoPanel extends JPanel {

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
	private JLabel lblCameraZoom;
	private JLabel lblToggleColor;
	private JLabel lblF;
	private JSlider slider;
	private JLabel lblTogglePoints;
	private JLabel lblP;
	private JLabel lblInvertColors;
	private JLabel lblI;
	private Knapsack knap;


	public InfoPanel(Knapsack parent) {
		this.knap = parent;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 41, 0, 0, 0, 111, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		lblCamera = new JLabel("Camera");
		lblCamera.setFont(new Font("Verdana", Font.BOLD,  12));
		GridBagConstraints gbc_lblCamera = new GridBagConstraints();
		gbc_lblCamera.insets = new Insets(0, 0, 5, 5);
		gbc_lblCamera.gridx = 1;
		gbc_lblCamera.gridy = 1;
		add(lblCamera, gbc_lblCamera);

		lblControls = new JLabel("Controls");
		lblControls.setFont(new Font("Verdana", Font.BOLD,  12));
		GridBagConstraints gbc_lblControls = new GridBagConstraints();
		gbc_lblControls.insets = new Insets(0, 0, 5, 5);
		gbc_lblControls.gridx = 4;
		gbc_lblControls.gridy = 1;
		add(lblControls, gbc_lblControls);

		lblKeys = new JLabel("Keys");
		lblKeys.setFont(new Font("Verdana", Font.BOLD,  12));
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
		lblElevationDisp.setFont(new Font("Verdana", Font.ITALIC,  12));
		GridBagConstraints gbc_lblElevationDisp = new GridBagConstraints();
		gbc_lblElevationDisp.insets = new Insets(0, 0, 5, 5);
		gbc_lblElevationDisp.gridx = 2;
		gbc_lblElevationDisp.gridy = 2;
		add(lblElevationDisp, gbc_lblElevationDisp);

		lblDownwardRoll = new JLabel("Downwards Roll:");
		GridBagConstraints gbc_lblDownwardRoll = new GridBagConstraints();
		gbc_lblDownwardRoll.insets = new Insets(0, 0, 5, 5);
		gbc_lblDownwardRoll.gridx = 4;
		gbc_lblDownwardRoll.gridy = 2;
		add(lblDownwardRoll, gbc_lblDownwardRoll);

		lblDownArrow = new JLabel("Down Arrow");
		lblDownArrow.setFont(new Font("Verdana", Font.ITALIC,  12));
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
		lblAngleDisp.setFont(new Font("Verdana", Font.ITALIC,  12));
		GridBagConstraints gbc_lblAngleDisp = new GridBagConstraints();
		gbc_lblAngleDisp.insets = new Insets(0, 0, 5, 5);
		gbc_lblAngleDisp.gridx = 2;
		gbc_lblAngleDisp.gridy = 3;
		add(lblAngleDisp, gbc_lblAngleDisp);

		lblUpwardsRoll = new JLabel("Upwards Roll:");
		GridBagConstraints gbc_lblUpwardsRoll = new GridBagConstraints();
		gbc_lblUpwardsRoll.insets = new Insets(0, 0, 5, 5);
		gbc_lblUpwardsRoll.gridx = 4;
		gbc_lblUpwardsRoll.gridy = 3;
		add(lblUpwardsRoll, gbc_lblUpwardsRoll);

		lblUpArrow = new JLabel("Up Arrow");
		lblUpArrow.setFont(new Font("Verdana", Font.ITALIC,  12));
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
		lblZoomDisp.setFont(new Font("Verdana", Font.ITALIC,  12));
		GridBagConstraints gbc_lblZoomDisp = new GridBagConstraints();
		gbc_lblZoomDisp.insets = new Insets(0, 0, 5, 5);
		gbc_lblZoomDisp.gridx = 2;
		gbc_lblZoomDisp.gridy = 4;
		add(lblZoomDisp, gbc_lblZoomDisp);

		lblRotateRight = new JLabel("Rotate Right:");
		GridBagConstraints gbc_lblRotateRight = new GridBagConstraints();
		gbc_lblRotateRight.insets = new Insets(0, 0, 5, 5);
		gbc_lblRotateRight.gridx = 4;
		gbc_lblRotateRight.gridy = 4;
		add(lblRotateRight, gbc_lblRotateRight);

		lblRightArrow = new JLabel("Right Arrow");
		lblRightArrow.setFont(new Font("Verdana", Font.ITALIC,  12));
		GridBagConstraints gbc_lblRightArrow = new GridBagConstraints();
		gbc_lblRightArrow.insets = new Insets(0, 0, 5, 5);
		gbc_lblRightArrow.gridx = 6;
		gbc_lblRightArrow.gridy = 4;
		add(lblRightArrow, gbc_lblRightArrow);

		lblRotateLeft = new JLabel("Rotate Left:");
		GridBagConstraints gbc_lblRotateLeft = new GridBagConstraints();
		gbc_lblRotateLeft.insets = new Insets(0, 0, 5, 5);
		gbc_lblRotateLeft.gridx = 4;
		gbc_lblRotateLeft.gridy = 5;
		add(lblRotateLeft, gbc_lblRotateLeft);

		lblLeftArrow = new JLabel("Left Arrow");
		lblLeftArrow.setFont(new Font("Verdana", Font.ITALIC,  12));
		GridBagConstraints gbc_lblLeftArrow = new GridBagConstraints();
		gbc_lblLeftArrow.insets = new Insets(0, 0, 5, 5);
		gbc_lblLeftArrow.gridx = 6;
		gbc_lblLeftArrow.gridy = 5;
		add(lblLeftArrow, gbc_lblLeftArrow);
		
		lblInvertColors = new JLabel("Invert Colors:");
		GridBagConstraints gbc_lblInvertColors = new GridBagConstraints();
		gbc_lblInvertColors.insets = new Insets(0, 0, 5, 5);
		gbc_lblInvertColors.gridx = 4;
		gbc_lblInvertColors.gridy = 6;
		add(lblInvertColors, gbc_lblInvertColors);
		
		lblI = new JLabel("I");
		lblI.setFont(new Font("Verdana", Font.ITALIC, 12));
		GridBagConstraints gbc_lblI = new GridBagConstraints();
		gbc_lblI.insets = new Insets(0, 0, 5, 5);
		gbc_lblI.gridx = 6;
		gbc_lblI.gridy = 6;
		add(lblI, gbc_lblI);
		
		lblZoomIn = new JLabel("Zoom in:");
		GridBagConstraints gbc_lblZoomIn = new GridBagConstraints();
		gbc_lblZoomIn.insets = new Insets(0, 0, 5, 5);
		gbc_lblZoomIn.gridx = 4;
		gbc_lblZoomIn.gridy = 7;
		add(lblZoomIn, gbc_lblZoomIn);

		lblPlus = new JLabel("Plus");
		lblPlus.setFont(new Font("Verdana", Font.ITALIC,  12));
		GridBagConstraints gbc_lblPlus = new GridBagConstraints();
		gbc_lblPlus.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlus.gridx = 6;
		gbc_lblPlus.gridy = 7;
		add(lblPlus, gbc_lblPlus);
		
		lblCameraZoom = new JLabel("Camera Zoom");
		GridBagConstraints gbc_lblCameraZoom = new GridBagConstraints();
		gbc_lblCameraZoom.gridwidth = 2;
		gbc_lblCameraZoom.insets = new Insets(0, 0, 5, 5);
		gbc_lblCameraZoom.gridx = 1;
		gbc_lblCameraZoom.gridy = 8;
		add(lblCameraZoom, gbc_lblCameraZoom);
		
		lblZoomOut = new JLabel("Zoom out:");
		GridBagConstraints gbc_lblZoomOut = new GridBagConstraints();
		gbc_lblZoomOut.insets = new Insets(0, 0, 5, 5);
		gbc_lblZoomOut.gridx = 4;
		gbc_lblZoomOut.gridy = 8;
		add(lblZoomOut, gbc_lblZoomOut);

		lblMinus = new JLabel("Minus");
		lblMinus.setFont(new Font("Verdana", Font.ITALIC,  12));
		GridBagConstraints gbc_lblMinus = new GridBagConstraints();
		gbc_lblMinus.fill = GridBagConstraints.VERTICAL;
		gbc_lblMinus.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinus.gridx = 6;
		gbc_lblMinus.gridy = 8;
		add(lblMinus, gbc_lblMinus);

		slider = new JSlider(JSlider.HORIZONTAL, 50, 150, 50);
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.fill = GridBagConstraints.HORIZONTAL;
		gbc_slider.gridwidth = 2;
		gbc_slider.insets = new Insets(0, 0, 5, 5);
		gbc_slider.gridx = 1;
		gbc_slider.gridy = 9;
		add(slider, gbc_slider);

		slider.setMajorTickSpacing(25);
		slider.setMinorTickSpacing(5);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);

		add(slider, gbc_slider);
		slider.addChangeListener(new SliderListener());
		
		lblToggleDebug = new JLabel("Toggle Debug:");
		GridBagConstraints gbc_lblToggleDebug = new GridBagConstraints();
		gbc_lblToggleDebug.insets = new Insets(0, 0, 5, 5);
		gbc_lblToggleDebug.gridx = 4;
		gbc_lblToggleDebug.gridy = 9;
		add(lblToggleDebug, gbc_lblToggleDebug);
				
		lblD = new JLabel("D");
		lblD.setFont(new Font("Verdana", Font.ITALIC,  12));
		GridBagConstraints gbc_lblD = new GridBagConstraints();
		gbc_lblD.insets = new Insets(0, 0, 5, 5);
		gbc_lblD.gridx = 6;
		gbc_lblD.gridy = 9;
		add(lblD, gbc_lblD);
				
		lblToggleColor = new JLabel("Toggle Color:");
		GridBagConstraints gbc_lblToggleColor = new GridBagConstraints();
		gbc_lblToggleColor.insets = new Insets(0, 0, 5, 5);
		gbc_lblToggleColor.gridx = 4;
		gbc_lblToggleColor.gridy = 10;
		add(lblToggleColor, gbc_lblToggleColor);
		
		lblF = new JLabel("F");
		lblF.setFont(new Font("Verdana", Font.ITALIC,  12));
		GridBagConstraints gbc_lblF = new GridBagConstraints();
		gbc_lblF.insets = new Insets(0, 0, 5, 5);
		gbc_lblF.gridx = 6;
		gbc_lblF.gridy = 10;
		add(lblF, gbc_lblF);
		
		lblTogglePoints = new JLabel("Toggle Points:");
		GridBagConstraints gbc_lblTogglePoints = new GridBagConstraints();
		gbc_lblTogglePoints.insets = new Insets(0, 0, 5, 5);
		gbc_lblTogglePoints.gridx = 4;
		gbc_lblTogglePoints.gridy = 11;
		add(lblTogglePoints, gbc_lblTogglePoints);
		
		lblP = new JLabel("P");
		lblP.setFont(new Font("Verdana", Font.ITALIC, 12));
		GridBagConstraints gbc_lblP = new GridBagConstraints();
		gbc_lblP.insets = new Insets(0, 0, 5, 5);
		gbc_lblP.gridx = 6;
		gbc_lblP.gridy = 11;
		add(lblP, gbc_lblP);


		//setZoom(knap.getCubeDrawer().getCamera());
	}

	class SliderListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider)e.getSource();
			knap.getCubeDrawer().getCamera().setZoom(source.getValue());
			setZoom(source.getValue());
		}
	}

	/**
     * Used to update label referring to current angle of camera
     * @param angle of camera
     */
	public void setAngle(int angle) {
		lblAngleDisp.setText(String.valueOf(angle) + " ");
	}

	/**
     * Used to update label referring current elevation of camera
     * @param elevation of camera
     */
	public void setElevation(int elevation) {
		lblElevationDisp.setText(String.valueOf(elevation) + " ");
	}

	/**
     * Used to update label referring current zoom of camera
     * @param zoom of camera
     */
	public void setZoom(int zoom) {
		lblZoomDisp.setText(String.valueOf(zoom) + " ");
	}
}