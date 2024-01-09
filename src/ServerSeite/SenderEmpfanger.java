/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerSeite;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alisi
 */
public class SenderEmpfanger {


    private SenderMit1Wire sender;
    private Receiver receiver;
   
    public SenderEmpfanger() {
        
        sender = new SenderMit1Wire();
        receiver = new Receiver();
        PipedOutputStream outputStream = sender.getOutputStream();
        // Obtener la secuencia de la tubería de entrada
        PipedInputStream inputStream = receiver.getInputStream();

        try {
            // Enlace las dos tuberías. Este paso es muy importante. Conecte la secuencia de entrada y la secuencia de salida.
            outputStream.connect(inputStream);
        } catch (IOException ex) {
            Logger.getLogger(SenderEmpfanger.class.getName()).log(Level.SEVERE, null, ex);
        }
        sender.start();
        // iniciar hilo receptor
        receiver.start();
    }
    
    
    /**
     * @return the sender
     */
    public SenderMit1Wire getSender() {
        return sender;
    }

    /**
     * @param sender the sender to set
     */
    public void setSender(SenderMit1Wire sender) {
        this.sender = sender;
    }

    /**
     * @return the receiver
     */
    public Receiver getReceiver() {
        return receiver;
    }

    /**
     * @param receiver the receiver to set
     */
    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }
    
   

}
