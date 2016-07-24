package Graphics.Model;

import static utils.Buffers.*;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import Graphics.Textures.Texture;

import static org.lwjgl.opengl.GL30.*;


import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL15.*;


public class Mesh {

	private Texture texture = new Texture();
	
	List<Integer> vaos = new ArrayList<Integer>();
	List<Integer> vbos = new ArrayList<Integer>();
	
	private int vaoID;
	
	public boolean isVerticesB() {
		return verticesB;
	}

	public boolean isColorB() {
		return colorB;
	}

	public boolean isTextureB() {
		return textureB;
	}

	public boolean isNormalsB() {
		return normalsB;
	}

	public boolean isIndiceB() {
		return indiceB;
	}

	public int getVaoID() {
		return vaoID;
	}


	private float[] verticesArray, colorsArray, textureCoordsArray, normalsArray;
	private int[] indices;
	private int draw_count;
	
	private boolean verticesB = false;
	private boolean colorB = false;
	private boolean textureB = false;
	private boolean normalsB = false;
	private boolean indiceB = false;
	
	private int v, c, t, n;
	
	public int getV() {
		return v;
	}

	public int getC() {
		return c;
	}

	public int getT() {
		return t;
	}

	public int getN() {
		return n;
	}

	public Mesh(){
		
	}
	
	public boolean isAlreadyLoad(){return verticesB;}
	
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
	public void setIndices(int[] indices){
		this.indices = indices;
		this.indiceB= true;
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
	private void useIndices(){
		int id = glGenBuffers();
		vbos.add(id);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, createIntBuffer(this.indices), GL_STATIC_DRAW);
		
	}
	
 	private void unbindVao(){
		glBindVertexArray(0);
	}
	
	public void updateBuffers(){
		
		createVAO();
		
		if(this.indiceB)
			useIndices();
		
		int count = 0;
		//Vertices BUFFER !!
		if(this.verticesB){
			setDataInAttributeList(count, verticesArray, 3);
			v = count;
			count++;
		}
		
		//Color Buffer !
		if(this.colorB){
			setDataInAttributeList(count, colorsArray, 3);
			c = count;
			count++;
		}
		
		//Texture Buffer
		if(this.textureB){
			t = count;
			setDataInAttributeList(count, textureCoordsArray, 2);
			count++;
		}
		
		//Normals Buffer
		if(this.normalsB){
			n = count;
			setDataInAttributeList(count, normalsArray, 3);
			count++;
		}
		
		unbindVao();
	}


	public void render(){
	
		if(this.indiceB)
			GL11.glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_INT, 0);
		else
			glDrawArrays(GL_TRIANGLES, 0, this.draw_count);

	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
}
