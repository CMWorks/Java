package engine3D;

public class Line extends SegmentBasedObject3D{
	/**
     * Creates a new wireframe cube
     */
    public Line(double x1, double y1, double z1, double x2, double y2, double z2){
		super();
		transformables.add(new Segment(new Vertex(x1,y1,z1),new Vertex(x2,y2,z2)));

	}
}
