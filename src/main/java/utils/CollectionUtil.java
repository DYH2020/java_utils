package utils;

import java.util.*;
import java.util.function.Function;

/**
 * 集合类
 */
public class CollectionUtil {

    public static <T> List<T> toList(Iterable<T> iterable) {
        if (iterable == null) return null;
        List<T> ts = new LinkedList<>();
        iterable.forEach(t1 -> ts.add(t1));
        return ts;
    }

    public static <S, T> List<T> toList(Iterable<S> iterable, Function<S, T> conver) {
        if (iterable == null) return null;
        List<T> ts = new LinkedList<>();
        for (S s : iterable){
            ts.add(conver.apply(s));
        }
        return ts;
    }

    public static <S, T> List<T> toList(List<S> list, Function<S, T> conver) {
        if (list == null) return null;
        List<T> ts = new LinkedList<>();
        for (S s : list){
            ts.add(conver.apply(s));
        }
        return ts;
    }


    public static <K, V> Map<K, V> toMap(List<V> list, Function<V, K> keyFunc) {
        if (list == null) return null;
        Map<K, V> map = new HashMap<K, V>();
        list.stream().forEach(v -> map.put(keyFunc.apply(v), v));
        return map;
    }

    public static <K, V> Map<K, V> toMap(V[] arr, Function<V, K> keyFunc) {
        if (arr == null) return null;
        Map<K, V> map = new HashMap<K, V>();
        for (V v : arr){
            map.put(keyFunc.apply(v), v);
        }
        return map;
    }

    public static <K, V> Map<K, V> toMap(Iterable<V> iterable, Function<V, K> keyFunc) {
        if (iterable == null) return null;
        Map<K, V> map = new HashMap<K, V>();
        iterable.forEach(v -> map.put(keyFunc.apply(v), v));
        return map;
    }

    public static <K, V> Map<K, V> toMap(Iterator<V> iterator, Function<V, K> keyFunc) {
        if (iterator == null) return null;
        Map<K, V> map = new HashMap<K, V>();
        while (iterator.hasNext()){
            V v = iterator.next();
            map.put(keyFunc.apply(v), v);
        }
        return map;
    }

    public static <T, V> Set<V> toSet(List<T> list, Function<T, V> vFun){
        if (list == null) return null;
        Set<V> set = new HashSet<V>();
        for (T t : list){
            set.add(vFun.apply(t));
        }
        return set;
    }

    public static <T, V> Set<V> toSet(Set<T> s, Function<T, V> vFun){
        if (s == null) return null;
        Set<V> set = new HashSet<V>();
        for (T t : s){
            set.add(vFun.apply(t));
        }
        return set;
    }
}
