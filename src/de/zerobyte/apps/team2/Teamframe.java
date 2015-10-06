
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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.beans.*;
import java.io.File;
import de.zerobyte.util.develop.Diagnostic;
import de.zerobyte.util.misc.*;

public class Teamframe extends JFrame implements PropertyChangeListener {
  JPanel contentPane;
  JMenuBar menuBar1 = new JMenuBar();
  JMenu menuFile = new JMenu();
  JMenuItem menuFileExit = new JMenuItem();
  JMenu menuHelp = new JMenu();
  JMenuItem menuHelpAbout = new JMenuItem();
  BorderLayout borderLayout1 = new BorderLayout();
  JMenuItem jMenuItem1 = new JMenuItem();
  JMenuItem jMenuItem3 = new JMenuItem();
  JMenuItem jMenuItem5 = new JMenuItem();
  JMenu jMenu2 = new JMenu();
  JMenuItem jMenuItem4 = new JMenuItem();
  JMenuItem jMenuItem6 = new JMenuItem();
  JMenu jMenu3 = new JMenu();
  JMenuItem jMenuItem8 = new JMenuItem();
  JMenuItem jMenuItem12 = new JMenuItem();
  private JPanel jPanel1 = new JPanel();
  private JLabel jLabel1 = new JLabel();
  private JTextField personenzahl = new JTextField();
  private TeamKern teamKern1 = new TeamKern();
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();
  private JButton jButton3 = new JButton();
  private JSplitPane jSplitPane1 = new JSplitPane();
  private JScrollPane jScrollPane1 = new JScrollPane();
  private JTable jTable1 = new JTable();
  private JDialog personeneingabe = new JDialog();
  private JLabel jLabel2 = new JLabel();
  private JLabel jLabel3 = new JLabel();
  private JLabel jLabel4 = new JLabel();
  private JTextField jTextField1 = new JTextField();
  private JTextField jTextField3 = new JTextField();
  private JPanel jPanel3 = new JPanel();
  private GridLayout gridLayout2 = new GridLayout();
  private BorderLayout borderLayout2 = new BorderLayout();
  private JPanel jPanel4 = new JPanel();
  private JButton jButton4 = new JButton();
  private JButton jButton5 = new JButton();
  private JCheckBox jCheckBox1 = new JCheckBox();
  private JScrollPane jScrollPane2 = new JScrollPane();
  private JList jList1 = new JList();
  private JButton jButton6 = new JButton();
  private JButton jButton7 = new JButton();
  private JDialog personeneingabe1 = new JDialog();
  private JCheckBox jCheckBox2 = new JCheckBox();
  private JPanel jPanel5 = new JPanel();
  private JPanel jPanel6 = new JPanel();
  private GridLayout gridLayout3 = new GridLayout();
  private JTextField jTextField4 = new JTextField();
  private JTextField jTextField2 = new JTextField();
  private BorderLayout borderLayout3 = new BorderLayout();
  private JLabel jLabel5 = new JLabel();
  private JLabel jLabel6 = new JLabel();
  private JLabel jLabel7 = new JLabel();
  private JDialog personenaendern = new JDialog();
  private JCheckBox geschlechtaendern = new JCheckBox();
  private JPanel jPanel7 = new JPanel();
  private GridLayout gridLayout4 = new GridLayout();
  private JTextField bewertungaendern = new JTextField();
  private JTextField nameaendern = new JTextField();
  private JLabel jLabel8 = new JLabel();
  private JLabel jLabel9 = new JLabel();
  private JLabel jLabel10 = new JLabel();
  private BorderLayout borderLayout4 = new BorderLayout();
  private JButton jButton8 = new JButton();
  private JButton jButton9 = new JButton();
  private JPanel jPanel8 = new JPanel();
  private JDialog jDialog1 = new JDialog();
  private JPanel jPanel2 = new JPanel();
  private JButton jButton10 = new JButton();
  private JButton jButton11 = new JButton();
  private JLabel jLabel11 = new JLabel();
  private JFileChooser personenspeichern = new JFileChooser();
  private JFileChooser personenladen = new JFileChooser();
  private JFileChooser einteilungspeichern = new JFileChooser();
  private JTextField jTextField5 = new JTextField();
  private JLabel jLabel12 = new JLabel();
  private JDialog teamanzahlaendern = new JDialog();
  private JPanel jPanel9 = new JPanel();
  private JLabel jLabel13 = new JLabel();
  private JTextField teamanzahl = new JTextField();
  private FlowLayout flowLayout1 = new FlowLayout();
  private JPanel jPanel10 = new JPanel();
  private JButton jButton12 = new JButton();
  private JButton jButton13 = new JButton();
  private JMenu jMenu1 = new JMenu();
  private JCheckBoxMenuItem jCheckBoxMenuItem1 = new JCheckBoxMenuItem();
  private JCheckBoxMenuItem jCheckBoxMenuItem2 = new JCheckBoxMenuItem();
  private HTMLFilter hTMLFilter1 = new HTMLFilter();
  private LaTeXFilter laTeXFilter1 = new LaTeXFilter();
  private TeamFilter teamFilter1 = new TeamFilter();
  private TXTFilter tXTFilter1 = new TXTFilter();
  private TextDruck textDruck1 = new TextDruck();
  private JMenuItem jMenuItem2 = new JMenuItem();

