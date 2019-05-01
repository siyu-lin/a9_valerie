/*Name: Valerie Jin, Al Mazzoli
 *File: Main.java
 *Desc:
 *The main driver program for Assignment 9.
 *
 *
 *
 */

import java.io.*;
public class Main{

    //public static final int FILELOC = 0;

    public static void main(String args[]) throws FileNotFoundException, IOException{

	//for(int a = 0; a < args.length; a++){

	  //  if(args[a].substring(args[a].length()-4).equals(".ppm")){

	//	String fileName = args[a];
    		String fileName = args[0];
		ProcessImage img = new ProcessImage(fileName);
		//Color[][] normal = img.getArray();
		//img.negative();
		//img.writeImg("noraml.ppm", img.getImg());
		//img.writeImg("negative.ppm", img.negative());
		//img.writeImg("edgeResult.ppm", img.edgeDetection());
    LinkedQuadTree test = new LinkedQuadTree(img.getImg());
    test.compress();
    Color[][] compressed = test.getCompressed();
   //  test.getCtr();
		img.writeImg("compressed.ppm", compressed);
		//Color[][] edgeDetection = img.

	   // }
//	}
    }
}
