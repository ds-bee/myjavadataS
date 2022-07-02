package com.huang.linkedlist;

public class DoubleLinkedListDemo {

    public static void main(String[] args) {

        //双向链表的测试
        HeroNode2 hero1 = new HeroNode2(1, "松江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "五月", "针对性");
        HeroNode2 hero4 = new HeroNode2(4, "灵宠", "爆炸头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();
        //修改
        HeroNode2 heroNode2 = new HeroNode2(4, "林冲", "豹子头");
        doubleLinkedList.update(heroNode2);
        doubleLinkedList.list();

        //删除
        doubleLinkedList.del(3);
        doubleLinkedList.list();

    }
}
    //创建一个双向链表的类
    class DoubleLinkedList {
        private HeroNode2 head = new HeroNode2(0, "", "");

        //返回头节点
        public HeroNode2 getHead() {
            return head;
        }

        //添加节点
        public void add(HeroNode2 heroNode) {

            //因为head节点不能动，因此需要一个辅助遍历temp
            HeroNode2 temp = head;
            //遍历链表找到最后
            while (true) {
                //找到最后
                if (temp.next == null) {//
                    break;
                }
                //移动链表
                temp = temp.next;
            }
            //退出循环时，就相当于指向了最后
            //形成一个双向链表
            temp.next = heroNode;
            heroNode.pre = temp;
        }

        //修改一个节点的类容
        public void update(HeroNode2 newHeroNode) {
            //判断链表是否为空
            if (head.next == null) {
                System.out.println("该节点为空~");
                return;
            }
            //头节点没有数据，充当一个指向指针
            HeroNode2 temp = head.next;
            boolean flag = false; //标志找到该节点没
            while (true) {
                if (temp == null) {
                    break; //节点为空就返回
                }
                if (temp.no == newHeroNode.no) {
                    //找到该节点
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            //根据flag来判断是否找到该节点
            if (flag) {
                temp.name = newHeroNode.name;
                temp.nickname = newHeroNode.nickname;
            } else { //没有找到该节点
                System.out.printf("û���ҵ� ��� %d �Ľڵ㣬�����޸�\n", newHeroNode.no);
            }
        }

        //从双向链表删除节点
        public void del(int no) {
            if (head.next == null) {
                System.out.println("链表为空，无法删除");
                return;
            }

            HeroNode2 temp = head.next;
            boolean flag = false; // 判断标志位
            while (true) {
                if (temp == null) { //找到要删除的节点
                    break;
                }
                if (temp.no == no) {
                    //改变标志位
                    flag = true;
                    break;
                }
                temp = temp.next; //记录链表改变
            }
            //flag标志位
            if (flag) { //�ҵ�
                //这是单链表的
//                 temp.next = temp.next.next;
                temp.pre.next = temp.next;
                //如果是最后一个节点不能执行下一条语句
                if(temp.next != null){
                    temp.next.pre = temp.pre;
                }
            } else {
                System.out.printf("没有匹配相关节点\n", no);
            }
        }
        //遍历双向链表的方法
        public void list() {
            //判断链表是否为空
            if (head.next == null) {
                System.out.println("链表为空");
                return;
            }
            HeroNode2 temp = head.next;
            while (true) {
                //判断是否链表到达最后
                if (temp == null) {
                    break;
                }
                //显示节点
                System.out.println(temp);
                temp = temp.next;
            }
        }
}


class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; //指向下一个节点
    public HeroNode2 pre; // 指向前一个节点

    //������
    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        //这里如果将next和pre也打印出来，会造成一种情况，就是会导致递归的出现，单链表时会一直打印出来
        //双向链表因为是双向，如果打印，就会造成重复递归，报错
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +

                '}';
    }
}
