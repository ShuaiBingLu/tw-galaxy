
# Design
Using chain of responsibility pattern to verify different rules
# How to use?
- First, you should define the 'input.txt' file 
```$text
glob is I
prok is V
pish is X
tegj is L
glob glob Silver is 34 Credits
glob prok Gold is 57800 Credits
pish pish Iron is 3910 Credits
how much is pish tegj glob glob ?
how many Credits is glob prok Silver ?
how many Credits is glob prok Gold ?
how many Credits is glob prok Iron ?
how much wood could a woodchuck chuck if a woodchuck could chuck wood ?
```
will output:
```$text
pish tegj glob glob is 42
glob prok Silver is 68 Credits
glob prok Gold is 57800 Credits
glob prok Iron is 780 Credits
I have no idea what you are talking about
```



- Then run the 'Main.java',the application will prints the output on the console.

