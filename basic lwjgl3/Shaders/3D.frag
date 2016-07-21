#version 400 core

in vec3 color;
in vec2 tex_coord;

uniform sampler2D sampler;

out vec4 colorF;

void main()
{
	colorF = (texture2D(sampler, tex_coord) + vec4(color, 1.0))/2;
	//vec4(color, 1.0);
}