package Graphics.Model;

import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import org.lwjgl.opengl.GL11;

import Entities.Camera;
import Entities.Entity;
import Graphics.Textures.Texture;

public class SuperRenderer {

	private Hashtable<Mesh, ArrayList<Entity>> entities = new Hashtable<Mesh, ArrayList<Entity>>();
	
	private ArrayList<Entity> E_list;
	
	private Mesh m;
	
	private Camera c1 = new Camera();
	
	public void addEntity(Entity ent){

		m = ent.getMesh();
		
		if(entities.containsKey(m))
			E_list = entities.get(m);
		else
			E_list = new ArrayList<Entity>();
		
		E_list.add(ent);
		entities.put(m, E_list);
		
		System.out.println(entities.get(m).size() + " size");
		System.out.println(entities.size() + "ent");
	}
	
	public void setCamera(Camera cam){c1 = cam;}
	
	public void render(){
		Set<Mesh> meshSet = entities.keySet();
		Iterator<Mesh> it = meshSet.iterator();
		while(it.hasNext()){
			Mesh mNext = it.next();
			bindAttributes(mNext);
			E_list = entities.get(mNext);
			for(Entity e : E_list){
				renderEntity(e);
			}
			unbind(mNext);
		}
	}
	
	
	private void bindAttributes(Mesh m){
		
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, m.getTexture().getTextureID());
		glBindVertexArray(m.getVaoID());
		
		if(m.isVerticesB())
			glEnableVertexAttribArray(m.getV());
		if(m.isColorB())
			glEnableVertexAttribArray(m.getC());
		if(m.isTextureB())
			glEnableVertexAttribArray(m.getT());
		if(m.isNormalsB())
			glEnableVertexAttribArray(m.getN());
	}
	
	private void unbind(Mesh m){
		if(m.isVerticesB())
			glDisableVertexAttribArray(m.getV());
		if(m.isColorB())
			glDisableVertexAttribArray(m.getC());
		if(m.isTextureB())
			glDisableVertexAttribArray(m.getT());
		if(m.isNormalsB())
			glDisableVertexAttribArray(m.getN());
		
		glBindVertexArray(0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	}
	
	private void renderEntity(Entity E){
		E.render(c1);
	}
}
