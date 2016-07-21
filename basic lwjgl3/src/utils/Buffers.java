package utils;

import java.nio.FloatBuffer;

import org.lwjglx.BufferUtils;

public abstract class Buffers {

		public static FloatBuffer createFloatBuffer(float[] data){
		FloatBuffer fb = BufferUtils.createFloatBuffer(data.length);
		for(int i = 0; i < data.length; i++)
			fb.put(data[i]);
		fb.flip();
		return fb;
	}
	
}
