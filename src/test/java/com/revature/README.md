# Unit Tests

Every unit test should be of the form
**Classname**Test.java i.e. a class of
Employee.java would have its unit tests
in EmployeeTest.java

Remember, maven surefire can be used to
run specific tests!

`mvn test -D test=Class`

`mvn test -D test=Class#method`