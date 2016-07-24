package Graphics.Shader;

import java.util.Hashtable;

public class ShaderHandler {

	private static Hashtable<String, Shader> shaderTable = new Hashtable<String, Shader>();
	
	public static void initShaders(){
		Shader3D s = new Shader3D("3D");
		shaderTable.put("3D", s);
		ShaderPerPixelLightning s1 = new ShaderPerPixelLightning("PerPixelLightning");
		shaderTable.put("PerPixelLightning", s1);
	}
	
	public static Shader getShader(String shaderName){
		return shaderTable.get(shaderName);
	}
	
	public static void cleanUp(){
		getShader("3D").cleanUp();
		getShader("PerPixelLightning").cleanUp();
	}
}
