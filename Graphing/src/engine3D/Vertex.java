package engine3D;

/**
 * User: mino.ermal(AT)gmail.com
 * Date: 9-dic-2006
 * Time: 18.51.45
 *
 * This class represents a vertexes, points and vectors
 * a simple object with 4 fields out of which only 3 are used for the moment.
 * The Transformable interface methods are effectively implemented only here.
 * Other Transformable children only delegate dhe method call.
 */
public class Vertex implements Transformable {
	public double x;
	public double y;
	public double z;
	public double w;

    /**
     * Creates a 0 vector
     */
    public Vertex(){
		x=0;
		y=0;
		z=0;
		w=1;
	}

    /**
     * Creates a vector with the parameter values
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     */
    public Vertex(double x,double y,double z){
		this.x=x;
		this.y=y;
		this.z=z;
		this.w=1;
	}

    /**
     * Creates a vector with the parameter values
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @param w W coordinate (not actually used as a coordinate)
     */
	public Vertex(double x,double y,double z,double w){
		this.x=x;
		this.y=y;
		this.z=z;
		this.w=w;
	}

    /**
     * @see mino.ermal.engine3d.interfaces.Transformable#scale(double)
     * @param factor scale factor
     */
    public void scale(double factor) {
		this.x*=factor;
		this.y*=factor;
		this.z*=factor;
	}

    /**
     * @see mino.ermal.engine3d.interfaces.Transformable#scale(mino.ermal.engine3d.primitives.Vertex)
     * @param vector Vertex value
     */
    public void scale(Vertex vector) {
		this.x*=vector.x;
		this.y*=vector.y;
		this.z*=vector.z;
	}

    /**
     * @see mino.ermal.engine3d.interfaces.Transformable#rotate(mino.ermal.engine3d.matrix.RotationMatrix3D)
     * @param rotationMatrix rotation matrix
     */
    public void rotate(RotationMatrix3D rotationMatrix) {
		rotationMatrix.multiply(this);
	}

    /**
     * @see mino.ermal.engine3d.interfaces.Transformable#translate(mino.ermal.engine3d.primitives.Vertex)
     * @param vector translation vector
     */
    public void translate(Vertex vector) {
		this.x+=vector.x;
		this.y+=vector.y;
		this.z+=vector.z;
	}

    /**
     * @see mino.ermal.engine3d.interfaces.Transformable#setColor(int, int, int)
     * @param r red (0..255)
     * @param g green (0..255)
     * @param b blue (0..255)
     */
    public void setColor(int r, int g, int b) {
        //not implemented for the moment
        //will be useful when implementing phong shading
    }

}
