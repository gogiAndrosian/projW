package Else.drivers.TrySocket;

/**
 * Created by Andrzej on 2017-09-29.
 */
public class test implements Runnable{
    public static void main(String[] args) {
        new Thread(new ChatServer()).start();
        new Thread(new ChatClient()).start();
    }

    @Override
    public void run() {
        new Thread(new ChatServer()).start();
        new Thread(new ChatClient()).start();
    }
}
