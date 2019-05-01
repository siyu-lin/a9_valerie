/*Name: Valerie Jin, Al Mazzoli
 *File: LinkedQuadTree.java
 *Desc:
 *
 *The LinkedQuadTree class for Assignment 9.
 *
 *This class creates a LinkedQuadTree to help with image compression.
 *
 */

public class LinkedQuadTree{

    //instance variables: root, underlying
    //2d array, and the threshold
    private Node root;
    private Color[][] img;
    public static final double THRESHOLD = 0.75;
    private Color[][] compressed;
    int ctr = 0;

    // nested node class
    private static class Node{

	// instance variables element and the four children
	private Rectangle element;
	private Node nw;
	private Node ne;
	private Node sw;
	private Node se;

	/**Creates a Node with the given instance variables
	 *@param element The Rectangle covered by the node
	 *@param nw The northwest child of the node
	 *@param ne The northeast child of the node
	 *@param sw The southwest child of the node
	 *@param se The southeast child of the node
	 */
	public Node(Rectangle element, Node nw, Node ne, Node sw, Node se){
	    //sets appropriate instance variables to the given parameters
	    this.element = element;
	    this.nw = nw;
	    this.ne = ne;
	    this.sw = sw;
	    this.se = se;
    	}

    	/**Returns the Rectangle associated with the node
	 *@return element The node's Rectangle
	 */
    	public Rectangle getElement(){
    	    return element;
    	}

	/**Returns the node's northwest child
	 *@return The northwest child
	 */
    	public Node getNW(){
    	    return nw;
    	}

	/**Returns the node's northeast child
	 *@return The northeast child
	 */
    	public Node getNE(){
    	    return ne;
    	}

	/**Returns the node's southwest child
	 *@return The southwest child
	 */
	public Node getSW(){
    	    return sw;
    	}

	/**Returns the node's southeast child
	 *@return The southeast child
	 */
    	public Node getSE(){
    	    return se;
    	}

	public boolean isLeaf(){
	    if(nw == null && ne == null && se == null && sw == null){
		return true;
	    }
	    return false;
	}

	/**Sets this node's northwest child to a given node
	 *@param n The node that will become this node's northwest child
	 */
    	public void setNW(Node n){
    	    nw = n;
    	}

	/**Sets this node's northeast child to a given node
	 *@param n The node that will become this node's northeast child
	 */
	public void setNE(Node n){
    	    ne = n;
    	}

	/**Sets this node's southwest child to a given node
	 *@param n The node that will become this node's southwest child
	 */
	public void setSW(Node n){
    	    sw = n;
    	}

	/**Sets this node's southeast child to a given node
	 *@param n The node that will become this node's southeast child
	 */
	public void setSE(Node n){
    	    se = n;
    	}

	/**Sets this node's element to a given Rectangle
	 *@param element  The Rectangle that will become this node's element
	 */
    	public void setElement(Rectangle element){
    	    this.element = element;
    	}
    }


    /**Creates a LinkedQuadTree with a given 2D Color array,
     *the root as the leftmost, topmost pixel, and all
     *four children of that root set to null
     *@param img The given 2D Color array
     */
    public LinkedQuadTree(Color[][] img){
    	this.img = img;
    	Rectangle element = new Rectangle(0, 0, img.length, img[0].length);
    	root = new Node(element, null, null, null, null);
      compressed = new Color[img.length][img[0].length];
      for(int i = 0; i < img.length; i ++){
         for(int j = 0; j < img[i].length; j ++){
	         compressed[i][j] = img[i][j];
	       }
      }
    }

