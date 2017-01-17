TextCanvas
============

# What is this? #
## Description: ##
TextCanvas is a simple interactive console application able to create and draw on a text based canvas with various commands.

The commands supported so far are:

**C [Width] [Height]** *Create a new canvas.*

**L [X1] [Y1] [X2] [Y2]** *Draw a line from point 1 to point 2 with character 'x'.*

**R [X1] [Y1] [X2] [Y2]** *Draw a rectangle from point 1 to point 2 with character 'x'.*

**B [X] [Y] [color]** *Boundary fill the area connected to the given point with character [color].*

**Q** *Quit and exit the application*

## Limitations: ##
* The canvas has a maximum size of 50x50 characters.
* No diagonal lines are supported yet.

# Usage and Development: #
The easiest way is to utilize gradle for all running and development tasks.

## Run ##
```
gradle run -q
```

## Build ##
```
gradle build
```

## Create IntelliJ project ##
```
gradle idea
```

## Test ##
```
gradle test
```