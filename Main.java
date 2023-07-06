package chat;
public class Main {
    public static void main(final String[] args) throws Exception {
         Runnable r1 = new Runnable() {

            public void run() {
                try {
                    while (true) {
                        TCPServer.main(args);
                        Thread.sleep(1500L);
                    }
                } catch (Exception ex) {
                }
            }
        };
        Runnable r2 = new Runnable() {

            public void run() {
                try {
                    while (true) {
                        TCPClient.main(args);
                        Thread.sleep(1000L);
                    }
                } catch (Exception ex) {
                }
            }
        };
        Thread t = new Thread(r1);
        Thread tt = new Thread(r2);

        tt.start();
        t.start();
    }//main
}
