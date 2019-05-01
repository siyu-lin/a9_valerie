// Title:            class Square
// Course:           cs206
// Author:           Valerie Jin, Al Mazzoli
// Date:             24/4/2019
// Class purpose:    A new object called Rectangle
public class Rectangle{
  //instance variables of x, y, width, length
  private int x;
  private int y;
  private int width;
  private int length;
  // Rectangle constructor
  public Rectangle(int x, int y, int width, int length){
    this.x = x;
    this.y = y;
    this.width = width;
    this.length = length;
  }
  // getters
  public int getX(){
    return x;
  }
  public int getY(){
    return y;
  }
  public int getWidth(){
    return width;
  }
  public int getLength(){
    return length;
  }
  //setters
  public void setX(int x){
    this.x = x;
  }
  public void setY(int y){
    this.y = y;
  }
  public void setWidth(int width){
    this.width = width;
  }
  public void setLength(int length){
    this.length = length;
  }
  // toString()
  public String toString(){
    return "x" + x + "y" + y + " width " + width + " length " + length;
  }
}
