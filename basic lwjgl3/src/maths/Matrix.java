package maths;

import org.lwjglx.util.vector.Matrix4f;
import org.lwjglx.util.vector.Vector3f;
import org.lwjglx.util.vector.Vector4f;

public class Matrix {


	
	public static void resetMatrix(){
		
	}
	
	public static Matrix4f transformationMatrix(Vector3f translation, float rx, float ry, float rz, Vector3f scale){
		Matrix4f mat = new Matrix4f();
		mat.setIdentity();
		Matrix4f.translate(translation, mat, mat);
		Matrix4f.rotate((float) Math.toRadians(rx), new Vector3f(1, 0, 0), mat, mat);
		Matrix4f.rotate((float) Math.toRadians(ry), new Vector3f(0, 1, 0), mat, mat);
		Matrix4f.rotate((float) Math.toRadians(rz), new Vector3f(0, 0, 1), mat, mat);
		Matrix4f.scale(scale, mat,  mat);
		
		return mat;
		
	}
	
	public static Matrix4f perspectiveMatrix(float FOV, float near, float far, float aspectRatio){

		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = far - near;
		
		Matrix4f m = new Matrix4f();
		m.m00 = x_scale;
		m.m11 = y_scale;
		m.m22 = -((far+near)/frustum_length);
		m.m23 = -1;
		m.m32 = -((2* near * far) / frustum_length);
		m.m33 = 0;
		
		return m;
		
	}
	/*
	public static Matrix4f getIdentityMatrix(){
		Matrix4f result = new Matrix4f();
		for(int a = 0; a < 4 * 4; a++){
			result.elements[a] = 0;
		}
		result.elements[0 + 0 * 4] = 1;
		result.elements[1 + 1 * 4] = 1;
		result.elements[2 + 2 * 4] = 1;
		result.elements[3 + 3 * 4] = 1;
		
		return result;
	}
	
	public static Matrix4f getTranslationMatrix(Vector3f vec){
		Matrix4f result = getIdentityMatrix();
		result.elements[0 + 3 * 4] = vec.x;
		result.elements[1 + 3 * 4] = vec.y;
		result.elements[2 + 3 * 4] = vec.z;
		
		return result;
	}

	public static Matrix4f getRotationMatrix(Vector3f rotation){
		Matrix4f result = new Matrix4f();
		Vector3f rad = new Vector3f((float)Math.toRadians((double)rotation.x), (float)Math.toRadians((double)rotation.y), (float)Math.toRadians((double)rotation.z));
		
		
		float A       = (float)Math.cos(rad.x);
		float B       = (float)Math.sin(rad.x);
		float C       = (float)Math.cos(rad.y);
		float D       = (float)Math.sin(rad.y);
		float E       = (float)Math.cos(rad.z);
		float F       = (float)Math.sin(rad.z);

		float AD      =   A * D;
		float BD      =   B * D;

		result.elements[0]  =   C * E;
		result.elements[1]  =  -C * F;
		result.elements[2]  =  D;
		result.elements[4]  =  BD * E + A * F;
		result.elements[5]  =  -BD * F + A * E;
		result.elements[6]  =  -B * C;
		result.elements[8]  = -AD * E + B * F;
		result.elements[9]  =  AD * F + B * E;
		result.elements[10] =   A * C;

		result.elements[3]  =  result.elements[7] = result.elements[11] = result.elements[12] = result.elements[13] = result.elements[14] = 0;
		result.elements[15] =  1;
		
		return result;
	}
	
	public static Matrix4f getScaleMatrix(Vector3f scale){
		Matrix4f result = new Matrix4f();
		
		result.elements[0 + 0 * 4] = scale.x;
		result.elements[1 + 1 * 4] = scale.y;
		result.elements[2 + 2 * 4] = scale.z;
		result.elements[3 + 3 * 4] = 1.0f;
		
		return result;
	}
	
	public static Matrix4f getPerspectiveMatrix(float FOV, float near, float far, float aspectRatio){
		
		
		float y_scale = (float)((1f / Math.tan(Math.toRadians((double)FOV / 2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = far - near;
		
		Matrix4f result = Matrix4f.getIdentityMatrix();
		result.elements[0 + 0 * 4] = x_scale;
		result.elements[1 + 1 * 4] = y_scale;
		result.elements[2 + 2 * 4] = -((far + near) / frustum_length);
		result.elements[3 + 2 * 4] = -1;
		result.elements[2 + 3 * 4] = -((2 * near * far) / frustum_length);
		result.elements[3 + 3 * 4] = 0;
		
		return result;
	}


	
	public void writeMatrix(){
		for(int a = 0; a < 4 * 4; a+=4){
			System.out.println(elements[a] + " | " + elements[a+1] + " | " + elements[a+2] + " | " + elements[a+3] + " |");
		}
	}*/
}
