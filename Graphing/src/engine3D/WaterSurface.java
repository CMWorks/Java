package engine3D;

public class WaterSurface extends GridPlane {
    public WaterSurface(double u, double v, int detailU, int detailV, double waves[][]) {
        super(u, v, detailU, detailV);
        for(int i = 0; i < transformables.size(); i++) {
            Triangle tri = (Triangle)transformables.get(i);
            calculateDepth(tri.v1, waves);
            calculateDepth(tri.v2, waves);
            calculateDepth(tri.v3, waves);
        }
    }

    private void calculateDepth(Vertex v, double waves[][]) {
        double sum = 0.0D;
        for(int i = 0; i < waves.length; i++) {
            sum += waves[i][2] * Math.cos(6.2831853071795862D * waves[i][3] * VectorUtil.calculateDist(v, new Vertex(waves[i][0], waves[i][1], 0.0D)));
        }
        v.z = sum;
    }
}

