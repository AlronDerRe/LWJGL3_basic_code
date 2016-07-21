package Entities;

import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL20.*;

import Graphics.Camera;
import Graphics.ShaderHandler;
import Graphics.TextureHandler;
import Graphics.TextureLoader;

public class Picture extends Entity{

	private static float vertices[] = 
		{
			-0.5f, -0.2f, 0,
			-0.5f, 0.2f, 0,
			0.5f, 0.2f, 0,
			
			-0.5f, -0.2f, 0f,
			0.5f, 0.2f, 0f,
			0.5f, -0.2f, 0f,
		};
		private static float colors[] = 
		{
			0, 0, 0,
			0.25f, 0.25f, 0.25f,
			0.5f, 0.5f, 0.5f,
			
			
			0, 0, 0,
			0.5f, 0.5f, 0.5f,
			1, 1, 1
			
		};
		private static float tex[] = {
				0, 1,
				0, 0,
				1, 0,
				
				0, 1,
				1, 0,
				1, 1
		};
		
	private int texture;
	
	public Picture(String path){
		mesh.setVertices(Picture.vertices);
		mesh.setColors(Picture.colors);
		mesh.setTexturesCoords(tex);
		mesh.updateBuffers();
		
		TextureHandler TH = new TextureHandler();
		if(TH.load(path)){
			texture = TH.getID(path);
		}
	}

	@Override
	public void render(Camera cam) {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		glUseProgram(ShaderHandler.getShader("3D").getID());
			
		ShaderHandler.getShader("3D").setUniform("pm", cam.getPerspectiveMatrix());
		ShaderHandler.getShader("3D").setUniform("tm", getTransformationMatrix());
		ShaderHandler.getShader("3D").setUniform("ctm", cam.getTransformationMatrix());
		
		
		mesh.render();
		
		glUseProgram(0);
		
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	}
	
}
