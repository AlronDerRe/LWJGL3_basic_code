package Graphics;

import maths.Matrix4f;
import maths.Vector3f;

public abstract class Entity{
	
	protected Mesh mesh = new Mesh();
	protected Vector3f position = new Vector3f(0, 0, 0), rotation = new Vector3f(0, 0, 0), scale = new Vector3f(1, 1, 1);

	protected Matrix4f translationMatrix, rotationMatrix, scaleMatrix;
	
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
		this.translationMatrix = Matrix4f.getTranslationMatrix(this.position);
		this.rotationMatrix = Matrix4f.getRotationMatrix(this.rotation);
		this.scaleMatrix = Matrix4f.getScaleMatrix(this.scale);
	}
	
	public abstract void render(Camera cam);
	
	
	
	
	
	

	public Matrix4f getTranslationMatrix() {
		return translationMatrix;
	}

	public Matrix4f getRotationMatrix() {
		return rotationMatrix;
	}

	public Matrix4f getScaleMatrix() {
		return scaleMatrix;
	}

	public Mesh getMesh() {
		return mesh;
	}

	public void delete(){
		mesh.cleanUpVaosAndVbos();
	}
	
}
