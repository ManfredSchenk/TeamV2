
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

import de.zerobyte.util.misc.*;
import java.io.Serializable;
public class Personendaten implements Sortable,Serializable {

  private String myName;
  private int myKriterium;
  private boolean myMaennlich;

  public boolean isGreaterThan(Sortable so,int[] order) {
    return (myKriterium>((Personendaten)so).getKriterium());
  }

  public boolean isequal(Sortable so,int order) {
    return (myKriterium==((Personendaten)so).getKriterium());
  }

  public Personendaten() {
    //Defaults setzen:
    myName="Niemand Ohnename";// :)
    myKriterium=0;// werden zuletzt verteilt
    myMaennlich=true;
  }

  public void setName(String name) {
    myName=name;
  }

  public void setKriterium(int kriterium) {
    myKriterium=kriterium;
  }

  public void setMaennlich() {
    myMaennlich=true;
  }

  public void setWeiblich() {
    myMaennlich=false;
  }

  public String getName() {
    return myName;
  }

  public int getKriterium() {
    return myKriterium;
  }

  public boolean istMaennlich() {
    return myMaennlich;
  }

}