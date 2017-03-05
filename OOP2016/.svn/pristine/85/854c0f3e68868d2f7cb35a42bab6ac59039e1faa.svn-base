'''
Created on 2016. 2. 3.

@author: cskim
'''
import math
import turtle

def angleOfLine(p1, p2):
    x1, y1 = p1
    x2, y2 = p2
    dx = x2 - x1
    dy = y2 - y1
    angle = math.degrees(math.atan2(dy, dx))
    return angle
    
class TurtleGraphics:
    screen = turtle.Screen()
    screenWidth, screenHeight = screen.screensize()
    def __init__(self, fillColor='white', penColor='black'):
        self.tu = turtle.Turtle()

        self.fillColor = fillColor
        self.penColor = penColor  
        self.screen = turtle.Screen()
        self.screen.bgcolor("#F0F0F0")
        self.tu.fillcolor(self.fillColor)
        self.tu.pencolor(self.penColor)
              
    def turtleTransform(self, point):
        x,y = point
        y = -y
        x -= TurtleGraphics.screenWidth/2
        y -= TurtleGraphics.screenHeight/2
        return (x, y)
    
    def drawLine(self, x1, y1, x2, y2):
        x1,y1 = self.turtleTransform((x1, y1))
        x2,y2 = self.turtleTransform((x2, y2))
        
        self.tu.seth(angleOfLine((x1, y1), (x2, y2)))
        
        self.tu.pu()
        self.tu.goto(x1, y1)
        self.tu.pd()
        self.tu.goto(x2, y2)
    
    def drawRect(self, x, y, w, h):
        x,y = self.turtleTransform((x, y))
        self.tu.pu()
        self.tu.goto(x, y)
        self.tu.pd()
        
        self.tu.seth(0)
        self.tu.goto(x+w, y)
        self.tu.rt(90)
        self.tu.goto(x+w, y-h)
        self.tu.rt(90)
        self.tu.goto(x, y-h)
        self.tu.rt(90)
        self.tu.goto(x, y)
        self.tu.rt(90)
    
    def drawPolygon(self, poly):
        self.tu.pu()

        if poly is not None and len(poly)>0:
            x0,y0 = self.turtleTransform(poly[0])
        else:
            return
        
        x1, y1 = x0, y0
        self.tu.begin_fill()
        self.tu.pu()
        self.tu.goto(x0, y0)
        self.tu.pd()
        for p in poly[1:]:
            x,y = self.turtleTransform(p)
            self.tu.seth(angleOfLine((x1, y1), (x, y)))
            self.tu.goto(x, y)  
            x1, y1 = x, y
             
        self.tu.seth(angleOfLine((x1, y1), (x0, y0)))        
        self.tu.goto(x0, y0)
        self.tu.end_fill()
        
    def drawArc(self, x, y, w, h, angle1, angle2):
        x,y = self.turtleTransform((x, y))
        cx = x+w/2
        cy = y-h/2
        r = w/2
        tx = cx + r*math.cos(angle1)
        ty = cy + r*math.sin(angle1)
        self.tu.pu()
        self.tu.goto(tx, ty)
        self.tu.seth(90+angle1)
        
        self.tu.pd()
        self.tu.begin_fill()
        self.tu.circle(w/2, angle2)
        self.tu.end_fill()

        
    def fill(self, isfill):
        if isfill:
            self.tu.begin_fill()
        else:
            self.tu.end_fill()
           
    
        