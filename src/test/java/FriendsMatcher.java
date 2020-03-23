import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class FriendsMatcher {

    public static Matcher<Friendship> noInfoAboutPerson(final String person) {
        return new TypeSafeMatcher<Friendship>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Expected do not have any info about ").appendValue(person);
            }


            @Override
            protected boolean matchesSafely(Friendship f) {
                    if (f.getFriendsList(person) == null){
                        return true;
                    }
                    return false;
            }
        };
    }

    public static Matcher<Friendship> hasFriendshipBetween(final String person1, final String person2) {
        return new TypeSafeMatcher<Friendship>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Expected to have friendship between ").
                        appendValue(person1).appendText(" and ").appendValue(person2);
            }


            @Override
            protected boolean matchesSafely(Friendship f) {
                if (f.getFriendsList(person1).contains(person2) &&
                f.getFriendsList(person2).contains(person1))
                    return true;

                return false;
            }
        };
    }


}
