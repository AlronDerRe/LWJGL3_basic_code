package Graphics.Shader;

import static org.lwjgl.opengl.GL20.*;

public class Shader3D extends Shader{

	public Shader3D(String name) {
		super(name);
	}

	@Override
	protected void bindAttribLocations() {
		glBindAttribLocation(programID, 0, "in_Vertex");
		glBindAttribLocation(programID, 1, "in_Texture");
		
	}

	@Override
	protected void setUniformNames() {
		String[] u ={"pm", "tm","ctm"};
		uniformLocations = u;
		
	}
	

}
