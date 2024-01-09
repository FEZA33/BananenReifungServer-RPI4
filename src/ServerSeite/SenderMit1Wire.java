/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerSeite;

/**
 *
 * @author pi
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



import java.io.IOException;
import java.io.PipedOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.IOException;
import java.io.PipedOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SenderMit1Wire extends Thread {

  

    public SenderMit1Wire() {

//     access=this.getDSAdapterE();
//        leseSensoren(access);
    }

    private double temp, damp, co2, ethylen;
    private int minTemp = 0;
    private int maxTemp = 1000;

    private int minDamp = 0;
    private int maxDamp = 1000;

    private int minCo2 = 0;
    private int maxCo2 = 500;

    private int minEthyl = 0;
    private int maxEthyl = 10;
    private double zufallsZahl = 0;
    private Read1WireBus wireBus;
    String sensorDaten = "";
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    // Declara un objeto de flujo de salida canalizado como remitente
    private PipedOutputStream pos = new PipedOutputStream();

    public PipedOutputStream getOutputStream() {
        return getPos();
    }

    @Override
    public synchronized void run() {
        setWireBus(new Read1WireBus());
        getWireBus().start();
        //TempReaderEinzeln tre = new TempReaderEinzeln();
        //  access = tre.getAdapterA();
        //   System.out.println("üüüüüüüüüüüüüüüüüüüü       " + tre.leseSensorenA());
        int i = 0;
     //    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
      // System.out.println("yyyy/MM/dd HH:mm:ss-> "+dtf.format(LocalDateTime.now()));

        String msg = "Hello World SENDER STARTTE  " + i;
        while (true) {
            
             //  System.out.println(" SENDER SENDET  DATA SInan ");
             
            try {
                //   msg = "temp= " + temp + " damp= " + damp + " Co2=" + co2 + " Ethyl.= " + ethylen;
                if (getWireBus().vectorReadyToRead) {

                    sensorDaten = getWireBus().getTempGesamt();
                    System.out.println( i+ "   "+dtf.format(LocalDateTime.now())+"   "+sensorDaten) ;
                  //  System.out.println(" SENDER SENDET  DATA    " + sensorDaten);
                    wireBus.vectorReadyToRead = false;
                    getWireBus().setTempGesamt("");
                    getWireBus().getVector().clear();//.setVector();...
                    
                     getPos().write(sensorDaten.getBytes());
                      i++;
                }

                //    System.out.println(" SENDER SENDET  DATA " + getData(i) +"   :   ");
                //    System.out.println(" SENDER SENDET  TEMP " + tt);
                //  System.out.println(" SENDER SENDET  GESAMT " + tt);
                //   getPos().write(getData(i).getBytes());
               

               
            } catch (IOException ex) {
                Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                Thread.sleep(1000);

            } catch (InterruptedException ex) {
                Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public String getDataReal(String sensordaten) {
        // zufallsZahl = getRandomNumber(minTemp, maxTemp);
        StringTokenizer st = new StringTokenizer(sensordaten, ";");

        setTemp(Float.parseFloat(st.nextToken()));//Mat) (getSinus(i) * 8));
        setDamp(Float.parseFloat(st.nextToken()));
        setCo2(Float.parseFloat(st.nextToken()) * 100);
        setEthylen(Float.parseFloat(st.nextToken()));

        //    System.out.println("Data write1 " + i + "  temp= " + getTemp() + " damp= " + getDamp() + " Co2= " + getCo2() + " Ethyl.= " + getEthylen());
        return getTemp() + " ; " + getDamp() + " ; " + getCo2() + " ; " + getEthylen() + " ; " + " ";
        //return i + " temp= " + getTemp() +";"+ " damp= " + getDamp() +";"+ " Co2= " + getCo2() +";"+ " Ethyl.= " + getEthylen()+";"+" ";

    }

    public String getData(int i) {
        // zufallsZahl = getRandomNumber(minTemp, maxTemp);
        setTemp((float) (getSinus(i) * 8));
        setDamp((float) (getCosinus(i) * 100));
        setCo2((float) (getTangens(i) * 1000));
        setEthylen((float) (getCosinus(i) * 100));

        //    System.out.println("Data write1 " + i + "  temp= " + getTemp() + " damp= " + getDamp() + " Co2= " + getCo2() + " Ethyl.= " + getEthylen());
        return getTemp() + " ; " + getDamp() + " ; " + getCo2() + " ; " + getEthylen() + " ; " + " ";
        //return i + " temp= " + getTemp() +";"+ " damp= " + getDamp() +";"+ " Co2= " + getCo2() +";"+ " Ethyl.= " + getEthylen()+";"+" ";

    }

    public int getRandomNumber(int min, int max) {

        return (int) ((Math.random() * (max - min)) + min);
    }

    public double getSinus(int i) {
        double zahl;

        zahl = (double) Math.abs(Math.sin(i * Math.PI / 180));
        //     System.out.println("Data COSINUS " + i + "   " + zahl);
        return zahl;
    }

    public double getCosinus(int i) {
        double zahl;

        zahl = (double) Math.abs(Math.cos(i * Math.PI / 180));
        //     System.out.println("Data COSINUS " + i + "   " + zahl);
        return zahl;
    }

    public double getTangens(int i) {
        double zahl;

        zahl = (double) Math.abs(Math.tan(i * Math.PI / 180));
        //     System.out.println("Data Tangens " + i + "   " + zahl);
        return zahl;
    }

    /**
     * @return the zufallsZahl
     */
    public double getZufallsZahl() {
        return zufallsZahl;
    }

    /**
     * @param zufallsZahl the zufallsZahl to set
     */
    public void setZufallsZahl(double zufallsZahl) {
        this.zufallsZahl = zufallsZahl;
    }

    /**
     * @return the pos
     */
    public PipedOutputStream getPos() {
        return pos;
    }

    /**
     * @param pos the pos to set
     */
    public void setPos(PipedOutputStream pos) {
        this.pos = pos;
    }

    /**
     * @return the temp
     */
    public double getTemp() {
        return temp;
    }

    /**
     * @param temp the temp to set
     */
    public void setTemp(double temp) {
        this.temp = temp;
    }

    /**
     * @return the damp
     */
    public double getDamp() {
        return damp;
    }

    /**
     * @param damp the damp to set
     */
    public void setDamp(double damp) {
        this.damp = damp;
    }

    /**
     * @return the co2
     */
    public double getCo2() {
        return co2;
    }

    /**
     * @param co2 the co2 to set
     */
    public void setCo2(double co2) {
        this.co2 = co2;
    }

    /**
     * @return the ethylen
     */
    public double getEthylen() {
        return ethylen;
    }

    /**
     * @param ethylen the ethylen to set
     */
    public void setEthylen(double ethylen) {
        this.ethylen = ethylen;
    }

    /**
     * @return the minTemp
     */
    public int getMinTemp() {
        return minTemp;
    }

    /**
     * @param minTemp the minTemp to set
     */
    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    /**
     * @return the maxTemp
     */
    public int getMaxTemp() {
        return maxTemp;
    }

    /**
     * @param maxTemp the maxTemp to set
     */
    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    /**
     * @return the minDamp
     */
    public int getMinDamp() {
        return minDamp;
    }

    /**
     * @param minDamp the minDamp to set
     */
    public void setMinDamp(int minDamp) {
        this.minDamp = minDamp;
    }

    /**
     * @return the maxDamp
     */
    public int getMaxDamp() {
        return maxDamp;
    }

    /**
     * @param maxDamp the maxDamp to set
     */
    public void setMaxDamp(int maxDamp) {
        this.maxDamp = maxDamp;
    }

    /**
     * @return the minCo2
     */
    public int getMinCo2() {
        return minCo2;
    }

    /**
     * @param minCo2 the minCo2 to set
     */
    public void setMinCo2(int minCo2) {
        this.minCo2 = minCo2;
    }

    /**
     * @return the maxCo2
     */
    public int getMaxCo2() {
        return maxCo2;
    }

    /**
     * @param maxCo2 the maxCo2 to set
     */
    public void setMaxCo2(int maxCo2) {
        this.maxCo2 = maxCo2;
    }

    /**
     * @return the minEthyl
     */
    public int getMinEthyl() {
        return minEthyl;
    }

    /**
     * @param minEthyl the minEthyl to set
     */
    public void setMinEthyl(int minEthyl) {
        this.minEthyl = minEthyl;
    }

    /**
     * @return the maxEthyl
     */
    public int getMaxEthyl() {
        return maxEthyl;
    }

    /**
     * @param maxEthyl the maxEthyl to set
     */
    public void setMaxEthyl(int maxEthyl) {
        this.maxEthyl = maxEthyl;
    }

    /**
     * @return the wireBus
     */
    public Read1WireBus getWireBus() {
        return wireBus;
    }

    /**
     * @param wireBus the wireBus to set
     */
    public void setWireBus(Read1WireBus wireBus) {
        this.wireBus = wireBus;
    }

}
