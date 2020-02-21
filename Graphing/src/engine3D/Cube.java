package engine3D;

/**
 * User: mino.ermal(AT)gmail.com
 * Date: 9-dic-2006
 * Time: 18.49.37
 *
 * This class creates a Cube (only segment based,
 * the triangle based has can be implemented too)
 *
 */
public class Cube extends SegmentBasedObject3D {

    /**
     * Creates a new wireframe cube
     */
    public Cube(){
		super();
		transformables.add(new Segment(new Vertex(0,0,0),new Vertex(1,0,0)));
		transformables.add(new Segment(new Vertex(0,0,0),new Vertex(0,1,0)));
		transformables.add(new Segment(new Vertex(0,0,0),new Vertex(0,0,1)));

		transformables.add(new Segment(new Vertex(0,1,0),new Vertex(1,1,0)));
		transformables.add(new Segment(new Vertex(0,1,0),new Vertex(0,1,1)));

		transformables.add(new Segment(new Vertex(1,0,0),new Vertex(1,1,0)));
		transformables.add(new Segment(new Vertex(0,1,1),new Vertex(0,0,1)));

		transformables.add(new Segment(new Vertex(0,0,1),new Vertex(1,0,1)));
		transformables.add(new Segment(new Vertex(1,0,0),new Vertex(1,0,1)));

		transformables.add(new Segment(new Vertex(0,1,1),new Vertex(1,1,1)));
		transformables.add(new Segment(new Vertex(1,1,0),new Vertex(1,1,1)));
		transformables.add(new Segment(new Vertex(1,0,1),new Vertex(1,1,1)));

	}
	

}
