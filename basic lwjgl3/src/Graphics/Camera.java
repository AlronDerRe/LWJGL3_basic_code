package Graphics;

import org.lwjgl.glfw.GLFW;
import org.lwjglx.input.Keyboard;
import org.lwjglx.util.vector.Matrix4f;
import org.lwjglx.util.vector.Vector3f;
import org.lwjglx.util.vector.Vector4f;

import Input.MouseButton;

import static Input.Input.*;
import maths.Matrix;


public class Camera {

	
	private Vector3f position = new Vector3f(0, 0, 0), rotation = new Vector3f(0, 0, 0), nextPos = new Vector3f(0, 0, 0);
	private int FOV;
	private float zNear, zFar, ratio;
	
	private Matrix4f perspectiveMatrix;
	private Matrix4f transformationMatrix;
	
	
	public Matrix4f getPerspectiveMatrix() {
		return perspectiveMatrix;
	}

	public Matrix4f getTransformationMatrix() {
		return transformationMatrix;
	}


	public Camera(){};
	
	public void init(int FOV, float zNear, float zFar, float ratio){
		
		this.FOV = FOV;
		this.zNear = zNear;
		this.zFar = zFar;
		this.ratio = ratio;	
		this.perspectiveMatrix = Matrix.perspectiveMatrix(FOV, this.zNear, this.zFar, ratio);
	}
	
	public void updatePosition(){
		/*if(getPressedKeyInfo()[0] == GLFW.GLFW_KEY_W)
			this.position.z -= 1f;
		if(getPressedKeyInfo()[0] == GLFW.GLFW_KEY_S)
			this.position.z += 1f;
		if(getPressedKeyInfo()[0] == GLFW.GLFW_KEY_D)
			this.position.x += 1f;
		if(getPressedKeyInfo()[0] == GLFW.GLFW_KEY_A)
			this.position.x -= 1f;
		if(getPressedKeyInfo()[0] == GLFW.GLFW_KEY_SPACE)
			this.position.y += 1f;
		if(getPressedKeyInfo()[0] == GLFW.GLFW_KEY_LEFT_SHIFT)
			this.position.y -= 1f;
		
		if(MouseButton.getMouseButtonInfo()[0] == GLFW.GLFW_MOUSE_BUTTON_LEFT){
			this.rotation.y += 1;
		}
		if(MouseButton.getMouseButtonInfo()[0] == GLFW.GLFW_MOUSE_BUTTON_RIGHT){
			this.rotation.y -= 1;
		}*/
		
		this.transformationMatrix = Matrix.transformationMatrix(position, this.rotation.x, this.rotation.y, this.rotation.z, new Vector3f(1, 1, 1));
		this.transformationMatrix.invert();
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
