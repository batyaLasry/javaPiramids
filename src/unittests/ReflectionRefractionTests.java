/**
 * 
 */
package unittests;

import org.junit.Test;

import elements.*;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 * 
 * @author dzilb
 *
 */
public class ReflectionRefractionTests {

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheres() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(1000);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

		scene.addGeometries(
				new Sphere(new Material(0.4, 0.3, 100, 0.3, 0),new Color(java.awt.Color.BLUE),  50,
						new Point3D(0, 0, 50)),
				new Sphere(new Material(0.5, 0.5, 100),new Color(java.awt.Color.RED),  25, new Point3D(0, 0, 50)));

		scene.addLights(new SpotLight(new Color(1000, 600, 0), new Point3D(-100, 100, -500), new Vector(-1, 1, 2), 1,
				0.0004, 0.0000006));

		ImageWriter imageWriter = new ImageWriter("twoSpheres", 150, 150, 500, 500);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheresOnMirrors() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -10000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(10000);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

		scene.addGeometries(
				new Sphere(new Material(0.25, 0.25, 20, 0.5, 0), new Color(0, 0, 100), 400, new Point3D(-950, 900, 1000)),
				new Sphere( new Material(0.25, 0.25, 20),new Color(100, 20, 20), 200, new Point3D(-950, 900, 1000)),
				new Triangle( new Material(0, 0, 0, 0, 1),new Color(20, 20, 20), new Point3D(1500, 1500, 1500),
						new Point3D(-1500, -1500, 1500), new Point3D(670, -670, -3000)),
				new Triangle(new Material(0, 0, 0, 0, 0.5),new Color(20, 20, 20),  new Point3D(1500, 1500, 1500),
						new Point3D(-1500, -1500, 1500), new Point3D(-1500, 1500, 2000)));

		scene.addLights(new SpotLight(new Color(1020, 400, 400),  new Point3D(-750, 750, 150), 
				   new Vector(-1, 1, 4), 1, 0.00001, 0.000005));

		ImageWriter imageWriter = new ImageWriter("twoSpheresMirrored", 2500, 2500, 500, 500);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}
	
	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially transparent Sphere
	 *  producing partial shadow
	 */
	@Test
	public void trianglesTransparentSphere() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(1000);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.addGeometries( //
				new Triangle(new Material(0.5, 0.5, 60),Color.BLACK,  //
						new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
				new Triangle(new Material(0.5, 0.5, 60),Color.BLACK,  //
						new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
				new Sphere(new Material(0.2, 0.2, 30, 0.6, 0),new Color(java.awt.Color.BLUE),   
						30, new Point3D(60, -50, 50)));

		scene.addLights(new SpotLight(new Color(700, 400, 400), //
				new Point3D(60, -50, 0), new Vector(0, 0, 1), 1, 4E-5, 2E-7));

		ImageWriter imageWriter = new ImageWriter("shadow with transparency", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();
	}
	@Test
	public void spheres() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(1000);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.black), 0));

		scene.addGeometries(
				new Sphere(new Material(0.4, 0.3, 100, 0.5, 0),new Color(java.awt.Color.BLUE),  50,
						new Point3D(0, -30, 50)),
				new Sphere(new Material(0.5, 0.5, 100,0.5, 0),new Color(java.awt.Color.CYAN),  35, new Point3D(0, -30, 50)),
				new Sphere(new Material(0.7, 0.9, 100),new Color(java.awt.Color.orange),  15, new Point3D(0, -30, 50)),
				new Plane(new Material(0.4, 0.3, 100, 0, 0.9), new Color(java.awt.Color.black), new Point3D(-30, 15, 0), new Vector(0, -1, 0)));

		scene.addLights(/*new SpotLight(new Color(1000, 600, 0), new Point3D(-100, 100, 30), new Vector(-1, 1, 5), 1,
				0.0004, 0.0000006),*/
				new SpotLight(new Color(1000, 600, 0),new Point3D(-100, -100, 30), new Vector(1, 1, -1), 1,
						0.0004, 0.0000006));

		ImageWriter imageWriter = new ImageWriter("Spheres", 150, 150, 500, 500);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}
	
		
}

