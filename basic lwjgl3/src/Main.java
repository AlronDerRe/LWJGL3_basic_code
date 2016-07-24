
import java.nio.ByteBuffer;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.system.MemoryUtil;
import org.lwjglx.util.vector.Vector3f;

import Entities.Camera;
import Entities.Dragon;
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
	private int WIDTH = 800, HEIGHT = 600;

	private Input keyCallBack = new Input();
	private MousePos mousePosCallBack;
	private MouseButton mouseButtonCallBack;
	
	//Objets à afficher pour les test !
	private Camera cam = new Camera();
	private Dragon drag1, drag2;
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
		
		//Paramétrage fenêtre
		//GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GL11.GL_TRUE);
		
		ByteBuffer vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		
		WIDTH = GLFWvidmode.width(vidmode);
		HEIGHT = GLFWvidmode.height(vidmode);
		
		window = GLFW.glfwCreateWindow(WIDTH, HEIGHT, "My First Window !", GLFW.glfwGetPrimaryMonitor(), MemoryUtil.NULL);

		if(window == MemoryUtil.NULL)
		{System.err.println("Error : glfwCreateWindow()");}
		
		
		GLFW.glfwMakeContextCurrent(window);
		
		GLFW.glfwShowWindow(window);	
		
		
		GLFW.glfwSetKeyCallback(window, this.keyCallBack);
		GLFW.glfwSetCursorPosCallback(window, this.mousePosCallBack = new MousePos());
		GLFW.glfwSetMouseButtonCallback(window, mouseButtonCallBack = new MouseButton());

		initOpenGlStates();
		
		ShaderHandler.initShaders();
	    cam.init(70,  0.1f, 100.0f, WIDTH/HEIGHT);
	    cam.move(new Vector3f(0, 6, 10));
	    cam.rotate(new Vector3f(-30,0, 0));

		drag1 = new Dragon();
		drag2 = new Dragon();
		//drag2.move(new Vector3f(0, 10, 6));
		
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
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);

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
				drag1.delete();
				drag2.delete();
			
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
		drag1.rotate(new Vector3f(0.0f, 0.07f, 0.0f));
		//cam.move(new Vector3f(0, 0, 0.001f));
		//drag2.rotate(new Vector3f(0, -0.07f, 0));
		drag2.move(new Vector3f(-0.0005f, 0 ,0));
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		drag1.render(cam);
		drag2.render(cam);
		
		GLFW.glfwSwapBuffers(window);
	}
}
