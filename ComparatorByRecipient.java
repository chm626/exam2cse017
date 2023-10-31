import java.util.Comparator;
public class ComparatorByRecipient implements Comparator<Message>{
    public int compare(Message m1, Message m2){
        String r1 = m1.getRecipient();
        String r2 = m2.getRecipient();
        return r1.compareTo(r2);
    }
}