/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author wukat
 */
public class Polynomials extends javax.swing.JFrame {

    /**
     * Text file input verifier
     */
    private class StrictInputVerifier extends InputVerifier {

        private final String validString;

        /**
         * Constructor
         * @param validString 
         */
        public StrictInputVerifier(String validString) {
            this.validString = validString;
        }

        @Override
        public boolean verify(JComponent input) {
            JTextField textField;
            textField = (JTextField) input;
            return textField.getText().matches(validString);
        }
    }

    /**
     * Creates new form Polynomials
     */
    public Polynomials() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        optionsPanel = new javax.swing.JPanel();
        drawButton = new javax.swing.JButton();
        factorsField = new javax.swing.JTextField();
        rangeBegField = new javax.swing.JTextField();
        rangeEndField = new javax.swing.JTextField();
        samplingField = new javax.swing.JTextField();
        factorsLabel = new javax.swing.JLabel();
        rangeBegLabel = new javax.swing.JLabel();
        rangeEndLabel = new javax.swing.JLabel();
        samplingLabel = new javax.swing.JLabel();
        graphPanelOut = new javax.swing.JPanel();
        graphPanelIn = new GraphPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Polynomial graph");

        optionsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Options"));

        drawButton.setText("Draw");
        drawButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawButtonActionPerformed(evt);
            }
        });

        factorsField.setText("1");
        factorsField.setInputVerifier(new StrictInputVerifier("[-?[0-9]*//.?[0-9]*,]*"));
        factorsField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                factorsFieldActionPerformed(evt);
            }
        });

        rangeBegField.setText("0");
        rangeBegField.setInputVerifier(samplingField.getInputVerifier());
        rangeBegField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rangeBegFieldActionPerformed(evt);
            }
        });

        rangeEndField.setText("100");
        rangeEndField.setInputVerifier(samplingField.getInputVerifier());
        rangeEndField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rangeEndFieldActionPerformed(evt);
            }
        });

        samplingField.setText("0.1");
        samplingField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        samplingField.setInputVerifier(new StrictInputVerifier("-?[0-9]{1,}\\.?[0-9]*"));
        samplingField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                samplingFieldActionPerformed(evt);
            }
        });

        factorsLabel.setText("Factors");

        rangeBegLabel.setText("Range from");

        rangeEndLabel.setText("Range to");

        samplingLabel.setText("Sampling");

        javax.swing.GroupLayout optionsPanelLayout = new javax.swing.GroupLayout(optionsPanel);
        optionsPanel.setLayout(optionsPanelLayout);
        optionsPanelLayout.setHorizontalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addGroup(optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(factorsLabel)
                    .addComponent(factorsField, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rangeBegLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rangeBegField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rangeEndField)
                    .addComponent(rangeEndLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(samplingLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(samplingField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(drawButton)
                .addGap(65, 65, 65))
        );
        optionsPanelLayout.setVerticalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(factorsLabel)
                    .addComponent(rangeBegLabel)
                    .addComponent(rangeEndLabel)
                    .addComponent(samplingLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rangeEndField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rangeBegField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(factorsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(samplingField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(drawButton)))
        );

        graphPanelOut.setBorder(javax.swing.BorderFactory.createTitledBorder("Graph"));

        graphPanelIn.setBackground(new java.awt.Color(254, 254, 254));

        javax.swing.GroupLayout graphPanelInLayout = new javax.swing.GroupLayout(graphPanelIn);
        graphPanelIn.setLayout(graphPanelInLayout);
        graphPanelInLayout.setHorizontalGroup(
            graphPanelInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        graphPanelInLayout.setVerticalGroup(
            graphPanelInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 226, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout graphPanelOutLayout = new javax.swing.GroupLayout(graphPanelOut);
        graphPanelOut.setLayout(graphPanelOutLayout);
        graphPanelOutLayout.setHorizontalGroup(
            graphPanelOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(graphPanelIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        graphPanelOutLayout.setVerticalGroup(
            graphPanelOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(graphPanelIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(graphPanelOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(graphPanelOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rangeEndFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rangeEndFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rangeEndFieldActionPerformed

    private void samplingFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_samplingFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_samplingFieldActionPerformed

    /**
     * Action for draw button
     * @param evt 
     */
    private void drawButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawButtonActionPerformed
        errorLog = null;
        Double temp;

        InputVerifier doubleVerifier = samplingField.getInputVerifier();

        if (!factorsField.getInputVerifier().verify(factorsField)) {
            errorLog = "Błędnie wpisane współczynniki (liczby rzeczywiste oddzielane przecinkami)!";
        } else if (!doubleVerifier.verify(rangeBegField)) {
            errorLog = "Błędnie wpisany początek zakresu (liczba rzeczywista)!";
        } else if (!doubleVerifier.verify(rangeEndField)) {
            errorLog = "Błędnie wpisany koniec zakresu (liczba rzeczywista)!";
        } else if (!doubleVerifier.verify(samplingField)) {
            errorLog = "Błędnie wpisane próbkowanie (liczba rzeczywista)!";
        } else if (Double.parseDouble(rangeBegField.getText()) == Double.parseDouble(rangeEndField.getText())) {
            errorLog = "Błędne dane wejściowe - początek równy końcowi zakresu!";
        } else {
            beginning = Double.parseDouble(rangeBegField.getText());
            end = Double.parseDouble(rangeEndField.getText());
            sampling = Double.parseDouble(samplingField.getText());
            factors = new LinkedList<>();
            for (String factor : factorsField.getText().split(",")) {
                factors.add(Double.parseDouble(factor));
            }
            if (beginning > end) {
                temp = beginning;
                beginning = end;
                end = temp;
            }
            points = new HashMap<>();
            for (Double i = beginning; i <= end; i += sampling) {
                points.put(i, fPoly(i));
            }
        }
        graphPanelIn.paint(graphPanelIn.getGraphics());
    }//GEN-LAST:event_drawButtonActionPerformed

    /**
     * Function counts value of polynomial in point x
     * @param x - argument
     * @return value f(x)
     */
    private Double fPoly(Double x) {
        Double result = 0.0;
        for (Double factor : factors) {
            result = result * x + factor;
        }
        return result;
    }

    private class GraphPanel extends JPanel {

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Integer width = this.getWidth() - 15;
            Integer height = this.getHeight() - 15;
            Long temp;
            if (errorLog != null) {
                g.drawString(errorLog, 10, 15);
            } else if (beginning != null) {
                g.drawLine(15, 0, 15, height);
                g.drawLine(15, height, width + 15, height);
                temp = Math.round(beginning);
                g.drawString(temp.toString(), 10, height + 15);
                temp = Math.round(end);
                g.drawString(temp.toString(), width - 10, height + 15);
                Double scaleX = width / Math.abs(beginning - end);

                Double minY = points.get(beginning);
                Double maxY = minY;
                for (Entry<Double, Double> point : points.entrySet()) {
                    if (point.getValue() < minY) {
                        minY = point.getValue();
                    } else if (point.getValue() > maxY) {
                        maxY = point.getValue();
                    }
                }

                if (maxY != minY) {
                    temp = Math.round(maxY);
                    g.drawString(temp.toString(), 0, 15);
                    temp = Math.round(minY);
                    g.drawString(temp.toString(), 0, height);
                    Double scaleY = height / Math.abs(maxY - minY);
                    Double prevX = null, prevY = null;
                    for (Double i = beginning; i <= end; i += sampling) {
                        if (prevY == null) {
                            prevY = points.get(i);
                            prevX = i;
                        } else {
                            g.drawLine((int) ((prevX - beginning) * scaleX) + 15, 
                                    (int) (height - (prevY - minY) * scaleY), (int) 
                                            ((i - beginning) * scaleX) + 15, (int) 
                                                    (height - (points.get(i) - minY) * scaleY));
                            prevY = points.get(i);
                            prevX = i;
                        }
                    }
                } else if (minY != 0) {
                    g.drawLine(15, (int) height/2, width + 15, (int) height/2);
                    temp = Math.round(maxY);
                    g.drawString(temp.toString(), 0, (int) height/2 + 5);                    
                }
            }
        }
    }

    private void rangeBegFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rangeBegFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rangeBegFieldActionPerformed

    private void factorsFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_factorsFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_factorsFieldActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Polynomials.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Polynomials().setVisible(true);
            }
        });
    }

    private String errorLog = null;
    private HashMap<Double, Double> points;
    private Double sampling;
    private LinkedList<Double> factors;
    private Double beginning;
    private Double end;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton drawButton;
    private javax.swing.JTextField factorsField;
    private javax.swing.JLabel factorsLabel;
    private javax.swing.JPanel graphPanelIn;
    private javax.swing.JPanel graphPanelOut;
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JTextField rangeBegField;
    private javax.swing.JLabel rangeBegLabel;
    private javax.swing.JTextField rangeEndField;
    private javax.swing.JLabel rangeEndLabel;
    private javax.swing.JTextField samplingField;
    private javax.swing.JLabel samplingLabel;
    // End of variables declaration//GEN-END:variables
}
