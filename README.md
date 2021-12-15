# CS-360-T2671_Mobile_Archtct_Prgming
 This repository includes the project created in Android Studio of an Event Track mobile application
--------------------------------------------------------
Briefly summarize the requirements and goals of the app you developed. What user needs was this app designed to address?

The requirement of this project was to pick a mobile app to develop given either an Inventory Tracker, an Event Tracker, or a Weight Tracker. I chose to develop an Event Tracker because I already had experience in creating a few different inventory systems, I was not interested in the Weight Tracker. Further, the requirements for this app were to allow a user to login with credentials, create an account if one did not exist, provide an interface connected to an SQLite database backend, and to request permission from the user to send SMS messages and have the act react accordingly with their permission choice. Additionally, it was required for the app to perform simple CRUD operations (create, read, update, and delete) whilst tracking the event, or otherwise performing the intended premise of the application.

--------------------------------------------------------
What screens and features were necessary to support user needs and produce a user-centered UI for the app? How did your UI designs keep users in mind? Why were your designs successful?

The screens and features necessary to support user needs are a login page, a registration page, a main page where the list of events reside, an edit page that outlines editable fields alongside a Submit button, and the SMS permissions page which shows upon hitting the Submit button. This app produces a user-centered UI by providing a simple event card to the user where a card may be added and/or removed. The cards can be edited via time picker/spinner, calendar view to pick the date, an editable multi-line text box for adding event notes, and an editable event title. The SMS permission prompts the user for a phone number and a text box for the user to add event details and send to their own phone.
The UI designs kept the users in mind by making the editing of the cards very easy to where they will not have to think much about how to edit the event. The times and dates are displayed as they are chosen or changed. The titles and notes are placed in expected locations on the screen. These designs are successful because they adhere to a general flow of how a user would expect to enter the event data, and upon permission, the event card list displays event data in simple condensed format. The event cards are scrollable when overflowing the list page as expected.

--------------------------------------------------------
How did you approach the process of coding your app? What techniques or strategies did you use? How could those be applied in the future?

I approached the process of coding my app by first designing the UI how I wanted with the layout editor. I wanted to establish the feel and theme for how I wanted everything to look before I connected everything together. This helped me to visualize the types of layouts I would need for the app to perform how I wanted. This could be applied to the future by using the same techniques and strategies to create another mobile app or other applications where UI is a pivotal key to the success of the software.

--------------------------------------------------------
How did you test to ensure your code was functional? Why is this process important and what did it reveal?

I tested to ensure the code was functional by repeatedly running the emulator with every little small addition of a feature, new method, functional integration, new activity, new class, layout changes, image uploads or changes, string changes, and any other changes where running the emulator would inform me of updated aesthetics, presence of runtime bugs, connection of components, and expected behaviors. This process is important because the sooner you catch a problem in the code, the less expensive the fix is in time, resources, and ultimately profits. Performing these frequent tests revealed how the code was working, how things were coming along overall, provided a visual representation of implementations, and kept up that sense of accomplishment and motivation to keep going.

--------------------------------------------------------
Considering the full app design and development process, from initial planning to finalization, where did you have to innovate to overcome a challenge?

I had to innovate to overcome a challenge when I was running into a deadline for the completion of the project and could not get my edit buttons on the cards to react to a click due to multiple nested layouts clashing with content views. As a solution to get the job done, I moved the event card 'edit' and event card 'delete' buttons alongside the event card 'add' button such that they all resided on the same (Recycler) View. This allowed the buttons to be on the same layout where I knew for sure the buttons would react properly. However, this also separated the buttons from the Card layout, so I provided an EditText View for the user to enter specific indexes of cards to be added, edited, and/or deleted. This did work and I was able to meet the project deadline as a result.

--------------------------------------------------------
In what specific component from your mobile app were you particularly successful in demonstrating your knowledge, skills, and experience?

The specific component from my mobile app where I was particularly successful in demonstrating my knowledge, skills, and experience was authenticating and validating user credentials, creating the SQLite database to perform basic CRUD functions, and designing a sensible user interface for a mobile application.

--------------------------------------------------------
