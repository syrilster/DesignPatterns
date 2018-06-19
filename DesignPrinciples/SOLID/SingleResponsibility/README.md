## Single-responsiblity principle

A class should have one and only one reason to change. i.e don't have more than one core functionality.

In the below picture, SRP can be used effectively: 

![srp](https://user-images.githubusercontent.com/6800366/36942688-49348c0c-1f9f-11e8-9159-d9bd0b7e57e6.PNG)

```
Interface Printer {
  void printPage(int page);
}

Class PlainTextPrinter implements Printer {
  void printPage() {
  /*logic here*/
  }
}

Class HtmlPrinter implements Printer {
  void printPage() {
  /*logic here*/
  }
}
```

