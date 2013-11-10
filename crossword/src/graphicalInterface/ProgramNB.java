/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ProgramNB.java
 *
 * Created on 2013-11-10, 00:48:03
 */
package graphicalInterface;

import Exceptions.FailedToGenerateCrosswordException;
import Strategies.EasyStrategy;
import browser.CwBrowser;
import dictionary.IntelLiCwDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author wukat
 */
public class ProgramNB extends javax.swing.JFrame {

    /** Creates new form ProgramNB */
    public ProgramNB() {
        importButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fc = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        ".txt", "txt");
                fc.setAcceptAllFileFilterUsed(false);
                fc.setFileFilter(filter);
                if (actionEvent.getSource() == importButton) {
                    int returnVal = fc.showDialog(importButton, "Import");

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        try {
                            browser.setDefaultCwDB(new IntelLiCwDB(fc.getSelectedFile().getPath()));
                        } catch (IOException e) {
                            logTextField.setText("Failed to import database");
                        }
                    }
                }
            }
        ;
        });

        loadButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (actionEvent.getSource() == loadButton) {
                    int returnVal = fc.showDialog(loadButton,
                            "Open directory");

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        try {
                            browser.loadFromFiles(fc.getSelectedFile().getPath());
                            actualizeButtons();
                        } catch (IOException e) {
                            logTextField.setText("Failed to load crosswords.");
                        }
                    }
                }
            }
        ;
        });

        generateButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getSource() == generateButton) {
                    if (hard.isSelected()) {
                        try {
                            browser.generateCw(Integer.parseInt(columns.getValue().toString()), Integer.parseInt(rows.getValue().toString()),
                                    easyStrategy);
                            actualizeButtons();
                        } catch (FailedToGenerateCrosswordException a) {
                            logTextField.setText("Failed to generate crossword from this database.");
                        }
                    } else {
                        try {
                            browser.generateCw(Integer.parseInt(columns.getValue().toString()), Integer.parseInt(rows.getValue().toString()),
                                    easyStrategy);
                            actualizeButtons();
                        } catch (FailedToGenerateCrosswordException a) {
                            logTextField.setText("Failed to generate crossword from this database.");
                        }
                    }
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (actionEvent.getSource() == saveButton) {
                    int returnVal = fc.showDialog(saveButton, "Save in directory");

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        try {
                            browser.saveActual(fc.getSelectedFile().getPath());
                        } catch (IOException e) {
                            logTextField.setText("Failed to save crossword.");
                        }
                    }
                }

            }
        });

        nextButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                browser.next(lastUsedNext);
                lastUsedNext = true;
                actualizeButtons();
            }
        });

        prevButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                browser.previous(lastUsedNext);
                lastUsedNext = false;
                actualizeButtons();
            }
        });

        lastUsedNext = true;
        actualizeButtons();

        printButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
