package Else.drivers.TrySocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by Andrzej on 2017-09-27.
 */
public class ChatServer implements Runnable{
    private final int port = 7000 ;

    public void starterServer(){
        try
        {
            //Создаем сокет
            DatagramSocket sock = new DatagramSocket(port);

            //буфер для получения входящих данных
            byte[] buffer = new byte[65536];
            DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);

            System.out.println("Ожидаем данные...");

            while(true)
            {
                //Получаем данные
                sock.receive(incoming);
                byte[] data = incoming.getData();
                String s = new String(data, 0, incoming.getLength());

                System.out.println("Сервер получил: " + s);

                //Отправляем данные клиенту
                DatagramPacket dp = new DatagramPacket(s.getBytes() , s.getBytes().length , incoming.getAddress() , incoming.getPort());
                sock.send(dp);
            }
        }

        catch(IOException e)
        {
            System.err.println("IOException " + e);
        }
    }

    @Override
    public void run() {
        new ChatServer().starterServer();
    }
}
