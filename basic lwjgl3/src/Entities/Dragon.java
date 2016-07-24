package Entities;

import static org.lwjgl.opengl.GL20.*;

import org.lwjglx.util.vector.Vector3f;

import Graphics.Model.ObjModelLoader;
import Graphics.Shader.ShaderHandler;
import Graphics.Textures.Texture;

public class Dragon extends Entity{

	
	public Dragon(){
		if(!mesh.isAlreadyLoad())
			mesh = ObjModelLoader.loadObjModelIntoMesh("DragonBlender");
		
		mesh.getTexture().loadFromFile("gold.png");
	}

	@Override
	public void render(Camera cam) {
		glUseProgram(ShaderHandler.getShader("PerPixelLightning").getID());
		
		ShaderHandler.getShader("PerPixelLightning").setUniformMatrix4f("pm", cam.getPerspectiveMatrix());
		ShaderHandler.getShader("PerPixelLightning").setUniformMatrix4f("tm", getTransformationMatrix());
		ShaderHandler.getShader("PerPixelLightning").setUniformMatrix4f("ctm", cam.getTransformationMatrix());
		ShaderHandler.getShader("PerPixelLightning").setUniform3f("lightPosition", 0, 10f, 7f);
		ShaderHandler.getShader("PerPixelLightning").setUniform3f("lightColour", 255f*(1f/255f), 255f*(1f/255f), 255f*(1f/255f));
		ShaderHandler.getShader("PerPixelLightning").setUniform1i("sampler", 0);
		
		mesh.render();
		
		glUseProgram(0);
		
	}
	
}
