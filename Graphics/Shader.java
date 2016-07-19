package Graphics;

import static org.lwjgl.opengl.GL20.*;

import org.lwjgl.opengl.GL20;

import utils.FonctionsUtiles;

public class Shader {

	private int programID, vertID, fragID;
	String vertFile, fragFile;
	
	public Shader(String vertName, String fragName){
		programID = glCreateProgram();
		
		
		vertFile = FonctionsUtiles.loadFileInString("Shaders/" + vertName + ".vert");
		vertID = glCreateShader(GL_VERTEX_SHADER);
		glShaderSource(vertID, vertFile);
		glCompileShader(vertID);
		if(glGetShaderi(vertID, GL_COMPILE_STATUS) != 1)
			System.err.println(glGetShaderInfoLog(vertID));
		
		
		fragFile = FonctionsUtiles.loadFileInString("Shaders/" + fragName + ".frag");
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
		int location = glGetAttribLocation(programID, name);
		if(location != 1)
			glUniform1i(location, value);
	}
	
	public void setUniform(String name, float value){
		int location = glGetAttribLocation(programID, name);
		if(location != 1)
			glUniform1f(location, value);
	}
	
	public void bind(){
		glUseProgram(programID);
	}
	
}
