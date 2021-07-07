import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import controlP5.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class INTERFAZ_AMERICA extends PApplet {


ControlP5 cp5;

public int GridSize= 9;
public int WaveCenter =1;
public float WaveHeight = .46f;
public float Speed = 0.08f;

float a;

Button top, left, center, right, bottom;
Bars gridSizeSc, gridWaveSize, boxSizeSc;

 
public void setup()
{
  
  frameRate(30);
  
  cp5 = new ControlP5( this );
  cp5.addColorWheel("c" ,130 , 10 , 120 ).setRGB(color(128,0,255));
  noStroke();
  
   cp5 = new ControlP5(this);
    cp5.addSlider("GridSize")
     .setRange(1,13)
     .setPosition(280, 20)
     .setSize(150, 20);
  
    cp5.addSlider("WaveCenter")
     .setRange(1,5)
     .setPosition(280, 50)
     .setSize(150, 20);
     
    cp5.addSlider("WaveHeight")
     .setRange(0,6)
     .setPosition(280,80)
     .setSize(150,20);
  
     cp5.addSlider("Speed")
     .setRange(0,.7f)
     .setPosition(280,110)
     .setSize(150, 20);
     
  gridSizeSc = new Bars();
  gridWaveSize = new Bars();
  boxSizeSc =  new Bars();
  
  top = new Button(45, 10, false);
  left = new Button(10, 45, false);
  center = new Button(45, 45, true);
  right = new Button(80, 45, false);
  bottom = new Button(45, 80, false);
}

int c = color(100);
  
public void draw()
{
 background(0);  
  a -= Speed;

buttonStopper(top, left, center, right, bottom);

cuadritos();
 
  top.update();
  left.update();
  center.update();
  right.update();
  bottom.update();
  top.displayButton();
  left.displayButton();
  center.displayButton();
  right.displayButton();
  bottom.displayButton();
}

  public void cuadritos(){
int  gridSize = GridSize; 
int waveSize = WaveCenter;
float boxSize = WaveHeight;

    
  for (int x = 0; x < gridSize; x++) {
  for (int z = 0; z < gridSize; z++) {

 int y = PApplet.parseInt(24*boxSize * cos(waveSize * distance(x - (int)gridSize/2, z - (int)gridSize/2,0,0) + a));

if(top.clicked){
 y = PApplet.parseInt(24*boxSize * cos(waveSize * distance(x, z, 0, 0) + a)); }
else if(left.clicked){
 y = PApplet.parseInt(24*boxSize * cos(waveSize * distance(x, z - gridSize, 0, 0) + a)); }
else if(right.clicked){
y = PApplet.parseInt(24*boxSize * cos(waveSize * distance(x - gridSize, z, 0, 0) + a)); }
else if(bottom.clicked){
 y = PApplet.parseInt(24*boxSize * cos(waveSize * distance(x - gridSize, z - gridSize, 0, 0) + a)); }
  
       if(y<-30)
        y=-30;
        
        

        
      float xm = x*17 -8.5f;
      float xt = x*17 +8.5f;
      float zm = z*17 -8.5f;
      float zt = z*17 +8.5f;
 
     float halfw = (int)width/2;
     float halfh = (int)height/2 +WaveHeight*8;

      int isox1 = PApplet.parseInt(xm - zm + halfw);
      int isoy1 = PApplet.parseInt((xm + zm) * 0.5f + halfh);
      int isox2 = PApplet.parseInt(xm - zt + halfw);
      int isoy2 = PApplet.parseInt((xm + zt) * 0.5f + halfh);
      int isox3 = PApplet.parseInt(xt - zt + halfw);
      int isoy3 = PApplet.parseInt((xt + zt) * 0.5f + halfh);
      int isox4 = PApplet.parseInt(xt - zm + halfw);
      int isoy4 = PApplet.parseInt((xt + zm) * 0.5f + halfh);

  stroke(255);
      fill (c-100);
      quad(isox2, isoy2-y, isox3, isoy3-y, isox3, isoy3+40, isox2, isoy2+40);
      fill (c+100);
      quad(isox3, isoy3-y, isox4, isoy4-y, isox4, isoy4+40, isox3, isoy3+40);
      fill(c+y*10);
      quad(isox1, isoy1-y, isox2, isoy2-y, isox3, isoy3-y, isox4, isoy4-y);
  }  }  }


public float distance(float x,float y,float cx,float cy) {
  return sqrt(sq(cx - x) + sq(cy - y));
}
class Bars
{  int xpos, ypos;         
  float spos;   
  float ratio;

  Bars (
) {} 
  
  public float getPos() {    return spos * ratio;  }
  public float getRatio(){     return (spos-120)/width;  }
  

}
class Button
{
  boolean over;
  boolean clicked;
  int xpos;
  int ypos;
  int xSize=30;
  int ySize=30;
 
  Button(int x, int y, boolean pressed)
  {    xpos=x;
    ypos=y;
    clicked=pressed;  }
 
  public void update() {
    if(mousePressed && over()) {
      clicked = true;    }  }
 
  public boolean over() {
    if(mouseX > xpos && mouseX < xpos+xSize &&
      mouseY > ypos && mouseY < ypos+ySize) {
      return true;    }
    else {      return false;    }  }
 
  public void displayButton() {
    if(over()) {
      fill(150);    }
    else if(clicked){
      fill(255);    }
    else {
      fill(0);    }
    rect(xpos, ypos, xSize, ySize);  }
 
  public void click(){
    clicked=true;  }
 
  public void unclick(){
    clicked=false;  }}
 

public void buttonStopper(Button one, Button two, Button three, Button four, Button five){
  if(one.over() && mousePressed){
    two.unclick();
    three.unclick();
    four.unclick();
    five.unclick();  }
  else if(two.over() && mousePressed){
    one.unclick();
    three.unclick();
    four.unclick();
    five.unclick();  }
  else if(three.over() && mousePressed){
    one.unclick();
    two.unclick();
    four.unclick();
    five.unclick();   }
  else if(four.over() && mousePressed){
    one.unclick();
    two.unclick();
    three.unclick();
    five.unclick();  }
  else if(five.over() && mousePressed){
    one.unclick();
    two.unclick();
    three.unclick();
    four.unclick();  }
  
}
  public void settings() {  size(500,500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "INTERFAZ_AMERICA" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
