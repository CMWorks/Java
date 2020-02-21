package engine3D;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * User: mino.ermal(AT)gmail.com
 * Date: Sep 30, 2007
 * Time: 4:41:48 PM
 *
 * Z-Buffer based renderer
 *
 */
public class ZBufferRenderer implements D3Renderer {

    //perspective division constant
    private static final double D =400;

    private int wireframeRed=255; //red wireframe color component
    private int wireframeGreen=0; //green wireframe color component
    private int wireframeBlue=0;  //blue wireframe color component

    private boolean renderAxis=false; //render axis flag
    private boolean wireframed=false; //render wireframe flag
    private boolean filled=true; //render filled flag

    /**
     * Renders segment based objects
     * @param object3d object to be rendered
     * @param view off screen image
     * @param observer image observer object
     * @param zbuffer z depth buffer
     */
    private void renderSegmentBased(Object3D object3d,Image view, ImageObserver observer,int[][] zbuffer){

        Graphics gr=view.getGraphics(); //obtain graphic object from off screen image

        int w=view.getWidth(observer); //obtain image width
        int h=view.getHeight(observer); //obtain image height
        
        for (int i = 0; object3d != null && i < object3d.transformables.size(); i++) {
            Segment segment = (Segment) object3d.transformables.get(i);

            //sets the color of the graphic object used to draw
            gr.setColor(new Color(segment.r, segment.g, segment.b));

            //calculate perspective transformation for all vertexes
            //and for x,y and z coordinates
            int x1, x2;
            x1 = (int) (segment.start.x / (1 + segment.start.z / D)) + w / 2;
            x2 = (int) (segment.end.x / (1 + segment.end.z / D)) + w / 2;

            int y1, y2;
            y1 = (int) (segment.start.y / (1 + segment.start.z / D)) + h / 2;
            y2 = (int) (segment.end.y / (1 + segment.end.z / D)) + h / 2;

            int z1, z2;
            z1 = (int) segment.start.z;
            z2 = (int) segment.end.z;

            //draw line
            ZbufferUtil.drawLine(gr, x1, y1, z1, x2, y2, z2, zbuffer);

        }


    }

    /**
     * Renders triangle based objects
     * @param object3d object to be rendered
     * @param view off screen image
     * @param observer image observer object
     * @param zbuffer z depth buffer
     */
    private void renderTriangleBased(Object3D object3d,Image view, ImageObserver observer,int[][] zbuffer){

        Graphics gr=view.getGraphics(); //obtain graphic object from offscreen image
		
        int w=view.getWidth(observer); //obtain image width
        int h=view.getHeight(observer); //obtain image height

        for(int i=0;i<object3d.transformables.size();i++){
			Triangle tri=(Triangle)object3d.transformables.get(i);

            //calculate perspective transformation for all vertexes
            //and for x,y and z coordinates
            int[] x=new int[3];
			x[0]=(int)(tri.v1.x/(1+tri.v1.z/D))+w/2;
			x[1]=(int)(tri.v2.x/(1+tri.v2.z/D))+w/2;
			x[2]=(int)(tri.v3.x/(1+tri.v3.z/D))+w/2;

			int[] y=new int[3];
			y[0]=(int)(tri.v1.y/(1+tri.v1.z/D))+h/2;
			y[1]=(int)(tri.v2.y/(1+tri.v2.z/D))+h/2;
			y[2]=(int)(tri.v3.y/(1+tri.v3.z/D))+h/2;

            int[] z=new int[3];
			z[0]=(int)tri.v1.z;
			z[1]=(int)tri.v2.z;
			z[2]=(int)tri.v3.z;

            //obtains the normal vector of the triangle
            Vertex normale= VectorUtil.calculateNormal(tri);

			/*
			calculates cos between triangle normal and light direction vector
			and calculate color intensity in function of cos value
			 */
            double cos= VectorUtil.calculateAngleCosBetweenVectors(normale,new Vertex(Math.cos(Math.PI/2),Math.cos(Math.PI/2)/2,Math.cos(Math.PI/2)));
			int r,g,b;
			cos=Math.abs(cos);
            r=(int)(cos*tri.r);
            g=(int)(cos*tri.g);
            b=(int)(cos*tri.b);

            //set graphic color
            gr.setColor(new Color(r,g,b));

            //if filled flag is true render filled triangles
            if(filled) ZbufferUtil.fillTriangle(gr,x,y,z,zbuffer);

            //if wireframed flag is true draw triangle edges
            if(wireframed) {
                gr.setColor(new Color(wireframeRed,wireframeGreen,wireframeBlue));
                ZbufferUtil.drawLine(gr,x[0], y[0], z[0], x[1], y[1], z[1], zbuffer );
                ZbufferUtil.drawLine(gr,x[1], y[1], z[1], x[2], y[2], z[2], zbuffer );
                ZbufferUtil.drawLine(gr,x[2], y[2], z[2], x[0], y[0], z[0], zbuffer );
            }

    }
}

