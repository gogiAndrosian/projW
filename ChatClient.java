package Else.drivers.TrySocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Andrzej on 2017-09-27.
 */
public class ChatClient implements Runnable{

    private int port = 7000;

    public static void main(String[] args) throws IOException {
        String ip = "192.168.1.15";//localhost
        int port = 6666;
        Socket  s = new Socket(ip,port);

    }
    @Override
    public void run() {
        System.out.println("Connection complete");
        DatagramSocket sock = null;
        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            sock = new DatagramSocket();
            System.out.println(" in try");
            while(true)
            {
                //Ожидаем ввод сообщения серверу
                System.out.println("Введите сообщение серверу: ");
                String s = (String)cin.readLine();
                byte[] b = s.getBytes();

                //Отправляем сообщение
                DatagramPacket dp = new DatagramPacket(b , b.length , InetAddress.getByName("192.168.1.201") , port);
                sock.send(dp);

                //буфер для получения входящих данных
                byte[] buffer = new byte[65536];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

                //Получаем данные
                sock.receive(reply);
                byte[] data = reply.getData();
                s = new String(data, 0, reply.getLength());

                System.out.println("Сервер: " + reply.getAddress().getHostAddress() + ", порт: " + reply.getPort() + ", " +
                        "получил: " + s);
            }
        }catch(IOException e)
        {
            System.err.println("IOException " + e);
        }
    }
}
