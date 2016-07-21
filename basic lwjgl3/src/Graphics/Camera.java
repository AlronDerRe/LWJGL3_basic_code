package Graphics;

import org.lwjglx.input.Keyboard;

import maths.Matrix4f;
import maths.Vector3f;

public class Camera {

	
	private Vector3f position = new Vector3f(0, 0, 0), rotation = new Vector3f(0, 0, 0), nextPos = new Vector3f(0, 0, 0);
	private int FOV;
	private float zNear, zFar, ratio;
	
	private Matrix4f perspectiveMatrix;
	private Matrix4f translationMatrix, rotationMatrix;
	
	public Matrix4f getPerspectiveMatrix() {
		return perspectiveMatrix;
	}

	public Matrix4f getTranslationMatrix() {
		return translationMatrix;
	}

	public Matrix4f getRotationMatrix() {
		return rotationMatrix;
	}

	public Camera(){};
	
	public void init(int FOV, float zNear, float zFar, float ratio){
		
		this.FOV = FOV;
		this.zNear = zNear;
		this.zFar = zFar;
		this.ratio = ratio;	
		this.perspectiveMatrix = Matrix4f.getPerspectiveMatrix(FOV, this.zNear, this.zFar, this.ratio);
	}
	
	public void updatePosition(){
	/*	nextPos = new Vector3f(0, 0, 0);
		if(Keyboard.isKeyDown(Keyboard.KEY_Z))
			nextPos.z += 0.001f;
		if(Keyboard.isKeyDown(Keyboard.KEY_S))
			nextPos.z -= 0.001f;
		if(Keyboard.isKeyDown(Keyboard.KEY_D))
			nextPos.x += 0.001f;
		if(Keyboard.isKeyDown(Keyboard.KEY_Q))
			nextPos.x -= 0.001f;*/
		
		this.position = this.nextPos;
		this.translationMatrix = Matrix4f.getTranslationMatrix(new Vector3f(-this.position.x, -this.position.y, -this.position.z));
		this.rotationMatrix = Matrix4f.getRotationMatrix(new Vector3f(-this.rotation.x, -this.rotation.y, -this.rotation.z));
	}
	
	public void move(Vector3f translation){
		this.position.x += translation.x;
		this.position.y += translation.y;
		this.position.z += translation.z;
	}
	
	public void setPosition(Vector3f position){
		this.position = position;
	}
	
	public void rotate(Vector3f rotation){
		this.rotation.x += rotation.x;
		this.rotation.y += rotation.y;
		this.rotation.z += rotation.z;
	}
	
	public void setRotation(Vector3f rotation){
		this.rotation = rotation;
	}
	
}
