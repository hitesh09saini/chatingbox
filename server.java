import java.net.*;
import java.nio.channels.SocketChannel;
import java.io.*;

public class server {
    ServerSocket server;
    Socket socket;

    BufferedReader br;
    PrintWriter out;

    public server() {
        try {
            server = new ServerSocket(9694);
            System.out.println("server is ready to accept connection");
            System.out.println("waiting....");
            socket = server.accept();

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out = new PrintWriter(socket.getOutputStream());
            startReading();
            startwirting();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void startReading() { // thread data ko read krke dega
        Runnable r1 = () -> {

            System.out.println("reader started..");
            while (true) {
                
                
                try {String msg = br.readLine(); 
                  
                    if (msg.equals("exit")) {
                        System.out.println("client terminated the chat");
                        break;
                    }System.out.println("client : "+msg);

                } catch (Exception e) {

                    e.printStackTrace();
                }

            }
        };
        new Thread(r1).start();

    }


    public void startwirting() { // send to data for client

        System.out.println("writing started");
        Runnable r2 = () -> {

            while(true){

                try {
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                    out.println();

                    String content = br1.readLine();
                    out.println(content);

                    out.flush();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        };new Thread(r2).start();
    }

    public static void main(String[] args) {
        System.out.println("this is server ... going to start");
        new server();
    }
}
