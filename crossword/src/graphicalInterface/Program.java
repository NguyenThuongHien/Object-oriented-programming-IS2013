package graphicalInterface;

import Exceptions.FailedToGenerateCrosswordException;
import Exceptions.WrongDimensionInBoardAsked;
import Strategies.EasyStrategy;
import browser.CwBrowser;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: wukat
 * Date: 06.11.13
 * Time: 09:16
 * To change this template use File | Settings | File Templates.
 */
public class Program {
    private JRadioButton Easy;
    private JRadioButton Hard;
    private JPanel Size;
    private JSpinner Columns;
    private JSpinner Rows;
    private JLabel ColumnsLabel;
    private JLabel RowsLabel;
    private JPanel Strategy;
    private JButton FileChoose;
    private JPanel MainPanel;
    private JPanel DB;
    private JButton InputCrossword;
    private JPanel Cw;
    private JButton Save;
    private JButton Generate;
    private JButton Print;
    private JButton Solve;
    private JPanel Options;
    private JButton Previous;
    private JButton Next;
    private JPanel Browse;
    private JPanel Menu;
    private JTextField LogField;
    private JPanel LogPanel;
    private JPanel CrosswordPanel;
    private static CwBrowser browser;
    private static EasyStrategy easyStrategy = new EasyStrategy();

