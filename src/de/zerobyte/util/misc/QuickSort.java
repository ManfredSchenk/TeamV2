
package de.zerobyte.util.misc;

import java.lang.reflect.Array;
import java.util.Vector;
public class QuickSort {
public QuickSort ( ) {
	super();
}
public Sortable[] qsort(Sortable[] liste,int[] order) {
        if (liste.length>1) {
	  Sortable[] wert=(Sortable[])Array.newInstance(liste[0].getClass(),liste.length);
          //new Sortable[liste.length];
	  System.arraycopy(liste,0,wert,0,liste.length);
	  quicksort(wert,0,wert.length-1,order);
	  return wert;
        } else return liste;
}
public Sortable[] qsortV(Sortable[] so,int[] order) {
	if (so.length>1) {
          Vector daten=new Vector();
	  for (int i=0;i<so.length;i++) {
		daten.addElement(so[i]);
	  };
	  quicksortV(daten,0,so.length-1,order);
	  Sortable[] wert=(Sortable[])Array.newInstance(daten.elementAt(0).getClass(),daten.size());
          //new Sortable[daten.size()];
	  for (int i=0;i<daten.size();i++) {
		wert[i]=(Sortable)daten.elementAt(i);
	  };
	  return wert;
        } else return so;
}
private void quicksort(Sortable[] a,int lo0,int hi0,int[] order) {
	int lo=lo0;
	int hi=hi0;

	Sortable mid;

	if (hi0>lo0) {
		mid=a[(lo0+hi0)/2];
		while (lo<=hi) {
			while ((lo<hi0) && (a[lo].isGreaterThan(mid,order))) ++lo;
			while ((hi>lo0) && (mid.isGreaterThan(a[hi],order))) --hi;
			if (lo<=hi) {
				a=swap(a,lo,hi);
				++lo;
				--hi;
			};
			if (lo0<hi) quicksort(a,lo0,hi,order);
			if (lo<hi0) quicksort(a,lo,hi0,order);
		};
	};
}
private void quicksortV(Vector a,int lo0,int hi0,int[] order) {
	int lo=lo0;
	int hi=hi0;

	Sortable mid;

	if (hi0>lo0) {
		mid=(Sortable)a.elementAt((lo0+hi0)/2);
		while (lo<=hi) {
			while ((lo<hi0) && (((Sortable)(a.elementAt(lo))).isGreaterThan(mid,order))) ++lo;
			while ((hi>lo0) && (mid.isGreaterThan((Sortable)(a.elementAt(hi)),order))) --hi;
			if (lo<=hi) {
				a=swap(a,lo,hi);
				++lo;
				--hi;
			};
			if (lo0<hi) quicksortV(a,lo0,hi,order);
			if (lo<hi0) quicksortV(a,lo,hi0,order);
		};
	};
	return;
}
private Vector swap(Vector vec,int a,int b) {
	Object t;
	t=vec.elementAt(a);
	vec.setElementAt(vec.elementAt(b),a);
	vec.setElementAt(t,b);
	return vec;
}
private Sortable[] swap(Sortable[] a,int p1,int p2) {
	Sortable t;
	t=a[p1];
	a[p1]=a[p2];
	a[p2]=t;
	return a;
}
}