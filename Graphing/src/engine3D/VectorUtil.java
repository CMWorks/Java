package engine3D;

/**
 * User: mino.ermal(AT)gmail.com
 * Date: 26-dic-2006
 * Time: 8.58.20
 *
 * Utility class implementing the most common operations with vectors
 *
 */
public class VectorUtil {

    /**
     * Calculates the normal vector of the triangle tri
     * @param tri triangle
     * @return triangle normal vector
     */
    public static Vertex calculateNormal(Triangle tri){

		//obtain first vector
		Vertex v1=new Vertex();
		v1.x=tri.v2.x-tri.v1.x;
		v1.y=tri.v2.y-tri.v1.y;
		v1.z=tri.v2.z-tri.v1.z;

		//obtain second vector
		Vertex v2=new Vertex();
		v2.x=tri.v3.x-tri.v1.x;
		v2.y=tri.v3.y-tri.v1.y;
		v2.z=tri.v3.z-tri.v1.z;

		//calculate the cross product between the two vectors to obtain
		//a vector orthogonal to the others

		return calculateCrossProduct(v1,v2);
	}

    /**
     * Calculates the length(modulus) of a vector
     * @param vector vector to be calculated
     * @return vector length
     */
    public static double calculateLength(Vertex vector){
		return Math.sqrt(vector.x*vector.x+vector.y*vector.y+vector.z*vector.z);
	}

    /**
     * Calculate unit vector
     * @param vector vector from which to calculate the unit vector
     * @return unit vector
     */
    public static Vertex calculateUnitVector(Vertex vector){
		Vertex unit=new Vertex();
		double length= calculateLength(vector);
		unit.x=vector.x/length;
		unit.y=vector.y/length;
		unit.z=vector.z/length;
		return unit;
	}

    /**
     * Calculates the cosin value of the angle between the two vectors
     * @param v1 first vector
     * @param v2 second vector
     * @return cosin value
     */
    public static double calculateAngleCosBetweenVectors(Vertex v1,Vertex v2){
		Vertex u1= calculateUnitVector(v1);
		Vertex u2= calculateUnitVector(v2);
		return calculateDotProduct(u1,u2);
	}

    /**
     * Calculates the dot product between two vectors
     * @param v1 first vector
     * @param v2 second vector
     * @return dot product value
     */
    public static double calculateDotProduct(Vertex v1,Vertex v2){
		return v1.x*v2.x+v1.y*v2.y+v1.z*v2.z;
	}

    /**
     * Calculates the cross product between two vectors
     * @param v1 first vector
     * @param v2 second vector
     * @return cross product vector
     */
    public static Vertex calculateCrossProduct(Vertex v1,Vertex v2){
		return new Vertex(v1.y*v2.z-v1.z*v2.y,v1.z*v2.x-v1.x*v2.z,v1.x*v2.y-v1.y*v2.x);
	}

    public static double calculateDist(Vertex v1, Vertex v2) {
        double x = v1.x - v2.x;
        double y = v1.y - v2.y;
        double z = v1.z - v2.z;
        return Math.sqrt(x * x + y * y + z * z);
    }

}
