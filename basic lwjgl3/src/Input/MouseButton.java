package Input;

import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class MouseButton extends GLFWMouseButtonCallback{

	private static int[] infos = new int[4];
	
	@Override
	public void invoke(long window, int button, int action, int mods) {
		infos[0] = button;
		infos[1] = action;
		infos[2] = mods;
	}
	
	public static void resetMouseButtonInfo(){
		infos[0] = -1;
		infos[1] = -1;
		infos[2] = -1;
	}
	
	public static int[] getMouseButtonInfo(){
		return infos;
	}
	
}
