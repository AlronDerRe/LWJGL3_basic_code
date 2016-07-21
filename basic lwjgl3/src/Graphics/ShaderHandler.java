package Graphics;

import java.util.Hashtable;

public class ShaderHandler {

	private static Hashtable<String, Shader> shaderTable = new Hashtable<String, Shader>();
	
	public static void initShaders(){
		Shader s = new Shader();
		String[] ss = {"pm", "tm","ctm"};
		s.create("3D");
		s.cacheUniformsLocations(ss);
		shaderTable.put("3D", s);
	}
	
	public static Shader getShader(String shaderName){
		return shaderTable.get(shaderName);
	}
	
	public static void cleanUp(){
		getShader("3D").cleanUp();
	}
}
