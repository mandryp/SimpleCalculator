package calculatornew;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Simple Calculator. Only basic operations. One of firsts my projects.
 *
 * @author mandry
 */
public class CalculatorNew {

    private final JPanel pnlButtons2;
    private final JPanel pnlButtons;
    private double a, b;
    private String znak;
    private final ActionE btnAction;
    private final ActionE btnDigit;
    private final JButton lblResult;
    private boolean start = true;
    private final ActionE btnResult;

    /**
     * main void. Create object of class CalculatorNew
     *
     * @param args
     */
    public static void main(String[] args) {

        CalculatorNew calc = new CalculatorNew();

    }

    /**
     * Constructor
     */
    public CalculatorNew() {

        //create frame 300x300
        JFrame mainFrame = new JFrame("Калькулятор");
        mainFrame.setSize(300, 300);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // main panel with Border layout
        JPanel mainPanel = new JPanel(new BorderLayout(3, 3));

        //clear button where will be shown result of calculate
        lblResult = new JButton("");
        lblResult.setActionCommand("clear");
        lblResult.addActionListener(new ActionE());
        lblResult.setFont(new Font("Segoe UI", 3, 20));
        lblResult.setPreferredSize(new Dimension(300, 50));
        mainPanel.add(lblResult, BorderLayout.NORTH);

        //panel with digit buttons
        pnlButtons = new JPanel(new GridLayout(4, 3, 5, 5));
        //panel with action buttons
        pnlButtons2 = new JPanel(new GridLayout(4, 1, 5, 5));

        //action listeners for buttons
        btnAction = new ActionE();
        btnDigit = new ActionE();
        btnResult = new ActionE();

        //create buttons with names and actions
        for (int i = 1; i < 11; i++) {
            addButton(String.valueOf(i % 10), btnDigit);
        }
        addButton(".", btnDigit);
        addButton("=", btnResult);
        addButton("+", btnAction);
        addButton("-", btnAction);
        addButton("*", btnAction);
        addButton("/", btnAction);

        //add buttons to panels
        mainPanel.add(pnlButtons, BorderLayout.CENTER);
        mainPanel.add(pnlButtons2, BorderLayout.EAST);

        //add main panels to frame
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

    private void addButton(String name, ActionE action) {

        JButton btn = new JButton(name);
        btn.setFont(new Font("Segoe UI", 1, 20));
        btn.addActionListener(action);

        if (action == btnDigit) {
            btn.setActionCommand("digit");
            pnlButtons.add(btn);
        } else if (action == btnAction) {
            btn.setActionCommand("action");
            pnlButtons2.add(btn);
        } else if (action == btnResult) {
            btn.setActionCommand("action");
            pnlButtons.add(btn);
        }

    }

    class ActionE implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            JButton btn = (JButton) e.getSource();

            switch (btn.getActionCommand()) {
                case "clear":
                    lblResult.setText("");
                    start = true;
                    a = 0;
                    b = 0;
                    znak = "+";
                    break;
                case "digit":
                    lblResult.setText(lblResult.getText() + btn.getText());
                    break;
                case "action":

                    if (lblResult.getText().isEmpty() || lblResult.getText().equals(".")) {
                        if (start) {
                            a = 0;
                            start = false;
                        } else {
                            b = 0;
                            lblResult.setText(calculate(a, b, znak));
                            start = true;
                        }
                    } else {
                        if (start) {
                            a = Double.parseDouble(lblResult.getText());
                            znak = btn.getText();
                            lblResult.setText("");
                            start = false;
                        } else {
                            b = Double.parseDouble(lblResult.getText());
                            lblResult.setText(calculate(a, b, znak));
                            start = true;
                        }
                    }

                    break;
            }

        }
    }

    private String calculate(double a, double b, String znak) {

        switch (znak) {
            case "+":
                return String.valueOf(a + b);
            case "-":
                return String.valueOf(a - b);
            case "*":
                return String.valueOf(a * b);
            case "/":
                return String.valueOf(a / b);
        }
        return null;
    }
}
