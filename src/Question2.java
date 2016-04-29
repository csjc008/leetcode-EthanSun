import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author yahao.sun
 *
 */
public class Question2 {

    public List<String> getDirectFriendsForUser(String user) {
        return new ArrayList<String>();
    }

    public List<String> getAttendedCoursesForUser(String user) {
        return new ArrayList<String>();
    }

    public List<String> getRankedCourses(String user) {
        // the spacial complexity of the algorithm is
        // O(socialNetworkSize*courseListSize), hard to predict
        // assume the two external methods consumes constant time,
        // the time complexity is O(nlogn) where n is
        // socialNetworkSize*courseListSize

        // set to store the user's social network of 2nd-level link
        Set<String> socialSet = new HashSet<String>();
        // get the first level contact list for user
        List<String> firstContactList = this.getDirectFriendsForUser(user);
        socialSet.addAll(firstContactList);
        // get the 2nd level contact list for user
        for (String firstFriend : firstContactList) {
            socialSet.addAll(this.getDirectFriendsForUser(firstFriend));
        }
        // remove user itself, now we get a complete set for user's social
        // network
        socialSet.remove(user);
        // a map to store the courses and the attend times of which the user's
        // social network attended
        Map<String, Integer> courseMap = new HashMap<String, Integer>();
        for (String nu : socialSet) {
            List<String> courses = this.getAttendedCoursesForUser(nu);
            for (String c : courses) {
                if (courseMap.containsKey(c)) {
                    courseMap.put(c, courseMap.get(c) + 1);
                } else {
                    courseMap.put(c, 1);
                }
            }
        }
        // remove the courses that user itself attended
        List<String> attendedCourses = this.getAttendedCoursesForUser(user);
        for (String ac : attendedCourses) {
            courseMap.remove(ac);
        }
        // use a TreeMap to get the reverse order of <attendTimes,courseName>
        Map<Integer, String> courseOrderMap = new TreeMap<Integer, String>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o2 > o1) {
                    return 1;
                } else if (o1 > o2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        for (String c : courseMap.keySet()) {
            courseOrderMap.put(courseMap.get(c), c);
        }
        // return the ordered values(course names)
        return new ArrayList<String>(courseOrderMap.values());
    }

    public static void main(String[] args) {

    }
}
