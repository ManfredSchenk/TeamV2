
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

import javax.swing.table.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Vector;
import java.util.Enumeration;
import java.io.*;
import java.beans.*;
import de.zerobyte.util.misc.*;
import de.zerobyte.util.develop.Diagnostic;

public class TeamKern extends AbstractTableModel implements TableModel,ListModel,ListSelectionListener {

  private Personendaten[] myPersonen;
  private boolean mygeschlechtbeachten=true;
  private boolean mywertungbeachten=true;
  private int myZaehler;
  private int myMaxZaehler=30;
  private int myTeamanzahl=2;
  private int myPersonenProTeam=1;
  private int myaktuellePerson=0;
  private Personendaten[][] myEinteilung;
  private Vector myListDataListeners=new Vector();
  private PropertyChangeSupport myPropertyChangeSupport=new PropertyChangeSupport(this);
  private int myaendernperson=0;
  public static final String ANZAHL="Anzahl";
  public static final String STATUS="Status";

  public TeamKern() {
    myPersonen=new Personendaten[30];
    myZaehler=0;
    myEinteilung=new Personendaten[myTeamanzahl][myPersonenProTeam];
    myEinteilung[0][0]=new Personendaten();
    myEinteilung[0][0].setName("keine Einteilung vorhanden");
  }
  public boolean getgeschlechtbeachten() {
    return mygeschlechtbeachten;
  }
  public boolean getwertungbeachten() {
    return mywertungbeachten;
  }
  public void setgeschlechtbeachten(boolean bo) {
    mygeschlechtbeachten=bo;
  }
  public void setwertungbeachten(boolean bo) {
    mywertungbeachten=bo;
  }
  public void personenspeichern(File file) {
  File datei;
    if (file.getName().endsWith(".team")) datei=file; else datei=new File(file.getAbsolutePath()+".team");

    try {
      FileOutputStream fos=new FileOutputStream(datei);
      ObjectOutputStream oos=new ObjectOutputStream(fos);
      oos.writeInt(myZaehler);
      oos.writeObject(myPersonen);
      oos.flush();
      oos.close();
      Diagnostic.println(Diagnostic.INFO,"TeamKern -personenspeichern: fertig");
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }

  }

