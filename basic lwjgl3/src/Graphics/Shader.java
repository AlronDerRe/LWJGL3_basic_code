package Graphics;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.BufferUtils.*;
import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL20;
import org.lwjglx.BufferUtils;

import maths.Matrix4f;
import utils.Buffers;
import utils.FonctionsUtiles;

public class Shader {

	private int programID, vertID, fragID;
	String vertFile, fragFile;
	
	
	
	public Shader(String name){
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
		
		glLinkProgram(programID);
		if(glGetProgrami(programID, GL_LINK_STATUS) != 1)
			System.err.println(glGetProgramInfoLog(programID));
		
		glValidateProgram(programID);
		if(glGetProgrami(programID, GL_VALIDATE_STATUS) != 1)
			System.err.println(glGetProgramInfoLog(programID));
	}
	
	public void setUniform(String name, int value){
		int location = glGetUniformLocation(programID, name);
		if(location != -1)
			glUniform1i(location, value);
	}
	
	public void setUniform(String name, float value){
		int location = glGetUniformLocation(programID, name);
		if(location != -1)
			glUniform1f(location, value);
	}
	
	public void setUniform(String name, Matrix4f matrix){
		int location = glGetUniformLocation(programID, name);
		if(location != -1){		
			FloatBuffer matrixBuffer = Buffers.createFloatBuffer(matrix.elements);
			glUniformMatrix4fv(location, false, matrixBuffer);
		}
		
	}
	
	public int getID(){
		return programID;
	}
	
}
