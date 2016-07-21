#version 150

in vec3 in_Vertex;
in vec3 in_Color;
in vec2 in_Texture;
in vec3 in_Normal;

out vec3 color;
out vec2 tex_coord;

uniform mat4 pm;

uniform mat4 tm;
uniform mat4 rm;
uniform mat4 sm;

uniform mat4 ctm;
uniform mat4 crm;

void main()
{
	gl_Position =  pm * (ctm * crm) * (tm*rm*sm) * vec4(in_Vertex.x, in_Vertex.y, in_Vertex.z, 1.0);
	
	color = in_Color;
	tex_coord = in_Texture;
}