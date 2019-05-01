import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.lang.Math;
public class ProcessImage{

    public Color[][] img;
    //public int row;
    //public int column;

    public  ProcessImage(String fileName) throws FileNotFoundException{

	Scanner infile = new Scanner(new File(fileName)); // create a scanner file
 	if(!infile.hasNextInt()){
 	   infile.nextLine();
  	}

	int row = infile.nextInt();
	int column = infile.nextInt();
	int threshold = infile.nextInt();
	int r = 0;
	int g = 0;
	int b = 0;

	img = new Color[row][column];

	for (int i = 0; i< row; i++) {
	    for (int j = 0; j < column; j++) {

		r = infile.nextInt();
		g = infile.nextInt();
		b = infile.nextInt();
		img[i][j]= new Color(r,g,b);
	    }
	}
    }

    public Color[][] getImg(){
	return img;
  }

    public void writeImg(String filename, Color[][] img)throws IOException{
  	PrintWriter out = new PrintWriter(filename);
   	out.println("P3");
  	out.println(img.length + " " + img[0].length);
  	out.println(255);
  	for (int i = 0; i < img.length; i++) {
  	    for (int j = 0; j < img[i].length; j++) {
  		//System.out.println(img[i][j].getR() + " " + img[i][j].getG() + " " + img[i][j].getB() + " ");
  			out.println(img[i][j].getR());
  	   	 	out.println(img[i][j].getG());
  	    	out.println(img[i][j].getB());
  	    }
  	}
	out.close();
    }
    /*
      for (int i = 0; i< array.length; i++) {
      for (int j = 0; j < array[0].length; j++) {
      out.print(array[i][j].getR() + " " + array[i][j].getG() + " " + array[i][j].getB() + " ");
      }
      out.println();
      }
      out.close();

      }*/

    //can't call until processImage has been called
    public Color[][] negative(){
	Color[][] negative = new Color[img.length][img[0].length];
	for(int a = 0; a < img.length; a++){
	    for(int i = 0; i < img[0].length; i++){

		int oldRed = img[a][i].getR();
		int oldGreen = img[a][i].getG();
		int oldBlue = img[a][i].getB();

		Color addColor = new Color(255-oldRed, 255-oldGreen, 255-oldBlue);

		negative[a][i] = addColor;
	    }
	}
	return negative;
    }

