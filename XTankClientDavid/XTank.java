
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;

public class XTank 
{
	public static void main(String[] args) throws Exception 
    {
        try (var socket = new Socket("127.0.0.1", 59896)) 
        {
        	ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        	DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            var ui = new XTankUI(in, out);
            ui.start();
        }
    }
}


