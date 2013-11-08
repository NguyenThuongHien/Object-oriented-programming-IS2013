package graphicalInterface;

import Strategies.EasyStrategy;
import browser.CwBrowser;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

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
    private static CwBrowser browser;
    private static EasyStrategy easyStrategy;

    public Program() {

        FileChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newline = "\n";
                JFileChooser fc = new JFileChooser();
                if (e.getSource() == FileChoose) {
                    int returnVal = fc.showOpenDialog(FileChoose);

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
                    int returnVal = fc.showOpenDialog(InputCrossword);

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        try {
                            browser.loadFromFiles(fc.getSelectedFile().getPath());
                        } catch (Exception exc) {
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
                        } catch (Exception e) {
                        }
                    else {
                        try {
                            browser.generateCw(Integer.parseInt(Columns.getValue().toString()), Integer.parseInt(Rows.getValue().toString()), easyStrategy);
                        } catch (Exception e) {
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
                    int returnVal = fc.showOpenDialog(Save);

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
                Next.setEnabled(browser.hasNext());
            }
        });

        Previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                browser.previous();
                Previous.setEnabled(browser.hasPrevious());
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Program");
        try {
            browser = new CwBrowser(null);
        } catch (IOException e) {
            System.out.println("Failed to found data base.");
        }
        frame.setContentPane(new Program().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
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
        MainPanel.setLayout(new GridLayoutManager(2, 10, new Insets(0, 0, 0, 0), -1, -1));
        Strategy = new JPanel();
        Strategy.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        MainPanel.add(Strategy, new GridConstraints(0, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
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
        MainPanel.add(Size, new GridConstraints(0, 1, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
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
        MainPanel.add(DB, new GridConstraints(0, 2, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        DB.setBorder(BorderFactory.createTitledBorder("Import DB"));
        FileChoose = new JButton();
        FileChoose.setText("Choose file");
        DB.add(FileChoose, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Cw = new JPanel();
        Cw.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        MainPanel.add(Cw, new GridConstraints(0, 3, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Cw.setBorder(BorderFactory.createTitledBorder("Load crosswords"));
        InputCrossword = new JButton();
        InputCrossword.setText("Choose folder");
        Cw.add(InputCrossword, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        MainPanel.add(spacer1, new GridConstraints(0, 4, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        Options = new JPanel();
        Options.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        MainPanel.add(Options, new GridConstraints(0, 5, 2, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
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
        final Spacer spacer2 = new Spacer();
        MainPanel.add(spacer2, new GridConstraints(0, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        Browse = new JPanel();
        Browse.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        MainPanel.add(Browse, new GridConstraints(0, 8, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Browse.setBorder(BorderFactory.createTitledBorder("Browse"));
        Previous = new JButton();
        Previous.setEnabled(true);
        Previous.setText("Prev");
        Previous.putClientProperty("html.disable", Boolean.FALSE);
        Browse.add(Previous, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Next = new JButton();
        Next.setText("Next");
        Browse.add(Next, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */

}
