package engine3D;

/**
 * User: mino.ermal(AT)gmail.com
 * Date: 9-dic-2006
 * Time: 19.41.44
 *
 * Interface Transformable is implemented in all graphic objects, from Vertex,
 * Segment, Triangle and all 3D object classes creating a family hierarchy where
 * every object is a transofmable object. This makes 3D transforming operations
 * transparent indipendently form the type of object that is being transformed,
 * and also hierarchy transformation very straightforward.
 *
 * In fact when you call the scale() method of a 3D object the object just
 * delegates the call to it's primitives(Segments or Triangles) from which it is
 * composed, which delegates the real transformation to Vertex primitives.
 * 
 */
public interface Transformable {

    /**
     * This method scales all 3 dimensions by the same quantity
     * @param factor scale factor
     */
    public void scale(double factor);

    /**
     * This method scales each dimension indipendently
     * x,y and z values of the Vertex object are used separately to scale
     * each dimension respectively
     * @param vector Vertex value
     */
    public void scale(Vertex vector);

    /**
     * This method rotates the object multiplicating with rotation matrix
     * wich must be created and prepared previously
     * @param rotationMatrix rotation matrix
     */
    public void rotate(RotationMatrix3D rotationMatrix);

    /**
     * This method translates an object by the x,y,z values of the
     * vector in the x,y,z directions
     * @param vector translation vector
     */
    public void translate(Vertex vector);

    /**
     * Sets the color of this transformable
     * @param r red (0..255)
     * @param g green (0..255)
     * @param b blue (0..255)
     */
    public void setColor(int r,int g,int b);
}
