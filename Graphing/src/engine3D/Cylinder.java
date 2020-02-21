package engine3D;

/**
 * User: mino.ermal(AT)gmail.com
 * Date: 10-dic-2006
 * Time: 22.01.46
 *
 * Creates a cylinder
 *
 */
public class Cylinder extends TriangleBasedObject3D {

    /**
     * creates a cylinder with the desired level of detail given by
     * an angle value expressed in radiants, the smaller this value is
     * the greater will be the level of detail and vice versa
     * @param radius radius
     * @param height height
     * @param detail level of detail
     */
    public Cylinder(double radius, double height, int detail){
		super();
        double deltaR=2*Math.PI/detail;
        for(double i=0;i<=2*Math.PI;i+= deltaR){
			transformables.add(new Triangle(new Vertex(radius*Math.cos(i),height,radius*Math.sin(i)),new Vertex(radius*Math.cos(i),-height,radius*Math.sin(i)),new Vertex(radius*Math.cos(i+ deltaR),height,radius*Math.sin(i+ deltaR))));
			transformables.add(new Triangle(new Vertex(radius*Math.cos(i),-height,radius*Math.sin(i)),new Vertex(radius*Math.cos(i+ deltaR),-height,radius*Math.sin(i+ deltaR)),new Vertex(radius*Math.cos(i+ deltaR),height,radius*Math.sin(i+ deltaR))));

			transformables.add(new Triangle(new Vertex(radius*Math.cos(i),height,radius*Math.sin(i)),new Vertex(0,height,0),new Vertex(radius*Math.cos(i+ deltaR),height,radius*Math.sin(i+ deltaR))));
			transformables.add(new Triangle(new Vertex(radius*Math.cos(i),-height,radius*Math.sin(i)),new Vertex(radius*Math.cos(i+ deltaR),-height,radius*Math.sin(i+ deltaR)),new Vertex(0,-height,0)));
		}
	}
}
