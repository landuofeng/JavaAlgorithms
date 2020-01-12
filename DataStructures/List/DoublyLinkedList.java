package DataStructures.List;

/**
 * 这个类实现一个双链表，操作 int 类型数据。
 */
public class DoublyLinkedList {
    /**
     * 保存链表头结点的引用
     */
    private Dlnode head;
    /**
     * 保存链表尾结点的引用
     */
    private Dlnode tial;

    /**
     * 默认构造函数
     */
    public DoublyLinkedList() {
        this.head = null;
        this.tial = null;
    }

    /**
     * 将一个数组转化位链表
     * @param arr 提供一个数组
     */
    public DoublyLinkedList(int[] arr) {
        Dlnode dlnode = new Dlnode(0);
        head = dlnode;
        for (int i = 0; i < arr.length; i++) {
            insertTial(arr[i]);
        }
        deleteHead();
    }

    /**
     * 插入元素于链表首部
     * @param x 作为新结点得值插入双列表得表首
     */
    public void insertHead(int x) {
        Dlnode dlnode = new Dlnode(x);
        if (isEmpty()) {
            this.tial = dlnode;
        } else {
            this.head.previous = dlnode;
        }
        dlnode.next = head;
        head = dlnode;
    }

    /**
     * 插入袁术雨链表位部
     * @param x 是插入新节点的值
     */
    public void insertTial(int x) {
        Dlnode dlnode = new Dlnode(x);
        if (isEmpty()) {
            this.head = dlnode;
        } else {
            this.tial.next = dlnode;
        }
        dlnode.previous = tial;
        this.tial = dlnode;
    }

    /**
     * 删除链首结点
     * @return 被删除的结点
     */
    public Dlnode deleteHead() {
        if (isEmpty()) {
            return null;
        } else {
            Dlnode temp = head;
            this.head = temp.next;
            temp.next.previous = head;
            temp.previous = null;
            temp.next = null;
            return temp;
        }
    }

    /**
     * 删除链尾结点
     * @return 被删除的结点
     */
    public Dlnode deleteTial() {
        if (isEmpty()) {
            return null;
        } else {
            Dlnode temp = tial;
            this.tial = temp.previous;
            temp.previous.next = tial;
            temp.previous = null;
            temp.next = null;
            return temp;
        }
    }

    /**
     * 找出第一个值为x的元素，并将其删除
     * @param x
     * @return
     */
    public Dlnode delete(int x) {
        Dlnode current = head;
        while (current.value != x) {
            if (current != tial) {
                current = current.next;
            } else {
                throw new RuntimeException("欲删除的元素不在链表之中");
            }
        }

        if (head == current) {
            return deleteHead();
        } else if (tial == current) {
            return deleteTial();
        } else {
            // 1<-->2(current)<-->3
            current.previous.next = current.next; // 1-->3
            current.next.previous = current.previous; // 1<-->3
            return current;
        }
    }

    public boolean isEmpty() {
        if (head == null) {
            return true;
        } else {
            return false;
        }
    }

}

/**
 * 此类将实例化双链表的结点
 */
class Dlnode {
    /**
     * 结点值
     */
    protected int value;
    /**
     * 指向前级结点的指针
     */
    protected Dlnode previous;
    /**
     * 指向后级结点的指针
     */
    protected Dlnode next;

    /**
     * 结点构造函数
     *
     * @param value 为新结点赋初值
     */
    public Dlnode(int value) {
        this.value = value;
    }

    /**
     *
     */



}
