import java.util.Comparator;
public class ComparatorByDate implements Comparator<Message>{
    public int compare(Message m1, Message m2){
        Date d1 = m1.getDate();
        Date d2 = m2.getDate();
        return d1.compareTo(d2);
    }
}