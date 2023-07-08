//nasrin seifi 8907013
package multithreadchatclient;
import java.io.*;
import java.net.*;

public class MultiThreadChatClient implements Runnable {

  private static Socket clientSocket = null;
  private static PrintStream os = null;
  private static DataInputStream is = null;

  private static BufferedReader inputLine = null;
  private static boolean closed = false;
  private static String input="";

  public static void main(String[] args) {

    int portNumber = 2222;
    String host = "localhost";

    if (args.length < 2) {
      System.out
          .println("java MultiThreadChatClient <host> <portNumber>\n"
              + "Now using host=" + host + ", portNumber=" + portNumber);
    } else {
      host = args[0];
      portNumber = Integer.valueOf(args[1]).intValue();
    }

    try {
      clientSocket = new Socket(host, portNumber);
      inputLine = new BufferedReader(new InputStreamReader(System.in));
      os = new PrintStream(clientSocket.getOutputStream());
      is = new DataInputStream(clientSocket.getInputStream());
    } catch (UnknownHostException e) {    }
    catch (IOException e) {    }
    if (clientSocket != null && os != null && is != null) {
      try {
        new Thread(new MultiThreadChatClient()).start();
        while (!closed) {
            input=inputLine.readLine().trim();
          os.println(input);
        }
        os.close();
        is.close();
        clientSocket.close();
      } catch (IOException e) {      }
    }
  }

  public void run() {
    String responseLine;
    try {
      while ((responseLine = is.readLine()) != null) {
        System.out.println(responseLine);
        if (responseLine.indexOf("*** Bye") != -1)
          break;
      }
      closed = true;
    } catch (IOException e) {
      System.err.println("IOException:  " + e);
    }
  }
}