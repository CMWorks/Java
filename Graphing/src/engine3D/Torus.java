package engine3D;

/**
 * User: mino.ermal(AT)gmail.com
 * Date: Mar 20, 2008
 * Time: 8:51:45 PM
 *
 * This class creates a torus applying the torus equation transformation
 * to the GridPlane that it extends
 *
 */
public class Torus extends GridPlane {

    /**
     * Creates a torus shape with the main radius r1 and secondary radius r2
     * with respective level of detail detail1,detail2.
     * @param r1 main radius
     * @param detail1 level of detail of the main circle
     * @param r2 secondary radius
     * @param detail2 level of detail of the secondary cirlce
     */
    public Torus(double r1,int detail1,double r2,int detail2){

        //call of the base class constructor to create a grid domain
        super(2*Math.PI,2*Math.PI,detail2,detail1);

        //transforms the grid domain into a tube
        for(int i=0;i<transformables.size();i++){
            Triangle tri=(Triangle)transformables.get(i);
            calculateTube(tri.v1,r2);
            calculateTube(tri.v2,r2);
            calculateTube(tri.v3,r2);
        }

        //translates the tube domain by r1
        this.translate(new Vertex(r1,0,0));

        //transforms the tube into a torus
        for(int j=0;j<transformables.size();j++){
            Triangle tri=(Triangle)transformables.get(j);
            calculateTorus(tri.v1);
            calculateTorus(tri.v2);
            calculateTorus(tri.v3);
        }
    }

    /**
     * Calculates the firts phase of torus creation, that of a tube
     * It transforms only the x and z coordinates with the circle equation
     * creating a tube in the y direction
     * @param v vertex to be transformed
     * @param r2 secondary radius
     */
    private void calculateTube(Vertex v,double r2){
        v.z=r2*Math.sin(v.x);
        v.x=r2*Math.cos(v.x);

    }

    /**
     * transforms the tube vertexes into a torus by applying this time
     * the circle equation only to the x and y coordinates
     * @param v vertex to be transformed
     */
    private void calculateTorus(Vertex v){
        double x;
        x=v.x;

        v.x=x*Math.cos(v.y);
        v.y=x*Math.sin(v.y);
    }
}