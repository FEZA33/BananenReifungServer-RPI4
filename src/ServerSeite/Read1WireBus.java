/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerSeite;

import com.pi4j.component.temperature.TemperatureSensor;
import com.pi4j.io.w1.W1Master;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class Read1WireBus extends Thread {


    W1Master w1Master;
    TemperatureSensor device;
    int deviceZähler = 0;
    private Vector vector;
    boolean vectorReadyToRead = false;
    String deviceTemp = "";
    private String tempGesamt = "";
    String deviceNummer = "";

    public Read1WireBus() {
        w1Master = new W1Master();
        vector = new Vector();
       // this.run();
       
    }

    public void leseSensoren() {

        while (true) {
            vectorReadyToRead = false;

            for (TemperatureSensor device : w1Master.getDevices(TemperatureSensor.class)) {
                getVector().add(device.getTemperature());
                getVector().add(device.getName());
                //     System.out.println(deviceZähler++ + "  " + device.getName() + " " + device.getTemperature());
                //   System.out.println("---------------------------");
                deviceZähler++;
                if (deviceZähler == 8) {
                    deviceZähler = 0;

                    if (!vectorReadyToRead) {
                        for (int j = 0; j < 15; j++) {
                            //     System.out.println("-----" + getTempGesamt());
                            // System.out.println(j + "  " + vector.get(j) + "-----" + vector.get(j));
                            deviceTemp = String.valueOf(getVector().get(j));//.get(j);
                            //temps1 = temp + ";" + temps1;
                            deviceNummer = String.valueOf(getVector().get(++j));//.get(j);
                            setTempGesamt(deviceTemp + ";" + getTempGesamt());
                            
//                            deviceNummer = String.valueOf(getVector().get(++j));//.get(j);
//                            setTempGesamt(deviceNummer + ";" + deviceTemp + ";" + getTempGesamt());
                            
                        }
                    //    System.out.println("-----" + getTempGesamt());
                        //System.out.println("-----" + temps1);
//                        setTempGesamt("");
//                        getVector().clear();
                        vectorReadyToRead = true;
                        
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Read1WireBus.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

    }

    @Override
    public void run() {
        System.out.println("RUN PROZESS GESTARTET-----" );
         leseSensoren();
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * @return the vector
     */
    public Vector getVector() {
        return vector;
    }

    /**
     * @param vector the vector to set
     */
    public void setVector(Vector vector) {
        this.vector = vector;
    }

    /**
     * @return the tempGesamt
     */
    public String getTempGesamt() {
        return tempGesamt;
    }

    /**
     * @param tempGesamt the tempGesamt to set
     */
    public void setTempGesamt(String tempGesamt) {
        this.tempGesamt = tempGesamt;
    }
}
