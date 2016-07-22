package Graphics.Model;

import java.util.Hashtable;


public class TextureHandler {

	private static Hashtable<String, Integer> ht = new Hashtable<String, Integer>();
	
	public boolean load(String texturePath){
		if(ht.containsKey(texturePath)){
			System.out.println("Ressources déjà chargée !");
			return true;
		}
		else{
			int id = TextureLoader.generateTexture(texturePath);
			if(id != 0){
				ht.put(texturePath, id);
				System.out.println("Chargement de " + texturePath + " réussi !");
				return true;
			}
			else
				{
					System.err.println("Impossible de charger la texture !");
					return false;
				}
		}
	}
	
	public int getID(String path){
		return ht.get(path);
	}
	
}
