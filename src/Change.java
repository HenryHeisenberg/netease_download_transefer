import java.io.*;
 
public class Change {
	
	public static void transfer(String cache,String cacheMusicName,String local,String musicName) {
	       DataInputStream dis = null;
	        DataOutputStream dos = null;
	        try {
	            File inFile = new File(cache+cacheMusicName);
	            File outFile = new File(local+musicName);
	 
	            dis = new DataInputStream(new FileInputStream(inFile));
	            dos = new DataOutputStream(new FileOutputStream(outFile));
	            byte[] b = new byte[1024];
	            int len;
	            while ((len = dis.read(b)) != -1) {
	                for (int i = 0; i < len; i++) {
	                    b[i] ^= 0xa3;
	                }
	                dos.write(b, 0, len);
	            }
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (dos != null) {
	                try {
	                    dos.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            if (dis != null) {
	                try {
	                    dis.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	}
}
