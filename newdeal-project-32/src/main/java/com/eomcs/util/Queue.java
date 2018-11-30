package com.eomcs.util;

public class Queue<E> extends LinkedList<E> implements Cloneable {

  private int maxSize;

  public Queue() {
    maxSize = 100;
  }

  public Queue(int maxSize) {
    this.maxSize = maxSize;
  }

  @Override
  public Queue<E> clone() {
    Queue<E> temp = new Queue<>();
    for (int i = 0; i < size(); i++) {
      temp.add(get(i));
    }
    return (Queue<E>) temp;
  }

  public void offer(E value) {
    if (size() == maxSize)
      remove(0); // 꽉차면 맨 앞에 입력된 값을 제거한다.
    add(value);
  }

  public E poll() {
    if (size() > 0)
      return remove(0);
    return null;
  }

  public Iterator<E> iterator() {
    return new IteratorImpl<>();
  }


  //중첩 클래스 사용  - 클래스를 메소드처럼 사용 - 인스턴스 변수 접근 가능!
  class IteratorImpl<T> implements Iterator<T> {
    Queue<?> queue;
    int count;

    { // 인스턴스 블록 - 생성자가 실행되기 전에 먼저 생성되는 블록
      queue = Queue.this.clone(); //현재 클래스가 아닌 바깥 클래스 복제 후 현재 인스턴스 변수에 저장
      
    }

    @Override
    public boolean hasNext() {
      return this.count < Queue.this.size();
    }

    @Override
    public T next() {
      this.count++;
      return (T)queue.poll();
    }

  }




  /*
  public static void main(String[] args) throws Exception {
    Stack<String> stack = new Stack<>();
    stack.push("aaa");
    stack.push("bbb");
    stack.push("ccc");
    stack.push("ddd");
    stack.push("eee");
    stack.push("fff");

    Stack<String> temp1 = stack.clone();
    while (temp1.size() > 0) {
      System.out.println(temp1.pop());
    }
    System.out.println("----------------------");

    Stack<String> temp2 = stack.clone();
    while (temp2.size() > 0) {
      System.out.println(temp2.pop());
    }
  }*/
}
