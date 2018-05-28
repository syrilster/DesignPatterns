package DecoratorPattern;

import java.awt.*;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by Syril on 05-05-2018.
 */
public class Camera {
    private Function<Color, Color> filter;

    public Camera(Function<Color, Color>... filters) {
        setFilters(filters);
    }

    private void setFilters(Function<Color, Color>... filters) {
        //filter = color -> color;
        filter = Stream.of(filters)
                //.reduce(initialValue, lambda);
                //.reduce(color -> color, (theFilters, aFilter) -> theFilters.andThen(aFilter));
                .reduce(Function.identity(), Function::andThen);
    }


    public Color snap(Color input) {
        return filter.apply(input);
    }
}
