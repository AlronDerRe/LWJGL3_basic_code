package Graphics.Textures;

import static Graphics.Textures.TextureHandler.*;

public class Texture {

	private int textureID;
	
	public Texture(){}
	
	public void loadFromFile(String name){
		if(TextureHandler.load(name)){
			textureID = TextureHandler.getID(name);
		}
	}
	
	public int getTextureID(){return textureID;}

	
}
