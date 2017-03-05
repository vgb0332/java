package hufs.ces.rmi;
/**
 * Created on Dec 3, 2015
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
import java.io.Serializable;

import javax.swing.*;

public class CircularBuffer implements Serializable, Buffer {

	// each array element is a buffer 
	private final static int MAX_BUFFER_COUNT = 10;
	private Object buffers[];

	// occupiedBufferCount maintains count of occupied buffers
	private int occupiedBufferCount = 0;

	// variables that maintain read and write buffer locations
	private int readLocation = 0, writeLocation = 0;

	// constructor

	public CircularBuffer(int bufSize)
	{
		buffers = new Object[bufSize];
	}

	public CircularBuffer()
	{
		this(MAX_BUFFER_COUNT);
	}

	// place value into buffer
	public synchronized void write(Object value )
	{
		// while there are no empty locations, place thread in waiting state
		while ( occupiedBufferCount == buffers.length ) {

			// output thread information and buffer information, then wait
			try {
				wait();
			}

			// if waiting thread interrupted, print stack trace
			catch ( InterruptedException exception )
			{
				exception.printStackTrace();
			}

		} // end while

		// place value in writeLocation of buffers
		buffers[ writeLocation ] = value;
		// just produced a value, so increment number of occupied buffers
		++occupiedBufferCount;

		// update writeLocation for future write operation
		writeLocation = ( writeLocation + 1 ) % buffers.length;

		//System.out.println("Write:" + this.createStateOutput());                      
		notify(); // return waiting thread (if there is one) to ready state

	} // end method set

	// return value from buffer
	public synchronized Object read()
	{  

		// while no data to read, place thread in waiting state
		while ( occupiedBufferCount == 0 ) {

			// output thread information and buffer information, then wait
			try {        	 

				wait();
			}

			// if waiting thread interrupted, print stack trace
			catch ( InterruptedException exception ) {
				exception.printStackTrace();
			}

		} // end while

		// obtain value at current readLocation
		Object readValue = buffers[ readLocation ];

		// just consumed a value, so decrement number of occupied buffers
		--occupiedBufferCount;

		// update readLocation for future read operation
		readLocation = ( readLocation + 1 ) % buffers.length;

		//System.out.println("Read:" + this.createStateOutput());
		notify(); // return waiting thread (if there is one) to ready state

		return readValue;

	} // end method get

	// create state output
	public String createStateOutput()
	{
		// first line of state information
		String output = 
				"(buffers occupied: " + occupiedBufferCount + ")\nbuffers: ";

		for ( int i = 0; i < buffers.length; i++ ) {
			String theLine = (String) buffers[i];
			if (theLine == null)
				theLine = "^ ";
			else if (theLine.length() == 0)
				theLine = "^^";
			else if (theLine.length() == 1)
				theLine = theLine + " ";
			else
				theLine = theLine.trim().substring(0,2);
			output += " " + theLine + "  ";
		}

		// second line of state information
		output += "\n         ";

		for ( int i = 0; i < buffers.length; i++ )
			output += "---- ";

		// third line of state information
		output += "\n         ";

		// append readLocation (R) and writeLocation (W)
		// indicators below appropriate buffer locations
		for ( int i = 0; i < buffers.length; i++ )

			if ( i == writeLocation && writeLocation == readLocation )
				output += " WR  ";
			else if ( i == writeLocation )
				output += " W   ";
			else if ( i == readLocation )
				output += "  R  ";
			else 
				output += "     ";

		output += "\n";

		return output;

	} // end method createStateOutput

} // end class CircularBuffer
