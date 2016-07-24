#version 150 core

in vec3 normal;
in vec3 toLightVector;
in vec3 toCameraVector;

uniform vec3 lightColour;
uniform float shineDamper;
uniform float reflectivity;

out vec4 outColor;

void main(){
	
	vec3 unitNormal = normalize(normal);
	vec3 unitToLightVector = normalize(toLightVector);
	
	
	//DIFFUSE LIGHT SYSTEM
	float dotResult = dot(unitNormal, unitToLightVector);
	float brightness = max(dotResult, 0.0);
	
	vec3 diffuseLight = lightColour * brightness;
	
	//SPECULAR LIGHT SYSTEM
	vec3 FromLightToVertexVector = -unitToLightVector;
	vec3 reflectVector = normalize(reflect(FromLightToVertexVector, unitNormal));
	vec3 unitToCameraVector = normalize(toCameraVector);
	
	float specularFactor = max(dot(reflectVector, unitToCameraVector), 0.0);
	
	float dampedFactor = pow(specularFactor, shineDamper);
	
	vec3 finalSpecular = dampedFactor * lightColour * reflectivity;

	
	outColor = vec4(diffuseLight + finalSpecular, 1.0);
}