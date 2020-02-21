package engine3D;

/**
 * User: mino.ermal(AT)gmail.com
 * Date: 9-dic-2006
 * Time: 19.04.37
 *
 * Segment primitive.
 * Has two vertexes, start and end point.
 * It is rendered as a simple segment
 *
 */
public class Segment implements Transformable {

    public Vertex start; //start point
	public Vertex end; //end point
    public int r=0;   //red color component
    public int g=255; //green color component
    public int b=0;   //blue color component

    /**
     * Crates a new Segment with start point "start" and end point "end"
     * @param start start point
     * @param end end point
     */
    public Segment(Vertex start,Vertex end){
		this.start=start;
		this.end=end;
	}

    /**
     * @see mino.ermal.engine3d.interfaces.Transformable#scale(double)
     * @param factor scale factor
     */
    public void scale(double factor) {
		start.scale(factor);
		end.scale(factor);
	}

    /**
     * @see mino.ermal.engine3d.interfaces.Transformable#scale(mino.ermal.engine3d.primitives.Vertex)
     * @param vector Vertex value
     */
    public void scale(Vertex vector) {
		start.scale(vector);
		end.scale(vector);
	}

    /**
     * @see mino.ermal.engine3d.interfaces.Transformable#rotate(mino.ermal.engine3d.matrix.RotationMatrix3D)
     * @param rotationMatrix rotation matrix
     */
    public void rotate(RotationMatrix3D rotationMatrix) {
		start.rotate(rotationMatrix);
		end.rotate(rotationMatrix);
	}

    /**
     * @see mino.ermal.engine3d.interfaces.Transformable#translate(mino.ermal.engine3d.primitives.Vertex)
     * @param vector translation vector
     */
    public void translate(Vertex vector) {
		start.translate(vector);
		end.translate(vector);
	}

    /**
     * @see mino.ermal.engine3d.interfaces.Transformable#setColor(int, int, int)
     * @param r red (0..255)
     * @param g green (0..255)
     * @param b blue (0..255)
     */
    public void setColor(int r, int g, int b) {
        this.r=r;
        this.g=g;
        this.b=b;
    }
}
