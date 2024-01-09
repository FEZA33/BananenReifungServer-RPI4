/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerSeite;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ServerClientThread extends Thread {

    Socket serverClient;

    int clientNo;
    int squre;

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
    SenderEmpfanger se;
    Prozess prozess;
    String ankommingData = "";

    ServerClientThread(Socket inSocket, int counter, SenderEmpfanger se, Prozess prozess) {
        serverClient = inSocket;
        clientNo = counter;
        this.se = se;
        this.prozess = prozess;

        //   this.tre = tre;
    }

    synchronized public void run() {
        int i = 0;

        DataInputStream inStream = null;
        try {
            inStream = new DataInputStream(serverClient.getInputStream());
            DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
            String clientMessage = "", serverMessage = "";
            while (true) {
                // while (!clientMessage.equals("bye")) {
                clientMessage = inStream.readUTF();
                if (clientMessage.equals("bye")) {
                    inStream.close();
                    outStream.close();
                    serverClient.close();
                    break;
                }
                if (clientMessage.equals("GETNEWISTDATA")) {
                    System.out.println(" Client SENDET " + clientMessage + " DATEN VORBREITET " + se.getReceiver().getNewData1());
                     updateTempAnzeige(se.getReceiver().getNewData1());
//                    serverMessage = String.valueOf(prozess.getSimulator().getjSliderTemp().getValue()) + ";"
//                            + String.valueOf(prozess.getSimulator().getjSliderDamp().getValue() + ";"
//                                    + String.valueOf(prozess.getSimulator().getjSliderCo2().getValue()) + ";"
//                                    + String.valueOf(prozess.getSimulator().getjSliderEthylen().getValue()));
                    if (serverMessage != null) {
                        serverMessage = se.getReceiver().getNewData1();
                    } else {
                        serverMessage = "0.0" + ";" + "0.0" + ";" + "0.0" + ";" + "0.0" + ";" + "0.0" + ";" + "0.0" + ";" + "0.0" + ";" + "0.0" + ";";
                    }
                    outStream.writeUTF(serverMessage);
                    outStream.flush();

                }
                if (clientMessage.equals("GETDATA")) {
                    System.out.println("NEW DATA  ");
                    serverMessage = "DATA GÖNDERIYORUM ";
                    outStream.writeUTF(serverMessage);
                    outStream.flush();

                }
                if (clientMessage.equals("SETSOLLDATA")) {
                    // Anfordeung
                    outStream.writeUTF("OK ICH WARTE");
                    outStream.flush();
                    ankommingData = inStream.readUTF();
                    //System.out.println("Yeni DATA GELDi  " + data);
                    prozess.prozessSollDaten(ankommingData);
                    
                    prozess.setProzessDatenInMenu() ;

                    outStream.writeUTF(ankommingData);
                    outStream.flush();
                    System.out.println("From Client-" + ankommingData);

                    prozess.setIsProzessdatenVorhanden(true);
                    updateSimulatorMenu();
                    System.out.println("PROBE ----  " + ankommingData);
                    System.out.println("PROBE ----  " + prozess.getROOMNAME() + "   " + prozess.getS1SETTEMP());
                }
                if (clientMessage.equals("ISTDATENVORHANDEN")) {
                    if (!prozess.isIsProzessdatenVorhanden()) {

                        outStream.writeUTF("NICHT VORHANDEN ");
                        outStream.flush();
                        System.out.println("PROBE ----  " + prozess.getROOMNAME() + " " + prozess.getS1SETTEMP());

                        prozess.setIsProzessdatenVorhanden(false);//.isIsProzessdatenVorhanden();
//                        outStream.writeUTF("VORHANDEN");
//                        outStream.flush();
                    } else {
                        outStream.writeUTF("JA VORHANDEN VORHANDEN");
                        outStream.flush();
                    }
                }
                if (clientMessage.equals("GETSTATUS")) {

                    serverMessage = prozess.getStatusWord();
                    outStream.writeUTF(serverMessage);
                    outStream.flush();
                }
                //    if (clientMessage.equals("GETDATA")) {
//                outStream.writeUTF(clientMessage);
//                outStream.flush();
                //  }
                //    System.out.println("LESE SENSOREN "+tre.leseSensorenA());
                //se.getSender().getWireBus().getTempGesamt();
            //    updateTempAnzeige();

                System.out.println("From Client-" + clientNo + ": Number is :" + clientMessage);
                Thread.sleep(100);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(prozess.getSimulator(), "Verbindung unterbrochen","ERROR", JOptionPane.ERROR_MESSAGE);
            
            
            Logger.getLogger(ServerClientThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerClientThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inStream.close();
            } catch (IOException ex) {
                Logger.getLogger(ServerClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void updateTempAnzeige(String text) {
       // String text = se.getSender().getWireBus().getTempGesamt();
        StringTokenizer st = new StringTokenizer(text, ";");
        prozess.getSimulator().getjLabelT1().setText(st.nextToken());
        prozess.getSimulator().getjLabelT2().setText(st.nextToken());
        prozess.getSimulator().getjLabelT3().setText(st.nextToken());
        prozess.getSimulator().getjLabelT4().setText(st.nextToken());
        prozess.getSimulator().getjLabelT5().setText(st.nextToken());
        prozess.getSimulator().getjLabelT6().setText(st.nextToken());
        prozess.getSimulator().getjLabelT7().setText(st.nextToken());
        prozess.getSimulator().getjLabelT8().setText(st.nextToken());
    }

    public void updateSimulatorMenu() {

        prozess.prozessSollDaten(ankommingData);
        float x = Float.valueOf(prozess.getS1SETTEMP());
        x = x / 10;
        prozess.getSimulator().getjLabelTempSoll().setText(String.valueOf(x));
        prozess.getSimulator().getjLabelDampSoll().setText(prozess.getS1SETHUM());
        prozess.getSimulator().getjLabelCo2Soll().setText(prozess.getS1SETCO2());
        prozess.getSimulator().getjLabelEthylenSoll().setText(prozess.getS1SETETHYL());

        prozess.getSimulator().getjLabelTempTol().setText(prozess.getS1SETTEMPTOL());
        prozess.getSimulator().getjLabelDampTol().setText(prozess.getS1SETHUMTOL());
        prozess.getSimulator().getjLabelCo2Tol().setText(prozess.getS1SETCO2TOL());
        prozess.getSimulator().getjLabelEthylenTol().setText(prozess.getS1SETETHYLTOL());

    }

    synchronized public String getDataReal(String sensordaten) {
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
//    public PipedOutputStream getPos() {
//        return pos;
//    }
//
//    /**
//     * @param pos the pos to set
//     */
//    public void setPos(PipedOutputStream pos) {
//        this.pos = pos;
//    }
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

}
