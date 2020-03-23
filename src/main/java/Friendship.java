import java.util.*;

public class Friendship {
    Map<String, List<String>> friendships = new HashMap<>();

    //Dodanie przyjaciół - wykorzystuje funkcje addFriend!
    public void makeFriends(String person1, String person2) {
        if(person1 == null || person2 == null)
            throw new IllegalArgumentException();
        if(person1.length() == 0 || person2.length() == 0)
            throw new IllegalArgumentException();
        addFriend(person1, person2);
        addFriend(person2, person1);
    }

    //Pobranie listy przyjaciol
    public List<String> getFriendsList(String person) {
        if(person == null )
            throw new IllegalArgumentException();

        if(friendships.containsKey(person))
            return friendships.get(person);
        return null;
    }

    //Sprawdzenie czy przyjaciele
    public boolean areFriends(String person1, String person2) {
        if(person1 == null || person2 == null)
            throw new IllegalArgumentException();
        if(person1.length() == 0 || person2.length() == 0)
            throw new IllegalArgumentException();
        if(friendships.containsKey(person1) || friendships.containsKey(person2)) {
            if (getFriendsList(person1).contains(person2)) {
                return true;
            }
        }
        return false;
    }
    //Dodanie do listy przyjaciol do danej osoby
    private void addFriend(String person, String friend) {
        if(friendships.containsKey(person)){
            friendships.get(person).add(friend);
        }
        else{
            List<String> friends = new ArrayList();
            friends.add(friend);
            friendships.put(person, friends );

        }


    }
}