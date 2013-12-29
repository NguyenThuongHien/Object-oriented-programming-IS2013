/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplet1;

/**
 *
 * @author wukat
 */
public class Lokomotywa extends Wielobok {

    static int[] X = {0, 35, 35, 50, 45, 60, 55, 75, 75, 70, 75, 70, 55, 50, 55,
        45, 50, 45, 30, 25, 30, 20, 25, 20, 5, 0, 5, 0};
    static int[] Y = {225, 225, 250, 250, 225, 225, 250, 250, 280, 280, 290, 300, 300, 290, 280, 280,
        290, 300, 300, 290, 280, 280, 290, 300, 300, 290, 280, 280};

    public Lokomotywa() {
        super(X, Y);
    }

}
