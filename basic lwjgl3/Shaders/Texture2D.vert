#version 150

in vec3 in_Vertex;
in vec3 in_Color;
in vec2 in_Texture;

out vec3 color;
out vec2 tex_coord;


void main()
{
	vec4 test = vec4(in_Vertex, 0.0, 1.0);
	gl_Position = test;
	
	color = in_Color;
	tex_coord = in_Texture;
}