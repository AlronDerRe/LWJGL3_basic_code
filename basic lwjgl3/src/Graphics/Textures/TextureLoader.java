package Graphics.Textures;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;
import org.lwjglx.BufferUtils;

public abstract class TextureLoader {
	
	public static int generateTexture(String texPath){
		BufferedImage img;
		int texID = glGenTextures();
		//Récupération de l'image via les fonctions java.awt
		try {
			img = ImageIO.read(new File(texPath));
		} catch (IOException e) {
			System.err.println("Impossible de charger : " + texPath + " !");
			e.printStackTrace();
			img = null;
			texID = 0;
		}
	
		if(texID != 0){
		
		int bytePerPixel = 4;
		
		int pixels[] = new int[img.getHeight() * img.getWidth()];
		img.getRGB(0,  0, img.getWidth(), img.getHeight(), pixels, 0,  img.getWidth());
		
		ByteBuffer bb = BufferUtils.createByteBuffer(img.getHeight() * img.getWidth()*bytePerPixel);
		
		for(int y = 0; y < img.getHeight(); y++){
			for(int x = 0; x < img.getWidth(); x++){
				int pixel = pixels[y * img.getWidth() + x];
				bb.put((byte) ((pixel >> 16) & 0xFF));     
		        bb.put((byte) ((pixel >> 8) & 0xFF));      
		        bb.put((byte) (pixel & 0xFF));
		        bb.put((byte) ((pixel >> 24) & 0xFF));
			}
		}
		
		bb.flip();
		
		
		
			glBindTexture(GL_TEXTURE_2D, texID);
			
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
	        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);

	        //Setup texture scaling filtering
	        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
	        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			
			
			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, img.getWidth(), img.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, bb);
		}
		
			
		return texID;
	}
}
