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

## Add learning tests when exploring a third party API
* Learning tests end up costing nothing. We had to learn a API anyway, and writing those tests was an easy and isolated way to get that knowledge.
* Not only learning tests are free, they have a positive return on investment. When there are new releases of the third-party package, we run the learning tests to see whether there are behavioral differences.
* Learning tests verify that the third-party packages we are using work the way we expect them to.

## Using code that does not yet exist
Another kind of boundary is the one that separates the known from the unknown. Sometimes what is on the other side of the boundary is unknowable (at least right now).

![adapter pattern](https://user-images.githubusercontent.com/6800366/41607295-557ae322-7403-11e8-9e6c-540acab04301.PNG)

* In the above, the details of the Transmitter API was not clear and hence the TransmitterAdapter was written to bridge the gap. The ADAPTER encapsulated the interaction with the API and provides a single place to vhange when the API evolves.
* This design also gives the convienence during testing. Using a suitable FakeTransmitter, we can test the CommunicationController classes. We can also create boundary tests once we have the TransmitterAPI to make sure the API is correctly used.
* This way of managing third party boundaries promotes internally consistent usage across the boundary, and has fewer maintainance points when the third party code changes.



