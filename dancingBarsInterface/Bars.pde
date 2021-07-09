class Bars
{  int xpos, ypos;         
  float spos;   
  float ratio;

  Bars (
) {} 
  
  float getPos() {    return spos * ratio;  }
  float getRatio(){     return (spos-120)/width;  }
  

}