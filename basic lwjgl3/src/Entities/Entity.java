package Entities;

import org.lwjglx.util.vector.Matrix4f;
import org.lwjglx.util.vector.Vector3f;
import org.lwjglx.util.vector.Vector4f;

import Graphics.Model.Mesh;
import maths.Matrix;

public abstract class Entity{
	
	protected static Mesh mesh = new Mesh();
	protected Vector3f position = new Vector3f(0, 0, 0), rotation = new Vector3f(0, 0, 0), scale = new Vector3f(1, 1, 1);

	protected Matrix4f transformationMatrix;
	
	public Entity(){
		actualizeTransformationMatrix();
	}
	
	public void move(Vector3f translation){
		this.position.x += translation.x;
		this.position.y += translation.y;
		this.position.z += translation.z;
		actualizeTransformationMatrix();
	}
	public void rotate(Vector3f rotation){
		this.rotation.x += rotation.x;
		this.rotation.y += rotation.y;
		this.rotation.z += rotation.z;
		actualizeTransformationMatrix();
	}
	public void setScale(Vector3f scale){
		this.scale.x = scale.x;
		this.scale.y = scale.y;
		this.scale.z = scale.z;
		actualizeTransformationMatrix();
	}
	
	private void actualizeTransformationMatrix(){
		this.transformationMatrix = Matrix.transformationMatrix(position, this.rotation.x, this.rotation.y, this.rotation.z, scale);
		//this.rotationMatrix = Matrix4f.getRotationMatrix(this.rotation);
		//this.scaleMatrix = Matrix4f.getScaleMatrix(this.scale);
	}
	
	public abstract void render(Camera cam);
	
	

	public Matrix4f getTransformationMatrix() {
		return transformationMatrix;
	}

	public Mesh getMesh() {
		return mesh;
	}

	public void delete(){
		mesh.cleanUpVaosAndVbos();
	}
	
}
