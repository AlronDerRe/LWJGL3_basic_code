import java.nio.ByteBuffer;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.system.MemoryUtil;
import org.lwjglx.util.vector.Vector3f;

import Entities.Picture;

import Graphics.Camera;
import Graphics.Model.Mesh;
import Graphics.Model.ObjModelLoader;
import Graphics.Shader.ShaderHandler;
import Input.Input;
import Input.MouseButton;
import Input.MousePos;

public class Main implements Runnable{

	//Variables indispensables
	private Thread gameThread;
	public boolean running = false;
	
	private long window;
	private final int WIDTH = 800, HEIGHT = 600;

	private Input keyCallBack = new Input();
	private MousePos mousePosCallBack;
	private MouseButton mouseButtonCallBack;
	
	//Objets à afficher pour les test !
	private Camera cam = new Camera();
	private Picture pic, pic2;
	
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
		
		GLFW.glfwSetWindowPos(window, GLFWvidmode.width(vidmode)/2 - WIDTH/2, (GLFWvidmode.height(vidmode) - HEIGHT)/2);
		
		GLFW.glfwMakeContextCurrent(window);
		
		GLFW.glfwShowWindow(window);	
		
		
		GLFW.glfwSetKeyCallback(window, this.keyCallBack);
		GLFW.glfwSetCursorPosCallback(window, this.mousePosCallBack = new MousePos());
		GLFW.glfwSetMouseButtonCallback(window, mouseButtonCallBack = new MouseButton());

		initOpenGlStates();
		
		ShaderHandler.initShaders();
	    cam.init(70,  0.1f, 100.0f, (float)800/(float)600);
	    cam.move(new Vector3f(0, 6, 10));
	    cam.rotate(new Vector3f(-30, 0, 0));

		pic = new Picture("wood.png");
		//pic2 = new Picture("Sarah.png");
		//pic2.move(new Vector3f(0, 0, -1));
		
		cam.updatePosition();
		
	}
	
	private void initOpenGlStates(){
		
		GL.createCapabilities(true);
		GLContext.createFromCurrent();				
		
		//GL parameters
		GL11.glClearColor(1.0f, 0.5f, 1.0f, 0f);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_COLOR);
		GL11.glEnable(GL11.GL_DEPTH_TEST);			

	}
	
	@Override
	public void run(){
		init();
		
		while(running == true)
		{
			update();
			render();
			
			if(GLFW.glfwWindowShouldClose(window) == GL11.GL_TRUE){
				running = false;
				pic.delete();
				//pic2.delete();
			
				ShaderHandler.cleanUp();
			}
				
				
				
		}
	}

	
	private void update(){
		Input.resetKeyInfos();
		MouseButton.resetMouseButtonInfo();
		
		GLFW.glfwPollEvents();
		
	}
	
	private void render(){
		cam.updatePosition();
		//cam.rotate(new Vector3f(0, 0, 0.01f));
		pic.rotate(new Vector3f(0.0f, 0.01f, 0.0f));
		//cam.move(new Vector3f(0, 0, 0.001f));
		
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		//pic.render(cam);
		
		pic.render(cam);
			
		GLFW.glfwSwapBuffers(window);
	}
}
