# Computer Hardware Store:

## Project Proposal *(Phase 0)*

- The application I plan to create is an **online computer part / tech repair shop**
  where the user is able to place purchase orders for various computer parts and create appointments for repairs
  at some physical store location.
- The target audience for such a program would be users who are interested in building their own PC, users who *already*
  own a PC and need parts, or a user who needs something repaired on their PC. (Tech Support)
- This project is very interesting to me because since I was young I grew up around technology a lot and eventually
  ran my own small business where I would build computers for 
  people along with troubleshooting any issues that they encountered.
  Although it was a small business, the main issue I ran into was staying organized with my orders. While the program I
  plan on creating is different than building computers themselves, I've learned a lot about computer hardware and 
  would want to run a small scale hardware store one day of my own. I believe a program such as the one I plan on
  creating would greatly benefit organization, and the users experience while shopping.

## User Stories
- As a user, I want to be able to add multiple parts to a shopping cart.
- As a user, I want to be able to create appointments with a message box detailing my issue I would like repaired.
- As a user, I want to be able to find parts by category.
- As a user, I want to be able to save my cart to a file if I choose to.
- As a user, I want to be able to load my cart from a file if I choose to.
- As a user, I want to be able to see the total price of my cart
- As a user, I want to be able to remove things from my cart.

## Instructions for Grader
- You can generate the first (multiple) requires action related to adding Xs to a Y by..
- Clicking parts, you are prompted with 3 different categories of parts
- When clicking one of them, you are brought to our list of items within that category.
- When clicking on the item, it adds to your cart.
  Going to the cart section, you're able to view your cart
- Clicking the "Press to Calculate Total" button changes the total amount at the bottom to match
  any updates within the cart.
- You're able to remove items once they're in the cart by clicking the "remove" button next to the item


- You can generate the second required action to adding Xs to a Y by...
- Clicking on "Appointments", allows user to write a general date, along with an issue.
- Pressing "Submit", submits the appointment and clears the text-boxes.


- My visual components are the part images, and the main screen photo.
- You can save the state of the customer by clicking "Save" on the menu.
- You can reload the state of the application by clicking "Load" on the menu.



## Phase 4 Task 2:
- Mon Apr 03 18:12:25 PDT 2023
- Item added! i7 6700k
- Mon Apr 03 18:12:27 PDT 2023
- Item added! GTX 3060
- Mon Apr 03 18:12:29 PDT 2023
- Item added! 16GB DDR4
- Mon Apr 03 18:12:39 PDT 2023
- Appointment created! January 10th 2024 pc broke :((
- Mon Apr 03 18:12:41 PDT 2023
- Item removed! i7 6700k
- Mon Apr 03 18:12:43 PDT 2023
- Successfully Placed Order in! [GTX 3060, 16GB DDR4]


## Phase 4 Task 3:
If I had more time to work on my computer store project I would have done multiple
things to improve the design. Firstly, I would collapse the methods for the dates in the 
Appointments class to one method that takes a "Day", "Month", and "Year" parameter instead of
having multiple methods. This would improve organization and readability. Furthermore, I would
be able to have some try-catch blocks to ensure that the date written is a valid date. In my Customer
class, I have multiple methods for getting the users cart. Since my customer class is quite messy, I would try
to clean up those and collapse them into one method if possible as well to avoid the somewhat "duplicate" code.
One of the biggest refactors I'd make would be changing the way the Parts class works and create a class to manually
add parts within the UI (under some sort of admin login). This would clean up the code massively, as in order to add a
new part to the existing "stock" we have to manually code it in (which can get quite messy if it is a big store).
The AddStore class is actually quite similar to what I would have done in that case. It was primarily used for the
console based application portion of the project, and is quite confusing when reading it through without knowing that.
I would try to combine the AddStore and Parts method because they share some similarities. I could make 
Parts act more like a "Store". Or change the AddStore to a "Store" class and have more functionality within that class
to avoid clutter in the StoreApp. My GUI class has a lot of duplicate code and classes. One of the main ones being the 
"___panel" classes. If I were to make one main panel class that takes some other panel as a parameter, it would prevent
the need for multiple panel classes (the ones that change the visibility of the other panels).