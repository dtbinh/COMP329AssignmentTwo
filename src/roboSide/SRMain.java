package roboSide;

import jason.asSyntax.*;
import jason.environment.Environment;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Random;
import java.util.logging.Logger;

import lejos.nxt.*;
import lejos.nxt.addon.ColorHTSensor;
import lejos.pc.comm.NXTConnector;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.util.Delay;


public class SRMain {
  
  //global variables here
    //constants
  public static final int DEGREE  = 108;
  public static final int COLUMNS = 4  ;
  public static final int ROWS    = 5  ;
  public static final int DIST    = 25 ;
  
    //Jason String Literals
  public static final Term explore   = Literal.parseLiteral("explore()");
  public static final Term outputMap = Literal.parseLiteral("getMap()");
  public static final Term mvToDest  = Literal.parseLiteral("");
  public static Logger log = Logger.getLogger(SRMain.class.getName());

    //object/variable creation
  public static SRModel         modelObj  ;
  public static SRMov           movObj    ;
  public static BtStuff         btObj     ;
  public static SRInput         inputObj  ;
  public static Thread          btThread  ;
  public static NXTConnection   connObj   ; //holds the type of connection (BT)
  public static DataInputStream dis       ;
  public static Queue<String>   cmdList   ;
  public static String          currentCmd;




  
  //methods to connect with SREnv and obtain commands
  
  /*
   * Information required: 
   * start BT connection
   * get the explore command
   * output the list of victims
   * get the command to move to a particular cell (red victim)
   * moveTo(x, y, modelObj);
   * boolean hasVic = pickUpVictim();
   * moveTo(0, 0, modelObj);
   */
  
  SRMain() {
    movObj   = new SRMov(int DIST, int COLUMNS, int ROWS, int DEGREE);
    modelObj = new SRModel(COLUMNS, ROWS, movObj);
    connObj  = Bluetooth.waitForConnection();
    
    cmdList  = new Queue<String>;
    inputObj = new SRInput(connObj);
    btObj    = new BtStuff(connObj);
    
    btThread = new Thread(btObj);
    btThread.setDaemon(true);
    btThread.start();
  }
  
  public static void main(String[] args) {
    try{
      while(true) {
        if(inputObj.cmdList.empty() ) { //if there were no commands sent
          
        } else { //if there are commands in the queue waiting to be called
          currentCmd = inputObj.getCurrentCmd();
          if(currentCmd.equals("explore()") ) {
            explore();
            
          }
        }
      }
    } catch (Exception e) {
      
    }
  }
  
  public static int[][] explore() {
    //--I AM HERE--
    
    //boolean res = modelObj.scanAhead();
    //String colOutput = modelObj.getColour();
    //if(!colOutput.equals(NULL) ) {
      //btObj.sendString(colOutput);
    }
    
    addVictim(modelObj.getColour() );
    
    
    return modelObj.map;
  }
  
  public static void addVictim(String s) {
    btObj.vicList.push(s);
    
  }
  
}
