#version 400 core

in vec3 in_Vertex;
in vec2 in_Texture;

out vec2 tex_coord;

uniform mat4 pm;
uniform mat4 tm;
uniform mat4 ctm;

void main()
{
	gl_Position =  pm * ctm * tm * vec4(in_Vertex.x, in_Vertex.y, in_Vertex.z, 1.0);
	
	tex_coord = in_Texture;
}