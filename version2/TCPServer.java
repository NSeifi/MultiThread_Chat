//nasrin seifi 8907013
package chat;
import java.io.*;
import java.net.*;
class TCPServer {
public static void main(final String [] a) throws Exception
{
final ServerSocket welcomeSocket = new ServerSocket(6789);
 final Socket connectionSocket=welcomeSocket.accept();
final TCPServer c=new TCPServer();
Runnable r1 = new Runnable() {
 public void run() {
    try {
      while (true) {
    c.Sendm(connectionSocket);
        Thread.sleep(1000);
      }
    }catch (Exception ex) {}
  }
};
Runnable r2 = new Runnable() {
  public void run() {
    try {
      while (true) {
    c.Recvmm(connectionSocket);
        Thread.sleep(1500);
      }
    }    catch (Exception ex) {}
  }
};
Thread t=new Thread(r1);
Thread tt=new Thread(r2);
tt.start();
t.start();
}//main
public void Sendm(Socket connectionSocket)
{
    try{
BufferedReader infromserver=new BufferedReader(new InputStreamReader(System.in));
DataOutputStream outToclient =
new DataOutputStream(connectionSocket.getOutputStream());
String Sentence = infromserver.readLine();
outToclient.writeBytes(Sentence + '\n');
if(Sentence.equals("bye"))
    System.exit(1);}catch(IOException e){}
}
public void Recvmm(Socket connectionSocket)
{
try{
    BufferedReader inFromClient =
new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
String clientSentence = inFromClient.readLine();
System.out.println("FROM CLIENT: "+clientSentence);
if(clientSentence.equals("bye"))
    System.exit(1);
}catch(IOException e){}
}
}
