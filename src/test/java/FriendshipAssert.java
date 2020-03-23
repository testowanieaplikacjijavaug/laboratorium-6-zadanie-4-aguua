import org.assertj.core.api.AbstractAssert;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class FriendshipAssert extends AbstractAssert<FriendshipAssert, Friendship> {


    public FriendshipAssert(Friendship actual) {
        super(actual, FriendshipAssert.class);
    }

    public static FriendshipAssert assertThat(Friendship actual) {
        return new FriendshipAssert(actual);
    }

    public FriendshipAssert noInfoAboutPerson(String name) {

        if (actual.getFriendsList(name) != null) {
            failWithMessage("Expected do not have any info about  <%s> ", name);
        }
        return this;
    }

    public FriendshipAssert hasFriendshipBetween(String person1, String person2) {
        if (!actual.getFriendsList(person1).contains(person2) ||
                !actual.getFriendsList(person2).contains(person1)) {
            failWithMessage("Expected to have friendship between <%s> and <%s>", person1, person2);
        }
        return this;
    }
}

