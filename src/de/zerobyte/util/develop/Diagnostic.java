
/**
 * Title:        Team 2 <p>
 * Description:  Gruppeneinteilungsprogramm für Ferienfreizeiten...
 * Version für Java 2 Plattform<p>
 * Copyright:    Copyright (c) Manfred Schenk<p>
 * Company:      <p>
 * @author Manfred Schenk
 * @version 1.0
 */
package de.zerobyte.util.develop;

import java.util.Properties;
import java.io.*;
public class Diagnostic {
  public final static int DETAIL=0;
  public final static int INFO=1;
  public final static int WARN=2;
  public final static int ERR=3;
  public final static int NONE=255;
  private static int warnlevel=255;

  static {
    String level=System.getProperty("de.zerobyte.util.develop.Diagnostic","NONE");
    if (level.equalsIgnoreCase("DETAIL")) warnlevel=DETAIL;
    if (level.equalsIgnoreCase("INFO")) warnlevel=INFO;
    if (level.equalsIgnoreCase("WARN")) warnlevel=WARN;
    if (level.equalsIgnoreCase("ERR")) warnlevel=ERR;
    if (level.equalsIgnoreCase("NONE")) warnlevel=NONE;
  }

  public static int getWarnLevel() {
    return warnlevel;
  }
  public static void printWarnLevel() {
    switch (warnlevel) {
      case DETAIL : System.err.println("Diagnostic: current WarnLevel: DETAIL");break;
      case INFO : System.err.println("Diagnostic: current WarnLevel: INFO");break;
      case WARN : System.err.println("Diagnostic: current WarnLevel: WARN");break;
      case ERR : System.err.println("Diagnostic: current Warnlevel: ERR");break;
      default: System.err.println("Diagnostic: current Warnlevel: NONE");
    }
  }
  public static void println(int level,String text) {
    if (level<warnlevel) return;
    if (level==DETAIL) System.err.println("DETAIL: "+text);
    if (level==INFO)   System.err.println("INFO  : "+text);
    if (level==WARN)   System.err.println("WARN  : "+text);
    if (level==ERR)    System.err.println("ERROR : "+text);
  }

}