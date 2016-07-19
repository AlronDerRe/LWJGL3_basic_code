package Graphics;

import java.util.Hashtable;

public class ShaderHandler {

	private static Hashtable<String, Shader> shaderTable = new Hashtable<String, Shader>();
	
	public static void initShaders(){
		Shader s = new Shader("3D");
		shaderTable.put("3D", s);
	}
	
	public static Shader getShader(String shaderName){
		return shaderTable.get(shaderName);
	}
	
}
