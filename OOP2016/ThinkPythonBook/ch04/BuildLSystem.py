'''
Created on 2015. 11. 7.

@author: cskim
'''
import math
import turtle

def dragon(n):
    if n==0 :
        return 'F'
    else:
        return dragon(n-1)+'-'+nogard(n-1)
    
def nogard(n):
    if n==0 :
        return 'F'
    else:
        return dragon(n-1)+'+'+nogard(n-1)
    
def moveTurtleWithLSystem(bob, lsystem, angle, step, dir):
    
    bob.lt(dir)
    
    for c1 in lsystem:
        if c1=='F':
            bob.fd(step)
        elif c1=='+':
            bob.rt(angle)
        elif c1=='-':
            bob.lt(angle)

def koch(n):
    if n==0:
        return 'F'
    else:
        return koch(n-1)+'+'+koch(n-1)+'--'+koch(n-1)+'+'+koch(n-1)

def drawSnowflake(n):
    koch1 = koch(n)
    snowflake = "+"+koch1+"--"+koch1+"--"+koch1
    bob = turtle.Turtle()
    bob.pu()
    bob.setx(-200)
    bob.sety(-200)
    bob.pd()
    bob.speed(0)
    moveTurtleWithLSystem(bob, snowflake, -60, 2, 0)

    
def drawDragon4(n):
    lsystem = dragon(n)
    bob = turtle.Turtle()
    bob.speed(10)
    bob.pencolor('red')
    moveTurtleWithLSystem(bob, lsystem, 90, 10, 0)
    bob.pu()
    bob.home()
    bob.pd()
    bob.pencolor('green')
    moveTurtleWithLSystem(bob, lsystem, 90, 10, 90)
    bob.pu()
    bob.home()
    bob.pd()
    bob.pencolor('blue')
    moveTurtleWithLSystem(bob, lsystem, 90, 10, 180)
    bob.pu()
    bob.home()
    bob.pd()
    bob.pencolor("#ffd700")
    moveTurtleWithLSystem(bob, lsystem, 90, 10, 270)
            
if __name__ == '__main__':
    
    drawDragon4(9)
    #drawSnowflake(5)
    # wait for the user to close the window
    turtle.mainloop()
            
            
    