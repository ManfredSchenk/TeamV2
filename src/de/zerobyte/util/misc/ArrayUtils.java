
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

import java.util.Random;
import java.lang.reflect.Array;
public class ArrayUtils {

private static Random rand;

static {
  // neu eingefügt, nur einmal neues Random-Objekt erzeugen
  rand=new Random(System.currentTimeMillis());
  };
public static Object reverse(Object[] source) {
  int length=source.length;
  if (length==0) return source;
  Object result=Array.newInstance((source[0]).getClass(),length);
  for (int i=0;i<length;i++) {
    Array.set(result,i,source[length-1-i]);
  }
  return result;
}
public static Object permutate(Object[] source) {
  //Random rand=new Random(System.currentTimeMillis());
  int length=source.length;
  if (length==0) return source;
  Object result=Array.newInstance((source[0]).getClass(),length);
  boolean[] alreadyused=new boolean[length];
  int takeno=0;
  for (int i=0;i<length;i++) {
    alreadyused[i]=false;
  }
  for (int i=0;i<length;i++) {
    takeno=(Math.abs(rand.nextInt()))%length;
    while (alreadyused[takeno]) {
      takeno=(Math.abs(rand.nextInt()))%length;
    }
    alreadyused[takeno]=true;
    Array.set(result,i,source[takeno]);
  }
  return result;
  }

  public static Sortable[] sort(Sortable[] source,int[] order) {
    QuickSort q=new QuickSort();
    return q.qsort(source,order);
  }
}