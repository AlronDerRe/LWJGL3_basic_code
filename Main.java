import static utils.FonctionsUtiles.*;

import java.nio.ByteBuffer;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.system.MemoryUtil;

import Graphics.Mesh;
import Graphics.Shader;
import Graphics.TextureHandler;
import Graphics.TextureLoader;

public class Main implements Runnable{

	private Thread gameThread;
	public boolean running = false;
	
	private long window;
	private final int WIDTH = 800, HEIGHT = 600;

	
	public static void main(String[] args) {
		
		Main game = new Main();	
		
	}
	
	
	public Main(){this.start();}
	
	private void start(){
		running = true;
		gameThread = new Thread(this, "GameLoop");
		gameThread.start();

	}
	
	private void init(){
		System.out.println("INITIALISATION !");
	
		if(GLFW.glfwInit() != GL11.GL_TRUE){
			System.out.println("Error : glwfInit()");
		}
		
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GL11.GL_TRUE);
		
		window = GLFW.glfwCreateWindow(WIDTH, HEIGHT, "My First Window !", MemoryUtil.NULL, MemoryUtil.NULL);

		if(window == MemoryUtil.NULL)
		{System.err.println("Eror : glfwCreateWindow()");}
		
		ByteBuffer vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		
		System.out.println();
		
		
		
		GLFW.glfwSetWindowPos(window, GLFWvidmode.width(vidmode)/2 - WIDTH/2, (GLFWvidmode.height(vidmode) - HEIGHT)/2);
		
		GLFW.glfwMakeContextCurrent(window);
		
		GLFW.glfwShowWindow(window);
		
		GL.createCapabilities(true);
		

		GLContext.createFromCurrent();
		
		//GL parameters
		GL11.glClearColor(1.0f, 1, 1.0f, 0f);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_COLOR);
		GL11.glEnable(GL11.GL_BLEND); 
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA,GL11.GL_ONE_MINUS_SRC_ALPHA);
	}
	
	@Override
	public void run(){
		init();
		
		while(running == true)
		{
			update();
			render();
			
			if(GLFW.glfwWindowShouldClose(window) == GL11.GL_TRUE)
				running = false;
		}
	}

	
	private void update(){
		GLFW.glfwPollEvents();
	}
	
	private void render(){
		
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		
		GLFW.glfwSwapBuffers(window);
	}
}
