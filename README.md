# My Personal Project

## Drums Player

This application will consist of a GUI representing a drum set, wherein each 
part of the set could be controlled by keyboard to make sounds. This 
application could be used by anyone who enjoys or just wishes to play around
with drums. There is also an option to record a beat that the user is playing.
As an avid drummer, I have always had moments where I thought of a beat and
wanted to test it out right away, but couldn't as I don't carry a drumset in my
bag. Creating this application would allow me to
instantly record what I was thinking of so that it could be later played.

*User Stories*:
- As a user, I want to be able to press the keys on my keyboard and hear the respective sounds
- As a user, I wish to play in a mode where I can just freestyle
- As a user, I wish to play in a mode where I can learn a specific beat
- As a user, I wish to know how much I scored in the learn mode
- As a user, I wish to have an option to record what I play
- As a user, I wish to have an option to replay what I previously recorded
- As a user, I want to have an option to save my recordings when I'm one with my work
- As a user, I want to have an option to load my recordings when I'm starting the application

*Instructions for Grader*
- You can generate the first required action related to adding Xs to a Y by clicking on the red button, recording by pressing the keys (s, d, f, g, h, j, k), and the pressing the gray button
- You can generate the second required action related to adding Xs to a Y by clicking on song button, and clicking on 'Delete' button to remove a song
- You can generate the third required action related to adding Xs to a Y by clicking on song button, and clicking on 'Delete All' button to remove all song
- You can locate my visual component by simply running the application. It will be the background image.
- You can save the state of my application by pressing on the 'Save and Exit' button, and clicking on 'Yes'.
- You can reload the state of my application by running the application by clicking on 'Yes' tto he first prompt that appears

*Phase 4: Task 2*

This is what I get in the console when I add two songs, delete the song at index 1, and finally delete all the songs.

`Thu Apr 13 14:49:31 PDT 2023`
`Recorded f after 0 ms`

`Thu Apr 13 14:49:31 PDT 2023`
`Recorded g after 8 ms`

`Thu Apr 13 14:49:32 PDT 2023`
`Recorded g after 273 ms`

`Thu Apr 13 14:49:32 PDT 2023`
`Recorded f after 265 ms`

`Thu Apr 13 14:49:32 PDT 2023`
`Recorded f after 186 ms`

`Thu Apr 13 14:49:32 PDT 2023`
`Added a new song at index: 1`

`Thu Apr 13 14:49:34 PDT 2023`
`Recorded f after 0 ms`

`Thu Apr 13 14:49:34 PDT 2023`
`Recorded f after 82 ms`

`Thu Apr 13 14:49:34 PDT 2023`
`Recorded f after 161 ms`

`Thu Apr 13 14:49:34 PDT 2023`
`Recorded f after 142 ms`

`Thu Apr 13 14:49:34 PDT 2023`
`Recorded f after 140 ms`

`Thu Apr 13 14:49:35 PDT 2023`
`Added a new song at index: 2`

`Thu Apr 13 14:49:41 PDT 2023`
`Removed the song at index: 1`

`Thu Apr 13 14:49:43 PDT 2023`
`Deleted all the songs`

*Phase 4: Task 3*

After learning about the Singleton Design Pattern, I realised that I could have implemented this design principle in my 
Controller class. I am currently passing a single object of that class in the other UI classes, but if I start scaling 
my program, that would just result in some redundant coding and chances of errors if I create another object of 
Controller by mistake. To ensure that I have a single object of that class which could be accessed from a global point 
of access forever, Singleton Pattern would be of a great help.

I could've even implemented an iterator in my Player class, as currently my code knows that the collection of records 
has been made using a HashMap. If in future I ever change the collection type, my code would have to considerably 
change to adapt to the new collection. Instead, an iterator would have increased my code's efficiency and consistency.