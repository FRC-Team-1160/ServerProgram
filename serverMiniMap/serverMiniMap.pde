import processing.net.*;

PImage bg;
Server s; 
Client c;
String input;
int data[];

float botX, botY, botAngle, botWidth, botHeight;
float actualFieldWidth = 324, actualFieldHeight = 324;
float actualRobotWidth = 28.5, actualRobotHeight = 30.5;

void setup() { 
  bg = loadImage("..\\map2.png");
  size(736, 736);
  rectMode(CENTER);
  
  botWidth = (actualRobotWidth / actualFieldWidth) * bg.width;   // 63.685
  botHeight = (actualRobotHeight / actualFieldHeight) * bg.height;  // 68.719
  botAngle = 0;

  frameRate(60);
  s = new Server(this, 12345);  // 12345 is an example, port UDP/TCP 5800-5810 for "team use"
} 

void draw() {
  background(bg);
  
  // values are mouse coordinates until we get info about intial bot position
  botX = width / 2;
  botY = height - (botHeight / 2);
  
  // receive data from client
  c = s.available();
  if (c != null) {
    input = c.readString(); 
    println("Received " + input);
    input = input.substring(0, input.indexOf("\n"));  // only up to the newline
    data = int(split(input, ' '));  // split values into an array
    // draw line using received coords
    
    botX += data[0];
    botY += data[1];
    botAngle = data[2];
    
  }
  
  pushMatrix();
  translate(botX, botY);
  rotate(radians(botAngle));
  // rectangle
  stroke(0, 0, 150);
  strokeWeight(3);
  fill(255);
  rect(0, 0, botWidth, botHeight);
  popMatrix();
  
  pushMatrix();
  translate(botX, botY);
  rotate(radians(botAngle));
  noStroke();
  fill(200, 0, 0);
  triangle(-15, 20, 0, -20, 15, 20);
  popMatrix();
  
  //botAngle += 1;
}
