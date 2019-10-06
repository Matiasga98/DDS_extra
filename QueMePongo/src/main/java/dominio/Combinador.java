package dominio;

import java.util.*;

public class Combinador {


    public static Set<List<Prenda>> generarTodasLasCombinacionesPosibles(Prenda[] args, int cantidadDePrendasMaxima) {

        Set<List<Prenda>> TodasLasCombinacionesPosibles = new HashSet<List<Prenda>>();

        for (int i = 1; i <= cantidadDePrendasMaxima; i++)
            TodasLasCombinacionesPosibles.addAll(Combinador.combination(Arrays.asList(args), i));

        return TodasLasCombinacionesPosibles;
    }


    public static  <T> List<List<T>> combination(List<T> values, int size) {

        if (0 == size) {
            return Collections.singletonList(Collections.<T> emptyList());
        }

        if (values.isEmpty()) {
            return Collections.emptyList();
        }

        List<List<T>> combination = new ArrayList<List<T>>();

        T actual = values.iterator().next();

        List<T> subSet = new ArrayList<T>(values);
        subSet.remove(actual);

        List<List<T>> subSetCombination = combination(subSet, size - 1);

        for (List<T> set : subSetCombination) {
            List<T> newSet = new ArrayList<T>(set);
            newSet.add(0, actual);
            combination.add(newSet);
        }

        combination.addAll(combination(subSet, size));

        return combination;
    }
}
