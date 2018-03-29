## A client should never be forced to implement an interface that it doesn't use or clients shouldn't be forced to depend on methods they do not use.

i.e Classes that implement interfaces should not be forced to implement methods they do not use. An interface should be as precise as possible.

```
interface stream {
  void reset();
  void read();
  void write();
}

// Above interface could be refactored like the interfaces shown below.

interface ReadableStream {
  void reset();
  void read();
}

interface WriteableStream {
  void write();
}

```
