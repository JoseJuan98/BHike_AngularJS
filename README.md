# BHIKE - Web Application Project - PI_2

## Software Engineering UNEX - Subject Internet Programming

Project implemented for the course 2019-2020 based on the web application https://es.wikiloc.com/ . Basically, it consists in the implementation of a web application using that web as a reference for the core functionality, obviously, by designing their own user interface and configuration.

## Technologies used
### Frotend
	- HTML 
	- JSP
	- JS
	- JSTL
### Backend
	- Java
	- Framework AngularJS
	- JAVAX - REST API
### DB Model
	- SQL
	- JDBC
	- JavaSQL
	
## Non-functional requirements:

1. HTML & CSS are valid. Only cannot be valid due to JSP issues, never due to a misuse of HTML & CSS.

2. Security: the web app properly manages security issues using the concepts learned in theory and labs. “Properly” means that communication between client and server is ENCRYPTED, user cannot see, edit or delete any information of other users (directly or using any browser developer tool). They only can see the information that other users have shared specifically with them.

3. Good practices: web app code follows all the good practices and patterns that have been taught in the subject.

## Functional requirements (using REST + AngularJS):

1. Any person can register as user of the web app, edit, at least, her email and her password and delete her account at any moment. This requirement will not be met if users cannot see the information previously recorded for their data when they are updating their profiles so that they must fill in everything again.

2. Any user can create, see, edit and delete a route that contains, at least, a title, a description, a distance, a duration, a date, an elevation (in meters), a category (cycling, running or walking) and a difficulty (easy, medium or difficult). This requirement will not be met if users cannot see the information previously recorded for a route when they are editing it so that they must fill in all the route information again from scratch.

3. Any registered user may add or remove a kudos for a route: the system must provide an option for showing the routes sorted by number of kudos and a quick access to routes with a minimum number of kudos.

4. Search: a user can search routes typing the words she’s looking for either in the title or in the content (description).

5. Blocked routes: a user may change the state of her routes to blocked to indicate that a concrete route may not be followed at a particular moment due to a special situacion like bad weather consequences, crossing rivers, and so on. In that case, the route will still appear in the web page, however, it will be shown in a different format than those that may still be followed. The application should allow users to filter the routes in order to show just those that may be still followed (in other words, hiding those that are blocked).
