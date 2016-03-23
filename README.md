# Project
Group Project for 4413

How to set up SSL locally:
You will need to manually do two things.
1. In Eclipse go to your server.xml file (server project folder).  In here fine the <connector> tag with parameter port="8443".  Add to the end of this tag the parameters keystoreFile=${user.home}/.keystore" keystorePass="abc123"
2. I've uploaded a .keystore file to github.  Copy the file to your home directory (C://Users/<username> or /home/<username>), do not put it into a subfolder.

Restart your server and test SSL with: https://localhost:8443/
