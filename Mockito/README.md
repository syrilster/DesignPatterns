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

**To call a real method for a mock object**

```
when(someBeanMock.myMethodName()).thenCallRealMethod();

```

**Using argument captor to verify that a method was called using the given parameters**

```
ArgumentCaptor<String> argument = ArgumentCaptor.forClass(MyActualBean.class);
verify(someBean, atLeastOnce()).findAllByFilter(argument.capture());
assertEquals(someExpectedValues, argument.getValue().getClinicalItemTypeKeys());
```
