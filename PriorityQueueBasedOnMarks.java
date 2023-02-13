package MyPractice;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class PriorityQueueBasedOnMarks {

    public static class Student
    {
        String name;
        int marks;

        public Student(String name,int marks)
        {
            this.name=name;
            this.marks=marks;
        }
    }


    public static void main(String [] args)
    {
        LinkedList<Student> studentLinkedList = new LinkedList<>();

        studentLinkedList.add(new Student("Tushar",99));
        studentLinkedList.add(new Student("ABC",88));
        studentLinkedList.add(new Student("Sachin",100));
        studentLinkedList.add(new Student("Chinki",90));

        PriorityQueue<Student> pq = new PriorityQueue<>(studentLinkedList.size(),new StudentComparator());

        for(Student  s: studentLinkedList)
        {
            pq.offer(s); //does not throw exception
        }

        while(pq.isEmpty()==false)
        {
            Student s = pq.poll();
            System.out.println(s.name);
        }
    }

    public static class StudentComparator implements Comparator<Student>
    {

        @Override
        public int compare(Student a,Student b)
        {
            if(a.marks<b.marks)
                return 1;
            else if(a.marks==b.marks)
                return 0;
            else
                return -1;
        }
    }
}
