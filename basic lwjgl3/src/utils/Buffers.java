package utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjglx.BufferUtils;

public abstract class Buffers {

		
	
	public static FloatBuffer createFloatBuffer(float[] data){
		FloatBuffer fb = ByteBuffer.allocateDirect(data.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
		fb.put(data).flip();
		return fb;
	}
	
	public static IntBuffer createIntBuffer(int[] data){
		IntBuffer ib = BufferUtils.createIntBuffer(data.length);
		ib.put(data);
		ib.flip();
		return ib;
	}
	
}
