/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerSeite;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alisi
 */
public class Receiver extends Thread {

    private String newData1;
    //  Timer timer;

    //  int sec = 0;
    // declara un objeto de entrada de canalización como el receptor
    private PipedInputStream pis = new PipedInputStream();
    DataInputStream in = new DataInputStream(pis);
    //  RoomDisplay rmk;
    //   private int interval;

    public PipedInputStream getInputStream() {
        return pis;
    }

    public Receiver() {
        //this.rmk = rmk;
    }

    @Override
    public synchronized void run() {
        String text = "0.0" + ";" + "0.0" + ";" + "0.0" + ";" + "0.0" + ";" + "0.0" + ";" + "0.0" + ";" + "0.0" + ";" + "0.0" + ";";
        byte[] buf = new byte[1024];
        while (true) {
            try {
                // longitud de lectura por método de lectura
//               if(buf.length!=0){
                int len = pis.read(buf);
                if (pis!=null) {
                    setNewData1(new String(buf, 0, len));
                } else {
                    setNewData1(text);
                }
                // System.out.println("DaRECEIVER "+ getNewData1());

                //             }
                //     System.out.println("Data read 1 " + new String(buf, 0, len));
                //   System.out.println("Data read  sinan  "+buf.length+"   " + getNewData1());
//                StringTokenizer st = new StringTokenizer(text,";");
//               // st.nextToken();
//                //System.out.println("xxxxxxxxxxxxxxxxxxx  " + st.nextToken());
//            //    st.nextToken();
//                //rmk.getMultiTemp().setText(st.nextToken());
//                st.nextToken();
//                //rmk.getMultiDamp().setText(st.nextToken());
//                st.nextToken();
//                //rmk.getMultiCo2().setText(st.nextToken());
//               st.nextToken();
//                //rmk.getMultiEthyl().setText(st.nextToken());//.getS
//                st.nextToken();
//                //rmk.getManuel1().setText(st.nextToken());
//                st.nextToken();
//                //rmk.getManuel2().setText(st.nextToken());
//                st.nextToken();
//                //rmk.getManuel3().setText(st.nextToken());
//                st.nextToken();
                //rmk.getManuel4().setText(st.nextToken());
                //       System.out.println(st.nextToken());
//                int iValue = in.readInt();
//                System.out.println("Data read         xxxxxxxx 1 " + iValue);
                //    System.out.println(" --------------------------------------------------------------------------");
                //System.out.println(" AUS DEM BUFFER  "+pis.read());//new String(buf, 0, len));
            } catch (IOException e) {
            }
            try {
                Thread.sleep(50);
//                finally {
//			try {
//				 // cierra el flujo de entrada
//				pis.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
            } catch (InterruptedException ex) {
                Logger.getLogger(Receiver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @return the newData1
     */
    public String getNewData1() {
        return newData1;
    }

    /**
     * @param newData1 the newData1 to set
     */
    public void setNewData1(String newData1) {
        this.newData1 = newData1;
    }

//    public void rückwärtzZähler(int i) {
//        int t = i;
//
//        timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            int i = 20;
//
//            public void run() {
//                //    jLabel.setText("Time left: " + i);
//            //    rmk.getManuel2().setText(String.valueOf(i));
//                i--;
//                if (i < 0) {
//                    timer.cancel();
//                  //  rmk.getjTextArea1().append("Time Over");
//                }
//            }
//        }, 0, 1000);
//
//    }
//    public synchronized void countDown(int i, final JLabel soll, final JLabel ist) {
//
//        int delay = 0;
//        int period = 1000;
//
//        timer = new Timer();
//        int sec = i;
//
//        soll.setText(String.valueOf(sec));
//        timer.scheduleAtFixedRate(new TimerTask() {
//            public void run() {
//                --sec;
//                setInterval();
//                // getSec();
//                if (sec == 0) {
//                    ist.setText("Timeout");
//                    timer.cancel();
//                }
//                ist.setText(String.valueOf(sec));
//                //   rmk.getManuel3().setText(String.valueOf(setInterval()));
//            }
//        }, delay, period);
//        
//        
//
//    }
//    public void countDown(final int setTime,int delay,  final JLabel soll, final JPanel panel, JProgressBar js, int maxValue) {
//        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//        soll.setText(String.valueOf(setTime));
//        panel.setBackground(new java.awt.Color(255, 0, 0));
//        
//        js.setMaximum(maxValue);
//    
//        final Runnable runnable = new Runnable() {
//            int countdownStarter = setTime;
//
//            public void run() {
//
//                // System.out.println(countdownStarter);
//                soll.setText(String.valueOf(countdownStarter));
//                countdownStarter--;
//                 js.setValue(countdownStarter);
//                if (countdownStarter < 0) {
//                    System.out.println("Timer Over!");
//                    scheduler.shutdown();
//                        panel.setBackground(new java.awt.Color(51, 255, 0));
//                       
//                }
//            }
//        };
//        scheduler.scheduleAtFixedRate(runnable, delay, 1, SECONDS);
//    }
    /**
     * @return the newData
     */
}

//    private int  getSec() {
//        if (sec == 0) {
//            timer.cancel();
//            rmk.getjTextArea1().append("Timeout\n");
//           
//        }
//         rmk.getManuel3().setText(String.valueOf(sec));
//        
//         System.out.println("-------------------------------------------"+String.valueOf(sec));
//        return sec;
//    }
//    public void countDown() {
//       int count =10;
//        timer = new Timer();
//        TimerTask task;
//        task = new TimerTask() {
//            @Override
//            public void run() {
//
//                System.out.println(count);
//                if (count > 0) {
//                    count--;
//                }
//
//                if (count == 0) {
//                    System.exit(0);
//                }
//            }
//        };
//        timer.schedule(task, 0, 1000);
//    }
//public class CountDown {
//
//    Timer timer;
//
//    public CountDown() {
//        timer = new Timer();
//        timer.schedule(new DisplayCountdown(), 0, 1000);
//    }
//
//    class DisplayCountdown extends TimerTask {
//
//        int seconds = 60;
//
//        @Override
//        public void run() {
//            rmk.getManuel2().setText(String.valueOf("text"));
//        }
//
//    }
//
//}
// }
