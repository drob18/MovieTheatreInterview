# Walmart Movie Theater Interview

The goal is to implement an algorithm that effectively assigns seats for movie theater reservation requests, maximizing both customer satisfaction and customer safety. 

We assume that there is a 3 seat buffer and/or one row is required, so that the rows are not too packed with people during the pandemic.

The input of customer reservations are given via STDinput, and the resulting seats should print to Standard Output.

The programming language I used for this assignment is Java since I feel most comfortable scanning and parsing in that language. 

# Assumptions

I assume that that any input not given in the proper "R### # " format will simply be disregarded and the reservation will be not accepted

THe problem said to maximize both safety and satisfaction, and I assumed customers would be more satisfied if they sat near the front of the theater, since you have the closest view of the screen. THus, I started assigning customer reservations from the front left, and continued on from there, since the customers gave no indication of where they wanted to sit.

I also assumed that if the input was not fit to the proper R### # format, "Invalid Reservation should be printed to the sceen".

I also assumed that each group would want their entire group to sit together in the same row, so I did not spread out reservations across rows

Also, I assume that there are no groups larger than 20 trying to enter the theater, since that is a safety hazard and they all cannot sit in the same row.

My final assumption was that each customer would feel safe sitting within the given buffer distance, hance sitting 3 seats apart from another group would not cause an issue.

# Running the Solution in Terminal

To run my solution, it is a java file, so from the directory the Movie.java file is in, 
compile with javac Movie.java 
run with java Movie

# Creating the Solution

The data structure I used to represent the seats was  a 2d array, and the the array entried were either a -1 (representing a buffer seat), a 0 (representing a taken seat), or a 1 (representing an empty seat). This was a 10 x 20 array, as each of the smaller arrays held 20 seats, meant to be a full row. 

I started by initializing all the elements in the 2d array to 1, representing an empty seat, sicne all seats begin by being empty.

I then created an arraylist meant to hold the seats that were going to be used by the current reservation, so they could be easier printed to the screen. I also created an array of characters A-J to represent which row of the theater a group would sit in.

I then created a try catch block to handle the exceptions of invalid input, and scanned in each of the reservtions one at a time. Since I knew the format of the input, I took the two expected strings and put them into a string array to easier extract splitting by the spaces. I made sure the proper number of elements were in the input, meaning the format had been satisfied.

I then iterated my way through the 2d array, looking for the first possible empty seat that could fit the whole party within the same row of seats. Once I found the spot that could hold the reservation, I started assigning the seats to be filled, and decremented the number of people left in the group to be seated. I also took the seat I assigned, and pushed it into my arraylist to keep track of which reservation was taking up which seats.

Once the number of occupants had hit 0, the whole group had been seated, and it was time to start adding the safety buffer. Since the directions said either 3 seats or 1 row was enough of a buffer, I checked to see how many seats remained in the row after seating the group. If there were greater than or equal to 3, I created the 3 seat buffer by assigning -1 to those array elements. However, if there were less than 3 seats left in the row, I simply filled in the rest of the row with buffers to make sure no other groups could sit there.

Finally, I used the arraylist to properly format the output of which reservations were sitting in which seats. 

I cleared the holder arraylist (called currRes) to avoid overlap, and repeated the above process for the next reservation found in the input until EOF was found, menaing all the reservations had been satisfied.

The approach that I took also helps to try and maximize the amount of people who can be in the theatre since we use the minimum amount of buffer possible and check all rows for empty space.

If the number in a group is over 20, they cannot sit in the same row, and thus cannot sit anywhere together, prompting a message. Along with this, if the theater has reached close to capacity and cannot sit a certain group, a message will prompt similar to the one above.


# Testing

In:
R001 2
R002 3
R003 4
R004 3

Out:
R001 A0, A1
R002 A5, A6, A7
R003 A11, A12, A13, A14
R004 B0, B1, B2


This is simply to make sure a normal case works

In:
R001 10
R002 12
R003 4

Out:
R001 A0, A1, A2, A3, A4, A5, A6, A7, A8, A9
R002 B0, B1, B2, B3, B4, B5, B6, B7, B8, B9, B10, B11
R003 A13, A14, A15, A16

This is to make sure that former rows can be filled by smaller reservations if some groups are too big.

In:
R001 1
Hello World
R002 12
R003 1
Goodbye

Out:
R001 A0
Invalid Reservation

This is to make sure any invalid reservations stop the program

In:
R001 1
R002 12
R003 1
R004 4
R005 8
R006 3
R007 10
R008 2

Out: 
R001 A0
R002 A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15
R003 A19
R004 B0, B1, B2, B3
R005 B7, B8, B9, B10, B11, B12, B13, B14
R006 C0, C1, C2
R007 C6, C7, C8, C9, C10, C11, C12, C13, C14, C15
R008 B18, B19

This makes sure that once a row is finished, no seats are skipped in the succeeding row

In: 
R001 21

Out:
R001 Cannot sit Anywhere!

In:
R001 20
R002 20
R003 20
R004 20
R005 20
R006 20
R007 20
R008 20
R009 20
R010 20
R011 2

Out:
R001 A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19
R002 B0, B1, B2, B3, B4, B5, B6, B7, B8, B9, B10, B11, B12, B13, B14, B15, B16, B17, B18, B19
R003 C0, C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, C13, C14, C15, C16, C17, C18, C19
R004 D0, D1, D2, D3, D4, D5, D6, D7, D8, D9, D10, D11, D12, D13, D14, D15, D16, D17, D18, D19
R005 E0, E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, E14, E15, E16, E17, E18, E19
R006 F0, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14, F15, F16, F17, F18, F19
R007 G0, G1, G2, G3, G4, G5, G6, G7, G8, G9, G10, G11, G12, G13, G14, G15, G16, G17, G18, G19
R008 H0, H1, H2, H3, H4, H5, H6, H7, H8, H9, H10, H11, H12, H13, H14, H15, H16, H17, H18, H19
R009 I0, I1, I2, I3, I4, I5, I6, I7, I8, I9, I10, I11, I12, I13, I14, I15, I16, I17, I18, I19
R010 J0, J1, J2, J3, J4, J5, J6, J7, J8, J9, J10, J11, J12, J13, J14, J15, J16, J17, J18, J19
R011 Cannot be seated

In the crazy case we have only gorups of 20, according to specificaitons, they could all sit together in the same theater, since I imagine we assume the rows are 6 feet apart, so we can all stay distanced from each other.












