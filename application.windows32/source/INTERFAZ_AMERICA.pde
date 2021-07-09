import controlP5.*;
ControlP5 cp5;

public int GridSize= 9;
public int WaveCenter =1;
public float WaveHeight = .46;
public float Speed = 0.08;

float a;

Button top, left, center, right, bottom;
Bars gridSizeSc, gridWaveSize, boxSizeSc;

 
void setup()
{
  size(500,500);
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
     .setRange(0,.7)
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
  
void draw()
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

  void cuadritos(){
int  gridSize = GridSize; 
int waveSize = WaveCenter;
float boxSize = WaveHeight;

    
  for (int x = 0; x < gridSize; x++) {
  for (int z = 0; z < gridSize; z++) {

 int y = int(24*boxSize * cos(waveSize * distance(x - (int)gridSize/2, z - (int)gridSize/2,0,0) + a));

if(top.clicked){
 y = int(24*boxSize * cos(waveSize * distance(x, z, 0, 0) + a)); }
else if(left.clicked){
 y = int(24*boxSize * cos(waveSize * distance(x, z - gridSize, 0, 0) + a)); }
else if(right.clicked){
y = int(24*boxSize * cos(waveSize * distance(x - gridSize, z, 0, 0) + a)); }
else if(bottom.clicked){
 y = int(24*boxSize * cos(waveSize * distance(x - gridSize, z - gridSize, 0, 0) + a)); }
  
       if(y<-30)
        y=-30;
        
        

        
      float xm = x*17 -8.5;
      float xt = x*17 +8.5;
      float zm = z*17 -8.5;
      float zt = z*17 +8.5;
 
     float halfw = (int)width/2;
     float halfh = (int)height/2 +WaveHeight*8;

      int isox1 = int(xm - zm + halfw);
      int isoy1 = int((xm + zm) * 0.5 + halfh);
      int isox2 = int(xm - zt + halfw);
      int isoy2 = int((xm + zt) * 0.5 + halfh);
      int isox3 = int(xt - zt + halfw);
      int isoy3 = int((xt + zt) * 0.5 + halfh);
      int isox4 = int(xt - zm + halfw);
      int isoy4 = int((xt + zm) * 0.5 + halfh);

  stroke(255);
      fill (c-100);
      quad(isox2, isoy2-y, isox3, isoy3-y, isox3, isoy3+40, isox2, isoy2+40);
      fill (c+100);
      quad(isox3, isoy3-y, isox4, isoy4-y, isox4, isoy4+40, isox3, isoy3+40);
      fill(c+y*10);
      quad(isox1, isoy1-y, isox2, isoy2-y, isox3, isoy3-y, isox4, isoy4-y);
  }  }  }


float distance(float x,float y,float cx,float cy) {
  return sqrt(sq(cx - x) + sq(cy - y));
}