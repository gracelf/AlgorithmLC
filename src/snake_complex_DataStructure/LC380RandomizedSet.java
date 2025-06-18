package snake_complex_DataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 */
public class LC380RandomizedSet {

    Map<Integer, Integer> map;
    List<Integer> list;

    public LC380RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    public boolean insert(int val) {
        if (!map.containsKey(val)) {
            map.put(val, list.size());
            list.add(val);
            return true;
        }
        return false;

    }

    public boolean remove(int val) {
        if (map.containsKey(val)) {
            int idx = map.get(val);
            int lastIdx = list.size() - 1;
            int lastVal = list.get(lastIdx);
            map.put(lastVal, idx);
            map.remove(val);
            list.set(idx, lastVal);
            list.remove(lastIdx);
            return true;
        }
        return false;

    }

    public int getRandom() {
        Random rdm = new Random();
        return list.get(rdm.nextInt(list.size()));
    }

}
