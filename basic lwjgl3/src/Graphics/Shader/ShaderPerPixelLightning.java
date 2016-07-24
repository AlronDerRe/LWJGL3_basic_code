package Graphics.Shader;

import static org.lwjgl.opengl.GL20.*;

public class ShaderPerPixelLightning extends Shader{

	public ShaderPerPixelLightning(String name) {
		super(name);
	}

	@Override
	protected void bindAttribLocations() {
		glBindAttribLocation(programID, 0, "in_Vertex");
		glBindAttribLocation(programID, 1, "in_Normal");
		
	}
	
	@Override
	protected void setUniformNames() {
		String[] u = {"pm", "tm", "ctm", "lightPosition", "lightColour", "shineDamper", "reflectivity"};
		uniformLocations = u;
	}



}