    public Color[][] edgeDetection(){

	Color[][] edgeDetection = new Color[img.length][img[0].length];
	Color newColor = null;
	double trying = 0;

	for(int i = 0; i < img.length; i++){
	    for(int j = 0; j < img[0].length; j++){
		if( i == 0 && j == 0){
		    int newRed = 3 * img[i][j].getR() - img[i+1][j].getR() - img[i][j+1].getR() - img[i+1][j+1].getR();

		    int newGreen = 3 * img[i][j].getG() - img[i+1][j].getG() - img[i][j+1].getG() - img[i+1][j+1].getG();

		    int newBlue = 3 * img[i][j].getB() - img[i+1][j].getB() - img[i][j+1].getB() - img[i+1][j+1].getB();
		    newColor = validColor(newRed, newGreen, newBlue);
		}else if(i == 0 && j == img[0].length - 1){
		    int newRed =  - img[i][j-1].getR() - img[i+1][j-1].getR() + 3 * img[i][j].getR() - img[i+1][j].getR();

		    int newGreen =  - img[i][j-1].getG() - img[i+1][j-1].getG() + 3 * img[i][j].getG() - img[i+1][j].getG();

		    int newBlue =- img[i][j-1].getB() - img[i+1][j-1].getB() + 3 * img[i][j].getB() - img[i+1][j].getB();

		    newColor = validColor(newRed, newGreen, newBlue);
		}else if(i == img.length - 1 && j == 0){
		    int newRed = - img[i-1][j].getR() + 3 * img[i][j].getR() - img[i-1][j+1].getR() - img[i][j+1].getR();

		    int newGreen = - img[i-1][j].getG() + 3 * img[i][j].getG() - img[i-1][j+1].getG() - img[i][j+1].getG();

		    int newBlue = -img[i-1][j].getB() + 3 * img[i][j].getB() - img[i-1][j+1].getB() - img[i][j+1].getB();

		    newColor = validColor(newRed, newGreen, newBlue);
		}else if(i == img.length - 1 && j == img[0].length - 1){
		    int newRed = -img[i-1][j-1].getR() - img[i][j-1].getR() - img[i-1][j].getR() + 3 * img[i][j].getR();

		    int newGreen = -img[i-1][j-1].getG() - img[i][j-1].getG() - img[i-1][j].getG() + 3 * img[i][j].getG();

		    int newBlue = -img[i-1][j-1].getB() - img[i][j-1].getB() - img[i-1][j].getB() + 3 * img[i][j].getB();
		    newColor = validColor(newRed, newGreen, newBlue);
  		}else if (i == 0){
  		    int newRed = - img[i][j-1].getR() - img[i+1][j-1].getR() + 5 * img[i][j].getR()
			- img[i+1][j].getR() - img[i][j+1].getR() - img[i+1][j+1].getR();

  		    int newGreen = - img[i][j-1].getG() - img[i+1][j-1].getG() + 5 * img[i][j].getG()
			- img[i+1][j].getG() - img[i][j+1].getG() - img[i+1][j+1].getG();

  		    int newBlue = - img[i][j-1].getB() - img[i+1][j-1].getB() + 5 * img[i][j].getB()
                        - img[i+1][j].getB() - img[i][j+1].getB() - img[i+1][j+1].getB();
  		    newColor = validColor(newRed, newGreen, newBlue);
		}else if(i == img.length - 1){
		    int newRed = -img[i-1][j-1].getR() - img[i][j-1].getR()- img[i-1][j].getR()
			+ 5 * img[i][j].getR() - img[i-1][j+1].getR() - img[i][j+1].getR();

		    int newGreen = -img[i-1][j-1].getG() - img[i][j-1].getG() - img[i-1][j].getG()
			+ 5 * img[i][j].getG() - img[i-1][j+1].getG() - img[i][j+1].getG();

		    int newBlue = -img[i-1][j-1].getB() - img[i][j-1].getB() - img[i-1][j].getB()
			+ 5 * img[i][j].getB() - img[i-1][j+1].getB() - img[i][j+1].getB();
		    newColor = validColor(newRed, newGreen, newBlue);
		}else if(j == 0){
		    int newRed = - img[i-1][j].getR() + 5 * img[i][j].getR() - img[i+1][j].getR()
			- img[i-1][j+1].getR() - img[i][j+1].getR() - img[i+1][j+1].getR();

		    int newGreen = - img[i-1][j].getG() + 5 * img[i][j].getG() - img[i+1][j].getG()
			- img[i-1][j+1].getG() - img[i][j+1].getG() - img[i+1][j+1].getG();

		    int newBlue = - img[i-1][j].getB() + 5 * img[i][j].getB() - img[i+1][j].getB()
			- img[i-1][j+1].getB() - img[i][j+1].getB() - img[i+1][j+1].getB();

		    newColor = validColor(newRed, newGreen, newBlue);
		}else if(j == img[0].length - 1){
		    int newRed = -img[i-1][j-1].getR() - img[i][j-1].getR() - img[i+1][j-1].getR()
			- img[i-1][j].getR() + 5 * img[i][j].getR() - img[i+1][j].getR();

		    int newGreen = -img[i-1][j-1].getG() - img[i][j-1].getG() - img[i+1][j-1].getG()
			- img[i-1][j].getG() + 5 * img[i][j].getG() - img[i+1][j].getG();

		    int newBlue = -img[i-1][j-1].getB() - img[i][j-1].getB() - img[i+1][j-1].getB()
			- img[i-1][j].getB() + 5 * img[i][j].getB() - img[i+1][j].getB();

		    newColor = validColor(newRed, newGreen, newBlue);
		}else{
		    int newRed = -img[i-1][j-1].getR() - img[i][j-1].getR() - img[i+1][j-1].getR()
			- img[i-1][j].getR() + 8 * img[i][j].getR() - img[i+1][j].getR()
			- img[i-1][j+1].getR() - img[i][j+1].getR() - img[i+1][j+1].getR();

		    int newGreen = -img[i-1][j-1].getG() - img[i][j-1].getG() - img[i+1][j-1].getG()
			- img[i-1][j].getG() + 8 * img[i][j].getG() - img[i+1][j].getG()
			- img[i-1][j+1].getG() - img[i][j+1].getG() - img[i+1][j+1].getG();

		    int newBlue = -img[i-1][j-1].getB() - img[i][j-1].getB() - img[i+1][j-1].getB()
			- img[i-1][j].getB() + 8 * img[i][j].getB() - img[i+1][j].getB()
			- img[i-1][j+1].getB() - img[i][j+1].getB() - img[i+1][j+1].getB();

		    newColor = validColor(newRed, newGreen, newBlue);
		}
	       	edgeDetection[i][j] = newColor;
	    }
	}
	return edgeDetection;
    }
    // test if the color is valid. If not, change it into valid color range
    public Color validColor(int r, int g, int b){
	if(r < 0){
	    r = 0;
	}else if(r > 255){
	    r = 255;
	}
	if(g < 0){
	    g = 0;
	}else if(g > 255){
	    g = 255;
	}
	if(b < 0){
	    b = 0;
	}else if(b > 255){
	    b = 255;
	}
	Color color = new Color(r,g,b);
	return color;
    }
}
