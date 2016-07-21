package Graphics;

import static utils.Buffers.*;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL20;
import static org.lwjgl.opengl.GL30.*;


import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL15.*;


public class Mesh {

	List<Integer> vaos = new ArrayList<Integer>();
	List<Integer> vbos = new ArrayList<Integer>();
	
	private int vaoID;
	
	private float[] verticesArray, colorsArray, textureCoordsArray, normalsArray;
	private int draw_count;
	
	private boolean verticesB = false;
	private boolean colorB = false;
	private boolean textureB = false;
	private boolean normalsB = false;
	
	private FloatBuffer floatBuffer;
	
	public Mesh(){
		
	}
	
	public void setVertices(float[] verticesArray){
			this.verticesArray = verticesArray;
			draw_count = this.verticesArray.length / 3;
			this.verticesB = true;
	}	
	public void setColors(float[] colorsArray){
		if(colorsArray.length / 3 != draw_count){
			System.err.println("Le nombre de couleurs ne correspond pas au nombre de sommets !");
		}
		else{
			this.colorsArray = colorsArray;
			this.colorB = true;
		}	
	}	
	public void setTexturesCoords(float[] textureCoordsArray){
		if(textureCoordsArray.length / 2 != draw_count){
			System.err.println("Le nombre de coordonnés de texture ne correspond pas au nombre de sommets !");
		}
		else{
		this.textureCoordsArray = textureCoordsArray;
		this.textureB = true;
		}
	}	
	public void setNormals(float[] normalsArray){
		if(normalsArray.length /3 != draw_count){
			System.err.println("Le nombre de normales ne correspond pas au nombre de sommets !");
		}
		else{
			this.normalsArray = normalsArray;
			this.normalsB = true;
		}
	
	}
	
	public void cleanUpVaosAndVbos(){
		for(int a = 0; a < vaos.size(); a++)
			glDeleteVertexArrays(vaos.get(a));
		
		for(int a = 0; a < vbos.size(); a++)
			glDeleteBuffers(vbos.get(a));
		
		System.out.println("Vao abd Vbos cleaned up !");
	}
	
	private void createVAO(){
		vaoID = glGenVertexArrays();
		glBindVertexArray(vaoID);
		vaos.add(vaoID);
	}
	
	private void setDataInAttributeList(int attributeNumber, float[] data, int size){
		
		int vboID = glGenBuffers();
		vbos.add(vboID);
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		glBufferData(GL_ARRAY_BUFFER, createFloatBuffer(data), GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNumber, size, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	
	}
	
	private void unbindVao(){
		glBindVertexArray(0);
	}
	
	public void updateBuffers(){
		
		createVAO();
				
		//Vertices BUFFER !!
		if(this.verticesB){
			setDataInAttributeList(0, verticesArray, 3);
		}
		
		//Color Buffer !
		if(this.colorB){
			setDataInAttributeList(1, colorsArray, 3);
		}
		
		//Texture Buffer
		if(this.textureB){
			setDataInAttributeList(2, textureCoordsArray, 2);
		}
		
		//Normals Buffer
		if(this.normalsB){
			setDataInAttributeList(3, normalsArray, 3);
		}
		
		unbindVao();
	}


	public void render(){
		glBindVertexArray(vaoID);
		
		if(this.verticesB)
			glEnableVertexAttribArray(0);
		if(this.colorB)
			glEnableVertexAttribArray(1);
		if(this.textureB)
			glEnableVertexAttribArray(2);
		if(this.normalsB)
			glEnableVertexAttribArray(3);
		
		
		
		glDrawArrays(GL_TRIANGLES, 0, this.draw_count);
		
		
		
		if(this.verticesB)
			glDisableVertexAttribArray(0);
		if(this.colorB)
			glDisableVertexAttribArray(1);
		if(this.textureB)
			glDisableVertexAttribArray(2);
		if(this.normalsB)
			glDisableVertexAttribArray(3);
		
		glBindVertexArray(0);
	}
}
