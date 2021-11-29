# Test Assignment 4

## Mockito Powerups
Answer the following questions about Mockito. Use code examples in your
explanations.

- How do you verify that a mock was called?

````verify(mockObject).someMethodOfMockObject(someArgument);````

This code verifies that **someMethodOfMockObject** was called.

- How do you verify that a mock was NOT called?

````verify(mockObject, Mockito.times(0)).someMethodOfMockObject(someArgument);````or
````verify(mockObject, Mockito.never()).someMethodOfMockObject(someArgument);````

This code verifies that **someMethodOfMockObject** was called 0 times which is the same as not calling it at all (never).


- How do you specify how many times a mock should have been called?

````verify(mockObject, Mockito.times(5)).someMethodOfMockObject(someArgument);````

This code verifies that **someMethodOfMockObject** was called 5 times.

- How do you verify that a mock was called with specific arguments?

All of the code examples above have **someArgument** passed in the method, which will verify the method with that parameter. 
- How do you use a predicate to verify the properties of the arguments given to a call to the mock?

  https://www.youtube.com/watch?v=MztH6vkeFVk&ab_channel=AneeshMistry this ?????

   
## Snake Game TDD
### Environment
| Name            | Version       |
|-----------------|--------------:|
| Java            | 17            |
| JavaFX (openFX) | 17            |
| Junit           | 5             | 
| TestFX          | 4             |

TestFX is a framework for Simple and clean testing of JavaFX Applications. 
If you want to know more, you can read about it here: 
[TestFX GitHub Repository](https://github.com/TestFX/TestFX) 

All dependencies are handled with maven and can be found in the pom.xml file in 
the root folder of this project. 

### Task
Create a snake game using TDD (Test-Driven-Development), it should include a
coverage report, mutation testing and static analysis. 

### Solution


### How to run the game
1. Run Maven build.
2. Run Game class. Located at 
````src/main/java/dk/cosby/games/snaketdd/Game.java````
3. To control the snake use W,A,S,D,

