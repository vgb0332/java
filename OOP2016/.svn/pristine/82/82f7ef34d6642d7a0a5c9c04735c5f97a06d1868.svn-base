/**
 * Created on Dec 3, 2015
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package hufs.ces.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RMIMessangerImpl extends UnicastRemoteObject implements RMIMessanger {
	
	private HashMap<String,String> clientPair = null;
	private HashMap<String,Buffer> socketLikes = null;
	
	protected RMIMessangerImpl() throws RemoteException {
		super();
		clientPair = new HashMap<String,String>();
		socketLikes = new HashMap<String,Buffer>();
		
	}

	public boolean register (String id) {

		synchronized(clientPair){
			Set<String> keys = new HashSet<String>(clientPair.keySet());	
			if (!keys.contains(id)) { // new comer		
				System.out.println(id + "  registered");
				//this.write(id + "  registered");
				clientPair.put(id, id);
				socketLikes.put(id, new CircularBuffer());
				return true;
			}
			else {
				//this.write(id + "	 duplicated id\nTry new id");
				return false;
			}
		}
	}
	
	public String connect(String id) {

		synchronized(clientPair){
			Set<String> keys = new HashSet<String>(clientPair.keySet());	
			String pid = clientPair.get(id);

			if (pid.equals(id)) {
				// sender has no partner
				pid = selectReceiver(keys);
				if (pid == null || pid.equals(id)) 
					return null;
				else { // new Pair is made
					clientPair.put(id, pid);
					clientPair.put(pid, id);
				}
				keys.remove(id);
			}
			System.out.println(id + "  connected to " + pid);		
			return pid;
		}

	}

	private String selectReceiver(Set<String> keys) {
		// find first null valued key
		for (String rec:keys){
			if (clientPair.get(rec) == rec) return rec;
		}
		return null;

	}
	
	public String read(String id ) {
		Buffer rmiDataBuffer = socketLikes.get(clientPair.get(id));
		return (String) rmiDataBuffer.read();

	}

	public void write(String id, String data) {
		Buffer rmiDataBuffer = socketLikes.get(id);
		rmiDataBuffer.write(data);

	}

}
