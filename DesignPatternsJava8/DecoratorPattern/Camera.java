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
        //Stream.of is used  because filters is an array of objects
        filter = Stream.of(filters)
                /*reduce is to go to all values of this collection and reduce to one value.
                /.reduce(initialValue, lambda); -- > This is the reduce's two parameters*/
                //.reduce(color -> color, (theFilters, aFilter) -> theFilters.andThen(aFilter));
                //color -> color replaced by the Function.identity()
                .reduce(Function.identity(), Function::andThen);
    }


    public Color snap(Color input) {
        return filter.apply(input);
    }
}
