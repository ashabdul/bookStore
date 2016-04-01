# Project
Group Project for 4413

##SSL

Copy the file .keystore from the root directory of the Project WAR file to your home directory (C://Users/<username> or /home/<username>), do not put it into a subfolder.

Restart your server and test SSL with: https://localhost:8443/

##Derby

We have included a derby_commands.txt file which provides all the commands needed to create our tables and fill them with some data.  For the database to work properly a copy of derby.jar must be placed in WebContent/WEB-INF/lib, this should have been included.  You will also have to include a copy of the same derby.jar file into your Tomcat installation directory into the subdirectory lib.  The second jar in the Tomcat install directory is for use by the authentication system.

##How to Install
Two War files have been included.  The first one (Project.war) includes everything needed to install and launch the Project server.  It contains the derby_commands.txt and the .keystore file for SSL.  The second War file contains a seperate Project intended for testing SOAP.  In order to test SOAP you must deploy both war files.

##How to Use
Once the Project.war file has been deployed, the .keystore placed in the home directory, and derby.jar placed correctly simply start the server and navigate to localhost:8080/Project to load the homepage.  To run some tests visit localhost:8080/Project/TestCases.

In order to test and use SOAP you must have deployed the second war file.  If it is deployed navigate to localhost:8080/Project/partner.html.  It will ask for a partner password, from the derby_commands.txt the username will be 'partner1' and password is 'abc123' at which point you can access the SOAP interface.  Simply select the bookDAO from the methods on the left side (it will be at the bottom of the list).  Enter an ISBN (or any search term) into the box in the center frame.  The SOAP response will show up as XML in the bottom frame containing all the information about the top result of your search.