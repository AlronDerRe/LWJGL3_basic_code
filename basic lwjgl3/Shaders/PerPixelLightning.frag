#version 150 core

in vec3 normal;
in vec3 toLightVector;
in vec2 tex_coord;

uniform vec3 lightColour;
uniform sampler2D sampler;

out vec4 outColor;

void main(){
	
	vec3 unitNormal = normalize(normal);
	vec3 unitToLightVector = normalize(toLightVector);
	
	
	//DIFFUSE LIGHT SYSTEM
	float dotResult = dot(unitNormal, unitToLightVector);
	float brightness = max(dotResult, 0.5); 
	
	vec3 diffuseLight = lightColour * brightness;
	
	outColor = texture2D(sampler, tex_coord) * vec4(diffuseLight, 1.0);
}