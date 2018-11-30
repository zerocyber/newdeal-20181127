package com.eomcs.util;

public class StackIterator<E> implements Iterator<E> {

  Stack<E> stack;
  int size; //값을 꺼내면 사라지기 때문에 처음 사이즈를 갖고 있어야 반복할때 문제가 생기지 않는다.
  int count;
  
  public StackIterator (Stack<E> stack){
    this.stack = stack;
    this.size = stack.size();
  }
  
  @Override
  public boolean hasNext() {
    return this.count < this.size; 
  }

  @Override
  public E next() {
    this.count++;
    return stack.pop();
  }

  
}