    /**Computes the average red, green, and blue color values for a given Rectangle.
     *Also computes the mean error of the Rectangle. Stores all these values
     *in an array of Doubles, which is returned.
     *@param root The Node with the Rectangle whose averages are being computed
     *@return A Double array of the various averages
     */
  public double[] average(Node root){

	//initialize the average red, blue, and green color values
	double averageR = 0.0;
	double averageG = 0.0;
	double averageB = 0.0;

	//initializes the Double array that will store the averages
	double[] average = new double[4];

	//gets the Rectangle from the given root
	Rectangle rootRectangle = root.getElement();

	//gets the size, width, and length of the Rectangle
	int size = rootRectangle.getWidth() * rootRectangle.getLength();
	int width = rootRectangle.getWidth() + rootRectangle.getX();
	int length = rootRectangle.getLength() + rootRectangle.getY();

	//goes through the entire Rectangle, adding each color value
	//to its approproate variable, to get total numbers of the red,
	//green, and blue color values for the entire Rectangle
	for (int i = rootRectangle.getX(); i< width; i++) {
	    for (int j = rootRectangle.getY(); j < length; j++) {
  		averageR += img[i][j].getR();
  		averageG += img[i][j].getG();
  		averageB += img[i][j].getB();
	    }
	}

	//divide the totals by the size of the Rectangle to actually
	//get the averages
	average[1] = averageR / size;
	average[2] = averageG / size;
	average[3] = averageB / size;
	//Compute mean Rectangle error
  double sum = 0;
	for (int i = rootRectangle.getX(); i< width; i++) {
	    for (int j = rootRectangle.getY(); j < length; j++) {
		    sum += Math.pow(img[i][j].getR() - averageR, 2) +
		           Math.pow(img[i][j].getG() - averageG, 2) +
		           Math.pow(img[i][j].getB() - averageB, 2);
	    }
	}
  average[0] = sum / size;
	return average;
  }

    /**Recursively compresses a Rectangle by finding areas of similar colors
     *and combining them so that they are the same color.
     *@param root The node storing the Rectangle that is being compressed
     */

     public void compress(){
       compressRec(root);
       System.out.println(ctr);
     }


    public void compressRec(Node root){
        if(isPixel(root)) return;
	//get the average color values of the given Rectangle,
	//as well as the mean error
	  double[] average = average(root);
  	if( average[0] < 4000 ){
	    // Compress all pixels under this root
  	    int x = root.getElement().getX();
  	    int y = root.getElement().getY();
	      int end_x = root.getElement().getWidth() + x;
        int end_y = root.getElement().getLength() + y;
        //System.out.println("Successfully Compress: Row: [" + x + "," + end_x +  "], Col: [" + 
          //                                                   y + "," + end_y + "]");
        Color c = new Color((int)Math.round(average[1]),(int)Math.round(average[2]),(int)Math.round(average[3]));
        // Below tries to change the color to make the compression part more visible
        // Color c = new Color((int)Math.round(average[2]),(int)Math.round(average[3]),(int)Math.round(average[1]));

        for(int i = x; i < end_x; i++){
          for(int j = y; j < end_y; j++){
            System.out.println("Setting (" + i + "," + j + ") to Color: (" + c.getR() + "," + c.getG() + "," + c.getB() + ")" );
            compressed[i][j] = c;
            ctr++;
          }
        }
      } else {
	   // Check whether need to compress the children
           int x = root.getElement().getX();
           int y = root.getElement().getY();
           int width = root.getElement().getWidth() + x;
           int length = root.getElement().getLength() + y;
           //System.out.println("Dividing: Row: [" + x + "," + width +  "], Col: [" + 
                       //                            y + "," + length + "]");
           setChildren(root);
           compressRec(root.getNW());
           compressRec(root.getNE());
           compressRec(root.getSW());
           compressRec(root.getSE());
      	}
    }

      public void getCtr(){
        System.out.println(ctr);
      }

    public boolean isPixel(Node root){
    	if(root.getElement().getLength() == 1 &&
    	   root.getElement().getWidth() == 1){
    	    return true;
    	}
    	return false;
    }

    /**Returns the 2D array that stores the normal picture
     *@return The 2D array with the normal picture
     */
    public Color[][] getImg(){
	     return img;
    }
    public Color[][] getCompressed(){
      return compressed;
    }

    // set childrens at root
    public void setChildren(Node root){
    //  if(!isPixel(root)){
      	int width = root.getElement().getWidth() / 2;
      	int length = root.getElement().getLength() / 2;

      	int x = root.getElement().getX();
      	int y = root.getElement().getY();

      	Node nw = new Node(new Rectangle(x, y, width, length), null, null, null, null);
      	Node ne = new Node(new Rectangle(x + width, y, width, length), null, null, null, null);
      	Node sw = new Node(new Rectangle(x, y + length, width, length), null, null, null, null);
      	Node se = new Node(new Rectangle(x + width, y + length, width, length), null, null, null, null);

      	root.setNW(nw);
      	root.setNE(ne);
      	root.setSW(sw);
      	root.setSE(se);
    //}
    }
}
