package Graphics;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL11.*;
import java.nio.FloatBuffer;
import java.util.Hashtable;

import org.lwjgl.BufferUtils;
import org.lwjglx.util.vector.Matrix4f;

//import maths.Matrix4f;
import utils.Buffers;
import utils.FonctionsUtiles;

public class Shader {

	private int programID, vertID, fragID;
	String vertFile, fragFile;
	
	
	private Hashtable<String, Integer> uniformsLocations = new Hashtable<String, Integer>();
	
	private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
	
	public Shader(){
		
	}
	
	public void create(String name){
		programID = glCreateProgram();
		
		
		vertFile = FonctionsUtiles.loadFileInString("Shaders/" + name + ".vert");
		vertID = glCreateShader(GL_VERTEX_SHADER);
		glShaderSource(vertID, vertFile);
		glCompileShader(vertID);
		if(glGetShaderi(vertID, GL_COMPILE_STATUS) != 1)
			System.err.println(glGetShaderInfoLog(vertID));
		
		
		fragFile = FonctionsUtiles.loadFileInString("Shaders/" + name + ".frag");
		fragID = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(fragID, fragFile);
		glCompileShader(fragID);
		if(glGetShaderi(fragID, GL_COMPILE_STATUS) != 1)
			System.err.println(glGetShaderInfoLog(fragID));
		
		glAttachShader(programID, vertID);
		glAttachShader(programID, fragID);
		
		glBindAttribLocation(programID, 0, "in_Vertex");
		glBindAttribLocation(programID, 1, "in_Color");
		glBindAttribLocation(programID, 2, "in_Texture");
		glBindAttribLocation(programID, 3, "in_Normal");
		
		glLinkProgram(programID);
		if(glGetProgrami(programID, GL_LINK_STATUS) != 1)
			System.err.println(glGetProgramInfoLog(programID));
		
		glValidateProgram(programID);
		if(glGetProgrami(programID, GL_VALIDATE_STATUS) != 1)
			System.err.println(glGetProgramInfoLog(programID));
	}
	
	public void cacheUniformsLocations(String[] names){
		for(String str: names){
			int loc = glGetUniformLocation(programID, str);
			if(loc != -1)
				uniformsLocations.put(str, loc);
			else
				System.out.println("Can't get location of uniform : " + str);
		}
	}
	
	public void cleanUp(){
		glUseProgram(0);
		glDetachShader(programID, vertID);
		glDetachShader(programID, fragID);
		glDeleteShader(vertID);
		glDeleteShader(fragID);
		glDeleteProgram(programID);
	}
	
	public void setUniform(String name, int value){
		glUniform1i(uniformsLocations.get(name), value);
	}
	
	public void setUniform(String name, float value){
		glUniform1f(uniformsLocations.get(name), value);
	}
	
	public void setUniform(String name, Matrix4f matrix){	
			matrix.store(matrixBuffer);
			matrixBuffer.flip();
			glUniformMatrix4fv(uniformsLocations.get(name), false, matrixBuffer);
	}
	
	public int getID(){
		return programID;
	}
	
}
