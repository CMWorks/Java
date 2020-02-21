package engine3D;

import javax.swing.*;

import engine.MainRunable;
import parser.Parser;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * User: mino.ermal(AT)gmail.com
 * Date: 9-dic-2006
 * Time: 18.25.31
 *
 * This panel is where the drawing takes place.
 * It implements Transformable and D3Renderer to make it straight forward
 * for whatever object uses this panel to execute transformations and rendering.
 * It can be integrated to whatever user interface we want.
 *
 */
public class Engine3DRenderPanel extends JPanel implements Transformable,D3Renderer {

    //map containing 3d objects
    private final Map objectList=new LinkedHashMap();
    private World world; //world object to put 3d objects here before rendering
    private D3Renderer renderer; //the renderer

    private final Color background = Color.LIGHT_GRAY;
	private final double scale = 40;
	private double min = 0;
	private double max = 6.28;
	private double step = 30;
    
    /**
     * Creates a new 3DRenderer Panel
     */
    public Engine3DRenderPanel() {
        super();
        init();
    }

    /**
     * Initialize 3D objects and put them in a list.
     */
	private void init(){
        Object3D object3d;

        renderer=new ZBufferRenderer(); //create a new Z-buffer renderer
        //set the wireframe color to white
        renderer.setWireframeColor(255,255,255);
        world=new World(); //create a new world object   
    }

    /**
     * Superclass method override.
     * This method creates an offscreen image and renders offscreen first
     * before effectively drawing the result in the panel graphics for speed and
     * image flickering problem
     * @see javax.swing.JPanel#paint(java.awt.Graphics)
     * @param gr Graphics object used to paint
     */
	public void paintComponent(Graphics gr){

        //create an offscreen image of the size of this panel
        Image view=this.createImage(this.getWidth(),this.getHeight());

        //obtain image graphics
        Graphics igr=view.getGraphics();

        //black background in simple 3D rendering looks cool :D
        igr.setColor(background);
        //paint all image with black as background
        igr.fillRect(this.getX(),this.getY(),this.getWidth(),this.getHeight());

        //render in this image
        renderer.render(world,view,this);

        //draw the image on screen
        gr.drawImage(view,0,0,this);
        MainRunable.frame++;
    }
	
	public void drawGrid() {
		Object3D obj;
		obj = new Line(0, 0, 0, 1, 0, 0);
		obj.setColor(255, 0, 0);
		obj.scale(scale);
		world.add(obj);
		obj = new Line(0, 0, 0, 0, 1, 0);
		obj.setColor(0, 255, 0);
		obj.scale(scale);
		world.add(obj);
		obj = new Line(0, 0, 0, 0, 0, 1);
		obj.setColor(0, 0, 255);
		obj.scale(scale);
		world.add(obj);
	}
	
	public void clearGraph() {
		world.clear();
		drawGrid();
	}
	
	public void graphEq(String fnx, String fny, String fnz, Color lineColor) {
		Object3D obj;
		if (!(fnx.equals("") || fnx.equalsIgnoreCase("NAN") || fnx.equalsIgnoreCase("null") || fny.equals("") || fny.equalsIgnoreCase("NAN") || fny.equalsIgnoreCase("null") || fnz.equals("") || fnz.equalsIgnoreCase("NAN") || fnz.equalsIgnoreCase("null"))) {
			for (double i = min; i < max; i+=step) {
				double x1 = Parser.eval(fnx, new String[] {"t"}, new Double[] {i});
				double y1 = Parser.eval(fny, new String[] {"t"}, new Double[] {i});
				double z1 = Parser.eval(fnz, new String[] {"t"}, new Double[] {i});
				double x2 = Parser.eval(fnx, new String[] {"t"}, new Double[] {i+step});
				double y2 = Parser.eval(fny, new String[] {"t"}, new Double[] {i+step});
				double z2 = Parser.eval(fnz, new String[] {"t"}, new Double[] {i+step});
				if (y1 != Double.NaN && y1 != Double.NEGATIVE_INFINITY && y1 != Double.POSITIVE_INFINITY) {
					obj = new Line(x1, y1, z1, x2, y2, z2);
					obj.setColor(lineColor.getRed(), lineColor.getGreen(), lineColor.getBlue());
					obj.scale(scale);
					world.add(obj);
				}
			}					
		}
	}
	
	public void setBounds(double min, double max, double step) {
		this.min = min;
		this.max = max;
		this.step = step;
	}

    /**
     * It is invoked to select and change the current rendered object
     * @param objName the key of the object contained in the map
     */
    public void select(String objName){

        //remove previous objects on the world
        world.clear();

        //add the selected object to the world
        world.add((Object3D)objectList.get(objName));

        //refresh the painting
        //repaint();
	}

    /**
     * Gets the iterator object of the key set of the map containing the objects.
     * This is useful to the control panel that will be using this one to know the objects
     * contained herein
     * @return the key set iterator object
     */
    public Iterator getShapeList(){
		return objectList.keySet().iterator();
	}

    /*
    the following methods are the implementations of the interfaces
    Transformable and D3Renderer
     */

    public void scale(double factor) {
		world.scale(factor);
	}

	public void scale(Vertex vector) {
		world.scale(vector);
	}

	public void rotate(RotationMatrix3D rotationMatrix) {
		world.rotate(rotationMatrix);
	}

	public void translate(Vertex vector) {
		world.translate(vector);
	}

    public void setColor(int r, int g, int b) {
        //not needed
    }

    public void render(World world, Image view, ImageObserver observer) {
        //not needed
    }

    public void setWireframeColor(int r, int g, int b) {
        renderer.setWireframeColor(r,g,b);
    }

    
    public void setRenderAxis(boolean renderAxis) {
        renderer.setRenderAxis(renderAxis);
    }
    public boolean getRenderAxis() {
        return renderer.getRenderAxis();
    }


    public void setWireframed(boolean wireframed) {
        renderer.setWireframed(wireframed);
    }
    public boolean getWireframed() {
        return renderer.getWireframed();
    }


    public void setFilled(boolean filled) {
        renderer.setFilled(filled);
    }
    public boolean getFilled() {
        return renderer.getFilled();
    }
}






/*
 	
  
 
	
*/
