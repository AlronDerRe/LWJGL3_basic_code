package Entities;

import org.lwjglx.util.vector.Vector3f;

public class Light {

	private Vector3f pos;
	private Vector3f color;
	
	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

	public Vector3f getColor() {
		return color;
	}

	public void setColor(Vector3f color) {
		this.color = color;
	}

	public Light(){
		
	}
	
}