  //Construct the frame
  public Teamframe() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }



  public void propertyChange(PropertyChangeEvent pe) {
    String s=pe.getPropertyName();
    if (s.equals(TeamKern.ANZAHL)) {
      // Textfield "Anzahl" aktualisieren und evtl. Menueintraege (de-)aktivieren
      if (Integer.parseInt(pe.getNewValue().toString())>0) {
        jMenuItem12.setEnabled(true);
        jMenuItem3.setEnabled(true);
      } else {
        jMenuItem12.setEnabled(false);
        jMenuItem3.setEnabled(false);
      }
      personenzahl.setText("> "+pe.getNewValue().toString()+" <");

    }
    if (s.equals(TeamKern.STATUS)) {
      // Textfield "Status" aktualisieren
      jTextField5.setText(pe.getNewValue().toString());

    };
  }


  //Component initialization
  private void jbInit() throws Exception  {
    teamKern1.addPropertyChangeListener(this);
    jMenuItem1.setToolTipText("Daten der zu verteilenden Personen laden");
    jMenuItem1.setMnemonic('L');
    jMenuItem1.setText("Personendaten laden...");
    jMenuItem1.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        jMenuItem1_actionPerformed(e);
      }
    });
    jMenuItem3.setEnabled(false);
    jMenuItem3.setToolTipText("Liste der zu verteilenden Personen speichern");
    jMenuItem3.setMnemonic('S');
    jMenuItem3.setText("Personendaten speichern...");
    jMenuItem3.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        jMenuItem3_actionPerformed(e);
      }
    });
    jMenuItem5.setToolTipText("Gruppeneinteilung ausdrucken");
    jMenuItem5.setMnemonic('D');
    jMenuItem5.setText("Gruppeneinteilung drucken...");
    jMenuItem5.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        jMenuItem5_actionPerformed(e);
      }
    });
    jMenu2.setMnemonic('G');
    jMenu2.setText("Gruppeneinteilung speichern...");
    jMenuItem4.setToolTipText("Die erstellte Gruppeneinteilung als ASCII-Text speichern");
    jMenuItem4.setMnemonic('T');
    jMenuItem4.setText("als Text...");
    jMenuItem4.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        jMenuItem4_actionPerformed(e);
      }
    });
    jMenuItem6.setToolTipText("Die erstellte Gruppeneinteilung als HTML-Datei speichern");
    jMenuItem6.setMnemonic('H');
    jMenuItem6.setText("als HTML...");
    jMenuItem6.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        jMenuItem6_actionPerformed(e);
      }
    });
    jMenu3.setMnemonic('O');
    jMenu3.setText("Optionen");
    jMenuItem8.setToolTipText("Anzahl der zu erzeugenden Gruppen festlegen");
    jMenuItem8.setMnemonic('G');
    jMenuItem8.setText("Gruppenanzahl...");
    jMenuItem8.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        jMenuItem8_actionPerformed(e);
      }
    });
    jMenuItem12.setEnabled(false);
    jMenuItem12.setMnemonic('N');
    jMenuItem12.setText("Neue Einteilung erzeugen");
    jMenuItem12.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        jMenuItem12_actionPerformed(e);
      }
    });
    menuFileExit.setToolTipText("Programm beenden");
    menuFileExit.setMnemonic('E');
    menuHelpAbout.setToolTipText("Informationen über das Programm");
    menuHelpAbout.setMnemonic('B');
    jLabel1.setText("Anzahl der Personen:");
    personenzahl.setEditable(false);
    personenzahl.setText("> 00 <");
    personenzahl.setColumns(4);
    jButton1.setToolTipText("die ausgewählten Personendaten ändern");
    jButton1.setText("Person ändern");
    jButton1.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    jButton2.setToolTipText("Die ausgewählte Persondaten löschen");
    jButton2.setText("Person löschen");
    jButton2.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    jButton3.setToolTipText("neue Personendaten eingeben");
    jButton3.setText("neue Person");
    jButton3.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        jButton3_actionPerformed(e);
      }
    });
    jSplitPane1.setRightComponent(jScrollPane1);
    jScrollPane1.setToolTipText("erzeugte Gruppeneinteilung");
    jTable1.setModel(teamKern1);
    personeneingabe.setTitle("Neue Person eingeben...");
    personeneingabe.getContentPane().setLayout(borderLayout2);
    jLabel2.setText("Name:");
    jLabel3.setText("Geschlecht:");
    jLabel4.setText("Alter bzw. Bewertung:");
    jPanel3.setLayout(gridLayout2);
    gridLayout2.setColumns(2);
    gridLayout2.setRows(3);
    jButton4.setText("Okay");
    jButton4.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        jButton4_actionPerformed(e);
      }
    });
    jButton5.setText("Abbrechen");
    jButton5.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        jButton5_actionPerformed(e);
      }
    });
    jCheckBox1.setToolTipText("Wenn männlich, dann auswählen");
    jCheckBox1.setText("männlich");
    jScrollPane2.setToolTipText("");
    jList1.setToolTipText("zu verteilende Personen");
    jList1.setModel(teamKern1);
    jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    menuFile.setMnemonic('D');
    menuHelp.setMnemonic('H');
    jButton6.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        jButton6_actionPerformed(e);
      }
    });
    jButton6.setText("Abbrechen");
    jButton7.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        jButton7_actionPerformed(e);
      }
    });
    jButton7.setText("Okay");
    personeneingabe1.setTitle("Neue Person eingeben...");
    jCheckBox2.setToolTipText("Wenn männlich, dann auswählen");
    jCheckBox2.setText("männlich");
    jPanel6.setLayout(gridLayout3);
    gridLayout3.setColumns(2);
    gridLayout3.setRows(3);
    jLabel5.setText("Alter bzw. Bewertung:");
    jLabel6.setText("Geschlecht:");
    jLabel7.setText("Name:");
    geschlechtaendern.setText("männlich");
    geschlechtaendern.setToolTipText("Wenn männlich, dann auswählen");
    jPanel7.setLayout(gridLayout4);
    gridLayout4.setColumns(2);
    gridLayout4.setRows(3);
    jLabel8.setText("Alter bzw. Bewertung:");
    jLabel9.setText("Geschlecht:");
    jLabel10.setText("Name:");
    personenaendern.getContentPane().setLayout(borderLayout4);
    jButton8.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        jButton8_actionPerformed(e);
      }
    });
    jButton8.setText("Abbrechen");
    jButton9.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        jButton9_actionPerformed(e);
      }
    });
    jButton9.setText("Okay");
    personenaendern.setTitle("Personendaten ändern");
    personenaendern.setModal(true);
    jButton10.setText("Abbrechen");
    jButton10.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        jButton10_actionPerformed(e);
      }
    });
    jButton11.setText("Okay");
    jButton11.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        jButton11_actionPerformed(e);
      }
    });
    jLabel11.setFont(new java.awt.Font("Dialog", 0, 18));
    jLabel11.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel11.setText("Selektierte Person wirklich löschen ?");
    jDialog1.setTitle("Personendaten löschen");
    jDialog1.setModal(true);
    personenspeichern.setDialogTitle("Personendaten speichern unter...");
    personenspeichern.setFileFilter(teamFilter1);
    personenladen.setToolTipText("Personendaten aus einer Datei laden");
    personenladen.setDialogTitle("Personendaten laden...");
    personenladen.setFileFilter(teamFilter1);
    einteilungspeichern.setDialogTitle("Gruppeneinteilung speichern unter...");
    jTextField5.setToolTipText("Statusanzeige");
    jTextField5.setEditable(false);
    jTextField5.setText("unbekannt");
    jTextField5.setColumns(18);
    jLabel12.setText("Status:");

    teamanzahlaendern.setTitle("Teamanzahl festlegen...");
    jPanel9.setLayout(flowLayout1);
    jLabel13.setText("Gewünschte Teamanzahl:");
    teamanzahl.setSelectionEnd(15);
    teamanzahl.setColumns(15);
    jButton12.setText("Abbrechen");
    jButton12.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        jButton12_actionPerformed(e);
      }
    });
    jButton13.setText("Okay");
    jButton13.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        jButton13_actionPerformed(e);
      }
    });
    jMenu1.setMnemonic('V');
    jMenu1.setText("Verteilungskriterien...");
    jCheckBoxMenuItem1.setToolTipText("Soll das Geschlecht der Person bei der Einteilung berücksichtigt " +
    "werden?");
    jCheckBoxMenuItem1.setSelected(true);
    jCheckBoxMenuItem1.setText("Geschlecht beachten");
    jCheckBoxMenuItem1.addItemListener(new java.awt.event.ItemListener() {

      public void itemStateChanged(ItemEvent e) {
        jCheckBoxMenuItem1_itemStateChanged(e);
      }
    });
    jCheckBoxMenuItem2.setToolTipText("Soll das Alter bzw. die angegebene Bewertung berücksichtigt werden " +
    "?");
    jCheckBoxMenuItem2.setSelected(true);
    jCheckBoxMenuItem2.setText("Alter/Kriterium beachten");
    jCheckBoxMenuItem2.addItemListener(new java.awt.event.ItemListener() {

      public void itemStateChanged(ItemEvent e) {
        jCheckBoxMenuItem2_itemStateChanged(e);
      }
    });
    jMenuItem2.setToolTipText("Seite zum Drucken einrichten");
    jMenuItem2.setText("Seite einrichten...");
    jMenuItem2.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        jMenuItem2_actionPerformed(e);
      }
    });
    menuFile.add(jMenuItem1);
    menuFile.add(jMenuItem3);
    menuFile.addSeparator();
    menuFile.add(jMenuItem12);
    menuFile.add(jMenu2);
    menuFile.add(jMenuItem2);
    menuFile.add(jMenuItem5);
    menuFile.addSeparator();
    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(borderLayout1);
    contentPane.setPreferredSize(new Dimension(936, 459));

    this.setSize(new Dimension(959, 626));
    this.setTitle("Team V 2beta2  -  (c) 2000,2001 by ZERO-Byte  -  http://www.ZEROByte.de/");
    menuFile.setText("Datei");
    menuFileExit.setText("Beenden");
    menuFileExit.addActionListener(new ActionListener()  {

      public void actionPerformed(ActionEvent e) {
        fileExit_actionPerformed(e);
      }
    });
    menuHelp.setText("Hilfe");
    menuHelpAbout.setText("Über");
    menuHelpAbout.addActionListener(new ActionListener()  {

      public void actionPerformed(ActionEvent e) {
        helpAbout_actionPerformed(e);
      }
    });
    menuFile.add(menuFileExit);
    menuHelp.add(menuHelpAbout);
    menuBar1.add(menuFile);
    this.setJMenuBar(menuBar1);
    menuBar1.add(jMenu3);
    menuBar1.add(menuHelp);
    jMenu2.add(jMenuItem4);
    jMenu2.add(jMenuItem6);
    jMenu3.add(jMenuItem8);
    jMenu3.add(jMenu1);
    contentPane.add(jPanel1, BorderLayout.SOUTH);
    jPanel1.add(jLabel12, null);
    jPanel1.add(jTextField5, null);
    jPanel1.add(jButton3, null);
    jPanel1.add(jButton1, null);
    jPanel1.add(jButton2, null);
    jPanel1.add(jLabel1, null);
    jPanel1.add(personenzahl, null);
    contentPane.add(jSplitPane1, BorderLayout.CENTER);
    jSplitPane1.add(jScrollPane1, JSplitPane.RIGHT);
    jSplitPane1.add(jScrollPane2, JSplitPane.LEFT);
    jScrollPane2.getViewport().add(jList1, null);
    jScrollPane1.getViewport().add(jTable1, null);
    personeneingabe.getContentPane().add(jPanel3, BorderLayout.CENTER);
    jPanel3.add(jLabel2, null);
    jPanel3.add(jTextField1, null);
    jPanel3.add(jLabel3, null);
    jPanel3.add(jCheckBox1, null);
    jPanel3.add(jLabel4, null);
    jPanel3.add(jTextField3, null);
    personeneingabe.getContentPane().add(jPanel4, BorderLayout.SOUTH);
    jPanel4.add(jButton4, null);
    jPanel4.add(jButton5, null);
    personeneingabe1.getContentPane().add(jPanel6, BorderLayout.CENTER);
    personeneingabe1.getContentPane().add(jPanel5, BorderLayout.SOUTH);
    jPanel5.add(jButton7, null);
    jPanel5.add(jButton6, null);
    jPanel6.add(jLabel7, null);
    jPanel6.add(jTextField2, null);
    jPanel6.add(jLabel6, null);
    jPanel6.add(jCheckBox2, null);
    jPanel6.add(jLabel5, null);
    jPanel6.add(jTextField4, null);
    personenaendern.getContentPane().add(jPanel7, BorderLayout.CENTER);
    jPanel7.add(jLabel10, null);
    jPanel7.add(nameaendern, null);
    jPanel7.add(jLabel9, null);
    jPanel7.add(geschlechtaendern, null);
    jPanel7.add(jLabel8, null);
    jPanel7.add(bewertungaendern, null);
    personenaendern.getContentPane().add(jPanel8, BorderLayout.SOUTH);
    jPanel8.add(jButton9, null);
    jPanel8.add(jButton8, null);
    jDialog1.getContentPane().add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(jButton11, null);
    jPanel2.add(jButton10, null);
    jDialog1.getContentPane().add(jLabel11, BorderLayout.CENTER);
    teamanzahlaendern.getContentPane().add(jPanel9, BorderLayout.CENTER);
    jPanel9.add(jLabel13, null);
    jPanel9.add(teamanzahl, null);
    teamanzahlaendern.getContentPane().add(jPanel10, BorderLayout.SOUTH);
    jPanel10.add(jButton13, null);
    jPanel10.add(jButton12, null);
    jMenu1.add(jCheckBoxMenuItem1);
    jMenu1.add(jCheckBoxMenuItem2);
  }

  //File | Exit action performed
  public void fileExit_actionPerformed(ActionEvent e) {
    System.exit(0);
  }

  //Help | About action performed
  public void helpAbout_actionPerformed(ActionEvent e) {
    Teamframe_AboutBox dlg = new Teamframe_AboutBox(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.show();
  }

  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      fileExit_actionPerformed(null);
    }
  }

  void jButton3_actionPerformed(ActionEvent e) {
    personeneingabe.pack();
    personeneingabe.show();
  }

  void jButton5_actionPerformed(ActionEvent e) {
    //Abbrechen bei Dialog Eingabe
    personeneingabe.dispose();
  }

  void jButton4_actionPerformed(ActionEvent e) {
    //Okay bei Dialog Eingabe
    String name=jTextField1.getText();
    boolean m=jCheckBox1.isSelected();
    String wert=jTextField3.getText();
    try {
      Personendaten pd=new Personendaten();
      pd.setName(name);
      if (m) pd.setMaennlich(); else pd.setWeiblich();
      pd.setKriterium(Integer.parseInt(wert));
      teamKern1.addPersonendaten(pd);
    } catch (Exception ex) {
      // vorläufig nichts tun
    }
    personeneingabe.dispose();

  }

  void jButton6_actionPerformed(ActionEvent e) {

  }

  void jButton7_actionPerformed(ActionEvent e) {

  }

  void jButton8_actionPerformed(ActionEvent e) {
    //Personenändern- Abbrechen
    personenaendern.dispose();
  }

  void jButton9_actionPerformed(ActionEvent e) {
    //personenändern-Okay
    // zuerst die Werte updaten,
    String name=nameaendern.getText();
    boolean m=geschlechtaendern.isSelected();
    String wert=bewertungaendern.getText();
    try {
      Personendaten pd=new Personendaten();
      pd.setName(name);
      if (m) pd.setMaennlich(); else pd.setWeiblich();
      pd.setKriterium(Integer.parseInt(wert));
      teamKern1.updatePerson(pd);
    } catch (Exception ex) {
      // vorläufig nichts tun
    }
    // danach Dialog entfernen
    personenaendern.dispose();

  }

  void jButton1_actionPerformed(ActionEvent e) {
    // zuerst die entsprechenden Werte setzen
    int nr=jList1.getSelectedIndex();
    Personendaten pd=teamKern1.getDatenZumAendern(nr);
    nameaendern.setText(pd.getName());
    geschlechtaendern.setSelected(pd.istMaennlich());
    bewertungaendern.setText(""+pd.getKriterium());
    personenaendern.pack();
    personenaendern.show();
  }

  void jButton10_actionPerformed(ActionEvent e) {
    jDialog1.dispose();
  }

  void jButton11_actionPerformed(ActionEvent e) {
    int idx=jList1.getSelectedIndex();
    if (idx!=-1) teamKern1.removePersonendaten(idx);
    jDialog1.dispose();
  }

  void jButton2_actionPerformed(ActionEvent e) {
    jDialog1.pack();
    jDialog1.show();
  }

  void jMenuItem3_actionPerformed(ActionEvent e) {
    // Personendaten-speichern
    File datei=null;
    if (personenspeichern.showSaveDialog(this)==JFileChooser.APPROVE_OPTION) {
      datei=personenspeichern.getSelectedFile();
      // jetzt File an teamKern1 übergeben, damit gespeichert werden kann
      teamKern1.personenspeichern(datei);

    }
  }

  void jMenuItem1_actionPerformed(ActionEvent e) {
    // Personendaten-laden
    File datei=null;
    if (personenladen.showOpenDialog(this)==JFileChooser.APPROVE_OPTION) {
      datei=personenladen.getSelectedFile();
      teamKern1.personenladen(datei);
    }
  }

  void jMenuItem12_actionPerformed(ActionEvent e) {
    teamKern1.einteilungerzeugen();
  }

  void jMenuItem8_actionPerformed(ActionEvent e) {
    // Dialogfenster Gruppenanzahl anzeigen
    teamanzahlaendern.pack();
    teamanzahlaendern.show();
  }

  void jButton12_actionPerformed(ActionEvent e) {
    teamanzahlaendern.dispose();
  }

  void jButton13_actionPerformed(ActionEvent e) {
    String anz=teamanzahl.getText();
    teamanzahlaendern.dispose();
    int an=0;
    try {
      an=Integer.parseInt(anz);
      Diagnostic.println(Diagnostic.INFO,"neue Teamanzahl :"+an);
    } catch (NumberFormatException nfe) {
      an=0;
    }
    if (an>0) teamKern1.setTeamanzahl(an);

  }

  void jCheckBoxMenuItem2_itemStateChanged(ItemEvent e) {
    if (jCheckBoxMenuItem2.isSelected()) {
      teamKern1.setwertungbeachten(true);
    } else {
      teamKern1.setwertungbeachten(false);
    }
  }

  void jCheckBoxMenuItem1_itemStateChanged(ItemEvent e) {
    // Geschlecht beachten ?
    if (jCheckBoxMenuItem1.isSelected()) {
      teamKern1.setgeschlechtbeachten(true);
    } else {
      teamKern1.setgeschlechtbeachten(false);
    }
  }

  void jMenuItem2_actionPerformed(ActionEvent e) {
      textDruck1.seiteeinrichten();
  }

  void jMenuItem5_actionPerformed(ActionEvent e) {
      textDruck1.drucke(teamKern1,Long.toString(System.currentTimeMillis()),teamKern1.getgeschlechtbeachten(),teamKern1.getwertungbeachten());
  }

  void jMenuItem4_actionPerformed(ActionEvent e) {
      // als Text speichern
      File datei=null;
      einteilungspeichern.setFileFilter(tXTFilter1);
      if (einteilungspeichern.showSaveDialog(this)==JFileChooser.APPROVE_OPTION) {
        datei=einteilungspeichern.getSelectedFile();
        teamKern1.textspeichern(datei);
      };
  }

  void jMenuItem6_actionPerformed(ActionEvent e) {
    // als HTML speichern
    File datei=null;
    einteilungspeichern.setFileFilter(hTMLFilter1);
      if (einteilungspeichern.showSaveDialog(this)==JFileChooser.APPROVE_OPTION) {
        datei=einteilungspeichern.getSelectedFile();
        teamKern1.htmlspeichern(datei);
      };

  }

}