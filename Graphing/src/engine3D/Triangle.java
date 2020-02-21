package engine3D;

/**
 * User: mino.ermal(AT)gmail.com
 * Date: 9-dic-2006
 * Time: 19.13.51
 *
 * Triangle primitive composed of 3 vertexes
 *
 */
public class Triangle implements Transformable {

    public Vertex v1; //first vertex
	public Vertex v2; //second vertex
	public Vertex v3; //third vertex
    public int r=255; //red color component
    public int g=255; //green color component
    public int b=255; //blue color component

    /**
     * Creates a new triangle having as vertexes those given as parameter
     * @param v1 first vertex
     * @param v2 second vertex
     * @param v3 third vertex
     */
    public Triangle(Vertex v1,Vertex v2,Vertex v3){
		this.v1=v1;
		this.v2=v2;
		this.v3=v3;
	}

    /**
     * @see mino.ermal.engine3d.interfaces.Transformable#scale(double)
     * @param factor scale factor
     */
    public void scale(double factor) {
		v1.scale(factor);
		v2.scale(factor);
		v3.scale(factor);
	}

    /**
     * @see mino.ermal.engine3d.interfaces.Transformable#scale(mino.ermal.engine3d.primitives.Vertex)
     * @param vector Vertex value
     */
    public void scale(Vertex vector) {
		v1.scale(vector);
		v2.scale(vector);
		v3.scale(vector);
	}

    /**
     * @see mino.ermal.engine3d.interfaces.Transformable#rotate(mino.ermal.engine3d.matrix.RotationMatrix3D)
     * @param rotationMatrix rotation matrix
     */
	public void rotate(RotationMatrix3D rotationMatrix) {
		v1.rotate(rotationMatrix);
		v2.rotate(rotationMatrix);
		v3.rotate(rotationMatrix);
	}

    /**
     * @see mino.ermal.engine3d.interfaces.Transformable#translate(mino.ermal.engine3d.primitives.Vertex)
     * @param vector translation vector
     */
	public void translate(Vertex vector) {
		v1.translate(vector);
		v2.translate(vector);
		v3.translate(vector);
	}

    /**
     * @see mino.ermal.engine3d.interfaces.Transformable#setColor(int, int, int)
     * @param r red (0..255)
     * @param g green (0..255)
     * @param b blue (0..255)
     */
    public void setColor(int r,int g,int b){
        this.r = r;
        this.g = g;
        this.b = b;
    }
}
