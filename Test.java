import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test{
    public static void main(String[] args){
        List<Message>[] messages = new List[2];
        messages[0] = new ArrayList<Message>();
        messages[1] = new LinkedList<Message>();

        readMessages(messages[0], "list1.txt");
        readMessages(messages[1], "list2.txt");

        Date d = new Date("01/26/2022");
        List<Message> list = findMessages(messages[0], d);
        if(list == null){
            System.out.println("No messages found for the date " + d);
        }
        else{
            System.out.println(list.size() + " messages found for the date " + d + ":");
            print(list);
            System.out.println();
        }
        d = new Date("01/22/2022");
        list = findMessages(messages[1], d);
        if(list == null){
            System.out.println("No messages found for the date " + d);
        }
        else{
            System.out.println(list.size() + " messages found for the date " + d + ":");
            print(list);
            System.out.println();
        }

        List<Message> unionList =  new ArrayList<>();
        unionList(messages[0], messages[1], unionList);
        System.out.println("Union of the two lists:");
        print(unionList);
        System.out.println();
        
        /*List<Message> intersectionList =  new LinkedList<>();
        intersection(messages[0], messages[1], intersectionList);
        System.out.println("Intersection of the two lists:");
        print(intersectionList);
        System.out.println();*/

        /*List<Message> unionNoDuplicatesList =  new ArrayList<>();
        unionNoDuplicates(messages[0], messages[1], unionNoDuplicatesList);
        System.out.println("Union of the two lists without duplicates:");
        print(unionNoDuplicatesList);
        System.out.println();*/

        unionList.sort(null);
        System.out.println("Union List sorted by sender:");
        print(unionList);
        System.out.println();
        unionList.sort(new ComparatorByRecipient());
        System.out.println("Union List sorted by recipient:");
        print(unionList);
        System.out.println();
        unionList.sort(new ComparatorByDate());
        System.out.println("Union List sorted by date:");
        print(unionList);
        System.out.println();
    }
    
    public static void readMessages(List<Message> list, String filename){
        try{
            Scanner read = new Scanner(new File(filename));
            while(read.hasNextLine()){
                String line = read.nextLine();
                String[] tokens = line.split(",");
                Date date = new Date(tokens[0]);
                list.add(new Message(date, tokens[1], tokens[2], tokens[3]));
            }
            read.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File \"" + filename + "\" not found");
        }
    }
    // combines the elements from list1 and list 2 into list
    public static <E> void unionList(List<E> list1, List<E> list2, List<E> list){
        int list1Index = 0;
        int list2Index = 0;
        int listIndex = 0;
        while(list1Index < list1.size() && list2Index < list2.size()) {
            list.set(listIndex++, list1.get(list1Index++));
            list.set(listIndex++, list2.get(list2Index++));
        }
    }
    // the intersection of list1 and list2 in list
    public static <E> void intersection(List<E> list1, List<E> list2, List<E> list){
        int list1Index = 0;
        int list2Index = 0;
        int listIndex = 0;
        while(list1Index <= list1.size()) {
            if(list1.get(list1Index).equals(list2.get(list1Index).getText())){
                list.set(listIndex++, list1.get(list1Index++));
            }
        }
    }
    // combines list1 and list2 in list without duplicates
    public static <E> void unionNoDuplicates(List<E> list1, List<E> list2, List<E> list){
        int list1Index = 0;
        int list2Index = 0;
        int listIndex = 0;
        while(list1Index <= list1.size()) {
            if(!list1.get(list1Index).equals(list2.get(list1Index))){
                list.set(listIndex++, list1.get(list1Index++));
            }
        }
    }
    // returns the list of  messages sent at the date d, null if no messages were found
    public static Iterator<Message> iter = messages.iterator();

    public static ArrayList<Message> findMessages(Date d){
        while(iter.hasNext()){
            if(d.equals(iter.next().getDate())){
                messes.add(iter.next().getText());
            }
            else{
                return findMessages(messages, d);
            }
        }
        if((!iter.hasNext()) && messes.isEmpty()){return null;}
        
        return ArrayList<Message> messes;
    }

    public static ArrayList<Message> findMessages(List<Message> messages, Date d){
        ArrayList<Message> messes = new ArrayList<>();
        messes = new ArrayList<Message>(15);
        return findMessages(messages, d);
    }
    
    public static <E> void print(List<E> list){
        Iterator<E> iter = list.iterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
    }
}   
//time complexities
/*
findMessages() - O(n)
union() - O(n)
unionNoDuplicates() - O(n)
intersection() - O(n)
 */