## Using Third party code
Imagine an application which needs a Map of sensors.
```
Map<Sensor> sensors = new HashMap<>();
Sensor s = sensors.get(sensorId);
```
What happens when the Map interface changes? You might think such a change to be unlikely, but remember that it changed when generics support was added in java 5. A cleaner way to use Map might look like the following. No user of sensors would care one bit if generics were used or not.

```
public class Sensors {
  private Map sensors = new HashMap();
  
  public Sensor getById(String id) {
    return (Sensor) sensors.get(id);
  }
}
```

The interface at the boundary(Map) is hidden. It is able to evolve with very little impact on the rest of the application. This does not mean thate every use of Map be encapsulated in this form.
