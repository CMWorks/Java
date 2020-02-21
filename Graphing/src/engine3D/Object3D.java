package engine3D;

import java.util.Vector;


/**
 * User: mino.ermal(AT)gmail.com
 * Date: 9-dic-2006
 * Time: 18.49.58
 *
 * This class represents a generic 3D object which is basically a set of triangles
 * or segments that together make up the object.
 *
 * Note:
 *   this form of representing a 3D object using directly a set of primitives
 *   like triangles or segments makes bad use of memory because of the shared vertexes
 *   and segments between triangles making it extremely redundant.
 *   The ideal would be to use a list of Vertexes and a list of Edges in a graph like
 *   representation, which is that used in real 3D models.
 *   I have chosen this way for it's simplicity, not wanting to create more complexity
 *   to the model generation by programming.
 *   But in the future can be implemented a filter which takes a generated model
 *   and removes redundancy by creating a graph without necessity to change actual
 *   project structure.
 *
 */
public class Object3D implements Transformable {

    //primitive set that make up the object
    public Vector transformables;

    //coordinate axis unit vectors (used to render the object relative coordinates)
    private CoordinateAxis coordinateAxis;

    /**
     * Initializes an empty 3D object
     */
    public Object3D(){
		transformables=new Vector();
	}

    /**
     * Initializes the object with the given transformables
     * @param transformables primitive set
     */
    public Object3D(Vector transformables){
		this.transformables=transformables;
	}

    /**
     * Adds a new primitive to the actual set
     * @param transformable to be added
     */
    public void add(Transformable transformable){
		transformables.add(transformable);
	}

    /**
     * @see mino.ermal.engine3d.interfaces.Transformable#scale(double)
     * @param factor scale factor
     */
    public void scale(double factor) {
		for(int i=0;i<transformables.size();i++){
			((Transformable)transformables.get(i)).scale(factor);
		}
        if(coordinateAxis!=null) coordinateAxis.scale(factor);
    }

    /**
     * @see mino.ermal.engine3d.interfaces.Transformable#scale(mino.ermal.engine3d.primitives.Vertex)
     * @param vector Vertex value
     */
    public void scale(Vertex vector) {
		for(int i=0;i<transformables.size();i++){
			((Transformable)transformables.get(i)).scale(vector);
		}
        if(coordinateAxis!=null) coordinateAxis.scale(vector);
    }

    /**
     * @see mino.ermal.engine3d.interfaces.Transformable#rotate(mino.ermal.engine3d.matrix.RotationMatrix3D)
     * @param rotationMatrix rotation matrix
     */
    public void rotate(RotationMatrix3D rotationMatrix) {
		for(int i=0;i<transformables.size();i++){
			((Transformable)transformables.get(i)).rotate(rotationMatrix);
		}
        if(coordinateAxis!=null) coordinateAxis.rotate(rotationMatrix);
    }

    /**
     * @see mino.ermal.engine3d.interfaces.Transformable#translate(mino.ermal.engine3d.primitives.Vertex)
     * @param vector translation vector
     */
    public void translate(Vertex vector) {
		for(int i=0;i<transformables.size();i++){
			((Transformable)transformables.get(i)).translate(vector);
		}
        if(coordinateAxis!=null) coordinateAxis.translate(vector);
    }

    /**
     * @see mino.ermal.engine3d.interfaces.Transformable#setColor(int, int, int)
     * @param r red (0..255)
     * @param g green (0..255)
     * @param b blue (0..255)
     */
    public void setColor(int r,int g,int b){
        for(int i=0;i<transformables.size();i++){
			((Transformable)transformables.get(i)).setColor(r,g,b);
		}
    }

    /**
     * Adds the coordinate axis with length given by length parameter
     * @param length length of the coordinate axes
     */
    public void addCoordinateAxis(double length) {
        this.coordinateAxis = new CoordinateAxis(length);
    }

    /**
     * Returns the coordinate axis object
     * @return CoordinateAxis object
     */
    public CoordinateAxis getCoordinateAxis() {
        return coordinateAxis;
    }
}
