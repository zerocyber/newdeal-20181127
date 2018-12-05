package com.eomcs.util;

import java.util.Arrays;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;

public class ArrayList<T> {
  private static final int DEFAULT_CAPACITY = 10;
  private Object[] elementData;
  private int size = 0;
  
  public ArrayList() {
    elementData  = new Object[DEFAULT_CAPACITY];
  }
  
  public ArrayList(int initialCapacity) {
    if (initialCapacity > DEFAULT_CAPACITY) 
      elementData = new Object[initialCapacity];
    else
      elementData = new Object[DEFAULT_CAPACITY];
  }
  
  @SuppressWarnings("unchecked")
  public T[] toArray(T[] a) {
    if (a.length < size) {
      return (T[]) Arrays.copyOf(elementData, size, a.getClass());
    }
    System.arraycopy(elementData, 0, a, 0, size);
    if (a.length > size)
      a[size] = null;
    return a;
  }
  
  public void add(T obj) {
    if (size >= elementData.length) {
      int oldCapacity = elementData.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      elementData = Arrays.copyOf(elementData, newCapacity);
    }
    
    elementData[size++] = obj;
  }
  
  @SuppressWarnings("unchecked")
  public T get(int index) {
    if (index < 0 || index >= size) 
      return null;
    
    return (T) elementData[index];
  }

  public T set(int index, T obj) {
    if (index < 0 || index >= size)
      return null;
    
    @SuppressWarnings("unchecked")
    T old = (T)elementData[index];//이전 내용을 객체에 저장
    elementData[index] = obj; //기존 값을 Update

    return old; //이전 내용 반환
  }
  
  public T remove(int index) {
    if (index < 0 || index >= size)
      return null;
    
    @SuppressWarnings("unchecked")
    T old = (T)elementData[index];
    
    int newSize = size - 1;
    System.arraycopy(elementData, index + 1, elementData, index, newSize - index);
    elementData[size = newSize] = null;
    return old;
  }
  
  public int size() {
    return size;
  }
  
  public static void main(String[] args) {
/*    ArrayList<String> list = new ArrayList<>();
    
    list.add("aaa");
    list.add("bbb");
    list.add("ccc");
    list.add("ddd");
    list.add("eee");
    list.add("fff");
    list.add("ggg");
    
    System.out.println(list.size());
    
    System.out.println(list.remove(3));
    
    System.out.println(list.size());
    
    for (int i = 0; i < list.size(); i++) {
      System.out.println(list.get(i));
    }*/
    Scanner keyboard = new Scanner(System.in);
    
    String original = "기존에 있던 내용입니다.";
    System.out.println(original);
    
    System.out.printf("내용? ");
    String input = keyboard.nextLine();
    
    String old = original; // 바꿀 내용을 넣는다.
    
    original = input;
    
    System.out.println("old변수값 내용 : " + old);
    
    
  }
}
