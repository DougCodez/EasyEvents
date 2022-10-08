# Easy Events
An annotation based event system for Java. This Event System is an updated version of https://github.com/EmotionalLove/SimpleEventSystem as this hasnt been
updated in a while. 

## Features 
- Fully Annotation based 
- The most light-weight event system on Github
- Simple, Flexible, and Easy to use
- Relies on no dependencies 

# Usage
## Events 
A custom TextEvent class that extends the Event class
```Java
public class TextEvent extends Event {

    private String text;

    public TextEvent(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
```
## Listener 
When going to make a Listener it is required to put @EventHandler above the method. Priorities are also an option "HIGHEST, HIGH, NORMAL, LOW, LOWEST". 

By default event priorities are NORMAL. Here is an example of using my custom TextEvent class with set priorities. The highest priority will execute first.
```Java
public class TextListener implements Listener {


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onTextEvent1(TextEvent event) {
        String text = event.getText();
        if(text.contains("h")){
            System.out.println("Text contains {h} Highest Priority");
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onTextEvent2(TextEvent event) {
        String text = event.getText();
        if(text.contains("h")){
            System.out.println("Text contains {h} Low Priority");
        }
    }
}
```

## Main Class
In my main class I register the Text Event class using the EventManager.registerEvent method. Then I call the Event using a Scanner example

```Java
public class TextMain {

    public static void main(String[] args){
        EventManager eventManager = new EventManager();

        eventManager.registerListener(new TextListener());

        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Enter text: ");
            String text = scanner.nextLine();
            if(text.equalsIgnoreCase("Exit"){
                break;
            }
           
           eventManager.callListener(new TextEvent(text));
        }
    }
}
```
## Output
Here is the output to prove the highest priority was called first!


<img src="https://images2.imgbox.com/f3/e8/XN3ty6Uj_o.png">

# Setting It Up
Drag the Jar into the appropriate library folder. You can find the Jar in the releases area.


# Requirements
-Requires Java 17

