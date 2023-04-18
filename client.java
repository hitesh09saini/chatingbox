import java.net.*;
import java.nio.channels.SocketChannel;
import java.io.*;

public class client {
    Socket socket;
    BufferedReader br;
    PrintWriter out;
    public client(){
 try {
    System.out.println("sending request to server");
    socket = new Socket("",9694);
    System.out.println("connection done.");
    br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    out = new PrintWriter(socket.getOutputStream());
    startReading();
    startwirting();
     


 } catch (Exception e) {
    e.printStackTrace();
 }
    }

   
    public void startwirting() { // send to data for client\
        System.out.println("start writing ....");
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
    public void startReading() { // thread data ko read krke dega
        Runnable r1 = () -> {

            System.out.println("reader started..");
            while (true) {
                
                try {
                    String   msg = br.readLine();
                    if (msg.equals("exit")) {
                        System.out.println("client terminated the chat");
                        break;
                    }System.out.println("server : "+msg);

                } catch (Exception e) {

                    e.printStackTrace();
                }

            }
        };
        new Thread(r1).start();

    }


    public static void main(String[] args) {
        System.out.println("this is client...");
        new client();
    }
}
