package Graphics.Textures;

import static Graphics.Textures.TextureHandler.*;

public class Texture {

	private int textureID;
	
	private float shineDamper = 5f;
	private float reflectivity = 0.5f;
	
	
	public Texture(){}
	
	public void loadFromFile(String name){
		if(TextureHandler.load(name)){
			textureID = TextureHandler.getID(name);
		}
	}
	
	public int getTextureID(){return textureID;}

	public float getReflectivity() {
		return reflectivity;
	}

	public void setReflectivity(float reflectivity) {
		this.reflectivity = reflectivity;
	}

	public float getShineDamper() {
		return shineDamper;
	}

	public void setShineDamper(float shineDamper) {
		this.shineDamper = shineDamper;
	};
	
}
