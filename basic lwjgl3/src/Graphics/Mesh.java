package Graphics;

import static utils.Buffers.*;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import maths.Matrix4f;
import maths.Vector3f;

import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.system.MemoryUtil.*;


public class Mesh {

	private int draw_count = 0;
	private int v_id = 0, c_id = 0, t_id = 0, n_id = 0;
	
	private boolean verticesB = false;
	private boolean colorB = false;
	private boolean textureB = false;
	private boolean normalsB = false;
	
	private float[] verticesArray, colorsArray, textureCoordsArray, normalsArray;
	
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
	
	public void updateBuffers(){
		
		//Vertices BUFFER !!
		if(this.verticesB){
			if(v_id == 0){
				v_id = glGenBuffers();
			}
			glBindBuffer(GL_ARRAY_BUFFER, v_id);
			glBufferData(GL_ARRAY_BUFFER, createFloatBuffer(this.verticesArray), GL_STATIC_DRAW);
			
		}
		
		//Color Buffer !
		if(this.colorB){
			if(c_id == 0)
				c_id = glGenBuffers();
			glBindBuffer(GL_ARRAY_BUFFER, c_id);
			glBufferData(GL_ARRAY_BUFFER, createFloatBuffer(this.colorsArray), GL_STATIC_DRAW);
		}
		
		//Texture Buffer
		if(this.textureB){
			if(t_id == 0)
				t_id = glGenBuffers();
			glBindBuffer(GL_ARRAY_BUFFER, t_id);
			glBufferData(GL_ARRAY_BUFFER, createFloatBuffer(this.textureCoordsArray), GL_STATIC_DRAW);
		}
		
		//Normals Buffer
		if(this.normalsB){
			if(n_id == 0)
				n_id = glGenBuffers();
			glBindBuffer(GL_ARRAY_BUFFER, n_id);
			glBufferData(GL_ARRAY_BUFFER, createFloatBuffer(this.normalsArray), GL_STATIC_DRAW);
		}
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}


	public void render(){
		if(this.verticesB)
			glEnableVertexAttribArray(0);
		if(this.colorB)
			glEnableVertexAttribArray(1);
		if(this.textureB)
			glEnableVertexAttribArray(2);
		
		
		if(this.verticesB){
			glBindBuffer(GL_ARRAY_BUFFER, v_id);
			GL20.glVertexAttribPointer(0,  3,GL_FLOAT, false, 0, 0);
		}
		if(this.colorB){
			glBindBuffer(GL_ARRAY_BUFFER, c_id);
			GL20.glVertexAttribPointer(1,  3,GL_FLOAT , false, 0, 0);
		}
		if(this.textureB){
			glBindBuffer(GL_ARRAY_BUFFER, t_id);
			GL20.glVertexAttribPointer(2, 2, GL_FLOAT, false, 0, 0);
		}
		if(this.normalsB){
			glBindBuffer(GL_ARRAY_BUFFER, n_id);
			glNormalPointer(GL_FLOAT, 0, 0);
		}
		
		glDrawArrays(GL_TRIANGLES, 0, this.draw_count);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		
		if(this.verticesB)
			glDisableVertexAttribArray(0);
		if(this.colorB)
			glDisableVertexAttribArray(1);
		if(this.textureB)
			glDisableVertexAttribArray(2);
		
	}
}
