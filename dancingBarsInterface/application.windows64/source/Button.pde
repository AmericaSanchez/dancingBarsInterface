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
 
  void update() {
    if(mousePressed && over()) {
      clicked = true;    }  }
 
  boolean over() {
    if(mouseX > xpos && mouseX < xpos+xSize &&
      mouseY > ypos && mouseY < ypos+ySize) {
      return true;    }
    else {      return false;    }  }
 
  void displayButton() {
    if(over()) {
      fill(150);    }
    else if(clicked){
      fill(255);    }
    else {
      fill(0);    }
    rect(xpos, ypos, xSize, ySize);  }
 
  void click(){
    clicked=true;  }
 
  void unclick(){
    clicked=false;  }}
 

void buttonStopper(Button one, Button two, Button three, Button four, Button five){
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