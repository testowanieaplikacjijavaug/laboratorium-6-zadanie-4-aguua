import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.*;

public class FriendshipAssertJTest{
    private Friendship friendship;

    @Before
    public void setUp(){
        friendship = new Friendship();
    }
    @Test
    public void classInstance(){
        assertThat(friendship.getClass().isInstance(Friendship.class));
    }


    @Test
    public void test_empty_friendships() {
        assertThat(friendship.getFriendsList("Ola")).isNull();
    }

    @Test
    public void test_make_null_friends() {
        assertThatThrownBy(() -> {friendship.makeFriends(null,null);
        }).isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    public void test_make_empty_friends() {
        assertThatThrownBy(() -> {friendship.makeFriends("","");
        }).isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    public void test_make_one_empty_friends() {
        assertThatThrownBy(() -> {friendship.makeFriends("","Ela");
        }).isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    public void test_make_second_empty_friends() {
        assertThatThrownBy(() -> {friendship.makeFriends("Ola","");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void test_make_null1_friends() {
        assertThatThrownBy(() -> {friendship.makeFriends(null,"Ola");
        }).isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    public void test_make_null2_friends() {
        assertThatThrownBy(() -> {friendship.makeFriends("Ola", null);
        }).isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    public void test_make_friends_both_ways() {
        friendship.makeFriends("Ola", "Ala");
        assertThat(friendship.getFriendsList("Ola")).contains("Ala");
        assertThat(friendship.getFriendsList("Ala")).contains("Ola");
    }

    @Test
    public void test_make_more_friends() {
        friendship.makeFriends("Ola", "Ala");
        friendship.makeFriends("Ola", "Basia");
        friendship.makeFriends("Ola", "Kasia");
        assertThat(friendship.getFriendsList("Ola")).containsExactlyInAnyOrder("Ala", "Basia", "Kasia").hasSize(3);
    }

    @Test
    public void test_get_list(){
        friendship.makeFriends("Ola", "Ala");
        friendship.makeFriends("Ola", "Basia");
        assertThat(friendship.getFriendsList("Ola")).hasSize(2).isInstanceOf(List.class);
        assertThat(friendship.getFriendsList("Ala")).hasSize(1).isInstanceOf(List.class);
        assertThat(friendship.getFriendsList("Basia")).hasSize(1).isInstanceOf(List.class);
    }

    @Test
    public void test_my_friends_are_not_yours() {
        friendship.makeFriends("Ola", "Ala");
        friendship.makeFriends("Ola", "Basia");
        assertThat(friendship.getFriendsList("Ala")).contains("Ola").doesNotContain("Basia");
        assertThat(friendship.getFriendsList("Basia")).contains("Ola").doesNotContain("Ala");
    }

    @Test
    public void test_are_friends(){
        friendship.makeFriends("Ola", "Ala");
        assertThat(friendship.areFriends("Ola", "Ala")).isTrue();
        assertThat(friendship.areFriends("Ala", "Ola")).isTrue();
    }

    @Test
    public void test_are_null_friends() {
        assertThatThrownBy(() -> {friendship.areFriends("Ola", null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void test_are_empty_friends() {
        assertThatThrownBy(() -> {friendship.areFriends("","");
        }).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> {friendship.areFriends("","Ela");
        }).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> {friendship.areFriends("Ola","");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void test_are_friend_where_is_no_friendship(){
        assertThat(friendship.areFriends("Ola", "Ala")).isFalse();
        assertThat( friendship.areFriends("Ala", "Ola")).isFalse();
    }


    @Test
    public void test_are_not_friends(){
        friendship.makeFriends("Ola", "Ela");
        friendship.makeFriends("Ola", "Ala");
        assertThat(friendship.areFriends("Ela", "Ala")).isFalse();
        assertThat(friendship.areFriends("Ala", "Ela")).isFalse();
    }


    @After
    public void tearDown() {
        friendship = null;
    }

}