/*Name: Valerie Jin, Al Mazzoli
 *File: Color.java
 *Desc: 
 *
 *The Color class for Assignment 9.
 *
 *Creates a color with the given red, green, and blue values.
 *
 */

public class Color{
    
    //instance variables of red, green, blue
    private int r;
    private int g;
    private int b;

    
    /**Creates a Color with the given red, green, 
     *and blue values
     *@param r The given red value
     *@param g The given green value
     *@param b The given blue value 
     */
    public Color(int r, int g, int b){
	this.r = r;
	this.g = g;
	this.b = b;
    }

    /**Returns the red value of the color
     *@return The red value 
     */
    public int getR(){
	return r;
    }

    /**Returns the green value of the color
     *@return The green value 
     */
    public int getG(){
	return g;
    }

    /**Returns the blue value of the color
     *@return The blue value 
     */
    public int getB(){
	return b;
    }

    /**Sets the red value of the color to 
     *a given integer
     *@param The given red value
     */
    public void setR(int r){
	this.r = r;
    }

    /**Sets the green value of the color to 
     *a given integer
     *@param The given green value
     */
    public void setG(int g){
	this.g = g;
    }

    /**Sets the blue value of the color to 
     *a given integer
     *@param The given blue value
     */
    public void setB(int B){
	this.b = b;
    }

    /**Overrides toString so that the color
     *can be printed in String format 
     *@return The color in String format 
     */
    public String toString(){
	return r + " " + g + " " + b;
    }
}
