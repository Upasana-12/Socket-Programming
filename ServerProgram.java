import java.util.*;
import java.io.*;
import java.net.*;
class ServerProgram implements Runnable
{
int clientport=10,serverport=20;
DatagramSocket ds;
Thread recThread;
ServerProgram() throws Exception
{

    ds=new DatagramSocket(serverport);
    recThread=new Thread(this);
    recThread.start();

    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    while(true)
    {
        String data=br.readLine();
        if(data.equals("EXIT"))
        break;
        DatagramPacket dp=new DatagramPacket(data.getBytes(),data.length(),InetAddress.getLocalHost(),clientport);
        ds.send(dp);
    }
    ds.close();
}

public void run()
{
    byte b[]=new byte[1000];
    while(true)
    {
        try
        {
        DatagramPacket dp=new DatagramPacket(b, b.length);
        ds.receive(dp);
        String data=new String(b,0,dp.getLength());
        System.out.println("Client: "+data);
        }catch(Exception e)
        {

        }
    }
   // ds.close();
}

public static void main(String[] args) throws Exception 
{
    new ServerProgram();
}
}