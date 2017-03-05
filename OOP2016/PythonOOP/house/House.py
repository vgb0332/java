'''
Created on 2016. 2. 3.

@author: cskim
'''
import turtle
from house import turtleGraphics

KNOBGAP = 2
KNOBSIZE = 4
SASHWIDTH = 2


screen = turtle.Screen()
screen.setup (width=850, height=500, startx=100, starty=50)
screen.screensize(800, 450)
offsetX, offsetY = screen.screensize()
offsetX *= 0.25
offsetY *= 0.75
    
#print (offsetX, offsetY)

class Drawable:
    def draw(self, tg):
        pass
    
class Shape(Drawable):
    
    def __init__(self, parent, x, y, w, h):
        self.x = x
        self.y = y
        self.width = w
        self.height = h
        self.parent = parent

    def absX(self):
        
        if self.parent is None:
            return self.x - offsetX
        else:
            return self.x + self.parent.absX()

    def absY(self):
        if self.parent is None:
            return self.y - offsetY
        else:
            return self.y + self.parent.absY()

class Roof(Shape):
    pass

class CathedralRoof(Roof):
    
    def draw(self, tg):
        ax = self.absX()
        ay = self.absY()
        #print("CathedralRoof", ax, ay)
        roof = []
        roof.append((ax, ay+self.height))
        roof.append((ax+self.width/2, ay))
        roof.append((ax+self.width, ay+self.height))

        tg.drawPolygon(roof)

class DomeRoof(Roof): 
    
    def draw(self, tg):
        ax = self.absX()
        ay = self.absY()
        #print("DomeRoof", ax, ay)
        
        tg.drawArc(ax, ay, self.width, 2*self.height, 0, 180)


class GambrelRoof(Roof):  
    
    def draw(self, tg):
        ax = self.absX()
        ay = self.absY()
        #print("GambrelRoof", ax, ay)
        roof = []
        roof.append((ax, ay+self.height))
        roof.append((ax+self.width/6, ay+self.height/2))
        roof.append((ax+self.width/2, ay))
        roof.append((ax+self.width*5/6, ay+self.height/2))
        roof.append((ax+self.width, ay+self.height))
        
        tg.drawPolygon(roof)
         
    
class Wall(Shape):
    def __init__(self, parent, x, y, w, h): 
        super().__init__(parent, x, y, w, h)
        self.door = None
        self.windows = []
    
    def draw(self, tg):
        ax = self.absX()
        ay = self.absY()
        
        tg.fill(True)
        tg.drawRect(ax, ay, self.width, self.height)
        tg.fill(False)
        
        if self.door is not None:
            self.door.draw(tg)
        
        for w in self.windows:
            w.draw(tg)
   
        #print("Wall", ax, ay)

class Door(Shape):
    def __init__(self, parent, x, y, w, h): 
        super().__init__(parent, x, y, w, h)
        self.leftKnob = True
    
    def draw(self, tg):
        ax = self.absX()
        ay = self.absY()
        left = self.leftKnob
        #print("Door", ax, ay, left)
        
        tg.drawRect(ax, ay, self.width, self.height)
        
        if left:
            tg.drawArc(ax+KNOBGAP, ay+self.height/2-KNOBSIZE/2, 
                    KNOBSIZE, KNOBSIZE, 0, 360)
        else:
            tg.drawArc(ax+self.width-KNOBGAP-KNOBSIZE, ay+self.height/2-KNOBSIZE/2, 
                    KNOBSIZE, KNOBSIZE, 0, 360)

    
class Window(Shape):
    def __init__(self, parent, x, y, w, h): 
        super().__init__(parent, x, y, w, h)
        self.sashed = False
    
    def draw(self, tg):
        ax = self.absX()
        ay = self.absY()
        sash = self.sashed
        #print("Window", ax, ay, sash)
        
        tg.drawRect(ax, ay, self.width, self.height)
        if sash:
            tg.drawRect(ax-SASHWIDTH, ay-SASHWIDTH, self.width+2*SASHWIDTH, self.height+2*SASHWIDTH)

    
