package jp.water_cell.java.rxsample.collections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.water_cell.java.rxsample.collections.models.City;
import jp.water_cell.java.rxsample.collections.models.Customer;
import jp.water_cell.java.rxsample.collections.models.Shop;
import rx.Observable;

public class G_GroupBy implements ICollectionUtils {

    public static void main(String[] args) {
        new G_GroupBy().sample();
    }

    void sample() {
        HashMap<Integer, List<String>> result = Observable.just("a", "b", "ba", "ccc", "ad")
                .groupBy(s -> s.length())
                .collect(
                        () -> new HashMap<Integer, List<String>>(),
                        (m, o) -> o.toList().subscribe(list -> m.put(o.getKey(), list)))
                .toBlocking()
                .single();

        HashMap<Integer, List<String>> expected = new HashMap<>();
        expected.put(1, listOf("a", "b"));
        expected.put(2, listOf("ba", "ad"));
        expected.put(3, listOf("ccc"));

        assert result.equals(expected);
    }

    public Map<City, List<Customer>> groupCustomersByCity(Shop shop) {
        // Return the map of the customers living in each city
        // TODO
        return null;
    }
}