    public Program() {

        FileChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                if (e.getSource() == FileChoose) {
                    int returnVal = fc.showDialog(FileChoose, "Import");

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        //This is where a real application would open the file.
                    }
                }
            }

            ;
        });

        InputCrossword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (e.getSource() == InputCrossword) {
                    int returnVal = fc.showDialog(InputCrossword, "Open directory");

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        try {
                            browser.loadFromFiles(fc.getSelectedFile().getPath());
                            actualizeButtons();
                        } catch (Exception exc) {
                        }
                    } else {
                        LogField.setText("Failed to generate crossword from this database.");
                    }
                }
            }

            ;
        });

        Generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getSource() == Generate) {
                    if (Hard.isSelected())
                        try {
                            browser.generateCw(Integer.parseInt(Columns.getValue().toString()), Integer.parseInt(Rows.getValue().toString()), easyStrategy);
                            actualizeButtons();
                        } catch (Exception e) {
                        }
                    else {
                        try {
                            browser.generateCw(Integer.parseInt(Columns.getValue().toString()), Integer.parseInt(Rows.getValue().toString()), easyStrategy);
                            actualizeButtons();
                        } catch (WrongDimensionInBoardAsked e) {
                            LogField.setText("Wrong dimensions");
                        } catch (FailedToGenerateCrosswordException a) {
                            LogField.setText("Failed to generate crossword from this database.");
                        }
                    }
                }
            }
        });

        Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (actionEvent.getSource() == Save) {
                    int returnVal = fc.showDialog(Save, "Save in directory");

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        try {
                            browser.saveActual(fc.getSelectedFile().getPath());
                        } catch (IOException e) {
                        } catch (NullPointerException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

            }
        });

        Next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                browser.next();
                actualizeButtons();
            }
        });

        Previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                browser.previous();
                actualizeButtons();
            }
        });

        actualizeButtons();
        setLimitsWidthAndHeight();
    }

    public void actualizeButtons() {
        Previous.setEnabled(browser.hasOnlyActualOrNone() && browser.hasPrevious());
        Next.setEnabled(browser.hasOnlyActualOrNone() && browser.hasNext());
        Save.setEnabled(browser.hasActual());
        Solve.setEnabled(browser.hasActual());
        Print.setEnabled(browser.hasActual());
        LogField.setText("");
    }

    public void setLimitsWidthAndHeight() {
        Columns.setModel(new SpinnerNumberModel(5, 3, 12, 1));
        Rows.setModel(new SpinnerNumberModel(5, 3, 12, 1));
        ((JSpinner.DefaultEditor) Columns.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) Rows.getEditor()).getTextField().setEditable(false);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Program");
        try {
            browser = new CwBrowser(null);
            frame.setContentPane(new Program().MainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        } catch (IOException e) {

            Object[] options = {"Yes",
                    "No",
            };

            int n = JOptionPane.showOptionDialog(frame,
                    "Failed to load default data base. Do you want to choose it manually?",
                    "Failed to start",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (n == 0) {
                JFileChooser fc = new JFileChooser();
                int returnVal = fc.showDialog(fc, "Import database");
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        browser = new CwBrowser(fc.getSelectedFile().getAbsolutePath());
                        frame.setContentPane(new Program().MainPanel);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.pack();
                        frame.setVisible(true);
                    } catch (IOException a) {
                        JOptionPane.showMessageDialog(frame,
                                "Failed to load data base.",
                                "Failed to start",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Operation canceled, program halts.",
                            "Failed to start",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame,
                        "Operation canceled, program halts.",
                        "Failed to start",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        MainPanel = new JPanel();
        MainPanel.setLayout(new GridLayoutManager(4, 8, new Insets(0, 0, 0, 0), -1, -1));
        Menu = new JPanel();
        Menu.setLayout(new GridLayoutManager(2, 8, new Insets(0, 0, 0, 0), -1, -1));
        MainPanel.add(Menu, new GridConstraints(0, 0, 2, 8, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Strategy = new JPanel();
        Strategy.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        Menu.add(Strategy, new GridConstraints(0, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Strategy.setBorder(BorderFactory.createTitledBorder("Strategy"));
        Hard = new JRadioButton();
        Hard.setText("Hard");
        Strategy.add(Hard, new GridConstraints(1, 0, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Easy = new JRadioButton();
        Easy.setSelected(true);
        Easy.setText("Easy");
        Strategy.add(Easy, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Size = new JPanel();
        Size.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        Menu.add(Size, new GridConstraints(0, 1, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Size.setBorder(BorderFactory.createTitledBorder("Size"));
        Columns = new JSpinner();
        Columns.setEnabled(true);
        Columns.setFocusTraversalPolicyProvider(false);
        Columns.putClientProperty("html.disable", Boolean.FALSE);
        Size.add(Columns, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Rows = new JSpinner();
        Rows.setBackground(new Color(-2697514));
        Size.add(Rows, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ColumnsLabel = new JLabel();
        ColumnsLabel.setText("Columns");
        Size.add(ColumnsLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        RowsLabel = new JLabel();
        RowsLabel.setText("Rows");
        Size.add(RowsLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        DB = new JPanel();
        DB.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        Menu.add(DB, new GridConstraints(0, 2, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        DB.setBorder(BorderFactory.createTitledBorder("Import DB"));
        FileChoose = new JButton();
        FileChoose.setText("Choose file");
        DB.add(FileChoose, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Cw = new JPanel();
        Cw.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        Menu.add(Cw, new GridConstraints(0, 3, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Cw.setBorder(BorderFactory.createTitledBorder("Load crosswords"));
        InputCrossword = new JButton();
        InputCrossword.setText("Choose folder");
        Cw.add(InputCrossword, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Options = new JPanel();
        Options.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        Menu.add(Options, new GridConstraints(0, 4, 2, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Options.setBorder(BorderFactory.createTitledBorder("Options"));
        Save = new JButton();
        Save.setText("Save");
        Save.putClientProperty("html.disable", Boolean.TRUE);
        Options.add(Save, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Print = new JButton();
        Print.setText("Print");
        Options.add(Print, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Generate = new JButton();
        Generate.setText("Generate");
        Options.add(Generate, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Solve = new JButton();
        Solve.setText("Solve");
        Options.add(Solve, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Browse = new JPanel();
        Browse.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        Menu.add(Browse, new GridConstraints(0, 6, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Browse.setBorder(BorderFactory.createTitledBorder("Browse"));
        Previous = new JButton();
        Previous.setEnabled(true);
        Previous.setText("Prev");
        Previous.putClientProperty("html.disable", Boolean.FALSE);
        Browse.add(Previous, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Next = new JButton();
        Next.setText("Next");
        Browse.add(Next, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        LogPanel = new JPanel();
        LogPanel.setLayout(new BorderLayout(0, 0));
        LogPanel.setEnabled(true);
        LogPanel.setFocusCycleRoot(false);
        LogPanel.setFocusTraversalPolicyProvider(false);
        MainPanel.add(LogPanel, new GridConstraints(2, 0, 1, 8, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        LogPanel.setBorder(BorderFactory.createTitledBorder("Error logs"));
        LogField = new JTextField();
        LogField.setCaretColor(new Color(-2697514));
        LogField.setDisabledTextColor(new Color(-65536));
        LogField.setEditable(false);
        LogField.setFont(new Font(LogField.getFont().getName(), Font.BOLD, 12));
        LogField.setForeground(new Color(-65536));
        LogField.setText("");
        LogField.setVisible(true);
        LogPanel.add(LogField, BorderLayout.CENTER);
        CrosswordPanel = new JPanel();
        CrosswordPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        CrosswordPanel.setEnabled(true);
        MainPanel.add(CrosswordPanel, new GridConstraints(3, 0, 1, 8, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        CrosswordPanel.setBorder(BorderFactory.createTitledBorder("Crossword"));
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(Easy);
        buttonGroup.add(Hard);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return MainPanel;
    }
}

