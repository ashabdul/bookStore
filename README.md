# Project
Group Project for 4413

##SSL

You will need to manually do two things.

1. In Eclipse go to your server.xml file (server project folder).  In here fine the <connector> tag with parameter port="8443".  Add to the end of this tag the parameters keystoreFile="${user.home}/.keystore" keystorePass="abc123".  Now uncomment the tag (<!-- and -->).

2. I've uploaded a .keystore file to github.  Copy the file to your home directory (C://Users/<username> or /home/<username>), do not put it into a subfolder.

Restart your server and test SSL with: https://localhost:8443/

##Login

I have pushed some minor changes to login.jspx to be compatible.  Everything else must modify non-shared files.  

1. Modify web.xml,  insert the following text at the end just before the </web-app> tag.  This adds a new group of users called "partner", specifies that /home.jspx (change this, it was set as a test) requires this group in order to access, and specifies that /login.jspx has the form for logging in.
```
<security-constraint>
	<web-resource-collection>
		<web-resource-name>Partners</web-resource-name>
		<url-pattern>/home.jspx</url-pattern>
		<http-method>GET</http-method>
		<http-method>POST</http-method>
	</web-resource-collection>

	<auth-constraint>
		<role-name>partner</role-name>
	</auth-constraint>

	<user-data-constraint>
		<transport-guarantee>CONFIDENTIAL</transport-guarantee>
	</user-data-constraint>
</security-constraint>
	
<security-constraint>
	<web-resource-collection>
      		<web-resource-name>Admins</web-resource-name>
      		<url-pattern>/cart.jspx</url-pattern>
      		<http-method>GET</http-method>
      		<http-method>POST</http-method>
    	</web-resource-collection>

	<auth-constraint>
		<role-name>admin</role-name>
	</auth-constraint>
	 
	<user-data-constraint>
      		<transport-guarantee>CONFIDENTIAL</transport-guarantee>
      	 </user-data-constraint>
  </security-constraint>

<login-config>
	<auth-method>FORM</auth-method>
	<form-login-config>
		<form-login-page>/login.jspx</form-login-page>
		<form-error-page>/login-failed.html</form-error-page>
	</form-login-config>
</login-config>
```

2. Go to tomcat-users.xml and add the following before the </tomcat-users> tag.  This specifies a user with role "partner" so it is a valid authenticator to access /home.jspx.
```
<role rolename="partner"/>
<role rolename="admin"
<user username="admin" password="admin" roles="admin"
<user username="william" password="hello" roles="partner,admin"/>
```

The problem here is that the passwords are still plain text however I have not yet been able to get hashing to work.  So, I provide this so anyone who needs auth working to continue their own work can do so.  
