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
		
		ArrayList<Vector3f> vertices = new ArrayList<Vector3f>();
		ArrayList<Vector2f> textures = new ArrayList<Vector2f>();
		ArrayList<Vector3f> normals = new ArrayList<Vector3f>();
		ArrayList<Integer> indice = new ArrayList<Integer>();
		ArrayList<Integer> done = new ArrayList<Integer>();
		
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
				}
				else if(line.startsWith("vn ")){
					normals.add(new Vector3f(Float.parseFloat(lineElements[1]), Float.parseFloat(lineElements[2]), Float.parseFloat(lineElements[3])));
				}
				else if(line.startsWith("f ")){
					if(!initialize){
					vert = new float[vCount*3];
					tex = new float[vCount*2];
					norms = new float[vCount*3];
					initialize = true;}
					
					for(int i = 1; i < 4; i++){
						String[] vertex = lineElements[i].split("/");
						indice.add(Integer.parseInt(vertex[0])-1);
						if(!done.contains(Integer.parseInt(vertex[0])-1)){
							if(vertex[1].length() != 0){
								texture.put(Integer.parseInt(vertex[0])-1, textures.get(Integer.parseInt(vertex[1])-1));
								texD = true;
							}else
								tex = new float[0];
							if(vertex[2].length() != 0){
								normal.put(Integer.parseInt(vertex[0])-1, normals.get(Integer.parseInt(vertex[2])-1));
								normalD = true;
							}else
								norms = new float[0];
							done.add(Integer.parseInt(vertex[0])-1);
						}
						
					}
					
				}

			}
			
				for(int i = 0; i < vCount; i++){
					vert[i * 3 + 0] = vertices.get(i).x;
					vert[i * 3 + 1] = vertices.get(i).y;
					vert[i * 3 + 2] = vertices.get(i).z;
					
					if(texD){					
						tex[i * 2 + 0] = texture.get(i).x;
						tex[i * 2 + 1] = texture.get(i).y;
					}				
					if(normalD){
						norms[i * 3 + 0] = normal.get(i).x;
						norms[i * 3 + 1] = normal.get(i).x;
						norms[i * 3 + 2] = normal.get(i).x;
					}
				}
				
				indices = new int[indice.size()];
				for(int i = 0; i < indice.size(); i++)
					indices[i] = indice.get(i);
				
				for(int i = 0; i < vert.length; i++){
					System.out.println(vert[i] + " ");
					if(i%3 == 2 && i != 0)
						System.out.println(" ");
				}
					
				
				Mesh M = new Mesh();
				
				M.setVertices(vert);
				if(texD)
					M.setTexturesCoords(tex);
				if(normalD)
				    M.setNormals(norms);
				M.setIndices(indices);
				M.updateBuffers();
				return M;
		}
		
			return new Mesh();
			
		}
}
