README:
In assignment 9, we implement all 3 levels (Slider, rotation and layers) and we did this by adding the corresponding JComponents and additional code on the controller (Slider), adding another field in our shape class and adding code in the view classes so it can rotate (Rotation) and creating layers by changing the order of rendering shapes. 

All the related tests are in the ExtraCreditPtTests.java file. 

We made changes to Model, view and controller to achieve these. 

We also included many animation files that tests different aspects of the code via edit/visual view so we can demonstrate during the demo.






***************README FOR ASSIGNMENT 7********************************
In the assignment 7, we added more functionality to the animation by adding another view called "edit view" that gives the user ability to edit animation, pause, resume, restart, loop and change speed in real-time. Below are detailed specifications about the edit view as well as changes from the previous assignments.

************************UPDATE FROM ASSIGNMENT 6*****************************
1. Added functionality of converting motion-based animation to keyframe-based animation back and forth, allows the user to see the updated keyframe information in real-time via the console.
2. Added real-time keyframe-based animation editing. The user can add new keyframe or edit, delete existing keyframe. The user will be able to see the changes instantly via the canvas.
3. Layout of GUI is slightly changed so it has better compatibility with user's screen.
4. Fix the error where the files with shapes that does not initialize with time t=1 can't load correctly.
5. Other minor bug fix.


***********************READ THIS BEFORE USING THE EDIT VIEW!!!********************
1. The input file format for the edit view must be exactly the same as the previous assignment!

2. If the user cannot see all the buttons/text field/canvas, please maximize the window and then you can see everything in the right place.

3. The keyframe, motion ALWAYS start with tick t=1. t=0 is considered INVALID in our design!

4. There are 5 options avaliable for user to modify animation, including add, delete, edit keyframe as well as add/delete shape. Choose the option and insert the value, then press "set" button, the animation would then being edited successfully.

5. When adding a new shape, the program will automatically initialize a t=1 keyframe with default values and it won't appear on the screen unless the user adds another keyframe. It exists because it is crucial to ensure that every time AFTER the user add/delete/edit keyframe, each shape will ALWAYS have at least two keyframes(This is like an "invariant". If the user tries to modify keyframe and the program detects that there won't be 2 or more keyframes available in this shape after modifying, the program WILL NOT allow such modification. In other words, the user must add a keyframe once he/she created a new shape to ensure that there are at least 2 keyframes available. Then the user can do whatever modifications he/she wants if the condition of having at least 2 keyframes has been met AT ALL TIME, otherwise the user MUST DELETE the shape).
The user can delele/modify this placeholder keyframe manually after they ensure there is AT LEAST TWO keyframes available after deleting. 
The reason we designed our program this way is we believe a proper animation requires at least two keyframes. If there exists a shape with less than 2 keyframes, there is no reason for this shape to exist since the animation won't work properly (with exception when a shape is first being created).

6. Adding keyframe, editing keyframe requires all fields input to be valid (name, type, time, x, y, R, G, B, Width, Height). Adding shape as well as Deleting keyframe and shape only requires Name and Type input to be valid.

7. You cannot insert keyframe with time < 1. The "earliest" keyframe the user can add is t=1.

8. Speed refers to the time (in ms) it takes to refresh to the next frame. The larger the speed, slower the animation. Slower the speed value, faster the animation.

9. Since we use the double type when processing(tweening) shape data in the back end to ensure precision, the user's output (Console output) contains rounded version of shape keyframe data. Sometimes the data printed on the console might be +-1 off from actual value due to rounding but no worries, data stored inside the code is always precise!

10. ShowAllKeyFrame will print all keyframe information of all shapes in this animation to the console(So the user knows what to edit). When user is trying to edit the keyframes of an animation, the status of the command and other detailed information will also be printed on the console.


