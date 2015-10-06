
/**
 * Title:        Team 2 <p>
 * Description:  Gruppeneinteilungsprogramm für Ferienfreizeiten...
 * Version für Java 2 Plattform<p>
 * Copyright:    Copyright (c) Manfred Schenk<p>
 * Company:      <p>
 * @author Manfred Schenk
 * @version 1.0
 */
package de.zerobyte.apps.team2;

import javax.swing.filechooser.*;
import java.io.File;


public class TXTFilter extends FileFilter {

  public String getDescription() {
    //TODO: implement this javax.swing.filechooser.FileFilter abstract method
    return "nur .TXT Files";
  }

  public boolean accept(File parm1) {
    if (parm1.isDirectory()) return true;
     String name=parm1.getName();
    if (name.length()<5) return false;
    if (name.substring(name.length()-4).equalsIgnoreCase(".TXT")) return true; else return false;

    //TODO: implement this javax.swing.filechooser.FileFilter abstract method
  }
}