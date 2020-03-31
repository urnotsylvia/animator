# animator
Model:
I have Posn, RGBColor class to represent the color and the position of the elements in the animation - Shapes. The interface IShape has moethods to get the color of the shape, 
the name of the shape, position of the shape, the width or height of the shape, get the list of keyFrames from the shape, and also add new keyFrame to the keyFrames.
Abstract class ASape that implements IShape has fields: name, w(width), h(height), color, position and list of KeyFrame. The concrete class Oval and Rectangle extends AShape. 
KeyFrame represents the status of a shape at a specific time, with x of the position, y of th eposition, width of the shape, height of the shape and the color of it.

Interface AnimationOperations has 4 methods: add (add new shape to the animation), remove( remove the shape with given name), 
motionToString(to get the string format of the shape's motion that starts at the given time), and getShapes(return the list of shapes of the model) 
and getState(to return the present state of the game as a string). And the AnimationModel implements the interface AnimartionOperations.

I don't see a motion as an unit, instead, my model consist a list of shapes,
each shape includes a list of keyFrame(which records the status of the shape at each specific time). By doing so, the adjacent 2 key Frames would be a motion. In this way,
I don't need to worry about the overlapping and consistent status, only worry about things like add a new keyframe at an existing time which should be prohibited. 
I also have getState method in AnimationModel to get the list of shapes in the animation with the keyFrame that represent the status at the given time.

implements the builder in my model so that after the animation reader parse the file, the builder can build the model 

I added new Methods to the view to help the builder build the model

I initialized the values in the constructors for Shapes to fix the nullpointerException

I changed add method in model class so that it will only takes in the information rather than a pre-defined shape

View:
I have a IView interface that has showAnimation(), which is telling the view what to show, and makeVisible() method, which is make the view to show in the JFrame.
And I have a abstract class AView implements the IView that includes the makeVisible().
There different views extends the AView: TextualView, DisplayView, SVGView.
TextualView shows the animation model in the format same as the imported file
SVGView shows it in the format that is an XML-based format that can be used to describe images and animations. 
DisPlayView is visualize by displaying the animation
added panel to the display view frame, and draw the shapes that associate with the active keyFrames 
and also display the animation in between

Main class parses the command line to get the imported file, the speed of the tick, where to output and use which view to show the animation
and then initialize the model and view and ran the project

