#version 150 core

in vec3 in_Vertex;
in vec3 in_Normal;

out vec3 normal;
out vec3 toLightVector;
out vec3 toCameraVector;

uniform mat4 pm;
uniform mat4 tm;
uniform mat4 ctm;
uniform vec3 lightPosition;

void main(){


gl_Position = pm * ctm * tm * vec4(in_Vertex.x, in_Vertex.y, in_Vertex.z, 1.0);

normal = (tm * vec4(in_Normal, 0.0)).xyz;
toLightVector = lightPosition-(tm * vec4(in_Vertex, 1.0)).xyz;

toCameraVector = (inverse(pm) * vec4(0, 0, 0, 1.0)).xyz - (tm * vec4(in_Vertex, 1.0)).xyz;

}