import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MatrixGui {
	private static final String TITLE = "Matrix Helper GUI";
	public static final String START_STRING = TITLE;
	private final int TEXT_FIELD_WIDTH = 8;
	private int columnCount;
	private int rowCount;
	private Matrix matrix;
	private final JFrame newMatrixFrame;
	private JFrame matrixWindowFrame;
	private GridBagLayout layout;
	private GridBagConstraints constraints;
	private JLabel outputPane;
	private JButton newMatrixButton;
	private JButton clearInputButton;
	private final JTextField rowsText = new JTextField(TEXT_FIELD_WIDTH);
	private final JTextField columnsText = new JTextField(TEXT_FIELD_WIDTH);
	private JPanel maninWindowNorthPanel;
	private JPanel maninWindowSouthPanel;
	private JPanel maninWindowWestPanel;
	private JButton createMatrixButton;
	JTextField[][] allElements;
	
	public MatrixGui() {
		constraints = new GridBagConstraints();
		layout = new GridBagLayout();
		
		newMatrixFrame = new JFrame(TITLE);
		JLabel welcomeLabel = new JLabel(START_STRING
		 + "\n Enter the number of rows and "
		 + "columns to get started.");
		JPanel newMatrixNorthPanel = new JPanel(layout);
		constrainAndAdd(newMatrixNorthPanel, welcomeLabel, 1, 0);
		
		JPanel newMatrixSouthPanel = new JPanel(layout);
		
		JButton doneButton = new JButton("Done");
		constrainAndAdd(newMatrixSouthPanel, doneButton, 2, 3, 2, 1);
		doneButton.addActionListener(e->checkNewMatrixParameters());
		
		JLabel rowsLabel = new JLabel("# of rows:");
		constrainAndAdd(newMatrixSouthPanel, rowsLabel, 1, 1);
		constrainAndAdd(newMatrixSouthPanel, rowsText, 2, 1);
		
		JLabel columnsLabel = new JLabel("# of columns:");
		constrainAndAdd(newMatrixSouthPanel, columnsLabel, 1, 2);
		constrainAndAdd(newMatrixSouthPanel, columnsText, 2, 2);
		
		Container newMatrixContainer = newMatrixFrame.getContentPane();
		newMatrixContainer.add(newMatrixNorthPanel, BorderLayout.NORTH);
		newMatrixContainer.add(newMatrixSouthPanel, BorderLayout.SOUTH);
		
		newMatrixFrame.setLocationRelativeTo(null);
		newMatrixFrame.setSize(newMatrixContainer.getPreferredSize().width + 100,
		 newMatrixContainer.getPreferredSize().height + 100);
		newMatrixFrame.setVisible(true);
	}
	
	private void checkNewMatrixParameters() {
		int i = 0;
		System.out.println(rowsText.getText() + "\n" + columnsText.getText());
		try {
			rowCount = Integer.parseInt(rowsText.getText());
			i++;
			columnCount = Integer.parseInt(columnsText.getText());
		}
		catch(Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(newMatrixFrame, "your input \""
			 + (i == 0?rowsText:columnsText)
			 + "\" is not in the correct form." +
			 "Enter a whole number for row and column counts.");
			return;
		}
		JOptionPane
		 .showMessageDialog(newMatrixFrame, "Matrix will be " + rowCount + "x" + columnCount);
		newMatrixFrame.setVisible(false);
		createMainWindow();
	}
	
	private void createMainWindow() {
		class ElementData {
			public int N;
			public int M;
			public String string;
			
			public ElementData(String string, int i, int j) {
				this.string = string;
				this.M = i;
				this.N = j;
			}
		}
		matrixWindowFrame = new JFrame("Create a Matrix");
		maninWindowNorthPanel = new JPanel(layout);
		maninWindowSouthPanel = new JPanel(layout);
		maninWindowWestPanel = new JPanel(layout);
		JTextField[][] elements = new JTextField[rowCount][columnCount];
		for(int i = 0; i < rowCount; i++) {
			for(int j = 0; j < columnCount; j++) {
				elements[i][j] = new JTextField("element_" + i + "_" + j);
				constrainAndAdd(maninWindowSouthPanel, elements[i][j], i, j);
			}
		}
		createMatrixButton = new JButton("Create Matrix");
		constrainAndAdd(maninWindowSouthPanel, createMatrixButton, columnCount / 2, rowCount);
		createMatrixButton.addActionListener(e->{
			Fraction[][] array = new Fraction[rowCount][columnCount];
			for(int i = 0; i < rowCount; i++) {
				for(int j = 0; j < columnCount; j++) {
					array[i][j] = Fraction.parseFraction(elements[i][j].getText());
				}
			}
			
			matrix = new Matrix(array);
		});
		
		constrainAndAdd(maninWindowSouthPanel, createMatrixButton, rowCount / 2, columnCount + 1);
		Container matrixWindowContainer = newMatrixFrame.getContentPane();
		//matrixWindowContainer.add(maninWindowNorthPanel, BorderLayout.NORTH);
		matrixWindowContainer.add(maninWindowSouthPanel, BorderLayout.SOUTH);
		
		matrixWindowFrame.setLocationRelativeTo(null);
		matrixWindowFrame.setSize(matrixWindowContainer.getPreferredSize().width + 100,
		 matrixWindowContainer.getPreferredSize().height + 100);
		matrixWindowFrame.setVisible(true);
	}
	
	private void constrainAndAdd(JPanel panel, JComponent component, int x, int y, int width,
	 int height) {
		//TODO: check for width and height both greater than 0
		constraints.gridwidth = width;
		constraints.gridheight = height;
		constrainAndAdd(panel, component, x, y);
	}
	
	private void constrainAndAdd(Container container, JPanel panel, int x, int y) {
		constraints.gridx = x;
		constraints.gridy = y;
		container.add(panel, constraints);
		resetConstraints();
	}
	
	private void resetConstraints() {
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
	}
	
	private void constrainAndAdd(JPanel panel, JComponent component, int x, int y) {
		constraints.gridx = x;
		constraints.gridy = y;
		panel.add(component, constraints);
		resetConstraints();
	}
	
	// For GaussJordonEliminationHelper to post messages to the gui
	public JFrame getFrame() {
		return matrixWindowFrame;
	}
	
	public void printEcho(String string) {
		System.out.println(string);
		outputPane.setText(string);
	}
	
	public Matrix getMatrix() { 
		return matrix;
	}
}