  public void textspeichern(File file) {
    File datei;
    if (file.getName().endsWith(".txt")) datei=file; else datei=new File(file.getAbsolutePath()+".txt");
    try {
      FileOutputStream fos=new FileOutputStream(datei);
      DataOutputStream dos=new DataOutputStream(fos);
      dos.writeBytes("Teameinteilung erzeugt durch Team V 2beta\n\r");
      dos.writeBytes("(c) 2000 by ZERO-Byte (Manfred Schenk)   \n\r");
      dos.writeBytes("\n\r");
      dos.writeBytes("eingestellte Optionen:\n\r");
      if (mygeschlechtbeachten) dos.writeBytes("Geschlecht beruecksichtigen\n\r");
      if (mywertungbeachten) dos.writeBytes("Alter bzw. Faehigkeit beruecksichtigen\n\r");
      dos.writeBytes("\n\r");
      for (int i=0;i<getColumnCount();i++) {
        dos.writeBytes("Team "+(i+1)+":\n\r");
        for (int j=0;j<getRowCount();j++) {
          dos.writeBytes(getValueAt(j,i)+"\n\r");
        };
        dos.writeBytes("\n\r");
      };
      dos.flush();
      dos.close();
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
  }

  public void htmlspeichern(File file) {
    // Daten als HTML-Tabelle speichern
    File datei;
    if (file.getName().endsWith(".html")) datei=file; else datei=new File(file.getAbsolutePath()+".html");
    try {
      FileOutputStream fos=new FileOutputStream(datei);
      DataOutputStream dos=new DataOutputStream(fos);
      dos.writeBytes("<html>\r\n");
      dos.writeBytes("<head>\r\n");
      dos.writeBytes("<meta Name=\"Generator\" Content=\"TeamV2\">\r\n");
      dos.writeBytes("<title>Team-Einteilung-"+Long.toString(System.currentTimeMillis())+"</title>\r\n");
      dos.writeBytes("</head> \r\n");
      dos.writeBytes("<body bgcolor=\"#FFFFFF\">\r\n");
      dos.writeBytes("<h2>Optionen:</h2>\r\n");
      if (mygeschlechtbeachten) dos.writeBytes("Geschlecht ber&uuml;cksichtigen<P>\r\n");
      if (mywertungbeachten) dos.writeBytes("Alter bzw. F&auml;higkeit ber&uuml;cksichtigen<P>\r\n");
      dos.writeBytes("<h2>Teams:</h2>\r\n");
      dos.writeBytes("<table border=1 cellpadding=3 width=100%>\r\n");
      dos.writeBytes("<tr>\r\n");
      for (int i=0;i<getColumnCount();i++) {
        dos.writeBytes("<td>Team "+(i+1)+"</td>\r\n");
      };
      dos.writeBytes("</tr>\r\n");
      for (int j=0;j<getRowCount();j++) {

        dos.writeBytes("<tr>\r\n");
        for (int i=0;i<getColumnCount();i++) {
          dos.writeBytes("<td>"+getValueAt(j,i)+"</td>\r\n");
        }
        dos.writeBytes("</tr>\r\n");
      }
      dos.writeBytes("</table>\r\n");
      dos.writeBytes("<hr>\r\n");
      dos.writeBytes("erzeugt mit Team V 2beta von <A HREF=\"http://www.ZEROByte.de/\">ZERO-Byte</a>.");
      dos.flush();
      dos.close();
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }

  }

  public void personenladen(File file) {
    try {
      FileInputStream fis=new FileInputStream(file);
      ObjectInputStream ois=new ObjectInputStream(fis);
      myZaehler=ois.readInt();
      myPersonen=(Personendaten[])ois.readObject();

      myaendernperson=0;
      myMaxZaehler=myZaehler;
      myTeamanzahl=2;
      myPersonenProTeam=1;
      myaktuellePerson=0;
      myEinteilung=new Personendaten[1][1];
      myEinteilung[0][0]=new Personendaten();
      myEinteilung[0][0].setName("keine Einteilung vorhanden");
      ois.close();
      // jetzt die ListDataEvents verschicken
      Enumeration li=myListDataListeners.elements();
      if (li!=null) {
        while (li.hasMoreElements()) {
          ((ListDataListener)(li.nextElement())).contentsChanged(new ListDataEvent(this,ListDataEvent.CONTENTS_CHANGED,0,0));
        }
      }
      myPropertyChangeSupport.firePropertyChange(ANZAHL,0,myZaehler);
      Diagnostic.println(Diagnostic.INFO,"TeamKern -personenladen: fertig");
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public int getZaehler() {
    return myZaehler;
  }

  public Personendaten getDatenZumAendern(int nr) {
    myaendernperson=nr;
    return myPersonen[nr];
  }

  public void updatePerson(Personendaten p) {
    myPersonen[myaendernperson]=p;
  }
  public void addPersonendaten(Personendaten p) {
    myPersonen[myZaehler]=p;
    myZaehler++;
    if (myZaehler>=myMaxZaehler) {
      Personendaten[] dummy=new Personendaten[myMaxZaehler+10];
      System.arraycopy(myPersonen,0,dummy,0,myZaehler);
      myMaxZaehler+=10;
      myPersonen=dummy;
    }
    // jetzt die ListDataEvents verschicken
    Enumeration li=myListDataListeners.elements();
    if (li!=null) {
      while (li.hasMoreElements()) {
        ((ListDataListener)(li.nextElement())).contentsChanged(new ListDataEvent(this,ListDataEvent.CONTENTS_CHANGED,0,0));
      }
    }
    // jetzt PropertyChangeEvents verschicken
    myPropertyChangeSupport.firePropertyChange(ANZAHL,0,myZaehler);
  }

  public void removePersonendaten(int position) {
    if ((position<=0)&&(myZaehler==0)) {
      // nichts da zum löschen :(
    } else {
      if (myZaehler==1) {
        myZaehler=0;
      } else {
        myPersonen[position]=myPersonen[myZaehler-1];
        myZaehler--;
      }
      if (myMaxZaehler-myZaehler>30) {
        Personendaten[] dummy=new Personendaten[myMaxZaehler-10];
        System.arraycopy(myPersonen,0,dummy,0,myZaehler);
        myMaxZaehler-=10;
        myPersonen=dummy;
      }
      // jetzt die ListDataEvents verschicken
      Enumeration li=myListDataListeners.elements();
      if (li!=null) {
        while (li.hasMoreElements()) {
          ((ListDataListener)(li.nextElement())).contentsChanged(new ListDataEvent(this,ListDataEvent.CONTENTS_CHANGED,0,0));
        }
      }
      // jetzt die PropertyChangeEvents...
       myPropertyChangeSupport.firePropertyChange(ANZAHL,0,myZaehler);
    }
  }
  public void setTeamanzahl(int anz) {
    if (anz<=myZaehler) {
      myTeamanzahl=anz;
      // Werte fuer Table neu initialisieren
      myPersonenProTeam=1;
      myEinteilung=new Personendaten[myTeamanzahl][myPersonenProTeam];
      fireTableStructureChanged();
    }

  }

  public void einteilungerzeugen() {
    Diagnostic.println(Diagnostic.INFO,"TeamKern -einteilungerzeugen: Anfang");
    myPersonenProTeam=myZaehler/myTeamanzahl;
    if (myZaehler%myTeamanzahl!=0) myPersonenProTeam++;
    //benoetigte Daten:
    // Anzahl der Gruppen in myTeamanzahl
    // zu verteilende Personen in myPersonen
    // mygeschlechtbeachten & mywertungbeachten als Optionen
    myPropertyChangeSupport.firePropertyChange(STATUS,"","Erzeugung läuft...");
    myEinteilung=new Personendaten[myTeamanzahl][myPersonenProTeam];
    fireTableStructureChanged();

    Object[] workarray=null;
    Personendaten[] persons=new Personendaten[myZaehler];
    System.arraycopy(myPersonen,0,persons,0,myZaehler);
    // array zuerst kopieren, um die Originaldaten nicht zu veraendern
    // falls das geschlecht beachtet werden soll, muessen nun zwei getrennte Arrays
    // angelegt werden.
    Personendaten[] men,women;
    men=null;women=null;
    if (mygeschlechtbeachten) {
      Vector m=new Vector();
      Vector w=new Vector();
      for (int i=0;i<persons.length;i++) {
        if (persons[i].istMaennlich()) m.add(persons[i]); else w.add(persons[i]);
      };
      Enumeration e1=m.elements();
      men=new Personendaten[m.size()];
      women=new Personendaten[w.size()];
      int count=0;
      while (e1.hasMoreElements()) {
        men[count]=(Personendaten)(e1.nextElement());
        count++;
      };
      count=0;
      e1=w.elements();
      while (e1.hasMoreElements()) {
        women[count]=(Personendaten)(e1.nextElement());
        count++;
      };
      workarray=new Object[2];
      workarray[0]=men;
      workarray[1]=women;
    } else {
      workarray=new Object[1];
      workarray[0]=new Personendaten[myZaehler];
      System.arraycopy(persons,0,workarray[0],0,myZaehler);
    };
    int[] counter=new int[2];
    if (!mygeschlechtbeachten) counter[1]=0;
    for (int j=0;j<workarray.length;j++) {
      counter[j]=((Personendaten[])workarray[j]).length;
    };
    // falls die Wertung beachtet werden soll, muessen nun die arrays sortiert werden
    if (mywertungbeachten) {
      int[] order=new int[1];order[0]=0;

      for (int i=0;i<workarray.length;i++) {
        workarray[i]=ArrayUtils.reverse(ArrayUtils.sort((Personendaten[])workarray[i],order));
      }
    } else {
      // ansonsten zufaellig durcheinanderwuerfeln
      for (int i=0;i<workarray.length;i++) {
        workarray[i]=ArrayUtils.permutate((Personendaten[])workarray[i]);
      }
    }

    //hier beginnt die eigentliche Verteilung
    // Alle Personen muessen verteilt werden
    Personendaten pd=null;
    Object[] teams=new Object[myTeamanzahl];
    int flag=0;//welches Geschlecht ist als naechstes dran ?
    int nr=0;
    Personendaten[] pda=null;
    Object pdao=null;
    for (int i=0;i<myZaehler;i++) {
      Diagnostic.println(Diagnostic.INFO,"Schritt:"+i);
      if ((i % myTeamanzahl)==0) {
        Diagnostic.println(Diagnostic.INFO,"Wir permutieren");
        //vor jeder Runde die Teamreihenfolge permutieren und
        //vor jeder Verteilrunde bei Bedarf Geschlecht wechseln
        if ((workarray.length>0)&&(counter[1-flag]>0)) {
          flag=1-flag;
        };
        // teams mit den Werten 1..anzahl vorbelegen
        for (int j=0;j<myTeamanzahl;j++) {
          teams[j]=new Integer(j);
        };
        // neue Permutation
        teams=(Object[])(ArrayUtils.permutate(teams));
      }
      pdao=workarray[flag];
      pda=(Personendaten[])pdao;
      pd=pda[counter[flag]-1];
      counter[flag]--;
      if (counter[flag]==0) {
        flag=1-flag;
        // wenn von einem Geschlecht niemand mehr da, dann mit dem anderen auffüllen
      }

      // jetzt die Personendaten in das gewaehlte Team einfuegen:
      nr=0;
      while ((myEinteilung[((Integer)teams[i%myTeamanzahl]).intValue()][nr]!=null)&&(nr<myPersonenProTeam)) {
        nr++;
      }
      myEinteilung[((Integer)teams[i%myTeamanzahl]).intValue()][nr]=pd;




    }
    fireTableDataChanged();
    myPropertyChangeSupport.firePropertyChange(STATUS,"","Erzeugung beendet.");
    Diagnostic.println(Diagnostic.INFO,"TeamKern -einteilungerzeugen: fertig");
  }
  // Methoden fuer ListSelectionListener
  public void valueChanged(ListSelectionEvent e) {
      myaktuellePerson=((JList)e.getSource()).getSelectedIndex();
  }
  // Methoden fuer das Interface TableModel
  public String getColumnName(int col) {
    return new String("Gruppe "+(col+1));
  }

  public Object getValueAt(int row,int column) {
    //System.err.println("getValueAt("+row+","+column+") ");
    try {
      if (myEinteilung[column][row]!=null) {
        return (myEinteilung[column][row]).getName();
      } else {
        return "---";
      }
    } catch (ArrayIndexOutOfBoundsException aiobe) {
      return "---";
    }
  }
  public int getRowCount() {
    //System.err.println("getRowCount");
    return myPersonenProTeam;
  }
  public int getColumnCount() {
    //System.err.println("getColumnCount");
    return myTeamanzahl;
  }

  // Methoden fuer das Interface ListModel
  public int getSize() {
    return myZaehler;
  }

  public Object getElementAt(int pos) {
    return myPersonen[pos].getName();
  }

  public void addListDataListener(ListDataListener l) {
    myListDataListeners.add(l);
  }

  public void removeListDataListener(ListDataListener l) {
    myListDataListeners.remove(l);
  }
  public void addPropertyChangeListener(PropertyChangeListener l) {
    myPropertyChangeSupport.addPropertyChangeListener(l);
  }
  public void removePropertyChangeListener(PropertyChangeListener l) {
    myPropertyChangeSupport.removePropertyChangeListener(l);
  }

}
