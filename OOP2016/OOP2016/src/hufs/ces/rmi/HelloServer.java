package hufs.ces.rmi;

import java.rmi.Naming;

public class HelloServer {

	private static final String HOST = "localhost";
	public static void main(String[] args)
			throws Exception {
		//Create a reference to an
		//implementation object・
		HelloImpl temp = new HelloImpl();
		//Create the string URL holding the
		//object's name・
		String rmiObjectName = "rmi://" + HOST + "/Hello";
		//(Could omit host name here, since 'localhost'
		//would be assumed by default.)
		//'Bind' the object reference to the name・
		Naming.rebind(rmiObjectName,temp);
		//Display a message so that we know the process
		//has been completed・
		System.out.println("Binding complete・\n");
	}
}
