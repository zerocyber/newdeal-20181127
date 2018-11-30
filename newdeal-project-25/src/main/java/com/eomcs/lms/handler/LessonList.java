package com.eomcs.lms.handler;

import java.util.Arrays;
import com.eomcs.lms.domain.Lesson;

public class LessonList {
  static final int LENGTH = 10;
  private Lesson[] list;
  private int size = 0;

  public LessonList() {
    this.list = new Lesson[LENGTH]; //this 생략하면 자동으로 this로 인식
  }

  public LessonList(int initialCapacity) {
    if(initialCapacity > LENGTH)
      this.list = new Lesson[initialCapacity];
    else
      this.list = new Lesson[LENGTH];
  }
  
  public Lesson[] toArray() {
    return Arrays.copyOf(list, size);
  }
  
  public void add(Lesson lesson) {
    if(size >= list.length) {
      int oldLength = list.length;
      int newCapacity = oldLength + oldLength >> 1;  // 1011 >>1 하면 101 -> 비트연산자 -> 맨오른쪽 비트를 하나 빼기
      list = Arrays.copyOf(list, newCapacity);
    }
    list[size++]= lesson;
  }
}
