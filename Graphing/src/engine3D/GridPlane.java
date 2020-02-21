package engine3D;

/**
 * User: mino.ermal(AT)gmail.com
 * Date: Mar 20, 2008
 * Time: 8:53:06 PM
 *
 * This is a very important type of shape, and will provide the basis
 * for other shapes as Sphere and Torus by being transformed.
 *
 */
public class GridPlane extends TriangleBasedObject3D {

    /**
     * Creates a plane with width u, length v subdivided in a grid
     * of detailU x detailV  quadrates.
     * @param u width
     * @param v length
     * @param detailU level of detail in width
     * @param detailV level of detail in height
     */
    public GridPlane(double u,double v,int detailU,int detailV){
        double deltaU,deltaV;
        deltaU=u/detailU;
        deltaV=v/detailV;
        for(int i=0;i<detailU;i++){
            for(int j=0;j<detailV;j++){
                transformables.add(new Triangle(new Vertex(i*deltaU,j*deltaV,0),new Vertex(i*deltaU,(j+1)*deltaV,0),new Vertex((i+1)*deltaU,(j+1)*deltaV,0)));
                transformables.add(new Triangle(new Vertex(i*deltaU,j*deltaV,0),new Vertex((i+1)*deltaU,j*deltaV,0),new Vertex((i+1)*deltaU,(j+1)*deltaV,0)));
            }
        }

    }
}
