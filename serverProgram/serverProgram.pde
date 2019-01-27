import processing.net.*;

PImage bg;
Server s; 
Client c;
String input;
int data[];

int y;
boolean locked = false;
boolean overBox = false;
float bx, by, length;
float xOffset = 0.0; 
float yOffset = 0.0; 

void setup() { 
  size(479, 428);
  bx = 100;
  by = 500;
  length = 15;
  rectMode(RADIUS);
  bg = loadImage("C:\\Users\\Titanium Robotics\\Desktop\\image.png");
  frameRate(5); // Slow it down a little
  s = new Server(this, 12345);  // Start a simple server on a port
} 
void draw() { 
  //if (mousePressed == true) {
  // Draw our line
  background(bg);
  if (mouseX > bx-length && mouseX < bx+length && 
    mouseY > by-length && mouseY < by+length) {
    overBox = true;  
    if (!locked) { 
      stroke(255); 
      fill(153);
    }
  } else {
    stroke(255);
    fill(153);
    overBox = false;
  }
  rect(bx, by, length, length);
  // Send mouse coords to other person
  s.write(mouseX + " " + mouseY + "\n");
  //}

  // Receive data from client
  c = s.available();
  if (c != null) {
    input = c.readString(); 
    println("Received " + input);
    input = input.substring(0, input.indexOf("\n"));  // Only up to the newline
    data = int(split(input, ' '));  // Split values into an array
    // Draw line using received coords
    
    rect(data[0], data[1], length, length);
  }
}
void mousePressed() {
  if (overBox) { 
    locked = true;
  } else {
    locked = false;
  }
  xOffset = mouseX-bx; 
  yOffset = mouseY-by;
}
void mouseDragged() {
  if (locked) {
    bx = mouseX-xOffset; 
    by = mouseY-yOffset;
  }
}

void mouseReleased() {
  locked = false;
}
