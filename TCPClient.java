//nasrin seifi 8907013
package chat;
import java.io.*;
import java.net.*;
public class TCPClient {

public static void main(String a[]) throws Exception
{
    //int port=9090;
 final Socket clientSocket = new Socket("localhost", 6789);
final TCPClient c=new TCPClient();
Runnable r1 = new Runnable() {
 public void run() {
    try {
      while (true) {
    c.Sendm(clientSocket);
        Thread.sleep(1000);
      }
    }catch (Exception ex) {}
  }
};
Runnable r2 = new Runnable() {
  public void run() {
    try {
      while (true) {
    c.Recvm(clientSocket);
        Thread.sleep(1500);
      }
    }    catch (Exception ex) {}
  }
};

Thread t=new Thread(r1);
Thread tt=new Thread(r2);
t.start();
tt.start();
}

public void Sendm(Socket clientSocket)
	{
			try {
                  BufferedReader inFromUser =
new BufferedReader(new InputStreamReader(System.in));
                  DataOutputStream outToServer =
new DataOutputStream(clientSocket.getOutputStream());

String sentence = inFromUser.readLine();
outToServer.writeBytes(sentence + '\n');
if(sentence.equals("bye"))
{clientSocket.close();
    System.exit(1);}
			}catch(IOException _IOExc) { }
	}
public void Recvm(Socket clientSocket)
{

try
{
 BufferedReader inFromServer =
new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
String Sentence = inFromServer.readLine();
System.out.println("FROM SERVER: "+ Sentence);
if(Sentence.equals("bye"))
{
clientSocket.close();
System.exit(1);
}//if
}catch(IOException _IOExc) { }
}
}
