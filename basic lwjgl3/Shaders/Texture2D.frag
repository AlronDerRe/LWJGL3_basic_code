#version 150

in vec3 color;
in vec2 tex_coord;

uniform sampler2D sampler;

out vec4 colorF;

void main()
{
		colorF = texture2D(sampler, tex_coord);
}