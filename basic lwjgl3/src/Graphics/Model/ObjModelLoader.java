package Graphics.Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.lwjglx.util.vector.Vector2f;
import org.lwjglx.util.vector.Vector3f;

public abstract class ObjModelLoader {

	public static Mesh loadObjModelIntoMesh(String name){
		
		boolean texD = false, normalD = false;
		
		ArrayList<Vector3f> vertices = new ArrayList<Vector3f>(), vConfirm = new ArrayList<Vector3f>();
		ArrayList<Vector2f> textures = new ArrayList<Vector2f>(), texConfirm = new ArrayList<Vector2f>();
		ArrayList<Vector3f> normals = new ArrayList<Vector3f>(), normalConfirm = new ArrayList<Vector3f>();

		
		
		Hashtable<Integer, Vector2f>texture = new Hashtable<Integer, Vector2f>();
		Hashtable<Integer, Vector3f>normal = new Hashtable<Integer, Vector3f>();
		
		float[] vert = new float[0];
		float[] tex = new float[0];
		float[] norms = new float[0];
		int[] indices = new int[0];
		
		int vCount = 0;
		
		boolean initialize = false;
		
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader("Models/OBJ/" + name + ".obj" ));
		} catch (FileNotFoundException e) {
			System.err.println("Fichier inexistant!");
			e.printStackTrace();
			reader = null;
		}
		
		if(reader != null){
			String line = "";
			String[] lineElements=null;
			
			while(true){
				try {
					line = reader.readLine();
				} catch (IOException e) {
					System.err.println("FAIL");
				}
				if(line == null)
					break;
				
				
				lineElements = line.split(" ");
				
				if(line.startsWith("v ")){
					vertices.add(new Vector3f(Float.parseFloat(lineElements[1]), Float.parseFloat(lineElements[2]), Float.parseFloat(lineElements[3])));
					vCount++;
				}
				else if(line.startsWith("vt ")){
					textures.add(new Vector2f(Float.parseFloat(lineElements[1]), 1f-Float.parseFloat(lineElements[2])));
					texD = true;
				}
				else if(line.startsWith("vn ")){
					normals.add(new Vector3f(Float.parseFloat(lineElements[1]), Float.parseFloat(lineElements[2]), Float.parseFloat(lineElements[3])));
					normalD = true;
				}
				else if(line.startsWith("f ")){
					if(!initialize){
						initialize = true;
						vCount = 0;
					}
					
					for(int i = 1; i < 4; i++){
						String[] vertex = lineElements[i].split("/");
						vConfirm.add(vertices.get(Integer.parseInt(vertex[0]) - 1)); 
						
						if(texD){
							texConfirm.add(textures.get(Integer.parseInt(vertex[1])-1));
						}
						
						if(normalD){
							normalConfirm.add( normals.get(Integer.parseInt(vertex[2]) - 1));
						}						
						
					}
					vCount += 3;
				}

			}
			vert = new float[vCount*3];
			if(texD)
			tex = new float[vCount*2];
			if(normalD)
			norms = new float[vCount*3];
			
			for(int v = 0; v < vCount; v++){
				vert[v * 3 + 0] = vConfirm.get(v).x;
				vert[v * 3 + 1] = vConfirm.get(v).y;
				vert[v * 3 + 2] = vConfirm.get(v).z;
				
				if(texD){
					tex[v * 2 + 0] = texConfirm.get(v).x;
					tex[v * 2 + 1] = texConfirm.get(v).y;
				}
				if(normalD){
					norms[v * 3 + 0] = normalConfirm.get(v).x;
					norms[v * 3 + 1] = normalConfirm.get(v).y;
					norms[v * 3 + 2] = normalConfirm.get(v).z;
				}
			}
							
				Mesh M = new Mesh();
				
				M.setVertices(vert);
				if(texD)
					M.setTexturesCoords(tex);
				if(normalD)
				    M.setNormals(norms);
				
				M.updateBuffers();
				return M;
		}
		
			return new Mesh();
			
		}
}