class DoubleWindow(Window):
    
    def draw(self, tg):
        ax = self.absX()
        ay = self.absY()
        sash = self.sashed
        #print("DoubleWindow", ax, ay, sash)
        tg.drawRect(ax, ay, self.width, self.height)
        if sash:
            tg.drawRect(ax+SASHWIDTH, ay+SASHWIDTH, self.width-2*SASHWIDTH, self.height-2*SASHWIDTH)
        
        tg.drawLine(ax, ay+self.height/2, ax+self.width, ay+self.height/2)

class QuadWindow(Window):
    
    def draw(self, tg):
        ax = self.absX()
        ay = self.absY()
        sash = self.sashed
        #print("DoubleWindow", ax, ay, sash)
        
        tg.drawRect(ax, ay, self.width, self.height)
        if sash:
            tg.drawRect(ax+SASHWIDTH, ay+SASHWIDTH, self.width-2*SASHWIDTH, self.height-2*SASHWIDTH)
        
        tg.drawLine(ax, ay+self.height/2, ax+self.width, ay+self.height/2)
        tg.drawLine(ax+self.width/2, ay, ax+self.width/2, ay+self.height)


class House(Shape):
    def __init__(self, parent, x, y, w, h): 
        super().__init__(parent, x, y, w, h)
        self.wall = None
        self.roof = None
        
    def draw(self, tg):
        self.roof.draw(tg)
        self.wall.draw(tg)
    
def main():
        
    houses = []
    
    house1 =  House(None, 50, 50, 200, 350)
    roof1 = DomeRoof(house1, 0, 0, 200, 100)
    house1.roof = roof1
    wall1 = Wall(house1, 0, 100, 200, 250)
    win1 = DoubleWindow(wall1, 40, 40, 40, 80)
    #win1.sashed = True
    wall1.windows.append(win1)
    win2 = DoubleWindow(wall1, 120, 40, 40, 80)
    #win2.sashed = True 
    wall1.windows.append(win2)
    door1 = Door(wall1, 86, 180, 35, 70)
    #door1.leftKnob = False
    wall1.door = door1
    house1.wall = wall1
        
    houses.append(house1)
        
    house1 = House(None, 250, 100, 300, 300)
    roof1 = GambrelRoof(house1, 0, 0, 300, 100)
    house1.roof = roof1
    wall1 = Wall(house1, 0, 100, 300, 200)
    win1 = QuadWindow(wall1, 80, 20, 36, 70)
    win1.sashed = True
    wall1.windows.append(win1)
    win2 = QuadWindow(wall1, 200, 20, 36, 70)
    win2.sashed = True
    wall1.windows.append(win2)
    door1 = Door(wall1, 50, 140, 33, 60)
    #door1.leftKnob = False
    wall1.door = door1
    house1.wall = wall1
        
    houses.append(house1)
        
    house1 = House(None, 550, 20, 200, 380)
    roof1 = CathedralRoof(house1, 0, 0, 200, 100)
    house1.roof = roof1
    wall1 = Wall(house1, 0, 100, 200, 280)
    win1 = Window(wall1, 30, 40, 40, 80)
    win1.sashed = True
    wall1.windows.append(win1)
    win2 = Window(wall1, 130, 40, 40, 80)
    win2.sashed = True
    wall1.windows.append(win2)
    door1 = Door(wall1, 150, 200, 40, 80)
    door1.leftKnob = False
    wall1.door = door1
    house1.wall = wall1
        
    houses.append(house1)
    
    tg = turtleGraphics.TurtleGraphics()
    tg.tu.speed(3)
    for h in houses:
        h.draw(tg)

    tg.tu.ht() # hide turtle
    
if __name__ == "__main__":
    
    main()     
    turtle.mainloop()