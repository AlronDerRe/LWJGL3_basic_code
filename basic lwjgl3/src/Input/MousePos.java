package Input;

import org.lwjgl.glfw.GLFWCursorPosCallback;

public class MousePos extends GLFWCursorPosCallback{

	public static double x, y;
	@Override
	public void invoke(long window, double xP, double yP) {
		// TODO Auto-generated method stub
		x = xP;
		y = yP;
	}


	
	
}
