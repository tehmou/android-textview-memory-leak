An Android Studio project that demonstrates a memory leak when a
TextView is fully outside of its parent container. In this sample the
leaking xml is layout called no_gc.xml, in which a TextView is pushed
out of a surrounding FrameLayout. yes_gc.xml is otherwise identical, but
part of the TextView is still visible.

After starting the application and touching any part of the screen,
whole screen should become green. However, on some devices the left side
of the screen stays red, indidating a memory leak.


Tested devices:

leaks:
4.2.2
4.3
4.3 (emulator)

works:
4.1.2
4.4 (emulator) 
