/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerSeite;

/**
 *
 * @author alisi
 */
public class ProzessMain {
    
     
    public static void main(String[] args) {
        
        SenderEmpfanger se = new SenderEmpfanger();
        
   //     Thread t1= new Thread ((Runnable) se);
        Simulator simulator = new Simulator();
        simulator.setVisible(true);
        Prozess pr = new Prozess("prozess", simulator);
        DataServer mm = new DataServer(se, pr);

        Thread thread = new Thread(pr);
        thread.start();
    }
}
