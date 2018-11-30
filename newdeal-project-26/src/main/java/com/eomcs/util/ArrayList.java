package com.eomcs.util;

import java.util.Arrays;
import com.eomcs.lms.domain.Board;

public class ArrayList<T> { // T -> Type의 의미, List의 경우는 E(Element)도 많이 쓴다
  final int DEFAULT_CAPACITY = 10;
  Object[] list;
  int size = 0;

  public ArrayList() {
    list  = new Board[DEFAULT_CAPACITY];
  }

  public ArrayList(int initialCapacity) {
    if (initialCapacity > DEFAULT_CAPACITY) 
      list = new Object[initialCapacity];
    else
      list = new Object[DEFAULT_CAPACITY];
  }

  @SuppressWarnings("unchecked") //warning 경고 띄우지 않기
  public T[] toArray(T[] a) {
    if(a.length < size) {
      return (T[])Arrays.copyOf(list, size, a.getClass()); 
      // a.getClass() =>배열의 클래스 정보 넘겨주기
    }
    
    System.arraycopy(list, 0, a, 0, size);
    
    if(a.length > size)
      a[size] = null;
    return a;
  }

  public void add(T item) {
    if (size >= list.length) {
      int oldCapacity = list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      list = Arrays.copyOf(list, newCapacity);
    }

    list[size++] = item;
  }
  
  public int size() {
    return this.size;
  }
}
