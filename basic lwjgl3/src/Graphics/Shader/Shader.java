package Graphics.Shader;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL11.*;
import java.nio.FloatBuffer;
import java.util.Hashtable;

import org.lwjgl.BufferUtils;
import org.lwjglx.util.vector.Matrix4f;

//import maths.Matrix4f;
import utils.Buffers;
import utils.FonctionsUtiles;

public abstract class Shader {

	protected int programID, vertID, fragID;
	String vertFile, fragFile;
	protected String[] uniformLocations;
	
	protected Hashtable<String, Integer> uniformsLocations = new Hashtable<String, Integer>();
	
	private FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
	
	public Shader(String name){
		create(name);
	}
	
	private void create(String name){
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
		
		bindAttribLocations();
		
		glLinkProgram(programID);
		if(glGetProgrami(programID, GL_LINK_STATUS) != 1)
			System.err.println(glGetProgramInfoLog(programID));
		
		glValidateProgram(programID);
		if(glGetProgrami(programID, GL_VALIDATE_STATUS) != 1)
			System.err.println(glGetProgramInfoLog(programID));
		
		setUniformNames();
		cacheUniformsLocations();
	}
	
	public void cacheUniformsLocations(){
		for(String str: uniformLocations){
			int loc = glGetUniformLocation(programID, str);
			if(loc != -1)
				uniformsLocations.put(str, loc);
			else
				System.out.println("Can't get location of uniform : " + str);
		}
	}
	
	protected abstract void setUniformNames();
	protected abstract void bindAttribLocations();
	
	public void cleanUp(){
		glUseProgram(0);
		glDetachShader(programID, vertID);
		glDetachShader(programID, fragID);
		glDeleteShader(vertID);
		glDeleteShader(fragID);
		glDeleteProgram(programID);
	}
	
	public void setUniform1i(String name, int value){
		glUniform1i(uniformsLocations.get(name), value);
	}
	
	public void setUniform1f(String name, float value){
		glUniform1f(uniformsLocations.get(name), value);
	}
	
	public void setUniform3f(String name, float v1, float v2, float v3){
		glUniform3f(uniformsLocations.get(name), v1, v2, v3);
	}
	
	public void setUniformMatrix4f(String name, Matrix4f matrix){	
			matrix.store(matrixBuffer);
			matrixBuffer.flip();
			glUniformMatrix4fv(uniformsLocations.get(name), false, matrixBuffer);
	}
	
	public int getID(){
		return programID;
	}
	
}
