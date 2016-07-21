package utils;

import java.nio.FloatBuffer;

import org.lwjglx.BufferUtils;

public abstract class Buffers {

		public static FloatBuffer createFloatBuffer(float[] data){
		FloatBuffer fb = BufferUtils.createFloatBuffer(data.length);
		fb.put(data);
		fb.flip();
		return fb;
	}
	
}
