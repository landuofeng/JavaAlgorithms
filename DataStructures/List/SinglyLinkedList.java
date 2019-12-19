package DataStructures.List;

/**
 * 这个类文件实现了一个单链表。
 *
 * 单链表可以想数组一样，保存一系列的值。但是单链表不需要关心数据的长度，它的长度可以在
 * 程序的运行过程中变长或者变短。对此单链表，增加或删除数据只能在链表头部和尾部进行。
 */
public class SinglyLinkedList {
    /**
     * head变量指向链表的头结点，该头结点不保存数据，只保存第一数据结点的引用。
     */
    private Node head;

    /**
     * 表示链表的长度，当有一个数据节点时 size 值为1。
     */
    private int size;

    /**
     * 构造函数，构造时初始化链表
     */
    public SinglyLinkedList() {
        head = new Node();
        size = 0;
    }

    /**
     * 用已有的链表初始化这个链表
     * @param head
     * @param size
     */
    public SinglyLinkedList(Node head, int size) {
        this.head = head;
        this.size = size;
    }

    /**
     * 在指定位置插入新结点
     * @param val 新结点的值
     * @param position 位置，其值为从0开始。
     */
    public void InsertNode(int val, int position) {
        checkBounds(position, 0, size);
        Node cur = head;
        for (int i = 0; i < position; i++) {
            cur = cur.next;
        }
        Node node = new Node(val);
        node.next = cur.next;
        cur.next = node;
        size++;
    }

    /**
     * 在链表头部插入结点
     * @param val
     */
    public void InsertHead(int val) {
        InsertNode(val, 0);
    }

    /**
     * 在链表尾部插入结点
     * @param val
     */
    public void InsertTial(int val) {
        InsertNode(val, size);
    }

    /**
     * 插入结点
     * @param val
     * @param position
     */
    public void Insert(int val, int position) {
        InsertNode(val, position);
    }

    /**
     * 按从小到大排序，插入一个结点
     * @param val 新结点的值
     */
    public void InsertSorted(int val) {
        Node cur = head;
        while (cur.next != null && val > cur.next.value) {
            cur = cur.next;
        }
        Node node = new Node(val);
        node.next = cur.next;
        cur.next = node;
        size++;
    }

    /**
     * 删除指定位置的结点
     * @param position 指定删除结点的位置，从0开始
     * @return 返回被删除结点的值
     */
    public int deleteNodeByPosition(int position) {
        checkBounds(position, 0 , size - 1);
        int ret_val;
        Node cur = head;
        for (int i = 0; i < position; i++) {
            cur = cur.next;
        }
        ret_val = cur.next.value;
        cur.next = cur.next.next;
        size--;
        return ret_val;
    }

    public int deleteHead() {
        return deleteNodeByPosition(0);
    }

    public int delete() {
        return deleteNodeByPosition(size - 1);
    }

    /**
     * 清空单链表。删除链表中的所有数据节点。头结点会被保留
     */
    public void clear() {
        if (size == 0) {
            return;
        } else {
            Node prior;
            while (head.next != null) {
                prior = head.next;
                head.next = head.next.next;
                prior = null;
            }
            size = 0;
        }
    } //？与原作不同，待测试

    /**
     * 坐标越界检查
     * @param position  待检测的坐标值
     * @param low       最小坐标值
     * @param high      最大坐标值
     * @throws IndexOutOfBoundsException if {@code position} not in range {@code low} to {@code high}
     */
    public void checkBounds(int position, int low, int high) {
        if (position > high || position < low) {
            throw new IndexOutOfBoundsException(position + "");
        }
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        if (size == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        Node cur = head.next;
        while (cur != null) {
            builder.append(cur.value).append("->");
            cur = cur.next;
        }
        return builder.replace(builder.length() - 2, builder.length(), "").toString();
    }

    /**
     * 合并两个链表，且不改变原链表
     * @param list1
     * @param list2
     * @return
     */
    public static SinglyLinkedList merge(SinglyLinkedList list1, SinglyLinkedList list2) {
        Node cur1 = list1.head.next;
        Node cur2 = list2.head.next;

        Node head = new Node();
        Node cur = head;
        while (cur1 != null && cur2 != null) {
            if (cur1.value <= cur2.value) {
                cur.next = new Node(cur1.value);
                cur1 = cur1.next;
            } else {
                cur.next = new Node(cur2.value);
                cur2 = cur2.next;
            }
            cur = cur.next;
        }
        if ( cur1 != null ) {
            while (cur1 != null) {
                cur.next = new Node(cur1.value);
                cur1 = cur1.next;
            }
        }
        if ( cur2 != null ) {
            while (cur2 != null) {
                cur.next = new Node(cur2.value);
                cur2 = cur2.next;
            }
        }
        int size = list1.size + list2.size;
        return new SinglyLinkedList(head, size);
    }


    public int getData(int position) {
        checkBounds(position, 0, size);
        Node cur = head.next;
        for (int i = 0; i < position; i++) {
            cur = cur.next;
        }
        return cur.value;
    }

    public static void main(String[] args) {
        SinglyLinkedList sll = new SinglyLinkedList();
        sll.InsertTial(1);
        sll.InsertTial(2);
        sll.InsertTial(3);
        sll.InsertTial(4);
        sll.InsertTial(5);
        System.out.println(sll.getData(2));
        System.out.println(sll.deleteNodeByPosition(2));
        sll.Insert(12,2);
        System.out.println(sll.getData(2));
        sll.clear();
        System.out.println(sll.size());
    }
}

/**
 * 这个类定义了链表中使用到的结点。
 * 由结点值和后继结点的指针构成。
 */
class Node {

    /**
     * 结点所保存的值
     */
    protected int value;

    /**
     * 保存当前结点的后继结点的指针（引用/对象地址）
     */
    protected Node next;

    Node() {

    }

    /**
     * 构造函数
     * @param value 结点值
     */
    protected Node(int value) {
        this.value = value;
    }

    /**
     * 构造函数
     * @param value 结点值
     * @param next 后继结点的指针
     */
    protected Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }
}
