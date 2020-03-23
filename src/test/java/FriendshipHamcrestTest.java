import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.Matchers.*;


public class FriendshipHamcrestTest {
    private Friendship friendship;

    @Before
    public void setUp(){
        friendship = new Friendship();
    }
    @Test
    public void classInstance(){
        assertThat(friendship.getClass(), is(Friendship.class));
    }


    /// Custom Matchers///
    @Test
    public void test_matcher_no_friends(){
        assertThat(friendship,  FriendsMatcher.noInfoAboutPerson("Ola"));
    }

    @Test
    public void test_matcher_friendship_between(){
        friendship.makeFriends("Ola", "Ala");
        assertThat(friendship, FriendsMatcher.hasFriendshipBetween("Ola","Ala"));
    }
    ///
    @Test
    public void test_empty_friendships() {
        assertThat(friendship.getFriendsList("Ola"), is(nullValue()));
    }

    @Test
    public void test_make_null_friends() {
        assertThrows(IllegalArgumentException.class, () -> friendship.makeFriends(null,null));
    }
    @Test
    public void test_make_empty_friends() {
        assertThrows(IllegalArgumentException.class, () -> friendship.makeFriends("",""));
    }
    @Test
    public void test_get_list_friends_from_null() {
        assertThrows(IllegalArgumentException.class, () -> friendship.getFriendsList(null));
    }

    @Test
    public void tets_are_friends_null() {
        assertThrows(IllegalArgumentException.class, () -> friendship.areFriends(null, null));
    }
    @Test
    public void test_make_friends_both_ways() {
        friendship.makeFriends("Ola", "Ala");
        assertThat(friendship.getFriendsList("Ola"), contains("Ala"));
        assertThat(friendship.getFriendsList("Ala"), contains("Ola"));
    }

    @Test
    public void test_make_more_friends() {
        friendship.makeFriends("Ola", "Ala");
        friendship.makeFriends("Ola", "Basia");
        friendship.makeFriends("Ola", "Kasia");
        assertThat(friendship.getFriendsList("Ola"), containsInAnyOrder("Ala", "Basia", "Kasia"));
    }

    @Test
    public void test_get_list(){
        friendship.makeFriends("Ola", "Ala");
        friendship.makeFriends("Ola", "Basia");
        assertThat(friendship.getFriendsList("Ola"), hasSize(equalTo(2)));
        assertThat(friendship.getFriendsList("Ala"), hasSize(equalTo(1)));
        assertThat(friendship.getFriendsList("Basia"), hasSize(equalTo(1)));
    }

    @Test
    public void test_my_friends_are_not_yours() {
        friendship.makeFriends("Ola", "Ala");
        friendship.makeFriends("Ola", "Basia");
        assertThat(friendship.getFriendsList("Ala"), contains("Ola"));
        assertThat(friendship.getFriendsList("Ala"), not(contains("Basia")));
        assertThat(friendship.getFriendsList("Basia"), contains("Ola"));
        assertThat(friendship.getFriendsList("Basia"), not(contains("Ala")));
    }

    @Test
    public void test_are_friends(){
        friendship.makeFriends("Ola", "Ala");
        assertThat(friendship.areFriends("Ola", "Ala"), is(true));
        assertThat(friendship.areFriends("Ala", "Ola"), is(true));
    }
    @Test
    public void test_dont_have_friends(){
        friendship.makeFriends("Ola", "Ala");
        assertThat(friendship.areFriends("Ola", "Ela"), is(false));
    }

    @Test
    public void test_are_friend_where_is_no_friendship(){
        assertThat(friendship.areFriends("Ola", "Ala"), is(false));
        assertThat( friendship.areFriends("Ala", "Ola"), is(false));
    }


    @Test
    public void test_are_not_friends(){
        friendship.makeFriends("Ola", "Ela");
        friendship.makeFriends("Ola", "Ala");
        assertThat(friendship.areFriends("Ela", "Ala"), is(false));
        assertThat(friendship.areFriends("Ala", "Ela"), is(false));
    }


    @After
    public void tearDown() {
        friendship = null;
    }

}
