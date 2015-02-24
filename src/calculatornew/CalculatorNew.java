package calculatornew;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CalculatorNew {

    private final JPanel pnlButtons2;
    private final JPanel pnlButtons;
    private double a, b;
    private String znak;

    public static void main(String[] args) {

        CalculatorNew calc = new CalculatorNew();

    }
    private final ActionE btnAction;
    private final ActionE btnDigit;
    private JButton lblResult;
    private boolean start = true;
    private final ActionE btnResult;

    public CalculatorNew() {
        JFrame mainFrame = new JFrame("Калькулятор");
        mainFrame.setSize(300, 300);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(3, 3));
        lblResult = new JButton("");
        lblResult.setActionCommand("clear");
        lblResult.addActionListener(new ActionE());

        lblResult.setPreferredSize(new Dimension(300, 50));
        mainPanel.add(lblResult, BorderLayout.NORTH);

        pnlButtons = new JPanel(new GridLayout(4, 3, 5, 5));
        pnlButtons2 = new JPanel(new GridLayout(4, 1, 5, 5));

        btnAction = new ActionE();
        btnDigit = new ActionE();
        btnResult = new ActionE();
        for (int i = 1; i < 11; i++) {
            addButton(String.valueOf(i % 10), btnDigit);
        }
        addButton(".", btnDigit);
        addButton("=", btnResult);
        addButton("+", btnAction);
        addButton("-", btnAction);
        addButton("*", btnAction);
        addButton("/", btnAction);

        mainPanel.add(pnlButtons, BorderLayout.CENTER);
        mainPanel.add(pnlButtons2, BorderLayout.EAST);

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

    private void addButton(String name, ActionE action) {

        JButton btn = new JButton(name);
        btn.addActionListener(action);
        if (action == btnDigit) {
            btn.setActionCommand("digit");
            pnlButtons.add(btn);

        } else if (action == btnAction) {
            btn.setActionCommand("action");
            pnlButtons2.add(btn);

        } else if (action == btnResult) {
            btn.setActionCommand("result");
            pnlButtons.add(btn);
        }

    }

    class ActionE implements ActionListener {

        @Override
        @SuppressWarnings("empty-statement")
        public void actionPerformed(ActionEvent e) {

            JButton btn = (JButton) e.getSource();

            if (btn.getActionCommand().equals("clear")) {
                lblResult.setText("");
                start = true;

            }
            if (start) {
                switch (e.getActionCommand()) {
                    case "digit":
                        lblResult.setText(lblResult.getText() + btn.getText());
                        break;
                    case "action":
                        if (lblResult.getText().isEmpty()) {
                            a = 0.0;
                        } else {
                            a = Double.parseDouble(lblResult.getText());
                        }
                        ;
                        b = 0.0;
                        lblResult.setText("");
                        start = false;
                        znak = btn.getText();
                        break;
                }

            } else {
                switch (e.getActionCommand()) {
                    case "digit":
                        lblResult.setText(lblResult.getText() + btn.getText());
                        break;
                    case "action":
                        znak = btn.getText();
                        lblResult.setText("");
                        break;
                    case "result":
                        if (lblResult.getText().isEmpty()) {
                            b = 0.0;
                        } else {
                            b = Double.parseDouble(lblResult.getText());
                        }
                        ;
                        lblResult.setText(calculate(a, b, znak));
                        start = true;
                        a = 0.0;
                        b = 0.0;
                        znak = "+";
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
}
