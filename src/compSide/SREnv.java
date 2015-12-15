package compSide;

import jason.asSyntax.*;
import jason.environment.Environment;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;
import java.util.logging.Logger;

public class SREnv extends Environment {
  
  //Jason String Literals
  public static final Term mvFwd = Literal.parseLiteral("moveForward()")  ;
  public static final Term explore = Literal.parseLiteral("explore()")    ;
  //Boolean to tell when robot has finished exploring
  public static boolean explored = false                                  ;
  public static SRComms src                                               ;
  public static nxtBTAddress                                              ;
  
  
  @Override 
  public void init(String[] args){
    
    //Data Output Stream to send data to the robot
    DataOutPutStream dos = new DataOutputStream(conn.getOutputStream());
    //Input stream to take from the robot
    dis = new DataInputStream(conn.getInputStream());
    //New instance of SRComms, creating the BT connection
     src = new SRComms("LEGOBOT-06", nxtBTAddress);
  }
  @Override 
  public boolean executeAction(Strin ag, Structure action){
    try{
        if(action.equals(explore){
          src.sendBluetooth("explore()");
          if(explore){
            addPercept("scout", Literal.parseLiteral("explored()")
          }
        }
        else if(action.equals(mvFwd){
          src.sendBluetooth("moveForward()");
        }
    }catch(Exception e){return false;} 
    
  }
  void addVictim(int pos[], int colour){
    
    addPercept("scout", Literal.parseLiteral("victim( " + colour + ", " + pos[0] + " , " + pos[1] + ")"");
    
  }
}
