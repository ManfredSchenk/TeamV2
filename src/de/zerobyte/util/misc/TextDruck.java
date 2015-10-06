
/**
 * Title:        Team 2 <p>
 * Description:  Gruppeneinteilungsprogramm für Ferienfreizeiten...
 * Version für Java 2 Plattform<p>
 * Copyright:    Copyright (c) Manfred Schenk<p>
 * Company:      <p>
 * @author Manfred Schenk
 * @version 1.0
 */
package de.zerobyte.util.misc;

import java.awt.*;
import java.awt.print.*;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import javax.swing.table.TableModel;

public class TextDruck implements Printable {
  private String kennung="Kennung fehlt ;-)";
  private PageFormat pformat=null;
  private TableModel datenquelle;
  private boolean geschlecht;
  private boolean alter;

  public void seiteeinrichten() {
    PrinterJob pjob=PrinterJob.getPrinterJob();
    if (pformat==null) pformat=pjob.defaultPage();
    pformat=pjob.pageDialog(pformat);
  }

  public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
    //TODO: Implement this java.awt.print.Printable method
    if (datenquelle.getColumnCount()<=pageIndex) return Printable.NO_SUCH_PAGE;
    // Wir drucken jede Spalte der JTable als eigene Seite
    Graphics2D g2d=(Graphics2D)graphics;
    g2d.translate(pageFormat.getImageableX(),pageFormat.getImageableY());
    int fontsize=(int)(pageFormat.getImageableHeight()/(8+datenquelle.getRowCount()));
    if (fontsize>25) fontsize=25;
    double seitenbreite=pageFormat.getImageableWidth();
    Font titelfont=new Font("Times",Font.BOLD,2*fontsize);
    Font namensfont=new Font("Helvetica",Font.PLAIN,fontsize);
    Font hinweisfont=new Font("Courier",Font.PLAIN,fontsize/2);
    g2d.setFont(titelfont);
    String titel=new String("TEAM "+(pageIndex+1)+":");
    int x=(int)((seitenbreite-g2d.getFontMetrics().stringWidth(titel))/2);
    int y=(int)(2*fontsize);
    g2d.drawString(titel,x,y);
    g2d.setFont(hinweisfont);
    String text="(erzeugt durch Team V2)";
    x=(int)((seitenbreite-g2d.getFontMetrics().stringWidth(text))/2);
    y+=fontsize/2;
    g2d.drawString(text,x,y);
    text="(c) 2000 by ZERO-Byte";
    x=(int)((seitenbreite-g2d.getFontMetrics().stringWidth(text))/2);
    y+=fontsize/2;
    g2d.drawString(text,x,y);
    text="http://www.ZEROByte.de/";
    x=(int)((seitenbreite-g2d.getFontMetrics().stringWidth(text))/2);
    y+=fontsize/2;
    g2d.drawString(text,x,y);
    g2d.setFont(namensfont);
    text="Erstellungs-ID : "+kennung;
    x=(int)((seitenbreite-g2d.getFontMetrics().stringWidth(text))/2);
    y+=fontsize;
    g2d.drawString(text,x,y);
    y+=fontsize/2;
    g2d.drawLine(0,y,(int)seitenbreite,y);
    // jetzt noch die eigentlichen Namen ausgeben
    for (int j=0;j<datenquelle.getRowCount();j++) {
      text=(String)datenquelle.getValueAt(j,pageIndex);
      x=(int)((seitenbreite-g2d.getFontMetrics().stringWidth(text))/2);
      y+=fontsize;
      g2d.drawString(text,x,y);
    }
    y=(int)(pageFormat.getImageableHeight()-2*fontsize);
    g2d.drawLine(0,y,(int)seitenbreite,y);
    g2d.setFont(hinweisfont);
    if ((alter)&&(geschlecht)) text="Geschlecht und Alter/Fähigkeit berücksichtigt.";
    if ((alter)&&(!geschlecht)) text="Alter/Fähigkeit berücksichtigt.";
    if ((!alter)&&(!geschlecht)) text="Zufällige Verteilung.";
    if ((!alter)&&(geschlecht)) text="Geschlecht berücksichtigt.";
    x=(int)((seitenbreite-g2d.getFontMetrics().stringWidth(text))/2);
    y+=fontsize/2;
    g2d.drawString(text,x,y);
    g2d.drawLine(0,y,(int)seitenbreite,y);
    // Page exists zurueckliefern
    return Printable.PAGE_EXISTS;
  }

  public void drucke(TableModel daten,String kenn,boolean gesch,boolean alt){
    geschlecht=gesch;
    alter=alt;
    kennung=kenn;
    datenquelle=daten;
    PrinterJob pjob=PrinterJob.getPrinterJob();
    if (pjob!=null) {
      if (pformat==null) pformat=pjob.defaultPage();
      pjob.setPrintable(this,pformat);
      if (pjob.printDialog()) {
        try {
          pjob.print();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }
}