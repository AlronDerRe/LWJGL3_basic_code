package maths;

public class Matrix4f {

	public float[] elements = new float[4 * 4];
	
	public Matrix4f(){
		for(int a = 0; a < 4 * 4; a++){
			elements[a] = 0;
		}
	}
	
	public void resetMatrix(){
		for(int a = 0; a < 4 * 4; a++){
			elements[a] = 0;
		}
	}
	
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
	
	public static Matrix4f getVector3fToMatrix4f(Vector3f vec){
		Matrix4f a = new Matrix4f();
		a.elements[3 + 0 * 4] = vec.x;
		a.elements[3 + 1 * 4] = vec.y;
		a.elements[3 + 2 * 4] = vec.z;
		
		return a;		
	}
	
	public static Matrix4f multiplyMatrif4f(Matrix4f mat1, Matrix4f mat2){
		Matrix4f result = new Matrix4f();
		float sum;
		for(int a = 0; a < 4; a++){
			
			for(int b = 0; b < 4; b++){
				sum = 0f;
				for(int e = 0; e < 4; e++){
					sum += (mat1.elements[e + a * 4] * mat2.elements[b + e * 4]);
				}
				result.elements[b + a * 4] = sum;
			}
		}
		
		return result;
	}
	
	public void writeMatrix(){
		for(int a = 0; a < 4 * 4; a+=4){
			System.out.println(elements[a] + " | " + elements[a+1] + " | " + elements[a+2] + " | " + elements[a+3] + " |");
		}
	}
}
