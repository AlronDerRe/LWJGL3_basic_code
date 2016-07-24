package Entities;

import static org.lwjgl.opengl.GL20.*;

import org.lwjglx.util.vector.Vector3f;

import Graphics.Model.ObjModelLoader;
import Graphics.Shader.ShaderHandler;
import Graphics.Textures.Texture;

public class Dragon extends Entity{
	
	private static Texture t = new Texture();
	
	public Dragon(){
		if(!mesh.isAlreadyLoad())
			mesh = ObjModelLoader.loadObjModelIntoMesh("DragonBlender");
		
		
	}

	@Override
	public void render(Camera cam) {
		glUseProgram(ShaderHandler.getShader("PerPixelLightning").getID());
		
		ShaderHandler.getShader("PerPixelLightning").setUniformMatrix4f("pm", cam.getPerspectiveMatrix());
		ShaderHandler.getShader("PerPixelLightning").setUniformMatrix4f("tm", getTransformationMatrix());
		ShaderHandler.getShader("PerPixelLightning").setUniformMatrix4f("ctm", cam.getTransformationMatrix());
		ShaderHandler.getShader("PerPixelLightning").setUniform3f("lightPosition", 2f, 10f, 3f);
		ShaderHandler.getShader("PerPixelLightning").setUniform3f("lightColour", 244f*(1f/255f), 181f*(1f/255f), 24f*(1f/255f));
		ShaderHandler.getShader("PerPixelLightning").setUniform1f("shineDamper", t.getShineDamper());
		ShaderHandler.getShader("PerPixelLightning").setUniform1f("reflectivity", t.getReflectivity());
		
		mesh.render();
		
		glUseProgram(0);
		
	}
	
}
