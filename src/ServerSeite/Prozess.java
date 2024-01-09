/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerSeite;

import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Color;

/**
 *
 * @author alisi
 */
public final class Prozess extends Thread {

    private Thread t;
    private String threadName;
    private boolean isProzessdatenVorhanden = false;
    private boolean circulatingLeft, circulatingRight, suction, damp, co2, ethylen, cooler, heater = false;
    // PrintDemo PD;
    private Simulator simulator;

    private long s1Duration, s2Duration, s3Duration, s4Duration, s1Anfang, s2Anfang, s3Anfang, s4Anfang;

    private String ROOMNAME;
    private String PRODUKTSELECTION;
    private String USERSELECTION;
    private String PRODUKTNRSELECTION;
    private String SPLITMENGE;
    private String ETHYLSETPUTTIME;
    private String DELAYTIMEETHYLTOMES;
    private String CIRCULATINGAIR;
    private String CIRCULATINAIRDELAY;
    private String HEATERDELAY;
    private String COOLERDELAY;
    private String DAMPERDELAY;
    private String FRESHAIRDELAY;
    private String BEGINTIMEHOUR;
    private String BEGINTIMEMIN;
    private String IPADRESS;
    private String PORTNR;
    private String S1DURATIONDAY;
    private String S1DURATIONHOUR;
    private String S1DURATIONMIN;
    private String S1SETTEMP;
    private String S1SETTEMPANZEIGE;
    private String S1SETTEMPTOL;
    private String S1SETHUM;
    private String S1SETHUMANZEIGE;
    private String S1SETHUMTOL;
    private String S1SETCO2;
    private String S1SETCO2ANZEIGE;
    private String S1SETCO2TOL;
    private String S1SETETHYL;
    private String S1SETETHYLANZEIGE;
    private String S1SETETHYLTOL;
    private String S2DURATIONDAY;
    private String S2DURATIONHOUR;
    private String S2DURATIONMIN;
    private String S2SETTEMP;
    private String S2SETTEMPANZEIGE;
    private String S2SETTEMPTOL;
    private String S2SETHUM;
    private String S2SETHUMANZEIGE;
    private String S2SETHUMTOL;
    private String S2SETCO2;
    private String S2SETCO2ANZEIGE;
    private String S2SETCO2TOL;
    private String S2SETETHYL;
    private String S2SETETHYLANZEIGE;
    private String S2SETETHYLTOL;
    private String S3DURATIONDAY;
    private String S3DURATIONHOUR;
    private String S3DURATIONMIN;
    private String S3SETTEMP;
    private String S3SETTEMPANZEIGE;
    private String S3SETTEMPTOL;
    private String S3SETHUM;
    private String S3SETHUMANZEIGE;
    private String S3SETHUMTOL;
    private String S3SETCO2;
    private String S3SETCO2ANZEIGE;
    private String S3SETCO2TOL;
    private String S3SETETHYL;
    private String S3SETETHYLANZEIGE;
    private String S3SETETHYLTOL;
    private String S4DURATIONDAY;
    private String S4DURATIONHOUR;
    private String S4DURATIONMIN;
    private String S4SETTEMP;
    private String S4SETTEMPANZEIGE;
    private String S4SETTEMPTOL;
    private String S4SETHUM;
    private String S4SETHUMANZEIGE;
    private String S4SETHUMTOL;
    private String S4SETCO2;
    private String S4SETCO2ANZEIGE;
    private String S4SETCO2TOL;
    private String S4SETETHYL;
    private String S4SETETHYLANZEIGE;
    private String S4SETETHYLTOL;
    private String PROZESSBEGINN;
    private String PROZESSEND;
    private String aktuelleRoomValues;
    private String tempIst, humIst, co2Ist, ethylIst;
    private String multiDamp, multiTemp, z1In, z1Out, z2In, z2Out, prT1, prT2, prT3, mainTemp1Wire;

