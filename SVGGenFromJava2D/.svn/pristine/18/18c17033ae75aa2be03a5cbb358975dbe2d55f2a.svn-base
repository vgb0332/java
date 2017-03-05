/**
 * Created on Oct 16, 2012
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package hufs.cse.svg.svggen;

import java.awt.image.Kernel;

/**
 * @author cskim
 *
 */
public class KernelFactory {

	public static Kernel createKernel(int n){
		float[] matrix = new float[n*n];
		for (int i=0; i<n*n; ++i){
			matrix[i] = 1f / (float)(n*n);
		}
		return new Kernel(n, n, matrix);
	}
}
