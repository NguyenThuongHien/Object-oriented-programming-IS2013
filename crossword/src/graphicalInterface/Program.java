package graphicalInterface;

import Exceptions.FailedToGenerateCrosswordException;
import Strategies.EasyStrategy;
import browser.CwBrowser;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import dictionary.InteliCwDB;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JTable CwTable;
    private JTextPane EntriesPanel;
    private JPanel Box;
    private static CwBrowser browser;
    private static EasyStrategy easyStrategy = new EasyStrategy();
    private boolean lastUsedNext;

    public Program() {
        FileChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fc = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        ".txt", "txt");
                fc.setAcceptAllFileFilterUsed(false);
                fc.setFileFilter(filter);
                if (actionEvent.getSource() == FileChoose) {
                    int returnVal = fc.showDialog(FileChoose, "Import");

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        try {
                            browser.setDefaultCwDB(new InteliCwDB(fc.getSelectedFile().getPath()));
                        } catch (IOException e) {
                            LogField.setText("Failed to import database");
                        }
                    }
                }
            }

            ;
        });

        InputCrossword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (actionEvent.getSource() == InputCrossword) {
                    int returnVal = fc.showDialog(InputCrossword, "Open directory");

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        try {
                            browser.loadFromFiles(fc.getSelectedFile().getPath());
                            actualizeButtons();
                        } catch (IOException e) {
                            LogField.setText("Failed to load crosswords.");
                        }
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
                            LogField.setText("Failed to save crossword.");
                        }
                    }
                }

            }
        });

        Next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                browser.next(lastUsedNext);
                lastUsedNext = true;
                actualizeButtons();
            }
        });

        Previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                browser.previous(lastUsedNext);
                lastUsedNext = false;
                actualizeButtons();
            }
        });

        setLimitsWidthAndHeight();
        lastUsedNext = true;
        actualizeButtons();

        Print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    EntriesPanel.print();
                } catch (Exception e) {
                }
            }
        });
        Solve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    public void actualizeButtons() {
        if (lastUsedNext) {
            Next.setEnabled(browser.hasNext());
            Previous.setEnabled(browser.previousIndex() > 0);
        } else {
            Previous.setEnabled(browser.hasPrevious());
            Next.setEnabled(browser.nextIndex() < browser.getAmountOfCrosswords());
        }
        Save.setEnabled(browser.hasActual());
        Solve.setEnabled(browser.hasActual());
        Print.setEnabled(browser.hasActual());
        LogField.setText("");
        if (browser.hasActual()) show();
    }

    public void show() {
        EntriesPanel.setText(easyStrategy.printAllEntries(browser.getActual()));
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
        MainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        MainPanel.setMinimumSize(new Dimension(-1, -1));
        MainPanel.setOpaque(true);
        MainPanel.setPreferredSize(new Dimension(970, 600));
        Menu = new JPanel();
        Menu.setLayout(new GridLayoutManager(1, 6, new Insets(0, 0, 0, 0), -1, -1));
        Menu.setAutoscrolls(false);
        Menu.setVerifyInputWhenFocusTarget(true);
        MainPanel.add(Menu);
        Strategy = new JPanel();
        Strategy.setLayout(new GridBagLayout());
        Menu.add(Strategy, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Strategy.setBorder(BorderFactory.createTitledBorder("Strategy"));
        Hard = new JRadioButton();
        Hard.setMinimumSize(new Dimension(-1, -1));
        Hard.setText("Hard");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        Strategy.add(Hard, gbc);
        Easy = new JRadioButton();
        Easy.setSelected(true);
        Easy.setText("Easy");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        Strategy.add(Easy, gbc);
        Size = new JPanel();
        Size.setLayout(new GridBagLayout());
        Menu.add(Size, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Size.setBorder(BorderFactory.createTitledBorder("Size"));
        Columns = new JSpinner();
        Columns.setEnabled(true);
        Columns.setFocusTraversalPolicyProvider(false);
        Columns.putClientProperty("html.disable", Boolean.FALSE);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        Size.add(Columns, gbc);
        Rows = new JSpinner();
        Rows.setBackground(new Color(-2697514));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        Size.add(Rows, gbc);
        ColumnsLabel = new JLabel();
        ColumnsLabel.setText("Columns");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        Size.add(ColumnsLabel, gbc);
        RowsLabel = new JLabel();
        RowsLabel.setText("Rows");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        Size.add(RowsLabel, gbc);
        DB = new JPanel();
        DB.setLayout(new GridBagLayout());
        Menu.add(DB, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        DB.setBorder(BorderFactory.createTitledBorder("Import DB"));
        FileChoose = new JButton();
        FileChoose.setText("Choose file");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        DB.add(FileChoose, gbc);
        Cw = new JPanel();
        Cw.setLayout(new GridBagLayout());
        Menu.add(Cw, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Cw.setBorder(BorderFactory.createTitledBorder("Load crosswords"));
        InputCrossword = new JButton();
        InputCrossword.setText("Choose folder");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        Cw.add(InputCrossword, gbc);
        Options = new JPanel();
        Options.setLayout(new GridBagLayout());
        Menu.add(Options, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Options.setBorder(BorderFactory.createTitledBorder("Options"));
        Save = new JButton();
        Save.setText("Save");
        Save.putClientProperty("html.disable", Boolean.TRUE);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        Options.add(Save, gbc);
        Print = new JButton();
        Print.setText("Print");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        Options.add(Print, gbc);
        Generate = new JButton();
        Generate.setText("Generate");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        Options.add(Generate, gbc);
        Solve = new JButton();
        Solve.setText("Solve");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        Options.add(Solve, gbc);
        Browse = new JPanel();
        Browse.setLayout(new GridBagLayout());
        Menu.add(Browse, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Browse.setBorder(BorderFactory.createTitledBorder("Browse"));
        Previous = new JButton();
        Previous.setEnabled(true);
        Previous.setText("Prev");
        Previous.putClientProperty("html.disable", Boolean.FALSE);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        Browse.add(Previous, gbc);
        Next = new JButton();
        Next.setText("Next");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        Browse.add(Next, gbc);
        LogPanel = new JPanel();
        LogPanel.setLayout(new GridBagLayout());
        LogPanel.setEnabled(true);
        LogPanel.setFocusCycleRoot(false);
        LogPanel.setFocusTraversalPolicyProvider(false);
        LogPanel.setMaximumSize(new Dimension(310, 310));
        LogPanel.setPreferredSize(new Dimension(950, 44));
        MainPanel.add(LogPanel);
        LogPanel.setBorder(BorderFactory.createTitledBorder("Error logs"));
        LogField = new JTextField();
        LogField.setCaretColor(new Color(-2697514));
        LogField.setDisabledTextColor(new Color(-65536));
        LogField.setEditable(false);
        LogField.setEnabled(false);
        LogField.setFont(new Font(LogField.getFont().getName(), Font.BOLD, 12));
        LogField.setForeground(new Color(-2697514));
        LogField.setMinimumSize(new Dimension(950, 23));
        LogField.setPreferredSize(new Dimension(950, 23));
        LogField.setText("");
        LogField.setVisible(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        LogPanel.add(LogField, gbc);
        CrosswordPanel = new JPanel();
        CrosswordPanel.setLayout(new GridBagLayout());
        CrosswordPanel.setEnabled(true);
        CrosswordPanel.setPreferredSize(new Dimension(970, 470));
        MainPanel.add(CrosswordPanel);
        CrosswordPanel.setBorder(BorderFactory.createTitledBorder("Crossword"));
        EntriesPanel = new JTextPane();
        EntriesPanel.setDisabledTextColor(new Color(-16777216));
        EntriesPanel.setEditable(false);
        EntriesPanel.setEnabled(false);
        EntriesPanel.putClientProperty("charset", "");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        CrosswordPanel.add(EntriesPanel, gbc);
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

