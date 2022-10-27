
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.net.InetAddress;
import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * When a client connects, a new thread is started to handle it.
 */
public class XTankServer 
{
	static ArrayList<ObjectOutputStream> sq;
	static XTankModel md;
	
    public static void main(String[] args) throws Exception 
    {
		System.out.println(InetAddress.getLocalHost());
		sq = new ArrayList<>();
		md = new XTankModel();
		
        try (var listener = new ServerSocket(59896)) 
        {
            System.out.println("The XTank server is running...");
            var pool = Executors.newFixedThreadPool(20);
            while (true) 
            {
                pool.execute(new XTankManager(listener.accept()));
            }
        }
    }

    private static class XTankManager implements Runnable 
    {
        private Socket socket;

        XTankManager(Socket socket) { this.socket = socket; }

        @Override
        public void run() 
        {
            System.out.println("Connected: " + socket);
            try 
            {
            	System.out.println("CREATE STREAMS");
            	DataInputStream in = new DataInputStream(socket.getInputStream());
            	ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            	System.out.println("CREATE TANK");
            	Tank tank = new Tank(300, 300, 2, socket.toString());
            	md.addTank(tank);
            	//System.out.println("First: " + md.getTanks());
                sq.add(out);
                System.out.println("SQ: " + sq);
                char command;
                while (true)
                {
                	command = in.readChar();
                	System.out.println("COMMAND: "  +command);
                	if (command == ' ') {
                		md.addShot(tank, tank.getAngle());
                	}
                	
                	if (command == 'w') {
                		md.moveTank(tank, 0, -10, 2);
                	} 
                	if (command == 'a') {
                		md.moveTank(tank, -10, 0, 1);
                	}
                	if (command == 's') {
                		md.moveTank(tank, 0, 10, 3);
                	}
                	if (command == 'd') {
                		md.moveTank(tank, 10, 0, 0);
                	}
                	md.moveBullets();
                	md.regHits();
                	System.out.println("END");
                	for (ObjectOutputStream o: sq)
                	{
    					o.writeObject(md.getTanks());
    					o.writeObject(md.getShots());
    					System.out.println("Tanks: " + md.getTanks());
    					System.out.println("Shots: " + md.getShots());
                	}
                }
            } 
            catch (Exception e) 
            {
            	e.printStackTrace();
                System.out.println("Error:" + socket);
            } 
            finally 
            {
                try { socket.close(); } 
                catch (IOException e) {}
                System.out.println("Closed: " + socket);
            }
        }
    }
    
}