    /**
     *
     * @param world world object containing the set of 3d objects
     * @param view offscreen image to render the world in
     * @param observer image observer object necessary to get width and height of the
     */
	public void render(World world,Image view, ImageObserver observer){

        int w=view.getWidth(observer); //obtain image width
        int h=view.getHeight(observer); //obtain image height

        int[][] zbuffer=new int[w][h];
        //initialize zbuffer with tha max depth value
        for(int k=0; k<w; k++){
            for(int n=0; n<h; n++){
                zbuffer[k][n]=65534;
            }
        }
        for(int i=0;i<world.transformables.size();i++){
            Object3D object3d=(Object3D)world.transformables.get(i);

            //render the object appropriately according to their type
            if(object3d instanceof SegmentBasedObject3D){
			    renderSegmentBased(object3d,view,observer,zbuffer);
                Object3D obj=object3d.getCoordinateAxis();

                //if render axis flag is true and the object has an axis object then render it
                if(renderAxis && obj!=null) renderSegmentBased(obj,view,observer,zbuffer);
            }else{
                renderTriangleBased(object3d,view,observer,zbuffer);
                Object3D obj=object3d.getCoordinateAxis();

                //if render axis flag is true and the object has an axis object then render it
                if(renderAxis && obj!=null) renderSegmentBased(obj,view,observer,zbuffer);
            }
        }
    }

    /**
     * @see mino.ermal.engine3d.renderer.D3Renderer#setWireframeColor(int, int, int)
     * @param r red component
     * @param g green component
     * @param b blue component
     */
    public void setWireframeColor(int r,int g,int b){
        if(r>=0 && r<256) this.wireframeRed=r;
        if(g>=0 && g<256) this.wireframeGreen=g;
        if(b>=0 && b<256) this.wireframeBlue=b;
    }

    /**
     * @see mino.ermal.engine3d.renderer.D3Renderer#setRenderAxis(boolean)
     * @param renderAxis render axis flag
     */
    public void setRenderAxis(boolean renderAxis) {
        this.renderAxis = renderAxis;
    }
    /**
     * @return render axis flag
     */
    public boolean getRenderAxis() {
        return this.renderAxis;
    }

    /**
     * @see mino.ermal.engine3d.renderer.D3Renderer#setWireframed(boolean)
     * @param wireframed render wireframe flag
     */
    public void setWireframed(boolean wireframed) {
        this.wireframed=wireframed;
    }
    /**
     * @return render wireframe flag
     */
    public boolean getWireframed() {
        return this.wireframed;
    }

    /**
     * @see mino.ermal.engine3d.renderer.D3Renderer#setFilled(boolean)
     * @param filled render filled flag
     */
    public void setFilled(boolean filled) {
        this.filled=filled;
    }
    /**
     * @return render filled flag
     */
    public boolean getFilled() {
        return this.filled;
    }
}
