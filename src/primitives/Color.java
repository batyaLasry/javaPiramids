package primitives;

import java.util.List;

/**
 * Wrapper class for java.jwt.Color The constructors operate with any
 * non-negative RGB values. The colors are maintained without upper limit of
 * 255. Some additional operations are added that are useful for manipulating
 * light's colors
 * 
 * @author Dan Zilberstein
 */
public class Color {
	/**
	 * The internal fields tx`o maintain RGB components as double numbers from 0 to
	 * whatever...
	 */
	private double _r = 0.0, _g = 0.0, _b = 0.0;

	public final static Color BLACK = new Color();

	/**
	 * Default constructor - to generate Black Color (privately)
	 */
	public Color() {}

	/**
	 * Constructor to generate a color according to RGB components Each component in
	 * range 0..255 (for printed white color) or more [for lights]
	 *
	 * @param r Red component
	 * @param g Green component
	 * @param b Blue component
	 */
	public Color(double r, double g, double b) {
		if (r < 0 || g < 0 || b < 0)
			throw new IllegalArgumentException("Negative color component is illegal");
		_r = r;
		_g = g;
		_b = b;
	}
	/**
	 * color constructor from list color
	 * @param colors
	 */
	public  Color( List<Color> colors) 
	{

		for (Color c : colors) 
		{

			_r += c._r;

			_g += c._g;

			_b += c._b;

		}
	}

	/**
	 * Copy constructor for Color
	 * 
	 * @param other the source color
	 */
	public Color(Color other) {
		_r = other._r;
		_g = other._g;
		_b = other._b;
	}

	/**
	 * Constructor on base of java.awt.Color object
	 * 
	 * @param other java.awt.Color's source object
	 */
	public Color(java.awt.Color other) {
		_r = other.getRed();
		_g = other.getGreen();
		_b = other.getBlue();
	}

	/**
	 * Color setter to reset the color to BLACK
	 * 
	 * @return the Color object itself for chaining calls
	 */
	public Color setColor() {
		_r = 0.0;
		_g = 0.0;
		_b = 0.0;
		return this;
	}

	/**
	 * Color setter to generate a color according to RGB components Each component
	 * in range 0..255 (for printed white color) or more [for lights]
	 * 
	 * @param r Red component
	 * @param g Green component
	 * @param b Blue component
	 * @return the Color object itself for chaining calls
	 */
	public Color setColor(double r, double g, double b) {
		if (r < 0 || g < 0 || b < 0)
			throw new IllegalArgumentException("Negative color component is illegal");
		_r = r;
		_g = g;
		_b = b;
		return this;
	}

	/**
	 * Color setter to copy RGB components from another color
	 *
	 * @param other source Color object
	 * @return the Color object itself for chaining calls
	 */
	public Color setColor(Color other) {
		_r = other._r;
		_g = other._g;
		_b = other._b;
		return this;
	}

	/**
	 * Color setter to take components from an base of java.awt.Color object
	 *
	 * @param other java.awt.Color's source object
	 * @return the Color object itself for chaining calls
	 */
	public Color setColor(java.awt.Color other) {
		_r = other.getRed();
		_g = other.getGreen();
		_b = other.getBlue();
		return this;
	}

	/**
	 * Color getter - returns the color after converting it into java.awt.Color
	 * object During the conversion any component bigger than 255 is set to 255
	 *
	 * @return java.awt.Color object based on this Color RGB components
	 */
	public java.awt.Color getColor() {
		int r = (int)_r, g = (int)_g, b = (int)_b;
		return new java.awt.Color(r > 255 ? 255 : r, g > 255 ? 255 : g, b > 255 ? 255 : b);
	}

	/**
	 * create color from sum of list of colors
	 * @param colors
	 * @return
	 */
	public Color add( Color... colors) 
	{

		double r = _r, g = _g, b = _b;

		for (Color c : colors) {

			r += c._r;

			g += c._g;

			b += c._b;

		}

		return new Color(r, g, b);

	}
	/**
	 * Scale the color by a scalar
	 *
	 * @param k scale factor
	 * @return new Color object which is the result of the operation
	 */
	public Color scale(double k) {
		if (k < 0)
			throw new IllegalArgumentException("Can't scale a color by a negative number");
		double r = _r * k;
		double g = _g * k;
		double b = _b * k;
		return new Color(r, g, b);
	}
	/**
	 * check if the colors are equal approximately(5)
	 * @param color
	 * @return boolean
	 */
	public boolean equal(Color color)
	{
		Vector v1=new Vector(new Point3D(this._r+1, this._g+1, this._b+1));
		Vector v2=new Vector(new Point3D(color._r+1,color._g+1, color._b+1));
		if(v1.subtract(v2).length()<7)
			return true;
		return false;
			

	}
}