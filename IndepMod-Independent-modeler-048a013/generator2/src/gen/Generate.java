/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class Generate implements ActionListener {
    public void actionPerformed(ActionEvent e) {
       MapClass map = new MapClass();
       map.generate();
    }
}