    public Prozess(String tName, Simulator sim) {
        this.threadName = tName;
        this.simulator = sim;

        this.setS1SETTEMP("20");//.getS.getS1SETTEMP()="20";
        this.setS1SETTEMPTOL("2");
        this.setS1SETHUM("20");
        this.setS1SETHUMTOL("20");
        this.setCirculatingLeft(true);
        this.setCirculatingRight(true);
        this.setSuction(true);
        this.setHeater(true);
        this.setCooler(true);
        this.setEthylen(true);
        this.setCo2(true);
        this.setDamp(true);
        setRelais();
        System.out.println(getStatusWord());
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Prozess.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setCirculatingLeft(false);
        this.setCirculatingRight(true);
        this.setSuction(false);
        this.setHeater(false);
        this.setCooler(true);
        this.setEthylen(false);
        this.setCo2(false);
        this.setDamp(false);
        setRelais();
        System.out.println(getStatusWord());
        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(Prozess.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.start();

    }

    public void run() {

        while (true) {
            //   System.out.println("Prozess Läuft" + "  Thread  " + threadName + " exiting.");
            prozessArbeit();
            this.setRelais();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Prozess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
//public void checkData(){
//    if (prozessdatenVorhanden)
//}

    public void start() {

        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }

    public void setProzessDatenInMenu() {
 //     simulator.getjTextArea1().append("Start");
       
        simulator.getjTextArea1().append("ROOMNAME                " + this.getROOMNAME() + "\n");
        simulator.getjTextArea1().append("PRODUKTSELECTION        " + this.getPRODUKTSELECTION() + "\n");
        simulator.getjTextArea1().append("USERSELECTION           " + this.getUSERSELECTION() + "\n");
        simulator.getjTextArea1().append("PRODUKTNRSELECTION      " + this.getPRODUKTNRSELECTION() + "\n");
        simulator.getjTextArea1().append("SPLITMENGE              " + this.getSPLITMENGE() + "\n");
        simulator.getjTextArea1().append("ETHYLSETPUTTIME         " + this.getETHYLSETPUTTIME() + "\n");
        simulator.getjTextArea1().append("DELAYTIMEETHYLTOMES     " + this.getDELAYTIMEETHYLTOMES() + "\n");
        simulator.getjTextArea1().append("CIRCULATINGAIR          " + this.getCIRCULATINGAIR() + "\n");
        simulator.getjTextArea1().append("CIRCULATINAIRDELAY      " + this.getCIRCULATINAIRDELAY() + "\n");
        simulator.getjTextArea1().append("HEATERDELAY             " + this.getHEATERDELAY() + "\n");
        simulator.getjTextArea1().append("COOLERDELAY             " + this.getCOOLERDELAY() + "\n");
        simulator.getjTextArea1().append("DAMPERDELAY             " + this.getDAMPERDELAY() + "\n");
        simulator.getjTextArea1().append("FRESHAIRDELAY           " + this.getFRESHAIRDELAY() + "\n");
        simulator.getjTextArea1().append("BEGINTIMEHOUR           " + this.getBEGINTIMEHOUR() + "\n");
        simulator.getjTextArea1().append("BEGINTIMEMIN            " + this.getBEGINTIMEMIN() + "\n");
        simulator.getjTextArea1().append("IPADRESS                " + this.getIPADRESS() + "\n");
        simulator.getjTextArea1().append("PORTNR                  " + this.getPORTNR() + "\n");

        simulator.getjTextArea1().append("S1DURATIONDAY           " + this.getS1DURATIONDAY() + "\n");
        simulator.getjTextArea1().append("S1DURATIONHOUR          " + this.getS1DURATIONHOUR() + "\n");
        simulator.getjTextArea1().append("S1DURATIONMIN           " + this.getS1DURATIONMIN() + "\n");
        simulator.getjTextArea1().append("S1SETTEMP               " + this.getS1SETTEMP() + "\n");
        simulator.getjTextArea1().append("S1SETTEMPTOL            " + this.getS1SETTEMPTOL() + "\n");
       // simulator.getjTextArea1().append("S1SETTEMPANZEIGE" + this.getS1SETTEMPANZEIGE() + "\n");

        simulator.getjTextArea1().append("S1SETHUM                " + this.getS1SETHUM() + "\n");
        simulator.getjTextArea1().append("S1SETHUMTOL             " + this.getS1SETHUMTOL() + "\n");
       // simulator.getjTextArea1().append("S1SETHUMANZEIGE" + this.getS1SETHUMANZEIGE() + "\n");

        simulator.getjTextArea1().append("S1SETCO2                " + this.getS1SETCO2() + "\n");
        simulator.getjTextArea1().append("S1SETCO2TOL             " + this.getS1SETCO2TOL() + "\n");
       // simulator.getjTextArea1().append("S1SETCO2ANZEIGE" + this.getS1SETCO2ANZEIGE() + "\n");

        simulator.getjTextArea1().append("S1SETETHYL              " + this.getS1SETETHYL() + "\n");
        simulator.getjTextArea1().append("S1SETETHYLTOL           " + this.getS1SETETHYLTOL() + "\n");
  //      simulator.getjTextArea1().append("S1SETETHYLANZEIGE" + this.getS1SETETHYLANZEIGE() + "\n");
//

        simulator.getjTextArea1().append("S2DURATIONDAY             " + this.getS2DURATIONDAY() + "\n");
        simulator.getjTextArea1().append("S2DURATIONHOUR            " + this.getS2DURATIONHOUR() + "\n");
        simulator.getjTextArea1().append("S2DURATIONMIN             " + this.getS2DURATIONMIN() + "\n");
        simulator.getjTextArea1().append("S2SETTEMP                 " + this.getS2SETTEMP() + "\n");
        simulator.getjTextArea1().append("S2SETTEMPTOL              " + this.getS2SETTEMPTOL() + "\n");
       // simulator.getjTextArea1().append("S2SETTEMPANZEIGE" + this.getS2SETTEMPANZEIGE() + "\n");

        simulator.getjTextArea1().append("S2SETHUM                  " + this.getS2SETHUM() + "\n");
        simulator.getjTextArea1().append("S2SETHUMTOL               " + this.getS2SETHUMTOL() + "\n");
       // simulator.getjTextArea1().append("S2SETHUMANZEIGE" + this.getS2SETHUMANZEIGE() + "\n");

        simulator.getjTextArea1().append("S2SETCO2                  " + this.getS2SETCO2() + "\n");
        simulator.getjTextArea1().append("S2SETCO2TOL               " + this.getS2SETCO2TOL() + "\n");
       // simulator.getjTextArea1().append("S2SETCO2ANZEIGE" + this.getS2SETCO2ANZEIGE() + "\n");

        simulator.getjTextArea1().append("S2SETETHYL                " + this.getS2SETETHYL() + "\n");
        simulator.getjTextArea1().append("S2SETETHYLTOL             " + this.getS2SETETHYLTOL() + "\n");
       // simulator.getjTextArea1().append("S2SETETHYLANZEIGE" + this.getS2SETETHYLANZEIGE() + "\n");
//
        simulator.getjTextArea1().append("S3DURATIONDAY             " + this.getS3DURATIONDAY() + "\n");
        simulator.getjTextArea1().append("S3DURATIONHOUR            " + this.getS3DURATIONHOUR() + "\n");
        simulator.getjTextArea1().append("S3DURATIONMIN             " + this.getS3DURATIONMIN() + "\n");
        simulator.getjTextArea1().append("S3SETTEMP                 " + this.getS3SETTEMP() + "\n");
        simulator.getjTextArea1().append("S3SETTEMPTOL              " + this.getS3SETTEMPTOL() + "\n");
     //   simulator.getjTextArea1().append("S3SETTEMPANZEIGE" + this.getS3SETTEMPANZEIGE() + "\n");

        simulator.getjTextArea1().append("S3SETHUM                  " + this.getS3SETHUM() + "\n");
        simulator.getjTextArea1().append("S3SETHUMTOL               " + this.getS3SETHUMTOL() + "\n");
     //   simulator.getjTextArea1().append("S3SETHUMANZEIGE" + this.getS3SETHUMANZEIGE() + "\n");

        simulator.getjTextArea1().append("S3SETCO2                  " + this.getS3SETCO2() + "\n");
        simulator.getjTextArea1().append("S3SETCO2TOL               " + this.getS3SETCO2TOL() + "\n");
     //   simulator.getjTextArea1().append("S3SETCO2ANZEIGE" + this.getS3SETCO2ANZEIGE() + "\n");

        simulator.getjTextArea1().append("S3SETETHYL                " + this.getS3SETETHYL() + "\n");
        simulator.getjTextArea1().append("S3SETETHYLTOL             " + this.getS3SETETHYLTOL() + "\n");
      //  simulator.getjTextArea1().append("S3SETETHYLANZEIGE" + this.getS3SETETHYLANZEIGE() + "\n");

//
        simulator.getjTextArea1().append("S4DURATIONDAY             " + this.getS4DURATIONDAY() + "\n");
        simulator.getjTextArea1().append("S4DURATIONHOUR            " + this.getS4DURATIONHOUR() + "\n");
        simulator.getjTextArea1().append("S4DURATIONMIN             " + this.getS4DURATIONMIN() + "\n");
        simulator.getjTextArea1().append("S4SETTEMP                 " + this.getS4SETTEMP() + "\n");
        simulator.getjTextArea1().append("S4SETTEMPTOL              " + this.getS4SETTEMPTOL() + "\n");
     //     simulator.getjTextArea1().append("S4SETTEMPANZEIGE" + this.getS4SETTEMPANZEIGE() + "\n");

        simulator.getjTextArea1().append("S4SETHUM                  " + this.getS4SETHUM() + "\n");
        simulator.getjTextArea1().append("S4SETHUMTOL               " + this.getS4SETHUMTOL() + "\n");
      //  simulator.getjTextArea1().append("S4SETHUMANZEIGE" + this.getS4SETHUMANZEIGE() + "\n");

        simulator.getjTextArea1().append("S4SETCO2                  " + this.getS4SETCO2() + "\n");
        simulator.getjTextArea1().append("S4SETCO2TOL               " + this.getS4SETCO2TOL() + "\n");
      //  simulator.getjTextArea1().append("S4SETCO2ANZEIGE" + this.getS4SETCO2ANZEIGE() + "\n");

        simulator.getjTextArea1().append("S4SETETHYL                " + this.getS4SETETHYL() + "\n");
        simulator.getjTextArea1().append("S4SETETHYLTOL             " + this.getS4SETETHYLTOL() + "\n");
      //  simulator.getjTextArea1().append("S4SETETHYLANZEIGE" + this.getS4SETETHYLANZEIGE() + "\n");

        simulator.getjTextArea1().append("PROZESSBEGINN             " + this.getPROZESSBEGINN() + "\n");
        simulator.getjTextArea1().append("PROZESSEND                " + this.getPROZESSEND() + "\n");
        simulator.getjTextArea1().append("-------------------------------------------------" + "\n");
        
        simulator.getjTextArea1().setCaretPosition(simulator.getjTextArea1().getDocument().getLength());

    }

    private void prozessArbeit() {

        // System.out.println("SOLL TEMPERATUR   --------------" + this.getS1SETTEMP());
        float tempSoll = Float.valueOf(this.getS1SETTEMP()) / 10;
        float tempIst = simulator.getjSliderTemp().getValue();
        float tol = Float.valueOf(this.getS1SETTEMPTOL());
        // System.out.println("tempsoll = " + tempSoll + " tempist =  " + tempIst + " Tol = " + tol);

        if (tempIst >= tempSoll + tol) {
            this.setHeater(false);
            this.setCooler(true);
        } else {
            this.setHeater(false);
            this.setCooler(false);
        }

        if (tempIst <= tempSoll - tol) {
            this.setHeater(true);
            this.setCooler(false);
        }
        // DAMP
        //     System.out.println("SOLL Humidity   --------------" + this.getS1SETHUM());
        int dampSoll = Integer.valueOf(this.getS1SETHUM());
        int dampIst = simulator.getjSliderDamp().getValue();
        int dampTol = Integer.valueOf(this.getS1SETHUMTOL());
        //     System.out.println("Dampsoll = " + dampSoll + " dampist =  " + dampIst + " dampTol = " + dampTol);

        if (dampIst >= dampSoll + dampTol) {
            this.setDamp(true);//.setCo2(true);//.setHeater(false);

        } else {
            this.setDamp(false);//.setHeater(false);
            // this.setCooler(false);
        }

        // Etylen 
//         else{
//             this.setHeater(false);
//            this.setCooler(false);
//        }
    }

    public String getStatusWord() {
        String text = "circulatingLeft" + ";" + String.valueOf(this.isCirculatingLeft())
                + ";" + "circulatingRight" + ";" + String.valueOf(this.isCirculatingRight())
                + ";" + "suction" + ";" + String.valueOf(this.isSuction())
                + ";" + "damp" + ";" + String.valueOf(this.isDamp())
                + ";" + "co2" + ";" + String.valueOf(this.isCo2())
                + ";" + "ethylen" + ";" + String.valueOf(this.isEthylen())
                + ";" + "cooler" + ";" + String.valueOf(this.isCooler())
                + ";" + "heater" + ";" + String.valueOf(this.isHeater());
        return text;
    }

    public void setRelais() {

        if (this.circulatingLeft) {
            simulator.getCirculatingLeftP().setBackground(Color.red);
        } else {
            simulator.getCirculatingLeftP().setBackground(Color.GREEN);
        }

        if (this.isCirculatingRight()) {
            simulator.getCirculatingRightP().setBackground(Color.red);
        } else {
            simulator.getCirculatingRightP().setBackground(Color.GREEN);
        }

        if (this.isSuction()) {
            simulator.getSuctionP().setBackground(Color.red);
        } else {
            simulator.getSuctionP().setBackground(Color.GREEN);
        }
        if (this.isDamp()) {
            simulator.getDamperP().setBackground(Color.red);
        } else {
            simulator.getDamperP().setBackground(Color.GREEN);
        }
        if (this.isCo2()) {
            simulator.getFreshairP().setBackground(Color.red);
        } else {
            simulator.getFreshairP().setBackground(Color.GREEN);
        }
        if (this.isHeater()) {
            simulator.getHeaterP().setBackground(Color.red);
        } else {
            simulator.getHeaterP().setBackground(Color.GREEN);
        }
        if (this.isCooler()) {
            simulator.getCoolerP().setBackground(Color.red);
        } else {
            simulator.getCoolerP().setBackground(Color.GREEN);
        }
        if (this.isEthylen()) {
            simulator.getEthylenP().setBackground(Color.red);
        } else {
            simulator.getEthylenP().setBackground(Color.GREEN);
        }
       
    }

    public void prozessSollDaten(String text) {
        StringTokenizer st = new StringTokenizer(text, ";");
        st.nextToken();
        this.setROOMNAME(st.nextToken());
        st.nextToken();
        this.setUSERSELECTION(st.nextToken());
        st.nextToken();
        this.setPRODUKTSELECTION(st.nextToken());
        st.nextToken();
        this.setPRODUKTNRSELECTION(st.nextToken());
        st.nextToken();
        this.setSPLITMENGE(st.nextToken());
        st.nextToken();
        this.setETHYLSETPUTTIME(st.nextToken());
        st.nextToken();
        this.setDELAYTIMEETHYLTOMES(st.nextToken());
        st.nextToken();
        this.setCIRCULATINGAIR(st.nextToken());
        st.nextToken();
        this.setCIRCULATINAIRDELAY(st.nextToken());
        st.nextToken();
        this.setHEATERDELAY(st.nextToken());
        st.nextToken();
        this.setCOOLERDELAY(st.nextToken());
        st.nextToken();
        this.setDAMPERDELAY(st.nextToken());
        st.nextToken();
        this.setFRESHAIRDELAY(st.nextToken());
        st.nextToken();
        this.setBEGINTIMEHOUR(st.nextToken());
        st.nextToken();
        this.setBEGINTIMEMIN(st.nextToken());
        st.nextToken();
        this.setIPADRESS(st.nextToken());
        st.nextToken();
        this.setPORTNR(st.nextToken());
        st.nextToken();
        this.setS1DURATIONDAY(st.nextToken());
        st.nextToken();
        this.setS1DURATIONHOUR(st.nextToken());
        st.nextToken();
        this.setS1DURATIONMIN(st.nextToken());
        st.nextToken();
        this.setS1SETTEMP(st.nextToken());
        st.nextToken();
        this.setS1SETTEMPTOL(st.nextToken());
        st.nextToken();
        this.setS1SETHUM(st.nextToken());
        st.nextToken();
        this.setS1SETHUMTOL(st.nextToken());
        st.nextToken();
        this.setS1SETCO2(st.nextToken());
        st.nextToken();
        this.setS1SETCO2TOL(st.nextToken());
        st.nextToken();
        this.setS1SETETHYL(st.nextToken());
        st.nextToken();
        this.setS1SETETHYLTOL(st.nextToken());
        st.nextToken();
        this.setS2DURATIONDAY(st.nextToken());
        st.nextToken();
        this.setS2DURATIONHOUR(st.nextToken());
        st.nextToken();
        this.setS2DURATIONMIN(st.nextToken());
        st.nextToken();
        this.setS2SETTEMP(st.nextToken());
        st.nextToken();
        this.setS2SETTEMPTOL(st.nextToken());
        st.nextToken();
        this.setS2SETHUM(st.nextToken());
        st.nextToken();
        this.setS2SETHUMTOL(st.nextToken());
        st.nextToken();
        this.setS2SETCO2(st.nextToken());
        st.nextToken();
        this.setS2SETCO2TOL(st.nextToken());
        st.nextToken();
        this.setS2SETETHYL(st.nextToken());
        st.nextToken();
        this.setS2SETETHYLTOL(st.nextToken());
         st.nextToken();
        this.setS3DURATIONDAY(st.nextToken());
        st.nextToken();
        this.setS3DURATIONHOUR(st.nextToken());
        st.nextToken();
        this.setS3DURATIONMIN(st.nextToken());
        st.nextToken();
        this.setS3SETTEMP(st.nextToken());
        st.nextToken();
        this.setS3SETTEMPTOL(st.nextToken());
        st.nextToken();
        this.setS3SETHUM(st.nextToken());
        st.nextToken();
        this.setS3SETHUMTOL(st.nextToken());
        st.nextToken();
        this.setS3SETCO2(st.nextToken());
        st.nextToken();
        this.setS3SETCO2TOL(st.nextToken());
        st.nextToken();
        this.setS3SETETHYL(st.nextToken());
        st.nextToken();
        this.setS3SETETHYLTOL(st.nextToken());
        st.nextToken();
        this.setS4DURATIONDAY(st.nextToken());
        st.nextToken();
        this.setS4DURATIONHOUR(st.nextToken());
        st.nextToken();
        this.setS4DURATIONMIN(st.nextToken());
        st.nextToken();
        this.setS4SETTEMP(st.nextToken());
        st.nextToken();
        this.setS4SETTEMPTOL(st.nextToken());
        st.nextToken();
        this.setS4SETHUM(st.nextToken());
        st.nextToken();
        this.setS4SETHUMTOL(st.nextToken());
        st.nextToken();
        this.setS4SETCO2(st.nextToken());
        st.nextToken();
        this.setS4SETCO2TOL(st.nextToken());
        st.nextToken();
        this.setS4SETETHYL(st.nextToken());
        st.nextToken();
        this.setS4SETETHYLTOL(st.nextToken());

    }

    /**
     * @return the circulatingLeft
     */
    public boolean isCirculatingLeft() {
        return circulatingLeft;
    }

    /**
     * @param circulatingLeft the circulatingLeft to set
     */
    public void setCirculatingLeft(boolean circulatingLeft) {
        this.circulatingLeft = circulatingLeft;
    }

    /**
     * @return the circulatingRight
     */
    public boolean isCirculatingRight() {
        return circulatingRight;
    }

    /**
     * @param circulatingRight the circulatingRight to set
     */
    public void setCirculatingRight(boolean circulatingRight) {
        this.circulatingRight = circulatingRight;
    }

    /**
     * @return the suction
     */
    public boolean isSuction() {
        return suction;
    }

    /**
     * @param suction the suction to set
     */
    public void setSuction(boolean suction) {
        this.suction = suction;
    }

    /**
     * @return the damp
     */
    public boolean isDamp() {
        return damp;
    }

    /**
     * @param damp the damp to set
     */
    public void setDamp(boolean damp) {
        this.damp = damp;
    }

    /**
     * @return the co2
     */
    public boolean isCo2() {
        return co2;
    }

    /**
     * @param co2 the co2 to set
     */
    public void setCo2(boolean co2) {
        this.co2 = co2;
    }

    /**
     * @return the ethylen
     */
    public boolean isEthylen() {
        return ethylen;
    }

    /**
     * @param ethylen the ethylen to set
     */
    public void setEthylen(boolean ethylen) {
        this.ethylen = ethylen;
    }

    /**
     * @return the cooler
     */
    public boolean isCooler() {
        return cooler;
    }

    /**
     * @param cooler the cooler to set
     */
    public void setCooler(boolean cooler) {
        this.cooler = cooler;
    }

    /**
     * @return the heater
     */
    public boolean isHeater() {
        return heater;
    }

    /**
     * @param heater the heater to set
     */
    public void setHeater(boolean heater) {
        this.heater = heater;
    }

    /**
     * @return the s1Duration
     */
    public long getS1Duration() {
        return s1Duration;
    }

    /**
     * @param s1Duration the s1Duration to set
     */
    public void setS1Duration(long s1Duration) {
        this.s1Duration = s1Duration;
    }

    /**
     * @return the s2Duration
     */
    public long getS2Duration() {
        return s2Duration;
    }

    /**
     * @param s2Duration the s2Duration to set
     */
    public void setS2Duration(long s2Duration) {
        this.s2Duration = s2Duration;
    }

    /**
     * @return the s3Duration
     */
    public long getS3Duration() {
        return s3Duration;
    }

    /**
     * @param s3Duration the s3Duration to set
     */
    public void setS3Duration(long s3Duration) {
        this.s3Duration = s3Duration;
    }

    /**
     * @return the s4Duration
     */
    public long getS4Duration() {
        return s4Duration;
    }

    /**
     * @param s4Duration the s4Duration to set
     */
    public void setS4Duration(long s4Duration) {
        this.s4Duration = s4Duration;
    }

    /**
     * @return the s1Anfang
     */
    public long getS1Anfang() {
        return s1Anfang;
    }

    /**
     * @param s1Anfang the s1Anfang to set
     */
    public void setS1Anfang(long s1Anfang) {
        this.s1Anfang = s1Anfang;
    }

    /**
     * @return the s2Anfang
     */
    public long getS2Anfang() {
        return s2Anfang;
    }

    /**
     * @param s2Anfang the s2Anfang to set
     */
    public void setS2Anfang(long s2Anfang) {
        this.s2Anfang = s2Anfang;
    }

    /**
     * @return the s3Anfang
     */
    public long getS3Anfang() {
        return s3Anfang;
    }

    /**
     * @param s3Anfang the s3Anfang to set
     */
    public void setS3Anfang(long s3Anfang) {
        this.s3Anfang = s3Anfang;
    }

    /**
     * @return the s4Anfang
     */
    public long getS4Anfang() {
        return s4Anfang;
    }

    /**
     * @param s4Anfang the s4Anfang to set
     */
    public void setS4Anfang(long s4Anfang) {
        this.s4Anfang = s4Anfang;
    }

    /**
     * @return the ROOMNAME
     */
    public String getROOMNAME() {
        return ROOMNAME;
    }

    /**
     * @param ROOMNAME the ROOMNAME to set
     */
    public void setROOMNAME(String ROOMNAME) {
        this.ROOMNAME = ROOMNAME;
    }

    /**
     * @return the PRODUKTSELECTION
     */
    public String getPRODUKTSELECTION() {
        return PRODUKTSELECTION;
    }

    /**
     * @param PRODUKTSELECTION the PRODUKTSELECTION to set
     */
    public void setPRODUKTSELECTION(String PRODUKTSELECTION) {
        this.PRODUKTSELECTION = PRODUKTSELECTION;
    }

    /**
     * @return the USERSELECTION
     */
    public String getUSERSELECTION() {
        return USERSELECTION;
    }

    /**
     * @param USERSELECTION the USERSELECTION to set
     */
    public void setUSERSELECTION(String USERSELECTION) {
        this.USERSELECTION = USERSELECTION;
    }

    /**
     * @return the PRODUKTNRSELECTION
     */
    public String getPRODUKTNRSELECTION() {
        return PRODUKTNRSELECTION;
    }

    /**
     * @param PRODUKTNRSELECTION the PRODUKTNRSELECTION to set
     */
    public void setPRODUKTNRSELECTION(String PRODUKTNRSELECTION) {
        this.PRODUKTNRSELECTION = PRODUKTNRSELECTION;
    }

    /**
     * @return the SPLITMENGE
     */
    public String getSPLITMENGE() {
        return SPLITMENGE;
    }

    /**
     * @param SPLITMENGE the SPLITMENGE to set
     */
    public void setSPLITMENGE(String SPLITMENGE) {
        this.SPLITMENGE = SPLITMENGE;
    }

    /**
     * @return the ETHYLSETPUTTIME
     */
    public String getETHYLSETPUTTIME() {
        return ETHYLSETPUTTIME;
    }

    /**
     * @param ETHYLSETPUTTIME the ETHYLSETPUTTIME to set
     */
    public void setETHYLSETPUTTIME(String ETHYLSETPUTTIME) {
        this.ETHYLSETPUTTIME = ETHYLSETPUTTIME;
    }

    /**
     * @return the DELAYTIMEETHYLTOMES
     */
    public String getDELAYTIMEETHYLTOMES() {
        return DELAYTIMEETHYLTOMES;
    }

    /**
     * @param DELAYTIMEETHYLTOMES the DELAYTIMEETHYLTOMES to set
     */
    public void setDELAYTIMEETHYLTOMES(String DELAYTIMEETHYLTOMES) {
        this.DELAYTIMEETHYLTOMES = DELAYTIMEETHYLTOMES;
    }

    /**
     * @return the CIRCULATINGAIR
     */
    public String getCIRCULATINGAIR() {
        return CIRCULATINGAIR;
    }

    /**
     * @param CIRCULATINGAIR the CIRCULATINGAIR to set
     */
    public void setCIRCULATINGAIR(String CIRCULATINGAIR) {
        this.CIRCULATINGAIR = CIRCULATINGAIR;
    }

    /**
     * @return the CIRCULATINAIRDELAY
     */
    public String getCIRCULATINAIRDELAY() {
        return CIRCULATINAIRDELAY;
    }

    /**
     * @param CIRCULATINAIRDELAY the CIRCULATINAIRDELAY to set
     */
    public void setCIRCULATINAIRDELAY(String CIRCULATINAIRDELAY) {
        this.CIRCULATINAIRDELAY = CIRCULATINAIRDELAY;
    }

    /**
     * @return the HEATERDELAY
     */
    public String getHEATERDELAY() {
        return HEATERDELAY;
    }

    /**
     * @param HEATERDELAY the HEATERDELAY to set
     */
    public void setHEATERDELAY(String HEATERDELAY) {
        this.HEATERDELAY = HEATERDELAY;
    }

    /**
     * @return the COOLERDELAY
     */
    public String getCOOLERDELAY() {
        return COOLERDELAY;
    }

    /**
     * @param COOLERDELAY the COOLERDELAY to set
     */
    public void setCOOLERDELAY(String COOLERDELAY) {
        this.COOLERDELAY = COOLERDELAY;
    }

    /**
     * @return the DAMPERDELAY
     */
    public String getDAMPERDELAY() {
        return DAMPERDELAY;
    }

    /**
     * @param DAMPERDELAY the DAMPERDELAY to set
     */
    public void setDAMPERDELAY(String DAMPERDELAY) {
        this.DAMPERDELAY = DAMPERDELAY;
    }

    /**
     * @return the FRESHAIRDELAY
     */
    public String getFRESHAIRDELAY() {
        return FRESHAIRDELAY;
    }

    /**
     * @param FRESHAIRDELAY the FRESHAIRDELAY to set
     */
    public void setFRESHAIRDELAY(String FRESHAIRDELAY) {
        this.FRESHAIRDELAY = FRESHAIRDELAY;
    }

    /**
     * @return the BEGINTIMEHOUR
     */
    public String getBEGINTIMEHOUR() {
        return BEGINTIMEHOUR;
    }

    /**
     * @param BEGINTIMEHOUR the BEGINTIMEHOUR to set
     */
    public void setBEGINTIMEHOUR(String BEGINTIMEHOUR) {
        this.BEGINTIMEHOUR = BEGINTIMEHOUR;
    }

    /**
     * @return the BEGINTIMEMIN
     */
    public String getBEGINTIMEMIN() {
        return BEGINTIMEMIN;
    }

    /**
     * @param BEGINTIMEMIN the BEGINTIMEMIN to set
     */
    public void setBEGINTIMEMIN(String BEGINTIMEMIN) {
        this.BEGINTIMEMIN = BEGINTIMEMIN;
    }

    /**
     * @return the IPADRESS
     */
    public String getIPADRESS() {
        return IPADRESS;
    }

    /**
     * @param IPADRESS the IPADRESS to set
     */
    public void setIPADRESS(String IPADRESS) {
        this.IPADRESS = IPADRESS;
    }

    /**
     * @return the PORTNR
     */
    public String getPORTNR() {
        return PORTNR;
    }

    /**
     * @param PORTNR the PORTNR to set
     */
    public void setPORTNR(String PORTNR) {
        this.PORTNR = PORTNR;
    }

    /**
     * @return the S1DURATIONDAY
     */
    public String getS1DURATIONDAY() {
        return S1DURATIONDAY;
    }

    /**
     * @param S1DURATIONDAY the S1DURATIONDAY to set
     */
    public void setS1DURATIONDAY(String S1DURATIONDAY) {
        this.S1DURATIONDAY = S1DURATIONDAY;
    }

    /**
     * @return the S1DURATIONHOUR
     */
    public String getS1DURATIONHOUR() {
        return S1DURATIONHOUR;
    }

    /**
     * @param S1DURATIONHOUR the S1DURATIONHOUR to set
     */
    public void setS1DURATIONHOUR(String S1DURATIONHOUR) {
        this.S1DURATIONHOUR = S1DURATIONHOUR;
    }

    /**
     * @return the S1DURATIONMIN
     */
    public String getS1DURATIONMIN() {
        return S1DURATIONMIN;
    }

    /**
     * @param S1DURATIONMIN the S1DURATIONMIN to set
     */
    public void setS1DURATIONMIN(String S1DURATIONMIN) {
        this.S1DURATIONMIN = S1DURATIONMIN;
    }

    /**
     * @return the S1SETTEMP
     */
    public String getS1SETTEMP() {
        return S1SETTEMP;
    }

    /**
     * @param S1SETTEMP the S1SETTEMP to set
     */
    public void setS1SETTEMP(String S1SETTEMP) {
        this.S1SETTEMP = S1SETTEMP;
    }

    /**
     * @return the S1SETTEMPANZEIGE
     */
    public String getS1SETTEMPANZEIGE() {
        return S1SETTEMPANZEIGE;
    }

    /**
     * @param S1SETTEMPANZEIGE the S1SETTEMPANZEIGE to set
     */
    public void setS1SETTEMPANZEIGE(String S1SETTEMPANZEIGE) {
        this.S1SETTEMPANZEIGE = S1SETTEMPANZEIGE;
    }

    /**
     * @return the S1SETTEMPTOL
     */
    public String getS1SETTEMPTOL() {
        return S1SETTEMPTOL;
    }

    /**
     * @param S1SETTEMPTOL the S1SETTEMPTOL to set
     */
    public void setS1SETTEMPTOL(String S1SETTEMPTOL) {
        this.S1SETTEMPTOL = S1SETTEMPTOL;
    }

    /**
     * @return the S1SETHUM
     */
    public String getS1SETHUM() {
        return S1SETHUM;
    }

    /**
     * @param S1SETHUM the S1SETHUM to set
     */
    public void setS1SETHUM(String S1SETHUM) {
        this.S1SETHUM = S1SETHUM;
    }

    /**
     * @return the S1SETHUMANZEIGE
     */
    public String getS1SETHUMANZEIGE() {
        return S1SETHUMANZEIGE;
    }

    /**
     * @param S1SETHUMANZEIGE the S1SETHUMANZEIGE to set
     */
    public void setS1SETHUMANZEIGE(String S1SETHUMANZEIGE) {
        this.S1SETHUMANZEIGE = S1SETHUMANZEIGE;
    }

    /**
     * @return the S1SETHUMTOL
     */
    public String getS1SETHUMTOL() {
        return S1SETHUMTOL;
    }

    /**
     * @param S1SETHUMTOL the S1SETHUMTOL to set
     */
    public void setS1SETHUMTOL(String S1SETHUMTOL) {
        this.S1SETHUMTOL = S1SETHUMTOL;
    }

    /**
     * @return the S1SETCO2
     */
    public String getS1SETCO2() {
        return S1SETCO2;
    }

    /**
     * @param S1SETCO2 the S1SETCO2 to set
     */
    public void setS1SETCO2(String S1SETCO2) {
        this.S1SETCO2 = S1SETCO2;
    }

    /**
     * @return the S1SETCO2ANZEIGE
     */
    public String getS1SETCO2ANZEIGE() {
        return S1SETCO2ANZEIGE;
    }

    /**
     * @param S1SETCO2ANZEIGE the S1SETCO2ANZEIGE to set
     */
    public void setS1SETCO2ANZEIGE(String S1SETCO2ANZEIGE) {
        this.S1SETCO2ANZEIGE = S1SETCO2ANZEIGE;
    }

    /**
     * @return the S1SETCO2TOL
     */
    public String getS1SETCO2TOL() {
        return S1SETCO2TOL;
    }

    /**
     * @param S1SETCO2TOL the S1SETCO2TOL to set
     */
    public void setS1SETCO2TOL(String S1SETCO2TOL) {
        this.S1SETCO2TOL = S1SETCO2TOL;
    }

    /**
     * @return the S1SETETHYL
     */
    public String getS1SETETHYL() {
        return S1SETETHYL;
    }

    /**
     * @param S1SETETHYL the S1SETETHYL to set
     */
    public void setS1SETETHYL(String S1SETETHYL) {
        this.S1SETETHYL = S1SETETHYL;
    }

    /**
     * @return the S1SETETHYLANZEIGE
     */
    public String getS1SETETHYLANZEIGE() {
        return S1SETETHYLANZEIGE;
    }

    /**
     * @param S1SETETHYLANZEIGE the S1SETETHYLANZEIGE to set
     */
    public void setS1SETETHYLANZEIGE(String S1SETETHYLANZEIGE) {
        this.S1SETETHYLANZEIGE = S1SETETHYLANZEIGE;
    }

    /**
     * @return the S1SETETHYLTOL
     */
    public String getS1SETETHYLTOL() {
        return S1SETETHYLTOL;
    }

    /**
     * @param S1SETETHYLTOL the S1SETETHYLTOL to set
     */
    public void setS1SETETHYLTOL(String S1SETETHYLTOL) {
        this.S1SETETHYLTOL = S1SETETHYLTOL;
    }

    /**
     * @return the S2DURATIONDAY
     */
    public String getS2DURATIONDAY() {
        return S2DURATIONDAY;
    }

    /**
     * @param S2DURATIONDAY the S2DURATIONDAY to set
     */
    public void setS2DURATIONDAY(String S2DURATIONDAY) {
        this.S2DURATIONDAY = S2DURATIONDAY;
    }

    /**
     * @return the S2DURATIONHOUR
     */
    public String getS2DURATIONHOUR() {
        return S2DURATIONHOUR;
    }

    /**
     * @param S2DURATIONHOUR the S2DURATIONHOUR to set
     */
    public void setS2DURATIONHOUR(String S2DURATIONHOUR) {
        this.S2DURATIONHOUR = S2DURATIONHOUR;
    }

    /**
     * @return the S2DURATIONMIN
     */
    public String getS2DURATIONMIN() {
        return S2DURATIONMIN;
    }

    /**
     * @param S2DURATIONMIN the S2DURATIONMIN to set
     */
    public void setS2DURATIONMIN(String S2DURATIONMIN) {
        this.S2DURATIONMIN = S2DURATIONMIN;
    }

    /**
     * @return the S2SETTEMP
     */
    public String getS2SETTEMP() {
        return S2SETTEMP;
    }

    /**
     * @param S2SETTEMP the S2SETTEMP to set
     */
    public void setS2SETTEMP(String S2SETTEMP) {
        this.S2SETTEMP = S2SETTEMP;
    }

    /**
     * @return the S2SETTEMPANZEIGE
     */
    public String getS2SETTEMPANZEIGE() {
        return S2SETTEMPANZEIGE;
    }

    /**
     * @param S2SETTEMPANZEIGE the S2SETTEMPANZEIGE to set
     */
    public void setS2SETTEMPANZEIGE(String S2SETTEMPANZEIGE) {
        this.S2SETTEMPANZEIGE = S2SETTEMPANZEIGE;
    }

    /**
     * @return the S2SETTEMPTOL
     */
    public String getS2SETTEMPTOL() {
        return S2SETTEMPTOL;
    }

    /**
     * @param S2SETTEMPTOL the S2SETTEMPTOL to set
     */
    public void setS2SETTEMPTOL(String S2SETTEMPTOL) {
        this.S2SETTEMPTOL = S2SETTEMPTOL;
    }

    /**
     * @return the S2SETHUM
     */
    public String getS2SETHUM() {
        return S2SETHUM;
    }

    /**
     * @param S2SETHUM the S2SETHUM to set
     */
    public void setS2SETHUM(String S2SETHUM) {
        this.S2SETHUM = S2SETHUM;
    }

    /**
     * @return the S2SETHUMANZEIGE
     */
    public String getS2SETHUMANZEIGE() {
        return S2SETHUMANZEIGE;
    }

    /**
     * @param S2SETHUMANZEIGE the S2SETHUMANZEIGE to set
     */
    public void setS2SETHUMANZEIGE(String S2SETHUMANZEIGE) {
        this.S2SETHUMANZEIGE = S2SETHUMANZEIGE;
    }

    /**
     * @return the S2SETHUMTOL
     */
    public String getS2SETHUMTOL() {
        return S2SETHUMTOL;
    }

    /**
     * @param S2SETHUMTOL the S2SETHUMTOL to set
     */
    public void setS2SETHUMTOL(String S2SETHUMTOL) {
        this.S2SETHUMTOL = S2SETHUMTOL;
    }

    /**
     * @return the S2SETCO2
     */
    public String getS2SETCO2() {
        return S2SETCO2;
    }

    /**
     * @param S2SETCO2 the S2SETCO2 to set
     */
    public void setS2SETCO2(String S2SETCO2) {
        this.S2SETCO2 = S2SETCO2;
    }

    /**
     * @return the S2SETCO2ANZEIGE
     */
    public String getS2SETCO2ANZEIGE() {
        return S2SETCO2ANZEIGE;
    }

    /**
     * @param S2SETCO2ANZEIGE the S2SETCO2ANZEIGE to set
     */
    public void setS2SETCO2ANZEIGE(String S2SETCO2ANZEIGE) {
        this.S2SETCO2ANZEIGE = S2SETCO2ANZEIGE;
    }

    /**
     * @return the S2SETCO2TOL
     */
    public String getS2SETCO2TOL() {
        return S2SETCO2TOL;
    }

    /**
     * @param S2SETCO2TOL the S2SETCO2TOL to set
     */
    public void setS2SETCO2TOL(String S2SETCO2TOL) {
        this.S2SETCO2TOL = S2SETCO2TOL;
    }

    /**
     * @return the S2SETETHYL
     */
    public String getS2SETETHYL() {
        return S2SETETHYL;
    }

    /**
     * @param S2SETETHYL the S2SETETHYL to set
     */
    public void setS2SETETHYL(String S2SETETHYL) {
        this.S2SETETHYL = S2SETETHYL;
    }

    /**
     * @return the S2SETETHYLANZEIGE
     */
    public String getS2SETETHYLANZEIGE() {
        return S2SETETHYLANZEIGE;
    }

    /**
     * @param S2SETETHYLANZEIGE the S2SETETHYLANZEIGE to set
     */
    public void setS2SETETHYLANZEIGE(String S2SETETHYLANZEIGE) {
        this.S2SETETHYLANZEIGE = S2SETETHYLANZEIGE;
    }

    /**
     * @return the S2SETETHYLTOL
     */
    public String getS2SETETHYLTOL() {
        return S2SETETHYLTOL;
    }

    /**
     * @param S2SETETHYLTOL the S2SETETHYLTOL to set
     */
    public void setS2SETETHYLTOL(String S2SETETHYLTOL) {
        this.S2SETETHYLTOL = S2SETETHYLTOL;
    }

    /**
     * @return the S3DURATIONDAY
     */
    public String getS3DURATIONDAY() {
        return S3DURATIONDAY;
    }

    /**
     * @param S3DURATIONDAY the S3DURATIONDAY to set
     */
    public void setS3DURATIONDAY(String S3DURATIONDAY) {
        this.S3DURATIONDAY = S3DURATIONDAY;
    }

    /**
     * @return the S3DURATIONHOUR
     */
    public String getS3DURATIONHOUR() {
        return S3DURATIONHOUR;
    }

    /**
     * @param S3DURATIONHOUR the S3DURATIONHOUR to set
     */
    public void setS3DURATIONHOUR(String S3DURATIONHOUR) {
        this.S3DURATIONHOUR = S3DURATIONHOUR;
    }

    /**
     * @return the S3DURATIONMIN
     */
    public String getS3DURATIONMIN() {
        return S3DURATIONMIN;
    }

    /**
     * @param S3DURATIONMIN the S3DURATIONMIN to set
     */
    public void setS3DURATIONMIN(String S3DURATIONMIN) {
        this.S3DURATIONMIN = S3DURATIONMIN;
    }

    /**
     * @return the S3SETTEMP
     */
    public String getS3SETTEMP() {
        return S3SETTEMP;
    }

    /**
     * @param S3SETTEMP the S3SETTEMP to set
     */
    public void setS3SETTEMP(String S3SETTEMP) {
        this.S3SETTEMP = S3SETTEMP;
    }

    /**
     * @return the S3SETTEMPANZEIGE
     */
    public String getS3SETTEMPANZEIGE() {
        return S3SETTEMPANZEIGE;
    }

    /**
     * @param S3SETTEMPANZEIGE the S3SETTEMPANZEIGE to set
     */
    public void setS3SETTEMPANZEIGE(String S3SETTEMPANZEIGE) {
        this.S3SETTEMPANZEIGE = S3SETTEMPANZEIGE;
    }

    /**
     * @return the S3SETTEMPTOL
     */
    public String getS3SETTEMPTOL() {
        return S3SETTEMPTOL;
    }

    /**
     * @param S3SETTEMPTOL the S3SETTEMPTOL to set
     */
    public void setS3SETTEMPTOL(String S3SETTEMPTOL) {
        this.S3SETTEMPTOL = S3SETTEMPTOL;
    }

    /**
     * @return the S3SETHUM
     */
    public String getS3SETHUM() {
        return S3SETHUM;
    }

    /**
     * @param S3SETHUM the S3SETHUM to set
     */
    public void setS3SETHUM(String S3SETHUM) {
        this.S3SETHUM = S3SETHUM;
    }

    /**
     * @return the S3SETHUMANZEIGE
     */
    public String getS3SETHUMANZEIGE() {
        return S3SETHUMANZEIGE;
    }

    /**
     * @param S3SETHUMANZEIGE the S3SETHUMANZEIGE to set
     */
    public void setS3SETHUMANZEIGE(String S3SETHUMANZEIGE) {
        this.S3SETHUMANZEIGE = S3SETHUMANZEIGE;
    }

    /**
     * @return the S3SETHUMTOL
     */
    public String getS3SETHUMTOL() {
        return S3SETHUMTOL;
    }

    /**
     * @param S3SETHUMTOL the S3SETHUMTOL to set
     */
    public void setS3SETHUMTOL(String S3SETHUMTOL) {
        this.S3SETHUMTOL = S3SETHUMTOL;
    }

    /**
     * @return the S3SETCO2
     */
    public String getS3SETCO2() {
        return S3SETCO2;
    }

    /**
     * @param S3SETCO2 the S3SETCO2 to set
     */
    public void setS3SETCO2(String S3SETCO2) {
        this.S3SETCO2 = S3SETCO2;
    }

    /**
     * @return the S3SETCO2ANZEIGE
     */
    public String getS3SETCO2ANZEIGE() {
        return S3SETCO2ANZEIGE;
    }

    /**
     * @param S3SETCO2ANZEIGE the S3SETCO2ANZEIGE to set
     */
    public void setS3SETCO2ANZEIGE(String S3SETCO2ANZEIGE) {
        this.S3SETCO2ANZEIGE = S3SETCO2ANZEIGE;
    }

    /**
     * @return the S3SETCO2TOL
     */
    public String getS3SETCO2TOL() {
        return S3SETCO2TOL;
    }

    /**
     * @param S3SETCO2TOL the S3SETCO2TOL to set
     */
    public void setS3SETCO2TOL(String S3SETCO2TOL) {
        this.S3SETCO2TOL = S3SETCO2TOL;
    }

    /**
     * @return the S3SETETHYL
     */
    public String getS3SETETHYL() {
        return S3SETETHYL;
    }

    /**
     * @param S3SETETHYL the S3SETETHYL to set
     */
    public void setS3SETETHYL(String S3SETETHYL) {
        this.S3SETETHYL = S3SETETHYL;
    }

    /**
     * @return the S3SETETHYLANZEIGE
     */
    public String getS3SETETHYLANZEIGE() {
        return S3SETETHYLANZEIGE;
    }

    /**
     * @param S3SETETHYLANZEIGE the S3SETETHYLANZEIGE to set
     */
    public void setS3SETETHYLANZEIGE(String S3SETETHYLANZEIGE) {
        this.S3SETETHYLANZEIGE = S3SETETHYLANZEIGE;
    }

    /**
     * @return the S3SETETHYLTOL
     */
    public String getS3SETETHYLTOL() {
        return S3SETETHYLTOL;
    }

    /**
     * @param S3SETETHYLTOL the S3SETETHYLTOL to set
     */
    public void setS3SETETHYLTOL(String S3SETETHYLTOL) {
        this.S3SETETHYLTOL = S3SETETHYLTOL;
    }

    /**
     * @return the S4DURATIONDAY
     */
    public String getS4DURATIONDAY() {
        return S4DURATIONDAY;
    }

    /**
     * @param S4DURATIONDAY the S4DURATIONDAY to set
     */
    public void setS4DURATIONDAY(String S4DURATIONDAY) {
        this.S4DURATIONDAY = S4DURATIONDAY;
    }

    /**
     * @return the S4DURATIONHOUR
     */
    public String getS4DURATIONHOUR() {
        return S4DURATIONHOUR;
    }

    /**
     * @param S4DURATIONHOUR the S4DURATIONHOUR to set
     */
    public void setS4DURATIONHOUR(String S4DURATIONHOUR) {
        this.S4DURATIONHOUR = S4DURATIONHOUR;
    }

    /**
     * @return the S4DURATIONMIN
     */
    public String getS4DURATIONMIN() {
        return S4DURATIONMIN;
    }

    /**
     * @param S4DURATIONMIN the S4DURATIONMIN to set
     */
    public void setS4DURATIONMIN(String S4DURATIONMIN) {
        this.S4DURATIONMIN = S4DURATIONMIN;
    }

    /**
     * @return the S4SETTEMP
     */
    public String getS4SETTEMP() {
        return S4SETTEMP;
    }

    /**
     * @param S4SETTEMP the S4SETTEMP to set
     */
    public void setS4SETTEMP(String S4SETTEMP) {
        this.S4SETTEMP = S4SETTEMP;
    }

    /**
     * @return the S4SETTEMPANZEIGE
     */
    public String getS4SETTEMPANZEIGE() {
        return S4SETTEMPANZEIGE;
    }

    /**
     * @param S4SETTEMPANZEIGE the S4SETTEMPANZEIGE to set
     */
    public void setS4SETTEMPANZEIGE(String S4SETTEMPANZEIGE) {
        this.S4SETTEMPANZEIGE = S4SETTEMPANZEIGE;
    }

    /**
     * @return the S4SETTEMPTOL
     */
    public String getS4SETTEMPTOL() {
        return S4SETTEMPTOL;
    }

    /**
     * @param S4SETTEMPTOL the S4SETTEMPTOL to set
     */
    public void setS4SETTEMPTOL(String S4SETTEMPTOL) {
        this.S4SETTEMPTOL = S4SETTEMPTOL;
    }

    /**
     * @return the S4SETHUM
     */
    public String getS4SETHUM() {
        return S4SETHUM;
    }

    /**
     * @param S4SETHUM the S4SETHUM to set
     */
    public void setS4SETHUM(String S4SETHUM) {
        this.S4SETHUM = S4SETHUM;
    }

    /**
     * @return the S4SETHUMANZEIGE
     */
    public String getS4SETHUMANZEIGE() {
        return S4SETHUMANZEIGE;
    }

    /**
     * @param S4SETHUMANZEIGE the S4SETHUMANZEIGE to set
     */
    public void setS4SETHUMANZEIGE(String S4SETHUMANZEIGE) {
        this.S4SETHUMANZEIGE = S4SETHUMANZEIGE;
    }

    /**
     * @return the S4SETHUMTOL
     */
    public String getS4SETHUMTOL() {
        return S4SETHUMTOL;
    }

    /**
     * @param S4SETHUMTOL the S4SETHUMTOL to set
     */
    public void setS4SETHUMTOL(String S4SETHUMTOL) {
        this.S4SETHUMTOL = S4SETHUMTOL;
    }

    /**
     * @return the S4SETCO2
     */
    public String getS4SETCO2() {
        return S4SETCO2;
    }

    /**
     * @param S4SETCO2 the S4SETCO2 to set
     */
    public void setS4SETCO2(String S4SETCO2) {
        this.S4SETCO2 = S4SETCO2;
    }

    /**
     * @return the S4SETCO2ANZEIGE
     */
    public String getS4SETCO2ANZEIGE() {
        return S4SETCO2ANZEIGE;
    }

    /**
     * @param S4SETCO2ANZEIGE the S4SETCO2ANZEIGE to set
     */
    public void setS4SETCO2ANZEIGE(String S4SETCO2ANZEIGE) {
        this.S4SETCO2ANZEIGE = S4SETCO2ANZEIGE;
    }

    /**
     * @return the S4SETCO2TOL
     */
    public String getS4SETCO2TOL() {
        return S4SETCO2TOL;
    }

    /**
     * @param S4SETCO2TOL the S4SETCO2TOL to set
     */
    public void setS4SETCO2TOL(String S4SETCO2TOL) {
        this.S4SETCO2TOL = S4SETCO2TOL;
    }

    /**
     * @return the S4SETETHYL
     */
    public String getS4SETETHYL() {
        return S4SETETHYL;
    }

    /**
     * @param S4SETETHYL the S4SETETHYL to set
     */
    public void setS4SETETHYL(String S4SETETHYL) {
        this.S4SETETHYL = S4SETETHYL;
    }

    /**
     * @return the S4SETETHYLANZEIGE
     */
    public String getS4SETETHYLANZEIGE() {
        return S4SETETHYLANZEIGE;
    }

    /**
     * @param S4SETETHYLANZEIGE the S4SETETHYLANZEIGE to set
     */
    public void setS4SETETHYLANZEIGE(String S4SETETHYLANZEIGE) {
        this.S4SETETHYLANZEIGE = S4SETETHYLANZEIGE;
    }

    /**
     * @return the S4SETETHYLTOL
     */
    public String getS4SETETHYLTOL() {
        return S4SETETHYLTOL;
    }

    /**
     * @param S4SETETHYLTOL the S4SETETHYLTOL to set
     */
    public void setS4SETETHYLTOL(String S4SETETHYLTOL) {
        this.S4SETETHYLTOL = S4SETETHYLTOL;
    }

    /**
     * @return the PROZESSBEGINN
     */
    public String getPROZESSBEGINN() {
        return PROZESSBEGINN;
    }

    /**
     * @param PROZESSBEGINN the PROZESSBEGINN to set
     */
    public void setPROZESSBEGINN(String PROZESSBEGINN) {
        this.PROZESSBEGINN = PROZESSBEGINN;
    }

    /**
     * @return the PROZESSEND
     */
    public String getPROZESSEND() {
        return PROZESSEND;
    }

    /**
     * @param PROZESSEND the PROZESSEND to set
     */
    public void setPROZESSEND(String PROZESSEND) {
        this.PROZESSEND = PROZESSEND;
    }

    /**
     * @return the aktuelleRoomValues
     */
    public String getAktuelleRoomValues() {
        return aktuelleRoomValues;
    }

    /**
     * @param aktuelleRoomValues the aktuelleRoomValues to set
     */
    public void setAktuelleRoomValues(String aktuelleRoomValues) {
        this.aktuelleRoomValues = aktuelleRoomValues;
    }

    /**
     * @return the tempIst
     */
    public String getTempIst() {
        return tempIst;
    }

    /**
     * @param tempIst the tempIst to set
     */
    public void setTempIst(String tempIst) {
        this.tempIst = tempIst;
    }

    /**
     * @return the humIst
     */
    public String getHumIst() {
        return humIst;
    }

    /**
     * @param humIst the humIst to set
     */
    public void setHumIst(String humIst) {
        this.humIst = humIst;
    }

    /**
     * @return the co2Ist
     */
    public String getCo2Ist() {
        return co2Ist;
    }

    /**
     * @param co2Ist the co2Ist to set
     */
    public void setCo2Ist(String co2Ist) {
        this.co2Ist = co2Ist;
    }

    /**
     * @return the ethylIst
     */
    public String getEthylIst() {
        return ethylIst;
    }

    /**
     * @param ethylIst the ethylIst to set
     */
    public void setEthylIst(String ethylIst) {
        this.ethylIst = ethylIst;
    }

    /**
     * @return the multiDamp
     */
    public String getMultiDamp() {
        return multiDamp;
    }

    /**
     * @param multiDamp the multiDamp to set
     */
    public void setMultiDamp(String multiDamp) {
        this.multiDamp = multiDamp;
    }

    /**
     * @return the multiTemp
     */
    public String getMultiTemp() {
        return multiTemp;
    }

    /**
     * @param multiTemp the multiTemp to set
     */
    public void setMultiTemp(String multiTemp) {
        this.multiTemp = multiTemp;
    }

    /**
     * @return the z1In
     */
    public String getZ1In() {
        return z1In;
    }

    /**
     * @param z1In the z1In to set
     */
    public void setZ1In(String z1In) {
        this.z1In = z1In;
    }

    /**
     * @return the z1Out
     */
    public String getZ1Out() {
        return z1Out;
    }

    /**
     * @param z1Out the z1Out to set
     */
    public void setZ1Out(String z1Out) {
        this.z1Out = z1Out;
    }

    /**
     * @return the z2In
     */
    public String getZ2In() {
        return z2In;
    }

    /**
     * @param z2In the z2In to set
     */
    public void setZ2In(String z2In) {
        this.z2In = z2In;
    }

    /**
     * @return the z2Out
     */
    public String getZ2Out() {
        return z2Out;
    }

    /**
     * @param z2Out the z2Out to set
     */
    public void setZ2Out(String z2Out) {
        this.z2Out = z2Out;
    }

    /**
     * @return the prT1
     */
    public String getPrT1() {
        return prT1;
    }

    /**
     * @param prT1 the prT1 to set
     */
    public void setPrT1(String prT1) {
        this.prT1 = prT1;
    }

    /**
     * @return the prT2
     */
    public String getPrT2() {
        return prT2;
    }

    /**
     * @param prT2 the prT2 to set
     */
    public void setPrT2(String prT2) {
        this.prT2 = prT2;
    }

    /**
     * @return the prT3
     */
    public String getPrT3() {
        return prT3;
    }

    /**
     * @param prT3 the prT3 to set
     */
    public void setPrT3(String prT3) {
        this.prT3 = prT3;
    }

    /**
     * @return the mainTemp1Wire
     */
    public String getMainTemp1Wire() {
        return mainTemp1Wire;
    }

    /**
     * @param mainTemp1Wire the mainTemp1Wire to set
     */
    public void setMainTemp1Wire(String mainTemp1Wire) {
        this.mainTemp1Wire = mainTemp1Wire;
    }

    /**
     * @return the isProzessdatenVorhanden
     */
    public boolean isIsProzessdatenVorhanden() {
        return isProzessdatenVorhanden;
    }

    /**
     * @param isProzessdatenVorhanden the isProzessdatenVorhanden to set
     */
    public void setIsProzessdatenVorhanden(boolean isProzessdatenVorhanden) {
        this.isProzessdatenVorhanden = isProzessdatenVorhanden;
    }

    /**
     * @return the simulator
     */
    public Simulator getSimulator() {
        return simulator;
    }

    /**
     * @param simulator the simulator to set
     */
    public void setSimulator(Simulator simulator) {
        this.simulator = simulator;
    }

}
