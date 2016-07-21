package Input;

import org.lwjgl.glfw.GLFWKeyCallback;

public class Input extends GLFWKeyCallback{	
	
	private static int[] infos = new int[4];
	
	public Input(){}
	
	@Override
	public void invoke(long arg0/*window*/, int key, int scan_code, int action, int mods) {
		infos[0] = key;
		infos[1] = scan_code;
		infos[2] = action;
		infos[3] = mods;
	}

	public static int[] getPressedKeyInfo(){
		return infos;
	}
	
	public static void resetKeyInfos(){
		infos[0] = -1;
		infos[1] = -1;
		infos[2] = -1;
		infos[3] = -1;
	}
	
}
