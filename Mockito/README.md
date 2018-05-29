**To Mock a DB call kind of method using thenAnswer**

```
              
    @Before
    public void setUp() {
        when(mockObject.myMethod(anyString())).thenAnswer(
            new Answer<String>(){
            @Override
            public String answer(InvocationOnMock invocation){
                String theProperty = invocationOnMock.getArguments()[0];
                if ("value".equals(theProperty)){
                    return "result";
                }
                else if("otherValue".equals(theProperty)) {
                    return "otherResult";
                }
                return theProperty;
            }
       });
```