//                try {
//                    EntriesPanel.print();
//                } catch (Exception e) {
//                }
            }
        });
        solveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // To change body of implemented methods use File | Settings |
                // File Templates.
            }
        });
        initComponents();
    }

    public void actualizeButtons() {
        if (lastUsedNext) {
            prevButton.setEnabled(browser.hasNext());
            prevButton.setEnabled(browser.previousIndex() > 0);
        } else {
            prevButton.setEnabled(browser.hasPrevious());
            nextButton.setEnabled(browser.nextIndex() < browser.getAmountOfCrosswords());
        }
        saveButton.setEnabled(browser.hasActual());
        solveButton.setEnabled(browser.hasActual());
        printButton.setEnabled(browser.hasActual());
        logTextField.setText("");
        if (browser.hasActual()) {
            show();
        }
        
    }

    public void show() {
//        EntriesPanel.setText(easyStrategy.printAllEntries(browser.getActual()));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonStrategyGropu = new javax.swing.ButtonGroup();
        generatePanel = new javax.swing.JPanel();
        easy = new javax.swing.JRadioButton();
        hard = new javax.swing.JRadioButton();
        columnsLabel = new javax.swing.JLabel();
        rowsLabel = new javax.swing.JLabel();
        columns = new javax.swing.JSpinner();
        rows = new javax.swing.JSpinner();
        generateButton = new javax.swing.JButton();
        fromFilePanel = new javax.swing.JPanel();
        importLabel = new javax.swing.JLabel();
        importButton = new javax.swing.JButton();
        loadLabel = new javax.swing.JLabel();
        loadButton = new javax.swing.JButton();
        optionsPanel = new javax.swing.JPanel();
        solveButton = new javax.swing.JButton();
        printButton = new javax.swing.JButton();
        browsePanel = new javax.swing.JPanel();
        prevButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        crosswordPanel = new javax.swing.JPanel();
        canvasCrossword = new java.awt.Canvas();
        errorLogPanel = new javax.swing.JPanel();
        logTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Crosswords by wukat");

        generatePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Generate"));

        buttonStrategyGropu.add(easy);
        easy.setSelected(true);
        easy.setText("Easy");
        easy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                easyActionPerformed(evt);
            }
        });

        buttonStrategyGropu.add(hard);
        hard.setText("Hard");

        columnsLabel.setText("Columns");

        rowsLabel.setText("Rows");

        columns.setModel(new javax.swing.SpinnerNumberModel(5, 3, 12, 1));

        rows.setModel(new javax.swing.SpinnerNumberModel(5, 3, 12, 1));

        generateButton.setText("Generate");

        javax.swing.GroupLayout generatePanelLayout = new javax.swing.GroupLayout(generatePanel);
        generatePanel.setLayout(generatePanelLayout);
        generatePanelLayout.setHorizontalGroup(
            generatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generatePanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(easy)
                .addGap(6, 6, 6)
                .addComponent(hard))
            .addGroup(generatePanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(columnsLabel)
                .addGap(12, 12, 12)
                .addComponent(columns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(generatePanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(rowsLabel)
                .addGap(33, 33, 33)
                .addComponent(rows, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(generatePanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(generateButton))
        );
        generatePanelLayout.setVerticalGroup(
            generatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generatePanelLayout.createSequentialGroup()
                .addGroup(generatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(easy)
                    .addComponent(hard))
                .addGap(12, 12, 12)
                .addGroup(generatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generatePanelLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(columnsLabel))
                    .addComponent(columns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(generatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generatePanelLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(rowsLabel))
                    .addComponent(rows, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(generateButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        fromFilePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("From file"));

        importLabel.setText("Import database");

        importButton.setText("Choose file");

        loadLabel.setText("Load crosswords");

        loadButton.setText("Choose folder");

        javax.swing.GroupLayout fromFilePanelLayout = new javax.swing.GroupLayout(fromFilePanel);
        fromFilePanel.setLayout(fromFilePanelLayout);
        fromFilePanelLayout.setHorizontalGroup(
            fromFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fromFilePanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(importLabel))
            .addGroup(fromFilePanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(loadLabel))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fromFilePanelLayout.createSequentialGroup()
                .addContainerGap(77, Short.MAX_VALUE)
                .addComponent(importButton)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fromFilePanelLayout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addComponent(loadButton)
                .addContainerGap())
        );
        fromFilePanelLayout.setVerticalGroup(
            fromFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fromFilePanelLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(importLabel)
                .addGap(12, 12, 12)
                .addComponent(importButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(loadLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(loadButton)
                .addGap(21, 21, 21))
        );

        optionsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Options"));

        solveButton.setText("Solve");

        printButton.setText("Print");

        javax.swing.GroupLayout optionsPanelLayout = new javax.swing.GroupLayout(optionsPanel);
        optionsPanel.setLayout(optionsPanelLayout);
        optionsPanelLayout.setHorizontalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addComponent(solveButton)
                .addGap(6, 6, 6)
                .addComponent(printButton))
        );
        optionsPanelLayout.setVerticalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(solveButton))
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(printButton))
        );

        browsePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Browse"));

        prevButton.setText("Prev");

        nextButton.setText("Next");

        saveButton.setText("Save");

        javax.swing.GroupLayout browsePanelLayout = new javax.swing.GroupLayout(browsePanel);
        browsePanel.setLayout(browsePanelLayout);
        browsePanelLayout.setHorizontalGroup(
            browsePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(browsePanelLayout.createSequentialGroup()
                .addGroup(browsePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(browsePanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(prevButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(nextButton))
                    .addGroup(browsePanelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(saveButton)))
                .addContainerGap())
        );
        browsePanelLayout.setVerticalGroup(
            browsePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(browsePanelLayout.createSequentialGroup()
                .addGroup(browsePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prevButton)
                    .addComponent(nextButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveButton)
                .addContainerGap())
        );

        crosswordPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Crossword"));

        canvasCrossword.setBackground(new java.awt.Color(-1,true));
        canvasCrossword.setName(""); // NOI18N

        javax.swing.GroupLayout crosswordPanelLayout = new javax.swing.GroupLayout(crosswordPanel);
        crosswordPanel.setLayout(crosswordPanelLayout);
        crosswordPanelLayout.setHorizontalGroup(
            crosswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crosswordPanelLayout.createSequentialGroup()
                .addComponent(canvasCrossword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(446, Short.MAX_VALUE))
        );
        crosswordPanelLayout.setVerticalGroup(
            crosswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crosswordPanelLayout.createSequentialGroup()
                .addComponent(canvasCrossword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(201, Short.MAX_VALUE))
        );

        errorLogPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Error log"));

        logTextField.setEditable(false);
        logTextField.setDisabledTextColor(new java.awt.Color(-65536,true));

        javax.swing.GroupLayout errorLogPanelLayout = new javax.swing.GroupLayout(errorLogPanel);
        errorLogPanel.setLayout(errorLogPanelLayout);
        errorLogPanelLayout.setHorizontalGroup(
            errorLogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(errorLogPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(logTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        errorLogPanelLayout.setVerticalGroup(
            errorLogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(errorLogPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(crosswordPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(errorLogPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(generatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fromFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(browsePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(optionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(browsePanel, 0, 94, Short.MAX_VALUE))
                    .addComponent(generatePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fromFilePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorLogPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crosswordPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void easyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_easyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_easyActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProgramNB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProgramNB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProgramNB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProgramNB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ProgramNB().setVisible(true);
                JFrame frame = new JFrame("Crosswords by wukat");
                try {
                    browser = new CwBrowser(null);

                    frame.setContentPane(new ProgramNB());
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                } catch (IOException e) {

                    Object[] options = {"Yes", "No",};

                    int n = JOptionPane.showOptionDialog(
                            frame,
                            "Failed to load default data base. Do you want to choose it manually?",
                            "Failed to start", JOptionPane.YES_NO_OPTION,
                            JOptionPane.ERROR_MESSAGE, null, options,
                            options[0]);

                    if (n == 0) {
                        JFileChooser fc = new JFileChooser();
                        int returnVal = fc.showDialog(fc, "Import database");
                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            try {
                                browser = new CwBrowser(fc.getSelectedFile().getAbsolutePath());
                                frame.setContentPane(new Program());
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.pack();
                                frame.setVisible(true);
                            } catch (IOException a) {
                                JOptionPane.showMessageDialog(frame,
                                        "Failed to load data base.", "Failed to start",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(frame,
                                    "Operation canceled, program halts.",
                                    "Failed to start", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame,
                                "Operation canceled, program halts.",
                                "Failed to start", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
    }
    private static CwBrowser browser;
    private static EasyStrategy easyStrategy = new EasyStrategy();
    private boolean lastUsedNext = true;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel browsePanel;
    private javax.swing.ButtonGroup buttonStrategyGropu;
    private java.awt.Canvas canvasCrossword;
    private javax.swing.JSpinner columns;
    private javax.swing.JLabel columnsLabel;
    private javax.swing.JPanel crosswordPanel;
    private javax.swing.JRadioButton easy;
    private javax.swing.JPanel errorLogPanel;
    private javax.swing.JPanel fromFilePanel;
    private javax.swing.JButton generateButton;
    private javax.swing.JPanel generatePanel;
    private javax.swing.JRadioButton hard;
    private javax.swing.JButton importButton;
    private javax.swing.JLabel importLabel;
    private javax.swing.JButton loadButton;
    private javax.swing.JLabel loadLabel;
    private javax.swing.JTextField logTextField;
    private javax.swing.JButton nextButton;
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JButton prevButton;
    private javax.swing.JButton printButton;
    private javax.swing.JSpinner rows;
    private javax.swing.JLabel rowsLabel;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton solveButton;
    // End of variables declaration//GEN-END:variables
}
