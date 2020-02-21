package engine3D;

/**
 * User: mino.ermal(AT)gmail.com
 * Date: Mar 21, 2008
 * Time: 10:22:47 AM
 *
 * Creates three coordinate segments
 * By convention red is the color of the X coordinate vector,
 * green that of the Y coordinate and blue that of the Z one.
 */
public class CoordinateAxis extends SegmentBasedObject3D {

    /**
     * Creates three coordinate axes with the given length
     * @param length axis length
     */
    public CoordinateAxis(double length){
        super();

        Vertex o=new Vertex(0,0,0);  //origin vertex
        Vertex x=new Vertex(length,0,0); //X direction axis
        Vertex y=new Vertex(0,length,0); //Y direction axis
        Vertex z=new Vertex(0,0,length); //Z direction axis

        Segment ox=new Segment(o,x);
        ox.setColor(255,0,0);
        Segment oy=new Segment(o,y);
        oy.setColor(0,255,0);
        Segment oz=new Segment(o,z);
        oz.setColor(0,0,255);

        transformables.add(ox);
        transformables.add(oy);
        transformables.add(oz);
    }
}