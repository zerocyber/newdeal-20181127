package com.eomcs.lms.handler;

import java.util.Arrays;
import com.eomcs.lms.domain.Board;

public class BoardList {
  static final int LENGTH = 10;
  Board[] list;
  int size = 0;

  public BoardList() {
    this.list = new Board[LENGTH];
  }
  
  public BoardList(int initialCapacity) {
    if(initialCapacity > LENGTH)
      this.list =new Board[initialCapacity];
    else
      this.list= new Board[LENGTH];
  }

  public Board[] toArray() {
    return Arrays.copyOf(list, size);
  }

  public void addBoard(Board board) {
    if(size >= list.length) {
      int oldCapacity =  list.length;
      int newCapacity = oldCapacity + oldCapacity >>1;
      
      list = Arrays.copyOf(list, newCapacity);
    }
    list[size] = board;
    size++;
  }
}
