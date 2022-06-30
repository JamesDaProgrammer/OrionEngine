#version 330 core

uniform float u_RedValue;
out vec4 FragColor;

void main()
{
    FragColor = vec4(u_RedValue, 1.0f, 1.0f, 1.0f);
}