package engine3D;

/**
 * User: mino.ermal(AT)gmail.com
 * Date: Mar 21, 2008
 * Time: 10:22:47 AM
 *
 * This object creates a straight line of a certain length
 * divided into a number of segments.
 * This object also provides a basis for other segment based objects
 * like Spiral.
 *
 */
public class SegmentDomain extends SegmentBasedObject3D {

    /**
     * Creates a straight line of length "length" and divided into "detail"
     * segments.
     * @param length length
     * @param detail number of segments that compone this line
     */
    public SegmentDomain(double length, int detail){
        super();
        double delta=length/detail;
        for(int i=0;i<detail-1;i++){
            transformables.add(new Segment(new Vertex(i*delta,0,0),new Vertex((i+1)*delta,0,0)));
        }
    }
}
