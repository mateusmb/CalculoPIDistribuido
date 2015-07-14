/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piconcorrentedistribuido;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author Bruno
 */
public class Message implements Serializable {

    public byte[] getBytes () throws Exception {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ObjectOutputStream oos = new ObjectOutputStream(baos);

        oos.writeObject(this);

        return baos.toByteArray();

    }

    public static Message fromBytes (byte[] bytes) throws Exception {

        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);

        ObjectInputStream ois = new ObjectInputStream(bais);

        return (Message) ois.readObject();

    }

}